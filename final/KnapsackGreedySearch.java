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
        System.out.println("Greedy: Max Value Per Weight");
        int n = items.size();

        // get value to weight ratio of each object
        System.out.println("item \t value \t weight \t value:weight");
        ArrayList<Float> valueToWeight = new ArrayList<Float>(n);
        for (int i = 0; i < n; i++) {
            float v2w = (float)values.get(i) / (float)weights.get(i);
            valueToWeight.add(v2w);

            System.out.println((i+1) + " \t " + values.get(i) + " \t " + weights.get(i) + " \t " + valueToWeight.get(i));
        }
        System.out.println();

        // sort items in order of value to weight ratio


    } 
}
