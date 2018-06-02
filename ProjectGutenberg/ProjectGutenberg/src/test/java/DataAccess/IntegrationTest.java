package DataAccess;

import Connectors.Neo4jConnector;
import Connectors.PostgreSQLConnector;
import Interfaces.DataAccessInterface;
import Model.Book;
import Model.City;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class IntegrationTest {

    private DataAccessInterface dataAccess;
    Connection con;
   

    @Parameters
    public static Collection<DataAccessInterface> data() throws SQLException {
        Collection<DataAccessInterface> data = new ArrayList<>();
  //      data.add(new Neo4jDataAccess(new Neo4jConnector()));
        data.add(new PostgreSQLDataAccess(new PostgreSQLConnector()));
        return data;
    }
    
    public IntegrationTest(DataAccessInterface input) {
        dataAccess = input;
    }
    
//    @Before
//    public void setUp() throws SQLException {
////        con = DriverManager.getConnection(url, username, password);
//        dataAccess = new Neo4jDataAccess(new Neo4jConnector());
//    }

    @Test
    public void testGetCitiesByBookTitle() throws SQLException {
        String title = "Alice's Adventures in Wonderland";
        ArrayList<City> cities = dataAccess.getCitiesByBookTitle(title);
        boolean found = false;
        for (City c : cities) {
            if (c.getName().equals("London")) {
                found = true;
            }
        }
        Assert.assertThat(found, is(equalTo(true)));
    }

    @Test
    public void testGetCitiesByBookTitleFail() throws SQLException {
        String title = "Harry Potter";
        int actual = dataAccess.getCitiesByBookTitle(title).size();
        Assert.assertThat(actual, is(equalTo(0)));
    }

    @Test
    public void testGetBookAuthorByCity() throws SQLException {
        String city = "Berlin";
        ArrayList<Book> books = dataAccess.getBookAuthorByCity(city);
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().equals("The 1990 CIA World Factbook")) {
                found = true;
            }
        }
        Assert.assertThat(found, is(equalTo(true)));
    }

    @Test
    public void testGetBookAuthorByCityFail() throws SQLException {
        String city = "Karlslunde";
        int actual = dataAccess.getBookAuthorByCity(city).size();
        Assert.assertThat(actual, is(equalTo(0)));
    }

    @Test
    public void testGetBookAuthorCityByAuthor() throws SQLException {
        String author = "Muir, John";
        ArrayList<Book> books = dataAccess.getBookAuthorCityByAuthor(author);
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().equals("The Mountains of California")) {
                for (City c : b.getCities()) {
                    if (c.getName().equals("Santa Cruz")) {
                        found = true;
                    }
                }
            }
        }
        Assert.assertThat(found, is(equalTo(true)));
    }

    @Test
    public void testGetBookAuthorCityByAuthorFail() throws SQLException {
        String author = "Merlin";
        int actual = dataAccess.getBookAuthorCityByAuthor(author).size();
        Assert.assertThat(actual, is(equalTo(0)));
    }

    @Test
    public void testGetBookCityByGeolocation() throws SQLException {
        String longitude = "16.37208";
        String latitude = "48.20849";
        ArrayList<Book> books = dataAccess.getBookCityByGeolocation(latitude, longitude);
        boolean found = false;
        for (Book b : books) {
            for (City c : b.getCities()) {
                if (c.getName().equals("Vienna")) {
                    found = true;
                }
            }
        }
        Assert.assertThat(found, is(equalTo(true)));
    }
}
