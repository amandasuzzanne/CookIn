package com.amanda;
import java.lang.Math;

public class QuadraticFormula {
    static void roots(double a, double b, double c) {
        double D = b*b - 4*a*c;
        if (D >=0 ){
            double x1 = (-b + Math.sqrt(D))/(2*a);
            double x2 = (-b - Math.sqrt(D))/(2*a);
            System.out.println("Roots are: "+ x1 + ", " + x2);
        }else {
            double x1 = -b/(2*a);
            double x2 = Math.sqrt(-D)/(2*a);
            System.out.println("Roots are: "+ x1 + "+" + x2 + "i");
        }
    }
}