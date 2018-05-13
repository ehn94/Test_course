/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Emilie
 */
public class Triangle {
    
    private int a;
    private int b;
    private int c;
    
    public Triangle(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public Triangle()
    {}

    public static void lesserThanOr(int b) {

        if (Calendar.getInstance().get(Calendar.YEAR) - b < 18) {
            System.out.println("The diff is: " + (Calendar.getInstance().get(Calendar.YEAR) - b));
            throw new IllegalArgumentException("Too young mate");
        }
        System.out.println("Old enough " + (Calendar.getInstance().get(Calendar.YEAR) - b));

    }
    
    public static String isTriangle(Triangle t1) {
        String res;
       
        //Ikke en trekant, hvis summen af to af siderne ikke er større end den tredje 
        if ((t1.a + t1.b) > t1.c && (t1.c + t1.a) > t1.b && (t1.b + t1.c) > t1.a) {
            //Ligesidet 
            if (t1.a == t1.b && t1.b == t1.c) {
                res = "Equilateral triangle";
            } //Ligebenet
            else if (t1.a == t1.b || t1.b == t1.c || t1.c == t1.a) {
                res = "Isosceles triangle";
            } //Forskellige længder
            else {
                res = "Scalene triangle";
            }
        } else {
            res = "Not a triangle";
        }
        return res;
    }

    public static String isTriangle(int a, int b, int c) {
        String res;
        //Ikke en trekant, hvis summen af to af siderne ikke er større end den tredje 
        if ((a + b) > c && (c + a) > b && (b + c) > a) {
            //Ligesidet 
            if (a == b && b == c) {
                res = "Equilateral triangle";
            } //Ligebenet
            else if (a == b || b == c || c == a) {
                res = "Isosceles triangle";
            } //Forskellige længder
            else {
                res = "Scalene triangle";
            }
        } else {
            res = "Not a triangle";
        }
        return res;
    }
}
