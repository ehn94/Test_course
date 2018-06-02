package Facade;

import Controller.Facade;
import static Controller.Facade.dbType.NEO;
import static Controller.Facade.dbType.POSTGRESS;
import DataAccess.PostgreSQLDataAccess;
import Interfaces.DataAccessInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FacadeTest {
    
    private Facade facade;
    
    @Mock
    private DataAccessInterface accessNeo4J;
    
    @Mock
    private DataAccessInterface accessPostgres;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        facade = new Facade(accessPostgres, accessNeo4J);
    }
    
    @Test
    public void testPostgressDataAccessCity() throws SQLException{
        facade.getBookAuthorByCity(POSTGRESS, "");
        
        verify(accessPostgres).getBookAuthorByCity("");
    }
    
    @Test
    public void testNeoDataAccessCity() throws SQLException{
        facade.getBookAuthorByCity(NEO, "");
        
        verify(accessNeo4J).getBookAuthorByCity("");
    }
    
    @Test
    public void testPostgressDataAccessAuthor() throws SQLException{
        facade.getBookAuthorCityByAuthor(POSTGRESS, "");
        
        verify(accessPostgres).getBookAuthorCityByAuthor("");
    }
    
    @Test
    public void testNeoDataAccessAuthor() throws SQLException{
        facade.getBookAuthorCityByAuthor(NEO, "");
        
        verify(accessNeo4J).getBookAuthorCityByAuthor("");
    }
    @Test
    public void testPostgressDataAccessTitle() throws SQLException{
        facade.getCitiesByBookTitle(POSTGRESS, "");
        
        verify(accessPostgres).getCitiesByBookTitle("");
    }
    
    @Test
    public void testNeoDataAccessTitle() throws SQLException{
        facade.getCitiesByBookTitle(NEO, "");
        
        verify(accessNeo4J).getCitiesByBookTitle("");
    }
    @Test
    public void testPostgressDataAccessGeolocation() throws SQLException{
        facade.getBookCityByGeolocation(POSTGRESS, "", "");
        
        verify(accessPostgres).getBookCityByGeolocation("", "");
    }
    
    @Test
    public void testNeoDataAccessGeolocation() throws SQLException{
        facade.getBookCityByGeolocation(NEO, "", "");
        
        verify(accessNeo4J).getBookCityByGeolocation("", "");
    }
    
}
