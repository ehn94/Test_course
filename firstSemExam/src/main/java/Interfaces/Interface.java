/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entity.Cat;
import java.util.ArrayList;

/**
 *
 * @author ehn19
 */
public interface Interface {
    /**
    * Read the data from the file into an ArrayList of Cats.
    * @param fileName the name of the file to be read.
    * @return a list of Cat objects.
    **/
    public ArrayList<Cat> readFile(String fileName);
    /**
    * Append a new cat to the file. 
    * @param fileName the name of the file to be written to.
    * @param cat the cat object to append. 
    **/
    public void appendCatToFile(String fileName, Cat cat);
    /**
    * Find the oldest cat. 
    * @param cats the list of Cat objects to be searched. 
    * @return the name of the oldest cat.
    **/
    public String getOldest(ArrayList<Cat> cats);
    /**
    * Remove all the sick cats. 
    * @param cats the list of Cat objects to be searched. 
    **/
    public void removeSickCats(ArrayList<Cat> cats);
    /**
    * Update the name of a cat. 
    * @param cats the list of Cat objects to be searched. 
    * @param name of the cat to update. 
    * @param newName of the cat. 
    **/
    public void updateName(ArrayList<Cat> cats, String name, String newName);
    /**
    * Update a cat. 
    * @param cats the list of Cat objects to be searched. 
    * @param oldCat to be updated.
    * @param newCat to replace the oldCat. 
    **/
    public void updateCat(ArrayList<Cat> cats, String oldCat, Cat newCat);
    /**
    * Print all cats
    * @param cats the list of cat objects to print.
    **/
    public void printCats(ArrayList<Cat> cats);
    /**
    * Get all cats which names starts with the given letter.
    * @param cats the list of Cat objects to be searched. 
    * @param firstLetter of the cats to search for. 
    * @return an ArrayList of the names of the cats.
    **/
    public ArrayList<String> getSomeCats(ArrayList<Cat> cats, String firstLetter);
    /**
     * Sort list of cats  in alphabetical order.
     * @param cats list of Cat objects to sort. 
     * @return a list of Cat names in alphabetical order.
     */
    public ArrayList<String> sortedCats(ArrayList<Cat> cats);
    /**
     * Return a list of kittens. A cat is a kitten if it is age 3 or younger.
     * @param cats list of Cat object to sort. 
     * @return a list of kittens. 
     */
    public ArrayList<Cat> getKittens(ArrayList<Cat> cats);
}
