import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;
import java.lang.Integer;

// CSC 349 Final Assignment Knapsack Problems - FULL ENUMERATION

class KnapsackFullEnumeration
{

    public KnapsackFullEnumeration() {}


    public static void BruteForce(ArrayList<Integer> items, ArrayList<Integer> values, ArrayList<Integer> weights, int capacity) {
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
        int value = 0,  weight = 0, set = 0;
        ArrayList<Integer> setOfItems = new ArrayList<Integer>();
        
        for (int i = 0; i < (1<<n); i++) {      // loop through subsets
            // get weight and value of set
            ArrayList<Integer> temp = new ArrayList<Integer>();
            int v = 0, w = 0;
            
            for (int j=0; j < n; j++) {
                if ((subsets[i] & (1<<j)) > 0) {
                    v += values.get(j);
                    w += weights.get(j);
                    temp.add(j+1);          // item # is offset by 1
                }
            }

            if (w <= capacity && v >value) {    // check if new solution is found   
                value = v;
                weight = w;
                set = subsets[i];
                setOfItems = temp;
            }
        }

        System.out.println("Using Brute force, the best feasible solution found: Value " + value + ", Weight " + weight);
        System.out.println(Arrays.toString(setOfItems.toArray()));

    }
}
