/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sem.firstsemexam;

import Entity.Cat;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 *
 * @author ehn19
 */
public class ExercisesTest {

    Exercises ex = new Exercises();
    private ArrayList<Cat> cats;
    private ArrayList<Integer> data;
    /**
     * Test of readFile method, of class Exercises.
     */
    @org.junit.Test
    public void testReadFile() {
        System.out.println("Read from file");
        cats = ex.readFile("Cats.txt");

        int exp = 8;
        int result = cats.size();

        assertEquals(exp, result);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadWrongFile() {
        cats = ex.readFile("Cat.txt");
        cats.get(0);
    }

    /**
     * Test of appendCatToFile method, of class Exercises.
     */
//    @org.junit.Test
//    public void testAppendCatToFile() {
//        System.out.println("Append cat to file");
//        Cat purr = new Cat("Purr", 4, "White", true);
//        ex.appendCatToFile("Cats.txt", purr);
//        assertEquals(purr.getName(), data.get(7).getName());
//        
//        //data.remove(8);
//    }
    /**
     * Test of getOldest method, of class Exercises.
     */
    @org.junit.Test
    public void testGetOldest() {
        System.out.println("getOldest");
        cats = ex.readFile("Cats.txt");

        String expRes = "Whiskers";
        String res = ex.getOldest(cats);
        assertEquals(expRes, res);
    }

    /**
     * Test of removeSickCats method, of class Exercises.
     */
    @org.junit.Test
    public void testRemoveSickCats() {
        System.out.println("removeSickCats");
        cats = ex.readFile("Cats.txt");
        int expBefore = 8;
        assertEquals(expBefore, cats.size());
        ex.removeSickCats(cats);
        int expAfter = 5;
        assertEquals(expAfter, cats.size());
    }

    /**
     * Test of updateName method, of class Exercises.
     */
    @org.junit.Test
    public void testUpdateName() {
        System.out.println("updateName");
        cats = ex.readFile("Cats.txt");

        String expResBefore = "Felix";
        String resBefore = cats.get(1).getName();

        assertEquals(expResBefore, resBefore);

        ex.updateName(cats, "Felix", "Ole");

        String expResAfter = "Ole";
        String resAfter = cats.get(1).getName();

        assertEquals(expResAfter, resAfter);
    }

    @org.junit.Test
    public void testUpdateNameH() {
        System.out.println("updateName with Hamcrest");
        cats = ex.readFile("Cats.txt");
        String expResAfter = "Ole";
        String resBefore = cats.get(1).getName();

        assertThat("Ole", not(equalTo(resBefore)));
        ex.updateName(cats, "Felix", "Ole");
        String resAfter = cats.get(1).getName();
        assertThat(expResAfter, is(equalTo(resAfter)));
    }

    /**
     * Test of updateCat method, of class Exercises.
     */
    @org.junit.Test
    public void testUpdateCat() {
        System.out.println("updateCat");
        cats = ex.readFile("Cats.txt");

        Cat newCat = new Cat("Bo", 13, "gray", false);
        ex.updateCat(cats, "Whiskers", newCat);
        assertEquals(newCat, cats.get(2));

    }
    
    @org.junit.Test
    public void testUpdateCatH() {
        System.out.println("updateCat with Hamcrest");
        cats = ex.readFile("Cats.txt");

        Cat newCat = new Cat("Bo", 13, "gray", false);
        ex.updateCat(cats, "Whiskers", newCat);
        assertThat(newCat, is(equalTo(cats.get(2))));
    }

    /**
     * Test of printCats method, of class Exercises.
     */
//    @org.junit.Test
//    public void testPrintCats() {
//        System.out.println("printCats");
//        data = ex.readFile("Cats.txt");
//        
//        String printedCats = ex.printCats(data);
//    }
    /**
     * Test of getSomeCats method, of class Exercises.
     */
    @org.junit.Test
    public void testGetSomeCats() {
        System.out.println("getSomeCats");
        cats = ex.readFile("Cats.txt");

        int expBefore = 8;
        assertEquals(expBefore, cats.size());
        ArrayList<String> subList = ex.getSomeCats(cats, "B");
        int expAfter = 2;
        assertEquals(expAfter, subList.size());
    }

    /**
     * Test of sortedCats method, of class Exercises.
     */
    @org.junit.Test
    public void sortedCats() {
        System.out.println("sortedCats");
        cats = ex.readFile("Cats.txt");

        String expBefore = "Bailey";
        assertEquals(expBefore, cats.get(0).getName());
        ArrayList<String> subList = ex.sortedCats(cats);
        String expAfter = "Alex";
        assertEquals(expAfter, subList.get(0));
    }

    /**
     * Test of sortedCats method, of class Exercises.
     */
    @org.junit.Test
    public void getKittens() {
        System.out.println("sortedCats");
        cats = ex.readFile("Cats.txt");

        int expBefore = 8;
        assertEquals(expBefore, cats.size());
        ArrayList<Cat> subList = ex.getKittens(cats);
        int expAfter = 1;
        assertEquals(expAfter, subList.size());
    }
    
    @Test
    public void testReadData() {
        System.out.println("Read data from file");
        data = ex.readData("Data.txt");

        int exp = 90;
        int result = data.size();

        assertEquals(exp, result);

    }
    
    @Test
    public void dataDrivenTestDataFileEndsWith() {
        int lastValue = 90;
        data = ex.readData("Data.txt");

        assertThat(lastValue, is(equalTo(data.size())));
    }
}
