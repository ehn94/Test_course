/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triangle;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ehn19
 */
public class TriangleTest {

    @Test
    public void T1() {

        System.out.println("T1 Not a triangle");
        int a = 50;
        int b = 50;
        int c = 0;

        assertEquals(Triangle.isTriangle(a, b, c), "Not a triangle");
    }

    @Test
    public void T2() {

        System.out.println("T2 Not a triangle");
        int a = 50;
        int b = 50;
        int c = 100;

        assertEquals(Triangle.isTriangle(a, b, c), "Not a triangle");
    }

    @Test
    public void T3() {

        System.out.println("T3 Isosceles triangle");
        int a = 50;
        int b = 50;
        int c = 99;

        assertEquals(Triangle.isTriangle(a, b, c), "Isosceles triangle");
    }

    @Test
    public void T4() {

        System.out.println("T4 Equilateral triangle");
        int a = 50;
        int b = 50;
        int c = 50;

        assertEquals(Triangle.isTriangle(a, b, c), "Equilateral triangle");
    }

    @Test
    public void T5() {

        System.out.println("T5 Isosceles triangle");
        int a = 10;
        int b = 6;
        int c = 6;

        assertEquals(Triangle.isTriangle(a, b, c), "Isosceles triangle");
    }

    @Test
    public void T6() {

        System.out.println("T6 Isosceles triangle");
        int a = 50;
        int b = 50;
        int c = 1;

        assertEquals(Triangle.isTriangle(a, b, c), "Isosceles triangle");
    }

    @Test
    public void T7() {

        System.out.println("T7 Isosceles triangle");
        int a = 50;
        int b = 50;
        int c = 2;

        assertEquals(Triangle.isTriangle(a, b, c), "Isosceles triangle");
    }

    @Test
    public void T8() {

        System.out.println("T8 Isosceles triangle");
        int a = 50;
        int b = 1;
        int c = 50;

        assertEquals(Triangle.isTriangle(a, b, c), "Isosceles triangle");
    }

    @Test
    public void T9() {

        System.out.println("T9 Isosceles triangle");
        int a = 50;
        int b = 2;
        int c = 50;

        assertEquals(Triangle.isTriangle(a, b, c), "Isosceles triangle");
    }

    @Test
    public void T10() {

        System.out.println("T10 Isosceles triangle");
        int a = 1;
        int b = 50;
        int c = 50;

        assertEquals(Triangle.isTriangle(a, b, c), "Isosceles triangle");
    }

    @Test
    public void T11() {

        System.out.println("T11 Scalene triangle");
        int a = 50;
        int b = 49;
        int c = 51;

        assertEquals(Triangle.isTriangle(a, b, c), "Scalene triangle");
    }
}
