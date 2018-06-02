/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Book;
import Model.City;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ehn19
 */
public interface DataAccessInterface {
    
    public ArrayList<Book> getBookAuthorByCity(String city) throws SQLException;
    public ArrayList<City> getCitiesByBookTitle(String title) throws SQLException;
    public ArrayList<Book> getBookAuthorCityByAuthor(String author) throws SQLException;
    public ArrayList<Book> getBookCityByGeolocation(String latitude, String longitude) throws SQLException;
}
