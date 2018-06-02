package DataAccess;

import Connectors.PostgreSQLConnector;
import Interfaces.DataAccessInterface;
import Model.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UnitTest {

    private DataAccessInterface dataAccess;
    
    @Mock
    private Connection con;

    @Mock
    private PostgreSQLConnector connector;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dataAccess = new PostgreSQLDataAccess(connector);
    }

    @After
    public void tearDown() {
    }    

    @Test
    public void getBookAuthorByCityOneBook() throws SQLException {
        Mockito.when(connector.SQLConnector()).thenReturn(con);
        Mockito.when(con.prepareStatement(anyString())).thenReturn(stmt);
        Mockito.when(stmt.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getString(1)).thenReturn("The Wonderful Wizard of Oz");
        Mockito.when(resultSet.getString(2)).thenReturn("L. Frank Baum");
        ArrayList<Book> books = dataAccess.getBookAuthorByCity("London");
        assertThat(books.get(0).getTitle(), is(equalTo("The Wonderful Wizard of Oz")));
        assertThat(books.get(0).getAuthor(), is(equalTo("L. Frank Baum")));
    }
    
    @Test(expected = SQLException.class)
    public void getBookAuthorByCityException() throws SQLException {
        Mockito.when(connector.SQLConnector()).thenReturn(con);
        Mockito.when(con.prepareStatement(anyString())).thenReturn(stmt);
        Mockito.when(stmt.executeQuery()).thenThrow(new SQLException());
        ArrayList<Book> books = dataAccess.getBookAuthorByCity("London or 1 = 1--");
    }
    
    @Test
    public void getBookAuthorCityByAuthor() throws SQLException {
        Mockito.when(connector.SQLConnector()).thenReturn(con);
        Mockito.when(con.prepareStatement(anyString())).thenReturn(stmt);
        Mockito.when(stmt.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getString(1)).thenReturn("The Great English Short-Story Writers, Volume 1");
        Mockito.when(resultSet.getString(2)).thenReturn("Hawthorne, Nathaniel");
        ArrayList<Book> books = dataAccess.getBookAuthorByCity("Hawthorne, Nathaniel");
        assertThat(books.get(0).getTitle(), is(equalTo("The Great English Short-Story Writers, Volume 1")));
        assertThat(books.get(0).getAuthor(), is(equalTo("Hawthorne, Nathaniel")));
    }
}
