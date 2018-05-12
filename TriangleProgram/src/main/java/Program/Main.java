/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program;

import static Program.Triangle.isTriangle;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Emilie
 */
public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            
            Triangle t1 = new Triangle(a, b, c);
            System.out.println(isTriangle(t1));
            //lesserThanOr(b);
        } catch (InputMismatchException ex) {
            System.out.println("Sorry, only integers allowed");
        }
    }
}
