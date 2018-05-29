import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

class Inversions_Main
{

    // testing the program
    public static void main(String[] args) {

        Inversions Inv = new Inversions();
        
        int[] input = {6, 4, 3, 1};
        System.out.println("input: " + Arrays.toString(input));
        System.out.println("total inversions: " +  Inv.invCounter(input));
        System.out.println("result: " + Arrays.toString(input));
        System.out.println("");

        input = new int[] {2, 3, 8, 6, 1};
        System.out.println("input: " + Arrays.toString(input));
        System.out.println(" -------------------------- ");
        System.out.println("total inversions: " +  Inv.invCounter(input));
        System.out.println(" -------------------------- ");
        System.out.println("result: " + Arrays.toString(input));
        System.out.println();
    }

}
