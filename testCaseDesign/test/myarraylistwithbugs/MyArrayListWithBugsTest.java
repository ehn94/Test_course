/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myarraylistwithbugs;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ehn19
 */
public class MyArrayListWithBugsTest {
    
    public MyArrayListWithBugsTest() {
    }


    @Test
    public void testSizeBeforeAddingT10() {
        System.out.println("Testing size before adding element");
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    @Test
    public void testSizeAfterAddingT11() {
        System.out.println("Testing size after adding element");
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        instance.add(new Cat("Felix", 2));
        int expResult = 1;
        int result = instance.size();
        assertEquals(expResult, result);
    } 

    /**
     * Test of get method, of class MyArrayListWithBugs.
     */
    @Test
    public void testGetExistingObjectT12() {
        System.out.println("Getting existing object");
        int index = 0;
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        instance.add(new Cat("Felix", 12));
        String expResult = "Felix";
        Object result = instance.get(index).name;
        assertEquals(expResult, result);
    }

    
    @Test
    public void testCase1(){
        System.out.println("Add object to empty array, Test Case 1");
        Cat o = new Cat("Felix", 12);
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        assertEquals(0, instance.size());
        instance.add(o);
        assertEquals(1, instance.size());
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCase2(){
        System.out.println("Remove non-existing object from empty list");
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        instance.remove(1);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCase3(){
        System.out.println("Get object that does not exist");
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        instance.get(1);
    }
    
    @Test
    public void testCase4(){
        System.out.println("Add object to array with size 2");
        Cat o = new Cat("Felix", 12);
        Cat o1 = new Cat("Whiskers", 3);
        Cat o2 = new Cat("Ib", 4);
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        instance.add(o);
        instance.add(o1);
        assertEquals(2, instance.size());
        instance.add(o2);
        assertEquals(3, instance.size());
    }
    
    @Test
    public void testCase5(){
        System.out.println("Remove object from array with size 2");
        Cat o = new Cat("Felix", 12);
        Cat o1 = new Cat("Whiskers", 3);
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        instance.add(o);
        instance.add(o1);
        assertEquals(2, instance.size());
        instance.remove(1);
        assertEquals(1, instance.size());
    }
    
    @Test
    public void testCase6(){
        System.out.println("Remove object from array with size 1");
        Cat o = new Cat("Felix", 12);
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        instance.add(o);
        assertEquals(1, instance.size());
        instance.remove(0);
        assertEquals(0, instance.size());
    }
    @Test
    public void testCase7(){
        System.out.println("Add object to array with size 4");
        Cat cat1 = new Cat("Felix", 12);
        Cat cat2 = new Cat("Ib", 4);
        Cat cat3 = new Cat("Bailey", 8);
        Cat cat4 = new Cat("Alex", 14);
        Cat cat5 = new Cat("Meow", 2);
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        instance.add(cat1);
        instance.add(cat2);
        instance.add(cat3);
        instance.add(cat4);
        assertEquals(4, instance.size());
        instance.add(cat5);
        assertEquals(5, instance.size());
        assertEquals(5, instance.nextFree);
    }
    @Test
    public void testCase8(){
        System.out.println("Add object to array with size 4");
        Cat cat1 = new Cat("Felix", 12);
        Cat cat2 = new Cat("Ib", 4);
        Cat cat3 = new Cat("Bailey1", 8);
        Cat cat4 = new Cat("Alex", 14);
        Cat cat5 = new Cat("Meow", 2);
        Cat cat6 = new Cat("Bailey", 10);
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        instance.add(cat1);
        instance.add(cat2);
        instance.add(cat3);
        instance.add(cat4);
        instance.add(cat5);
        assertEquals(5, instance.size());
        instance.add(cat6);
        assertEquals(6, instance.size());
    }
    @Test
    public void testCase9(){
        System.out.println("Add object to array with size 4");
        Cat cat1 = new Cat("Felix", 12);
        Cat cat2 = new Cat("Ib", 4);
        Cat cat3 = new Cat("Bailey", 8);
        MyArrayListWithBugs instance = new MyArrayListWithBugs();
        instance.add(cat1);
        instance.add(cat2);
        instance.add(1, cat3);
        
        assertEquals("Felix", instance.get(0).name);
        assertEquals("Bailey", instance.get(1).name);
        assertEquals(3, instance.size());
        //assertEquals("Ib", instance.get(3).name);
        
    }
    
}
