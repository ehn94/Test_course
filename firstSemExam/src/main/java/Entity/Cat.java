/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Interfaces.CatInterface;

/**
 *
 * @author ehn19
 */
public class Cat implements CatInterface {

    private String name;
    private String color;
    private int age;
    private boolean isSick;

    public Cat() {

    }

    public Cat(String name, int age, String color, boolean isSick) {

        this.name = name;
        this.age = age;
        this.color = color;
        this.isSick = isSick;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String getColor(){
        return color;
    }
    
    @Override
    public void setColor(String color){
        this.color = color; 
    }
    
    @Override 
    public boolean getIsSick(){
        return isSick;
    }
    
    @Override
    public void setIsSick(boolean isSick){
        this.isSick = isSick;
    }
    
    @Override
    public String toString() {
        return '{' + "Name: " + name + ", Age:" + age + 
                ", Color: " + color + ", Is sick: " + isSick + '}' +"\n";
    }
    
}
