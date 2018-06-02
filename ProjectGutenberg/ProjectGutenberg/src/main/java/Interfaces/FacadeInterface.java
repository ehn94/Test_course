/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controller.Facade.dbType;
import Model.Book;
import Model.City;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ehn19
 */
public interface FacadeInterface {

    public ArrayList<Book> getBookAuthorByCity(dbType dbEnum, String city) throws SQLException;

    public ArrayList<City> getCitiesByBookTitle(dbType dbEnum, String title) throws SQLException;

    public ArrayList<Book> getBookAuthorCityByAuthor(dbType dbEnum, String author) throws SQLException;

    public ArrayList<Book> getBookCityByGeolocation(dbType dbEnum, String latitude, String longitude) throws SQLException;

}
