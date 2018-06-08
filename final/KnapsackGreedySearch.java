import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.lang.Integer;

// CSC 349 Final Assignment Knapsack Problems - FULL ENUMERATION

class KnapsackGreedySearch
{

    public KnapsackGreedySearch() {}


    public static void MaxValuePerWeight(ArrayList<Integer> items, ArrayList<Integer> values, ArrayList<Integer> weights, int capacity) {
        //System.out.println("Greedy: Max Value Per Weight");
        int n = items.size();

        // get value to weight ratio of each object
        //System.out.println("item \t value \t weight \t value:weight");
        ArrayList<Float> valueToWeight = new ArrayList<Float>(n);
        for (int i = 0; i < n; i++) {
            float v2w = (float)values.get(i) / (float)weights.get(i);
            valueToWeight.add(v2w);

           // System.out.println((i+1) + " \t " + values.get(i) + " \t " + weights.get(i) + " \t " + valueToWeight.get(i));
        }

        // sort items in order of value to weight ratio
        // items_sorted contains the items in order of decr. value per weight
        ArrayList<Integer> items_sorted = new ArrayList<Integer>(items);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (valueToWeight.get(items_sorted.get(j)-1) < valueToWeight.get(items_sorted.get(j+1)-1)) {
                    int temp = items_sorted.get(j);
                    items_sorted.set(j, items_sorted.get(j+1));
                    items_sorted.set(j+1, temp);
                }
            }   
        }

        // Add items to knapsack: items with highest value per weight get added first (if it fits in knapsack)
        int rc = capacity;              // remaining capacity
        int value = 0,  weight = 0;     // sum of value & weights in knapsack
        ArrayList<Integer> solution = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            int item = items_sorted.get(i);
            if (rc <= 0) break;
            if (weights.get(item-1) <= rc) {        // item fits -- put in knapsack
                solution.add(item);
                value += values.get(item-1);
                weight += weights.get(item-1);
                rc -= weights.get(item-1);
            }
        }

        Collections.sort(solution);        // sort item no. by incr. order
        System.out.println("Greedy solution (not necessarily optimal): Value " + value + ", Weight " + weight);
        System.out.println(Arrays.toString(solution.toArray()));
    }

}
