# ProjectGutenberg
##### Philip West Christiansen, Kasper Karstensen & Emilie Hinsch Nielsen
##### Forår 2018, Til: Rolf-Helge Pfeiffer & Jens Egholm Pedersen, Afleveret: 28/05/2018

# Indholdsfortegnelse  
<!--ts-->
  * [Introduktion](#introduktion)  
  * [Projektbeskrivelse](#projektbeskrivelse)  
  * [Valg af databaser](#valg-af-databaser)  
      * [Relationel database](#relationel-database)  
      * [Graph database](#graph-database)  
  * [Datamodellering i databaser](#datamodellering-i-databaser)  
      * [PostgreSQL](#postgresql)
      * [Neo4j](#neo4j)
  * [Datamodellering i applikation](#datamodellering-i-applikation)  
  * [Importering af data](#importering-af-data)
      * [Preprocessering af data](#preprocessering-af-data)
		* [Data fra RDF/XML filer](#data-fra-rdfxml-filer)
		* [Books](#books)
		* [Relationen mellem Book og Author](#relationen-mellem-book-og-author)
		* [City mentions](#city-mentions)
		* [City](#city)
      * [Setup-guide](#setup-guide)
		* [PostgreSQL](#postgresql-1)
		* [Neo4j](#neo4j-1)
  * [Benchmark](#benchmark)
      * [Queries](#queries)
		* [PostgreSQL](#postgresql-2)
		* [Neo4j](#neo4j-2)
      * [Test-setup](#test-setup)
      * [Resultater](#resultater)
		* [På JDBC før indeksering](#på-jdbc-før-indeksering)
		* [På JDBC efter indeksering](#på-jdbc-efter-indeksering)
		* [Via API før indeksering](#via-api-før-indeksering)
  * [Diskussion af benchmark resultater](#diskussion-af-benchmark-resultater)
  * [Konklusion](#konklusion)
<!--ts-->

# Introduktion 
Dette git-repository indeholder kode, rapport samt performance målinger for vores eksamensopgave i faget Database på PBA i Softwareudvikling ved CPHBusiness i foråret 2018. Opgaven er lavet af Emilie Nielsen, Philip West og Kasper Karstensen.

# Projektbeskrivelse
Projektet går ud på at lave en applikation med to typer af databaser der anvender data fra engelske bøger som er hentet fra Project Gutenberg(http://www.gutenberg.org/). Applikationen skal kunne lave forskellige forespørgsler og ved hjælp af performancemålinger skal vi finde ud af hvilken database der egner sig bedst i forskellige situationer. 

- Ud fra et bynavn skal man kunne finde alle bøger samt bogens forfatter hvor bogen nævner denne by.
- Ud fra en bog titel bliver alle byer den nævner plottet ind på et kort.
- Ud fra et forfatternavn bliver alle bøger listet som er skrevet af denne forfatter og de byer som bøgerne nævner bliver plottet ind på et kort.
- Ud fra en geolokation skal alle de bøger listes der nævner en by der ligger i nærheden af denne geolokation.

# Valg af databaser
I vores undervisning har vi arbejdet med fire forskellige paradigmer inden for databaser: Key-value stores, document-oriented, relational og graph database. Udfra disse paradigmer skal vi vælge 2 databaser som ikke hører under samme paradigme.

## Relationel database
Vi har valgt at arbejde med en relationel database i form af PostgreSQL. 
I gruppen mener vi at relationel database er det vi betegner som en “klassisk” database da det var den første type af database vi alle lærte at arbejde med da vi først begyndte at programmere. 
Vi vil gerne se hvordan denne performer i forhold til en nyere type af database.

## Graph database
Vi har valgt at arbejde med en graph database i form af Neo4J. Dette er den nyeste form for database som vi har arbejdet med. Ingen i gruppe havde hørt om graph databaser før vores undervisning og vil derfor gerne undersøge hvordan den performer i forhold til en “klassisk” database. Vi mener også at vi forholdsvis nemt vil kunne strukturere den data som vores applikation skal bruge i form af noder og edges.

# Datamodellering i databaser

## PostgreSQL

Nedenfor ses et EER diagram af vores PostgreSQL database.

![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/datamodel_post.png)

Som det ses ovenfor har vi benyttet os at have to mellem tabeller, book-city og book-author. Disse to tabeller bruges til at fortælle eksempelvis hvilke authors har skrevet hvilke books, hvilke books der nævner hvilke cities, og så fremdeles. De har hver især en foreign key til til de tabller de knytter sammen. 
Dette har vi gjort for at mindske redundant data i Book og author da en bog kan have flere forfattere, en forfatter kan have skrevet flere bøger og flere bøger kan nævne den samme by.

Vi har været nødsaget til at tage nogle beslutninger i form af design af databasen der bryder nogle af normal formerne. I teorien giver normalformerne en rigtig god tilgang til strukturering af data i en database, men i praksis kan der til tider være ting man er nødt til at gøre anderledes som ikke stemmer overens med normal formerne. 

Overordnet set tilfredsstiller vores database de fleste af normal formerne men her er et tilfælde hvor normalform 1 bliver brudt. Normalform 1 siger blandt andet at “der aldrig må være mere end en værdi i en attribut”. Vi bryder dette i vores Book table under book_title, her har vi nemlig en lang streng med forskellige bogtitler, hvor de forskellige titler separeres med kommaer. Vi gjorde dette da en bog kun havde en attribut til at starte med nemlig titel. Vi snakkede om at lave endnu en mellem tabel som så ville understøtte at en bog kunne have flere titler tilknyttet. Vi synes dog at dette er at normalisere dataen for meget da man derved har en book tabel der kun har et id og vi returnerer aldrig en bog uden titel så man skulle til at joine den anden tabel hver gang.

En primær nøgle bør kun bruges til at identificere en entry i databasen og ikke have nogen betydning herudover. Vores ID’er har en direkte forbindelse til rækkefølgen eksempelvis en bog er importeret i databasen. Vores ID’er mapper derfor til den specifikke bog hvilket vi mener på en måde kan være med til at bryde normalformen. Nedenfor gennemgås de forskellige normalformer, og hvordan vi anvender dem. 

## Neo4j

Nedenfor ses modellering af vores graf-database Neo4j. 

![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/datamodel_neo.png)

Author → Book,
Vi har valgt at forholdet mellem forfatter og bøger skal gå fra forfatter til bøger. Det har vi valgt da der er et krav om at man skal kunne finde de bøger en forfatter har skrevet.

Book → City,
Forholdet mellem bog og by skal gå fra bog til by. Man skal kunne finde ud af hvilken by en bog nævner.

# Datamodellering i applikation

På nuværende tidspunkt er Author kun gemt som en streng. Hvis applikationen skal  udvides med mere data om en author, havde det været smart fra starten af, at strukturere sine entiteter, således at der var både Book, City og Author, og så have de tre entiteter til at referere til hinanden. Dette skyldes at lige nu har vi ikke super meget data i databasen men hvis f.eks. en Author  skulle have fødselsår, dødsår ovs. så ville det være bedre at have som et object. Det samme gælder for City.

![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/book_city.png)

En anden måde vi havde snakket om at gøre det på var at bruge DTO(Data Transfer Object) pattern. Ved at bruge DTO’er kan man lave specifikke objekter som kun indeholder lige det data som databasen skal bruge og derved mindske hvor meget trafik der bliver sendt frem og tilbage. Det ville f.eks. være at lave et CityDTO object der kun har navn på byen da vi ikke altid skal bruge longitude og latitude eller en BookDTO som ikke altid har en liste af Cities.

# Importering af data

Dette afsnit handler om hvordan vi håndterer vores data. 

## Preprocessering af data

For nemt at kunne håndtere det data som skal importeres i PostgreSQL og Neo4j har vi valgt at lave en række CSV filer ud fra den data som vi har hentet fra Project Gutenbergs hjemmeside. For at kunne gøre dette har vi programmeret vores egne parsers.

### Data fra RDF/XML filer

At finde information omkring Books og Authors kan gøres på flere måder. 
Dette kan gøres på flere måder. Den første metode ville være at scanne bøgerne igennem og finde det sted hvor der f.eks. står “Title”. Project Gutenberg har dog også et katalog af RDF/XML filer som man kan bruge til at finde denne information. Vi har valgt at bruge RDF filerne da vi mener at det vil være hurtigere at slå op og der er mindre chance for at opstå fejl da man sikkert støder på flere steder i bogen hvor der kan stå “Title”. For at omdanne dataen fra RDF/XML til csv filer har vi skrevet vores egen parser som kan ses her https://github.com/Kaboka/ProjectGutenbergRDFParser Denne parser løber alle RDF/XML filerne igennem og danner tre CSV filer: Books.csv, Author.csv samt book_author.csv. Da det katalog af RDF/XML filer som man henter fra project gutenberg er over alle bøger (cirka 57000) men vi kun arbejder med de engelske bøger (ca 37000) tager parseren højde for dette ved først at finde alle ID’er fra de bøger vi har hentet og så kun parse RDF/XML filer som har et ID på en engelsk bog.

### Books

Til vores Book tabel/node skal vi bruge en titel samt et ID som passer på  bogen så man kan referere tilbage til den rigtige bog da vi ikke gemmer selve bøgernes tekst i databasen. 
Her bruger vi dataen fra den property som hedder Title

```xml
<dcterms:creator>
      <pgterms:agent rdf:about="2009/agents/485">
        <pgterms:name>Darwin, Charles</pgterms:name>
        <pgterms:deathdate rdf:datatype="http://www.w3.org/2001/XMLSchema#integer">1882</pgterms:deathdate>
        <pgterms:webpage rdf:resource="http://en.wikipedia.org/wiki/Charles_Darwin"/>
        <pgterms:alias>Darwin, Charles Robert</pgterms:alias>
        <pgterms:birthdate rdf:datatype="http://www.w3.org/2001/XMLSchema#integer">1809</pgterms:birthdate>
      </pgterms:agent>
```

ID’et er et vi selv genere da det ikke har nogen form for traceability til noget uden for databasen.

Eksempel på dataen fra CSV filen:

![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/csvfil_eksempel.png)

### Relationen mellem Book og Author

Til vores mellem tabel book_author samt vores edge [:WRITTEN] skal vi bruge book_id samt author_id. Når vi parser alle RDF filerne laver vi en CSV fil som indeholder id for den bog vi er igang med at parse samt ID’et for den forfatter der bliver nævnt i bogen. 
Hvis en bog har mere end en forfatter vil bogens id optræde flere gange i filen med forskelligt forfatter ID. For at sikre os at en forfatter ikke optræder flere gange med forskellige ID’er, da han kan have skrevet flere bøger, gemmer vi løbende en liste af de unikke forfattere som vi er stødt på og bruger det ID han tidligere er blevet tildelt.

Eksempel på dataen fra CSV filen:

![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/csvfil_eksempel2.png)

### City mentions

For at finde ud af hvilke byer der bliver nævnt i hvilke bøger, har vi skulle trække samtlige bynavne ud af hver enkelt bog. 
Det kunne gøres på to måder. Man kunne enten finde samtlige ord med store bogstaver og antage at disse er bynavne. Følger man denne fremgangsmåde vil man dog også få både personnavne, det første ord i ny sætning osv. med. 
Det er derfor ikke en særlig præcis metode, men den er forholdsvis hurtig. 
Derudover kunne vi også vælge at benytte os af Stanford Entity Recognizer. Dette er en ret præcis metode, men den tager også lang tid. En anden fordel ved denne metode frem for den anden er også at vi ikke selv skal tage højde for bynavne bestående af flere navne, det finder Stanford NER selv. 
Vi valgte at benytte os af sidstnævnte metode, da vi gerne ville have et så præcist resultat som muligt. 

Vi har skrevet et bash script, samt et java program for at hive byerne ud af hver bog. 
Scriptet som kan ses her https://github.com/ehn94/FindCities/blob/master/locationScript.sh benytter vi til at køre hver eneste fil igennem Stanford programmet, for derefter at gemme den taggede fil i en ny fil, i en ny mappe. 
Herefter kører vi de færdige filer igennem dette program https://github.com/ehn94/FindCities/tree/master/src/main/java/sem/findcities. 
Her bliver hvert ord med et <LOCATION> tag trukket ud og skrevet til en ny fil. 
Igennem hele processen sørger vi for at filnavnene forbliver det samme, så vi hele tiden ved hvilken bog det drejer sig om. 

Eksempel på dataen fra CSV filen:

![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/csvfil_eksempel3.png)

### City

Cities15000.csv filen kunne ikke direkte importeres til databasen, så vi omstrukturerede den således at den kunne.
Først og fremmest åbnede vi csv filen ind i excel. Inde i excel tastede vi filens tilhørende headers ind, og med hjælp fra excel, fik vi delt filen ind i struktureret kolonner. Derefter gemte vi filen som en tabulatorsepareret txt fil, og den fil kunne vi så importere til PostgreSQL databasen.

## Setup-guide

### PostgreSQL

1. Download TAR fil (https://drive.google.com/open?id=12vjsLJsbEYtgJsN50F4h85mWxCltRw7b).
2. Download pgAdmin 4 (https://www.pgadmin.org/download/).
3. Opret skema kaldet “schemaGutenberg”.
4. Højreklik på "schemaGutenberg" og tryk restore.
5. Ved filename finder du TAR filen og vælger den. 
6. Tryk restore. 
7. Kør følgende query:

```sql
Create function Haversine(lon1 float, lat1 float, lon2 float, lat2 float) returns float AS $$
BEGIN
	RETURN (2 * 3961 * asin(sqrt((sin(radians((lat2 - lat1) / 2))) ^ 2 + cos(radians(lat1)) * cos(radians(lat2)) * (sin(radians((lon2 - lon1) / 2))) ^ 2)));
	END;
$$ LANGUAGE plpgsql;
```

### Neo4j

1. Download zip-fil og unzip (https://drive.google.com/open?id=1OSrv945f4tmsOWqkIUhoztRUjreimmnD).
2. Download Neo4j Desktop (https://neo4j.com/download/).
3. Opret nyt projekt, anvend kode 1234.
4. Åben for projektet og klik “Import”. Flyt derefter alle CSV filerne fra unzippet zip-fil til denne mappe. 
5. Åben projektet op i din browser.
6. Kør følgende queries: 

#### Import Authors

```
LOAD CSV WITH HEADERS FROM "file:///author.csv" AS csvLine
CREATE (:AUTHOR { id: toInt(csvLine.id), author_name: (csvLine.name)})
```

#### Import Books

```
LOAD CSV WITH HEADERS FROM "file:///book.csv" AS csvLine
CREATE (:BOOK { id: toInt(csvLine.id), book_name: (csvLine.title)});
```

#### Import Cities
```
LOAD CSV WITH HEADERS FROM "file:///cities.csv" AS csvLine
CREATE (:CITY { id: toInt(csvLine.id), city_name: csvLine.city_name, longitude: toFloat(csvLine.longitude), latitude: toFloat(csvLine.latitude)})
```

#### Indexer ID’erne
```
CREATE INDEX ON :AUTHOR (id)
```
```
CREATE INDEX ON :BOOK (id)
```
```
CREATE INDEX ON :CITY (id)
```

#### Book_Author Written (mellemtabel)
```
LOAD CSV WITH HEADERS FROM "file:///book_author.csv" AS csvLine
MATCH (a:AUTHOR { id: toInt(csvLine.author_id)}),
 (b:BOOK { id: toInt(csvLine.book_id)})
CREATE (a)-[:WRITTEN]->(b)
```

#### Book_City Mention (mellemtabel)
```
USING PERIODIC COMMIT 500
LOAD CSV WITH HEADERS FROM "file:///book_city.csv" AS csvLine
MATCH (a:BOOK { id: toInt(csvLine.book_id)}),
 (b:CITY { id: toInt(csvLine.city_id)})
CREATE (a)-[:MENTION]->(b)
```

# Benchmark
Vi har foretaget en benchmark af de to databaser både før og efter de blev indekseret. Til dette har vi brugt JMeter. 
Vi har foretaget test med kald direkte til databasen, men også via vores API. Først et overblik over vores forskellige queries. 

## Queries

### PostgreSQL

##### 1. Given a city name your application returns all book titles with corresponding authors that mention this city.

```sql
SELECT book_title, author_name 
FROM "schemaGutenberg".book AS book 
INNER JOIN "schemaGutenberg"."book-author" AS book_author 
ON (book.id = book_author.book_id) 
INNER JOIN "schemaGutenberg".author AS author 
ON (book_author.author_id = author.id) 
INNER JOIN "schemaGutenberg"."book-city" AS book_city 
ON (book.id = book_city.book_id) 
INNER JOIN  "schemaGutenberg".city AS city 
ON (book_city.city_id = city.id) 
WHERE city.city_name = 'London';
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_1.png)
I alt 45.054 resultater.

##### 2. Given a book title, your application plots all cities mentioned in this book onto a map.

```sql
SELECT city_name, city.longitude, city.latitude 
FROM "schemaGutenberg".city AS city 
INNER JOIN "schemaGutenberg"."book-city" AS book_city 
ON (city.id = book_city.city_id) 
INNER JOIN "schemaGutenberg".book AS book 
ON (book_city.book_id = book.id) 
WHERE book_title = 'An Attic Philosopher in Paris — Volume 2';
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_2.png)
I alt 29 resultater.

##### 3. Given an author name your application lists all books written by that author and plots all cities mentioned in any of the books onto a map.

```sql
SELECT book_title, author_name, city_name, city.longitude, city.latitude 
FROM "schemaGutenberg".book AS book 
INNER JOIN "schemaGutenberg"."book-author" AS book_author 
ON (book.id = book_author.book_id) 
INNER JOIN "schemaGutenberg".author AS author 
ON (book_author.author_id = author.id)	
INNER JOIN "schemaGutenberg"."book-city" AS book_city 
ON (book.id = book_city.book_id) 
INNER JOIN  "schemaGutenberg".city AS city 
ON (book_city.city_id = city.id) 
WHERE author.author_name = 'United States. Central Intelligence Agency';
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_3.png)
I alt 17.613 resultater. 

##### 4. Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation.

```sql
SELECT book_title, city_name FROM "schemaGutenberg".book AS book 
INNER JOIN "schemaGutenberg"."book-city" AS book_city 
ON (book.id = book_city.book_id) 
INNER JOIN  "schemaGutenberg".city AS city 
ON (book_city.city_id = city.id) 
WHERE Haversine('12.56553', '55.67594 ', city.longitude, city.latitude) < 10;
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_4.png)
I alt 1.847 resultater.

### Neo4j

##### 1. Given a city name your application returns all book titles with corresponding authors that mention this city.

```
Match (c:CITY)<-[:MENTION]-(b:BOOK)<-[:WRITTEN]-(a:AUTHOR) 
where c.city_name = 'London' 
return b, a;
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_1.png)
I alt 45.054 resultater.

##### 2. Given a book title, your application plots all cities mentioned in this book onto a map.

```
Match (c:CITY)<-[:MENTION]-(b:BOOK) 
where b.book_name = 'An Attic Philosopher in Paris — Volume 2' 
return c, b;
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_2.png)
I alt 29 resultater.

##### 3. Given an author name your application lists all books written by that author and plots all cities mentioned in any of the books onto a map.

```
Match (c:CITY)<-[:MENTION]-(b:BOOK)<-[:WRITTEN]-(a:AUTHOR) 
where a.author_name = 'United States. Central Intelligence Agency' 
return b, c, a;
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_3.png)
I alt 17.613 resultater. 

##### 4. Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation.

```
MATCH (c:CITY)<-[:MENTION]-(b:BOOK) 
WITH b, c, distance(point({ longitude: c.longitude, latitude: c.latitude }) , point({ longitude: 12.56553, latitude: 55.67594 })) as dist 
WHERE dist<=10000 
RETURN b, c;
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_4.png)
I alt 1.847 resultater.

## Test setup

System: 
- Lenovo Thinkpad x220
- Windows 10 Pro
- CPU: Intel Core i7-2640M 2.80GHz
- RAM: 8 GB

Vi har valgt at simulere fem brugere, hvor hver bruger giver et forskelligt input. 
Test dataen er som følger: 

|Parameter   | Bruger 1  | Bruger 2 | Bruger 3 |   Bruger 4 |	Bruger 5 |
|---|---|---|---|---|---|
|city	|London	|Oslo	|Berlin	|Chicago |Tokyo	|
|title	|The Watcher, and other weird stories |Reminiscences of Tolstoy, by His Son |Carmilla |A Brief History of the United States |Christopher Columbus |
|author	|Jefferson, Thomas |Poe, Edgar Allan |Faxian |Twain, Mark |Hawthorne, Nathaniel |
|latitude |25.0657 |-43.24895 |48.20849 |-27.56056 |50.85045 |
|longitude |55.17128 |-65.30505	|16.37208 |151.95386 |4.34878 |

## Resultater
Alle resultater er opgivet i ms. 

#### På JDBC før indeksering
|   | Neo4j  Average  | Neo4j  Median | Postgres Average | Postgres Median |
|---|---|---|---|---|
|getBookAuthorByCity: |3827 |3652 |314 |70 |
|getCitiesByBookTitle: |330 |206 |9 |9 |
|getBookAuthorCityByAuthor: |335 |318 |25 |31 |
|getBookCityByGeolocation: |20565 |20156 |418 |314 |

Vi var overraskede over resultaterne for Neo4j, da nogle af tiderne var ekstremt langsomme. Derfor valgte vi at teste det direkte i Neo4j desktop, for at se om vi ville få samme resultat. 

|   | Neo4j  Average  | Neo4j  Median |
|---|---|---|
|getBookAuthorByCity: |245,6 |191 |
|getCitiesByBookTitle: |47 |46 |
|getBookAuthorCityByAuthor: |56 |48 |
|getBookCityByGeolocation: |10018 |10004 |

#### På JDBC efter indeksering
Her er resultaterne efter dataen er blevet indekseret. Det skal bemærkes at vi her har kørt queries direkte på Neo4j databasen fra start. Samme test data og fremgansmåde er brugt i øvrigt. 

|   | Neo4j  Average  | Neo4j  Median | Postgres Average | Postgres Median |
|---|---|---|---|---|
|getBookAuthorByCity: |211,4 |114 |173 |76 |
|getCitiesByBookTitle: |5,6 |2 |3 |2 |
|getBookAuthorCityByAuthor: |49,8 |39 |18 |19 |
|getBookCityByGeolocation: |10545 |10302 |330 |328 |

#### Via API før indeksering
Her kører vi jmeter testen på API'et fremfor direkte på databaserne. Vi benytter os af samme antal brugere, samt samme test data. 

|   | Neo4j  Average  | Neo4j  Median | Postgres Average | Postgres Median |
|---|---|---|---|---|
|getBookAuthorByCity: |2460 |1397 |317 |223 |
|getCitiesByBookTitle: |1025 |1242 |82 |83 |
|getBookAuthorCityByAuthor: |743 |824 |97 |105 |
|getBookCityByGeolocation: |109003 |109024 |766 |768 |

#### Via API efter indeksering

|   | Neo4j  Average  | Neo4j  Median | Postgres Average | Postgres Median |
|---|---|---|---|---|
|getBookAuthorByCity: |510 |238 |446 |240 |
|getCitiesByBookTitle: |99 |93 |92 |93 |
|getBookAuthorCityByAuthor: |196 |119 |107 |120 |
|getBookCityByGeolocation: |65589 |65630 |512 |478 |

# Diskussion af benchmark resultater

Vi må tage højde for det faktum at JMeter til tider har givet nogle resultater vi ikke vurderer stemmer overens med virkeligheden. Det gør desværre vores resultater en smule usikre, men vi har forsøgt at undersøge dette og mener at kunne stole på de endelige resultater. 
En anden gang ville det dog være værd at undersøge om der findes bedre værktøjer end lige JMeter til test af især Neo4j. 

Ud fra de benchmarks vi har lavet er der ikke stor forskel mellem Neo4j og Postgres når det gælder de tre første forespørgsler. Men når vi kommer til den fjerde hvor man skal finde alle bøger der nævner byer der ligger i nærheden af en geolocation er Neo4j meget langsommere. 
Vi havde ikke regnet med at Neo4j ville være så meget langsommere til at returnere dette. 
Vi vil mene at den store forskel kan skyldes at i Neo4J skal man finde alle book nodes og derefter hente location fra alle byer og derefter konvertere dataen til points. Det kan også skyldes den måde vi har modelleret vores data på da vores edge går BOOK →  CITY og vi ikke har et edge der går CITY → BOOK.


Vi ser en markant stigning i søgetiden når vi kører vores test via API’et. For postgres får vi dog i alle tilfælde stadig tider som vi finder hurtige. For Neo4j gælder dette også undtagen geolocation som vi stadig finder uacceptabelt. Neo4j ser også en procentvis markant langsommere svartid og vi tror at det kan skyldes den måde vi konverterer mellem det resultat vi får fra databasen og vores Book og City objekter.

I sidste ende vil vores anbefaling til lige netop dette projekt falde på Postgres. Med Postgres kan vi bygge en forholdsvis robust datamodel og eftersom dataen for dette projekt med stor sandsynlighed ikke ændre sig, passer det fint. 
Står man til gengæld med et projekt med dybere relationer, vil Neo4j passe bedre. Dette skyldes at Neo4j kan håndtere disse relationer med stort set konstant tid.

# Konklusion

Generelt synes vi at det har været et interessant projekt at arbejde med. Hele idéen med at skulle gå fra den rå data til at formatere den og optimere den bedst muligt til de forskellige databaser kunne vi ret godt lide.

En ting som vi kan se som kunne forbedres er den måde at byer bliver vist på vores kort. Som det er nu kan der være flere byer i verden der hedder det samme, så vi laver en markør for alle steder den by findes. En måde vi ser at dette kunne forbedres er hvis f.eks. Stanford toolet eller et andet tool kunne lave en dybere analyse af teksten. F.eks. hvis Washington, Boston og Florida, bliver fundet i en bog kunne de ud fra alle byer den har fundet prøve at give et procentvis gæt på hvilket land de er fra. 

Fordi alle disse byer alle sammen findes i USA er der ret stor chance for at f.eks. Florida ikke er byen Florida i Mexico. Dette vil dog stadig kunne give nogle fejl da det f.eks. vil være svært at gøre for en bog der nævner byer i hele verden, men vi mener dog at det ville kunne give et resultat der er lidt mere nøjagtigt. 

Ud fra vores benchmark samt oplevelser med modellering af dataen, vil vi anbefale Postgres til denne type projekt. 
