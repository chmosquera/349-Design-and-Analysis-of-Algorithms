import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;

// Assignment Week 5 - Rod Cutter

class RodCutter
{

    public RodCutter() {}

    public static int MSTP(int[] prices, int n) {
        int[] sub_solns = new int[n + 1];      // table to store solutions (including 0)
        Arrays.fill(sub_solns, -1);
        int max_value = -1;
        //ArrayList<int> predecessors = new ArrayList<int>();
        int[] predecessors = new int[n+1];      // for rod of length i, cut piece at p[i]

        // bottom up approach - recursively computer smaller rods first
        //      which will be used to computer solutions for larger rods
        //
        sub_solns[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            max_value = -1;
            for (int j = 1; j <= i; j++) {
                if (max_value < (prices[j-1] + sub_solns[i-j])) {       // offsetting prices because prices starts at length 1, but units start at 0
                    predecessors[i] = j;
                    max_value = prices[j-1] + sub_solns[i-j];
                }
            }
            sub_solns[i] = max_value;
        }


        /* testing print rods
        System.out.println("MSTP(" + n + ")");
        for (i = 0; i < predecessors.length; i++) {
            System.out.println("predecessors[" + i + "]: " + predecessors[i]); 
        } */

        for (int i = 1; i < sub_solns.length; i++) {
            System.out.println("total for length " + (i) + "\t\t = " + sub_solns[i]);
        } 

        System.out.println("Optimal rod cutting");

        // print optimal rod lengths to cut
        int i = n;
        int[] num_rods_of_length = new int [n];
        Arrays.fill(num_rods_of_length, 0);

        while (i > 0) {
            num_rods_of_length[predecessors[i]]++;      // rod was cut into this size
            i = i - predecessors[i];
        }

        for (i = 0; i < num_rods_of_length.length; i++) {
            if (num_rods_of_length[i] != 0) {
                System.out.println("Number of rods of length " + i + "   = " + num_rods_of_length[i]);
            }
        }

        return max_value;
    }

    public static void main(String args[]) {

        File file = new File("rodOptTest.txt");

        try {
            int num_testcases = 0;
            int length = 0;
            int[] prices;
            Scanner sc = new Scanner(file);

            num_testcases = sc.nextInt();                   // get number of test cases

            for (int i = 0; i < num_testcases; i++) {       // loop thru testcases
                System.out.println("Case: " + (i+1));
                length = sc.nextInt();
                prices = new int[length];
                for (int j = 0; j < length; j++) {          // fill array of prices
                    prices[j] = sc.nextInt();
                   // System.out.println("total for length: " + (j+1) + "\t\t = " + prices[j]);
                }

                int max_value = MSTP(prices, length);
                System.out.println();
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Could not process file");
        }
    }


}
