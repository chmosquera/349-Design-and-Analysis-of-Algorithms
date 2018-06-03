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

    public static void main(String args[]) {


        try {

            File file = new File("easy20.txt");
            Scanner sc = new Scanner(file);
            int item_cnt = sc.nextInt();            // n

            ArrayList<Integer> items = new ArrayList<Integer>(item_cnt);
            ArrayList<Integer> values = new ArrayList<Integer>(item_cnt);
            ArrayList<Integer> weights = new ArrayList<Integer>(item_cnt);

            for (int i = 0; i < item_cnt; i++) {    
                items.add(sc.nextInt());            // items
                values.add(sc.nextInt());           // values
                weights.add(sc.nextInt());          // weights
            }
            int capacity = sc.nextInt();            // capacity

          /*  System.out.println(item_cnt);
            for(int i = 0; i < item_cnt; i++) {
                System.out.println(items.get(i) + "\t" +  values.get(i) + "\t" +  weights.get(i)); 
            }
            System.out.println(capacity);
        */
        
            // exhaustive
            KnapsackFullEnumeration method1 = new KnapsackFullEnumeration();
            method1.BruteForce(items, values, weights, capacity);
            // Greedy
            KnapsackGreedySearch method2 = new KnapsackGreedySearch();
            method2.MaxValuePerWeight(items, values, weights, capacity);

        }
        catch(FileNotFoundException e) {
            System.out.println("Could not process file");
        }


        ArrayList<Integer> t_i = new ArrayList<Integer>();
        t_i.add(1); t_i.add(2); t_i.add(3); 
        ArrayList<Integer> t_v = new ArrayList<Integer>();
        t_v.add(4); t_v.add(5); t_v.add(5); 
        ArrayList<Integer> t_w = new ArrayList<Integer>();
        t_w.add(2); t_w.add(4); t_w.add(1);
//        ExhaustiveMethod(t_i, t_v, t_w, 6);
//


    }


}
