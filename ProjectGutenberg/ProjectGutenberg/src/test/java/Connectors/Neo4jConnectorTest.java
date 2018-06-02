//package Connectors;
//
//import org.junit.*;
//import org.neo4j.driver.v1.*;
//
//public class Neo4jConnectorTest {
//    
//    // Fields
//    public static Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "1234"));
//    public static Session session = driver.session();
//    
//    @Before
//    public void setUp() {
//        try {
//            
//            Neo4jConnector.DBConnector(driver, session);
//        } catch (Exception e) {
//            System.out.println("Exception - " + e);
//        }
//    }
//    
//    @After
//    public void tearDown() {
//        Neo4jConnector.DBConnectorClose(driver, session);
//    }
//
//    @Test
//    public void testDBConnector() {
//    }
//
//    @Test
//    public void testGetBook() {
//    }
//}