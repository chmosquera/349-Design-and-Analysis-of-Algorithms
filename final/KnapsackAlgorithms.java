import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;
import java.lang.Integer;

// CSC 349 Final Assignment Knapsack Problems

class KnapsackAlgorithms
{

    public KnapsackAlgorithms() {}

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

    public static void main(String args[]) {

        if (args.length < 2) {
            System.out.println("Usage: KnapsackAlgorithms <filename> <approach>");
            System.out.println("(approach: 1 - brute force | 2 - greedy | 3 - dynamic programming | 4 - branch and bound)");
            return;
        }

        int approachID = Integer.parseInt(args[1]);
        File file = new File(args[0]);

        try {

            Scanner sc = new Scanner(file);
            int item_cnt = sc.nextInt();

            ArrayList<Integer> items = new ArrayList<Integer>(item_cnt);
            ArrayList<Integer> values = new ArrayList<Integer>(item_cnt);
            ArrayList<Integer> weights = new ArrayList<Integer>(item_cnt);

            for (int i = 0; i < item_cnt; i++) {
                items.add(sc.nextInt());
                values.add(sc.nextInt());
                weights.add(sc.nextInt());
            }

            int capacity = sc.nextInt();

            System.out.println(item_cnt);
            for(int i = 0; i < item_cnt; i++) {
                System.out.println(items.get(i) + "\t" +  values.get(i) + "\t" +  weights.get(i)); 
            }
            System.out.println(capacity);


        KnapsackFullEnumeration method1 = new KnapsackFullEnumeration();
        method1.BruteForce(items, values, weights, capacity);
        ArrayList<Integer> t_i = new ArrayList<Integer>();
        t_i.add(1); t_i.add(2); t_i.add(3); 
        ArrayList<Integer> t_v = new ArrayList<Integer>();
        t_v.add(4); t_v.add(5); t_v.add(5); 
        ArrayList<Integer> t_w = new ArrayList<Integer>();
        t_w.add(2); t_w.add(4); t_w.add(1);
//        ExhaustiveMethod(t_i, t_v, t_w, 6);


        }
        catch(FileNotFoundException e) {
            System.out.println("Could not process file");
        }

    }


}
