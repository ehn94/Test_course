package Connectors;

import org.neo4j.driver.internal.spi.Connection;
import org.neo4j.driver.v1.*;

public class Neo4jConnector {

    // Fields
    private Driver driver;
    private Session session;
    
    public Neo4jConnector() {
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "class"));
    }
    
    public Neo4jConnector(Driver driver) {
        this.driver =  driver;
    }
    
    // Connecting to Neo4j database
    public Session DBConnector() {
        try {
            session = driver.session();
        } catch (Exception e) {
            System.out.println("Exception - " + e);
        }
        return session;
    }

    // Closing connection to Neo4j database
    public void DBConnectorClose() {
        session.close();
    }
}

//public static Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "1234"));
//public static Session session = driver.session();