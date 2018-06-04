import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;

// Assignment Week 5 - Rod Cutter

class KnapsackDynProg
{

    public KnapsackDynProg() {}

    public void Solve(int[] items, int[] values, int[] weights, int capacity) {
        int n = items.length;
        int[][] sub_solns = new int[n+1][capacity+1];      // table to store solutions - offset by 1 to include row/col of 0's
        Arrays.fill(sub_solns, 0);
        int tot_value = 0, tot_weight = 0;
        int[][] predecessors = new int[n][capacity];      // for rod of length i, cut piece at p[i]
        int[] ITEMS = new int[n+1];                     // prepend 0th index to arrays to include row/col of 0's
        int[] VALUES = Arrays.copyOf(values, n+1);
        int[] WEIGHTS = Arrays.copyOf(weights, n+1);
        System.arraycopy(items, 0, ITEMS, 1, n+1);
        System.arraycopy(values, 0, VALUES, 1, n+1);
        System.arraycopy(weights, 0, WEIGHTS, 1, n+1);

        // bottom up approach - recursively compute the max value found for smaller caps
        //      which will be used to computer max value for larger caps
        for (int c = 1; c < capacity; c++) {
            for (int i = 1; i < n; i++) {
                int v = 0;
                // can we pick up this item?
                if (WEIGHTS[i] < c) {
                    int w = c - WEIGHTS[i];     // remaining weight if item picked up

                    // should we pick up this item?
                    if (sub_solns[i-1][c] < (VALUES[i] + sub_solns[i-1][w])) {
                        sub_solns[i][c] = VALUES[i] + sub_solns[i-1][w];
                    } else {
                        sub_solns[i][c] = sub_solns[i-1][c];
                    }
                }
            }
        }

        // trace back
        ArrayList<Integer> soln = new ArrayList<Integer>();
        int i = n, c = capacity;        // start at last item & at capacity
        while (c > 0) {
            if (sub_solns[n][c] == sub_solns[n-1][c]) {     // i was not picked up
                
            } else {
                soln.add(i);
                tot_value += VALUES[i];
                tot_weight += WEIGHTS[i];
            }
        }
       
        System.out.println("Dynamic Programming solution: Value " + tot_value + ", Weight " + tot_weight);
        System.out.println(Arrays.toString(soln.toArray()));
        
    }
}
