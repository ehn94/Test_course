package REST;

import Connectors.Neo4jConnector;
import Connectors.PostgreSQLConnector;
import Controller.Facade;
import Controller.Facade.dbType;
import DataAccess.Neo4jDataAccess;
import DataAccess.PostgreSQLDataAccess;
import Exceptions.NotFoundExceptionMapper;
import Interfaces.FacadeInterface;
import Model.Book;
import Model.City;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("api")

public class API {

    @Context
    private HttpServletResponse servletResponse;

    private void allowCrossDomainAccess() {
        if (servletResponse != null){
            servletResponse.setHeader("Access-Control-Allow-Origin", "*");
        }
    }
    FacadeInterface facade = new Facade(new PostgreSQLDataAccess(new PostgreSQLConnector()), new Neo4jDataAccess(new Neo4jConnector()));

    @GET
    @Path("getBookAuthorByCity/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookAuthorByCity(@PathParam("id") String city_name) throws SQLException, NotFoundExceptionMapper {
                                long nanoTime = System.nanoTime();
        List<Book> books = facade.getBookAuthorByCity(dbType.POSTGRESS, city_name);
                                nanoTime = System.nanoTime() - nanoTime;
        calculateAndShowResult(nanoTime,"getBookAuthorByCity");
        if (books.isEmpty()) {
            throw new NotFoundExceptionMapper("No books found with the given city name");
        }
        return new Gson().toJson(books);
    }

    @GET
    @Path("getCitiesByBookTitle/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCitiesByBookTitle(@PathParam("id") String book_title) throws SQLException, NotFoundExceptionMapper {
        allowCrossDomainAccess();
                        long nanoTime = System.nanoTime();
        List<City> cities = facade.getCitiesByBookTitle(dbType.POSTGRESS, book_title);
                        nanoTime = System.nanoTime() - nanoTime;
        calculateAndShowResult(nanoTime,"getCitiesByBookTitle");
        if (cities.isEmpty()) {
            throw new NotFoundExceptionMapper("No cities found with the given book title");
        }
        return new Gson().toJson(cities);
    }

    @GET
    @Path("getBookAuthorCityByAuthor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookAuthorCityByAuthor(@PathParam("id") String author_name) throws SQLException, NotFoundExceptionMapper {
        allowCrossDomainAccess();
                long nanoTime = System.nanoTime();
        List<Book> books = facade.getBookAuthorCityByAuthor(dbType.POSTGRESS, author_name);
                nanoTime = System.nanoTime() - nanoTime;
        calculateAndShowResult(nanoTime,"getBookAuthorCityByAuthor");
        if (books.isEmpty()) {
            throw new NotFoundExceptionMapper("No book found with the given author name");
        }
        return new Gson().toJson(books);
    }

    @GET
    @Path("getBookCityByGeolocation/{latitude}/{longitude}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookCityByGeolocation(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude) throws SQLException, NotFoundExceptionMapper {
        long nanoTime = System.nanoTime();
        List<Book> books = facade.getBookCityByGeolocation(dbType.POSTGRESS, latitude, longitude);
        nanoTime = System.nanoTime() - nanoTime;
        calculateAndShowResult(nanoTime,"getBookCityByGeolocation");
        if (books.isEmpty()) {
            throw new NotFoundExceptionMapper("No book found with the given geolocation");
        }
        return new Gson().toJson(books);
    }
    
    
     private void calculateAndShowResult(long result, String text){
         System.out.println(text + " " + " time: "  + (result/1000000));
    }
}
