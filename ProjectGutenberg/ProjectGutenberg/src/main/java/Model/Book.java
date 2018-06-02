package Model;

import java.util.ArrayList;

public class Book {

    private String book_title;
    private String author_name;
    private ArrayList<City> cities;

    public Book(){}
    
    public Book(String book_title, String author_name) {
        this.book_title = book_title;
        this.author_name = author_name;
        cities = new ArrayList<>();
    }
    
    public Book(String book_title) {
        this.book_title = book_title;
        cities = new ArrayList<>();
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void addCity(City city) {
        cities.add(city);
    }

    

    public String getTitle() {
        return book_title;
    }

    public void setTitle(String title) {
        this.book_title = title;
    }

    public String getAuthor() {
        return author_name;
    }

    public void setAuthor(String author) {
        this.author_name = author;
    }

}
