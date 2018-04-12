/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triangle;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ehn19
 */
public class Triangle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            System.out.println(isTriangle(a, b, c));
            //lesserThanOr(b);
        } catch (InputMismatchException ex) {
            System.out.println("Sorry, only integers allowed");
        }
    }
    
    public static void lesserThanOr(int b){
        
        if(Calendar.getInstance().get(Calendar.YEAR) - b < 18){
            System.out.println("The diff is: " + (Calendar.getInstance().get(Calendar.YEAR)-b));
            throw new IllegalArgumentException("Too young mate");
        }
        System.out.println("Old enough " + (Calendar.getInstance().get(Calendar.YEAR)-b));
    
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
