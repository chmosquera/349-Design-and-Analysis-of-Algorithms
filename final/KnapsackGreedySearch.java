import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;
import java.lang.Integer;

// CSC 349 Final Assignment Knapsack Problems - FULL ENUMERATION

class KnapsackGreedySearch
{

    public KnapsackGreedySearch() {}


    public static void MaxValuePerWeight(ArrayList<Integer> items, ArrayList<Integer> values, ArrayList<Integer> weights, int capacity) {
    
        int n = items.size();

        // generate subsets
        int[] subsets = new int[1<<n];      // contains all sets
        for (int i = 0; i < (1<<n); i++) {
            int set = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1<<j)) > 0) {
                    set = set | (i&(1<<j));
                }
            }
            subsets[i] = set;

        }

        // iterate through subsets - find solution to knapsack problem
        System.out.println("printing entire set");
        int value = 0,  weight = 0, set = 0;
        ArrayList<Integer> setOfItems = new ArrayList<Integer>();
        
        for (int i = 0; i < (1<<n); i++) {      // loop through subsets
            
            ArrayList<Integer> temp = new ArrayList<Integer>();
//            System.out.println(Integer.toBinaryString(subsets[i]));

            // get weight and value of set
            int v = 0, w = 0;
            
            for (int j=0; j < n; j++) {
                if ((subsets[i] & j) == j) {
                   // System.out.println("\t item " + (j + 1) + " is included");
//                    System.out.println("\t\t value: " + values.get(j) + " weight: " + weights.get(j));
                    v += values.get(j);
                    w += weights.get(j);
                    temp.add(j+1);          // item # is offset by 1
                }
            }

            if (w <= capacity && v >value) {    // check if new solution is found
//                System.out.println("New solution found! " + Integer.toBinaryString(i));
                value = v;
                weight = w;
                set = subsets[i];
                setOfItems = temp;
            }

//            System.out.println("\t\t TotalValue: " + v + " TotalWeight: " + w);
        }

        System.out.println("Using Brute force, the best feasible solution found: Value " + value + ", Weight " + weight);
        System.out.println(Arrays.toString(setOfItems.toArray()));

    }
}
