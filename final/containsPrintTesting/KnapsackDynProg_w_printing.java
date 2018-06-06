import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;

// Final Assignment - Knapsack Algorithms - Dynamic Progrmaming

class KnapsackDynProg
{

    public KnapsackDynProg() {}

    public void Solve(ArrayList<Integer> items, ArrayList<Integer> values, ArrayList<Integer> weights, int capacity) {
        int n = items.size();
        int[][] sub_solns = new int[n+1][capacity+1];      // table to store solutions - offset by 1 to include row/col of 0's

        // fill array with 0;
        for (int[] row : sub_solns) {
            Arrays.fill(row, 0);
        }
        int tot_value = 0, tot_weight = 0;

        items.add(0,0);     // prepend 0 to get row/col of 0's
        values.add(0,0);
        weights.add(0,0);

        // bottom up approach - recursively compute the max value found for smaller caps
        //      which will be used to computer max value for larger caps
        for (int c = 1; c <= capacity; c++) {
            for (int i = 1; i <= n; i++) {
                // can we pick up this item?
                if (weights.get(i) <= c) {
                    int w = c - weights.get(i);     // remaining weight if item picked up
                    // should we pick up this item?
                    if (sub_solns[i-1][c] < (values.get(i) + sub_solns[i-1][w])) {
                        sub_solns[i][c] = values.get(i) + sub_solns[i-1][w];
                    } else {
                        sub_solns[i][c] = sub_solns[i-1][c];
                    }
                    //System.out.println("this item:\ti: " + i + "\tv: " + values.get(i) + "\tw: " + weights.get(i)  + "\tvalue thus far: " + sub_solns[i][c]);
                    //System.out.println("prev item:\ti: " + (i-1) + "\tv: " + values.get(i-1) + "\tw: " + weights.get(i-1)  + "\tvalue thus far: " + sub_solns[i-1][c]);
                } else {
                    sub_solns[i][c] = sub_solns[i-1][c];
                }
            }
        }

        // testing
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < capacity; j++) {
               // System.out.println("item: " + i + "\tcap: " + j + "\tvalue up to this point:" + sub_solns[i][j]);
            }
        }

        // trace back
        ArrayList<Integer> soln = new ArrayList<Integer>();
        int i = n, c = capacity;        // start at last item & at capacity
       // System.out.println("i = " + n + "\tc: " + capacity  + " last solution: " + sub_solns[n][c]);
        while (c > 0 && i > 0) {
            if (sub_solns[i][c] == sub_solns[i-1][c]) {     // i was not picked up
             i--;
            } else {
                soln.add(i);
                tot_value += values.get(i);
                tot_weight += weights.get(i);
                c-=weights.get(i);
                i--;
            }
        }
      
        System.out.println("Dynamic Programming solution: Value " + tot_value + ", Weight " + tot_weight);
        System.out.println(Arrays.toString(soln.toArray()));
        
    }
}
