/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ehn19
 */
public class City {
    private String city_name;
    private double longitude;
    private double latitude;

    public City(String name ,double longitude, double latitude) {
        this.city_name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public City(String name){
        this.city_name = name;
    }
    
    public String getName(){
        return city_name; 
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
    
}
