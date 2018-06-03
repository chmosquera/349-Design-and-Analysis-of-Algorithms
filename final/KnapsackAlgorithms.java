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

    public static void ExhaustiveMethod(ArrayList<Integer> items, ArrayList<Integer> values, ArrayList<Integer> weights, int capacity) {
        int n = items.size();

        System.out.println("(1<<n): " + (1<<n));
        // generate subsets
        int[] subsets = new int[1<<n];      // contains all sets
        for (int i = 0; i < (1<<n); i++) {
            //System.out.println("first for loop - i: " + i);
            

//            System.out.println("i: " + i);
            int temp = 0;

            for (int j = 0; j < n; j++) {
      //          System.out.println("\tj: " + j);
    //            System.out.println("\t\t1<<j: " + (1<<j));
  //              System.out.println("\t\ti&(1<<j): " + (i&(1<<j)));
//                System.out.println("temp: " + temp);
                    //System.out.println("j: " + j );
                if ((i & (1<<j)) > 0) {
//                    System.out.print(set[j] + " ");
                    //System.out.print("temp | j = temp: " + temp + "|" + j);
                    temp = temp | (i&(1<<j));
                  //  System.out.println(" = " + temp);
                    
                } 
            }
//            System.out.println("\t--> set: " + Integer.toBinaryString(temp));
  //          System.out.println("}");
            subsets[i] = temp;

        }

        // iterate through subsets - find weights and values of each
        System.out.println("printing entire set");
        int maxvalue = 0;
        int weight = 0;

        for (int i = 0; i < (1<<n); i++) {      // loop through subsets
            
            System.out.println(Integer.toBinaryString(subsets[i]));
            int v = 0;
            int w = 0;
            for (int j=0; j < n; j++) {
                if ((subsets[i] & j) == j) {
                    System.out.println("\t item " + (j + 1) + " is included");
                    System.out.println("\t\t value: " + values.get(j) + " weight: " + weights.get(j));
                    v += values.get(j);
                    w += weights.get(j);
                }
            }
            System.out.println("\t\t TotalValue: " + v + " TotalWeight: " + w);
        }

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

//        ExhaustiveMethod(items, values, weights);
        ArrayList<Integer> t_i = new ArrayList<Integer>();
        t_i.add(1); t_i.add(2); t_i.add(3); 
        ArrayList<Integer> t_v = new ArrayList<Integer>();
        t_v.add(4); t_v.add(5); t_v.add(5); 
        ArrayList<Integer> t_w = new ArrayList<Integer>();
        t_w.add(2); t_w.add(4); t_w.add(1);
        ExhaustiveMethod(t_i, t_v, t_w, 5);


        }
        catch(FileNotFoundException e) {
            System.out.println("Could not process file");
        }

    }


}
