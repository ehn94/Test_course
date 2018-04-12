/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myarraylistwithbugs;

public class MyArrayListWithBugs {

    private static Cat[] list;
    int nextFree;

    // Creates a new empty list
    public MyArrayListWithBugs() {
        list = new Cat[5];
        nextFree = 0;
    }

    // Inserts object at the end of list
    public void add(Cat o) {
        // check capacity
        if (list.length <= nextFree) {
            list = getLongerList();
        }

        list[nextFree] = o;
        nextFree++;
    }

    // Returns the number of objects in the list
    public int size() {
        return nextFree;
    }

    // Returns a reference to the object at position index
    // Throws IndexOutOfBoundsException
    public Cat get(int index) {
        if (index < 0 || nextFree < index) { //Der skal ikke være noget = tegn
            throw new IndexOutOfBoundsException("Error (get): Invalid index"
                    + index);
        }

        return list[index];
    }

    // Inserts object at position index
    // Throws IndexOutOfBoundsException
    public void add(int index, Cat o) {
        if (index < 0 || nextFree < index) {
            throw new IndexOutOfBoundsException("Error (add): Invalid index"
                    + index);
        }

        // check capacity
        if (list.length <= nextFree) {
            list = getLongerList();
        }

        // Shift elements upwards to make position index free
        // Start with last element and move backwards
        for (int i = (nextFree - 1); i >= index; i--) { 
            System.out.println("Helloo " + list[i+1] + " and list[i] " + list[i]);
            list[i] = list[i +1 ];
            
        }

        list[index] = o;
    }

    // Removes object at position index
    // Returns a reference to the removed object
    // Throws IndexOutOfBoundsException
    public Cat remove(int index) { //Der er nok noget galt her, find ud af hvad 
        if (index < 0 || nextFree <= index) {
            throw new IndexOutOfBoundsException("Error (remove): Invalid index"
                    + index);
        }

        // Shift elements down to fill indexed position
        // Start with first element
        for (int i = index; i < nextFree - 1; i++) {
            list[i] = list[i + 1];
        }
        nextFree--;

        return list[index];

    }

    //============== private helper methods ==========
    // create a list with double capacity and
    // copy all elements to this
    private Cat[] getLongerList() {
        Cat[] tempList = new Cat[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            tempList[i] = list[i];
        }
        return tempList;
    }

}
