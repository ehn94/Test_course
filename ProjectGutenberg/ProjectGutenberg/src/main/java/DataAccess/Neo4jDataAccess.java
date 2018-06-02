package DataAccess;

import Connectors.Neo4jConnector;
import org.neo4j.driver.v1.*;
import Interfaces.DataAccessInterface;
import Model.Book;
import Model.City;
import java.sql.SQLException;
import java.util.ArrayList;

public class Neo4jDataAccess implements DataAccessInterface{

    private Neo4jConnector connector;
    
    public Neo4jDataAccess(Neo4jConnector connector) {
        this.connector = connector;
    }
    
    @Override
    public ArrayList<Book> getBookAuthorByCity(String city) throws SQLException {
        Session session = connector.DBConnector();
        StatementResult result = session.run("Match (c:CITY)<-[:MENTION]-(b:BOOK)<-[:WRITTEN]-(a:AUTHOR) where c.city_name = '" + city + "' return b.book_name, a.author_name");
        ArrayList<Book> books = new ArrayList();
        while (result.hasNext()) {
            Record record = result.next();
            books.add(new Book(record.get("b.book_name").asString(), record.get("a.author_name").asString()));
        }
        return books;
    }

    @Override
    public ArrayList<City> getCitiesByBookTitle(String title) throws SQLException {
        Session session = connector.DBConnector();
        StatementResult result = session.run("Match (c:CITY)<-[:MENTION]-(b:BOOK) where b.book_name = " + '"' + title + '"' + " return c;");
        ArrayList<City> cities = new ArrayList();
        while (result.hasNext()) {
            Record record = result.next();
            cities.add(new City(record.get("c").get("city_name").asString(), record.get("c").get("longitude").asDouble(), record.get("c").get("latitude").asDouble()));
        }
        connector.DBConnectorClose();
        return cities;
    }

    @Override
    public ArrayList<Book> getBookAuthorCityByAuthor(String author) throws SQLException {
        Session session = connector.DBConnector();
        StatementResult result = session.run("Match (c:CITY)<-[:MENTION]-(b:BOOK)<-[:WRITTEN]-(a:AUTHOR) where a.author_name = '" + author + "' return b, c, a;");
        ArrayList<Book> books = new ArrayList();
        while (result.hasNext()) {
            Record record = result.next();
            Book book = new Book(record.get("b").get("book_name").asString(), record.get("a").get("author_name").asString());
            book.addCity(new City(record.get("c").get("city_name").asString(), record.get("c").get("longitude").asDouble(), record.get("c").get("latitude").asDouble()));
            books.add(book);
        }
        connector.DBConnectorClose();
        return books;
    }

    @Override
    public ArrayList<Book> getBookCityByGeolocation(String latitude, String longitude) throws SQLException {
        Session session = connector.DBConnector();
        StatementResult result = session.run("MATCH (c:CITY)<-[:MENTION]-(b:BOOK) WITH b, c, distance(point({ longitude: c.longitude, latitude: c.latitude }) , point({ longitude: " + longitude + ", latitude: " + latitude + " })) as dist WHERE dist<10000 RETURN b, c");
        ArrayList<Book> books = new ArrayList();
        while (result.hasNext()) {
            Record record = result.next();
            Book book = new Book(record.get("b").get("book_name").asString());
            book.addCity(new City(record.get("c").get("city_name").asString(), record.get("c").get("longitude").asDouble(), record.get("c").get("latitude").asDouble()));
            books.add(book);
        }
        connector.DBConnectorClose();
        return books;
    }
}
