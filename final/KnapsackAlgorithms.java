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

        if (args.length < 2) {
            System.out.println("Usage: KnapsackAlgorithms <file> <approach>");
            System.out.println("(approach: 1 - enum | 2 - greedy | 3 - dyn prog | 4 - b'n'b)");
            return;
        }

        int approachID = Integer.parseInt(args[1]);
        File file = new File(args[0]);

        try {

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

            // for testing smaller arrays
            ArrayList<Integer> t_i = new ArrayList<Integer>();
            t_i.add(1); t_i.add(2); t_i.add(3); t_i.add(4); t_i.add(4); 
           ArrayList<Integer> t_v = new ArrayList<Integer>();
            t_v.add(4); t_v.add(1); t_v.add(2); t_v.add(3); t_v.add(5);
            ArrayList<Integer> t_w = new ArrayList<Integer>();
            t_w.add(5); t_w.add(4); t_w.add(3); t_w.add(2); t_w.add(1);

            switch (approachID) {
                case 1:     // full enumeration
                    KnapsackFullEnumeration fullenum = new KnapsackFullEnumeration();
                    fullenum.BruteForce(items, values, weights, capacity);
                    break;
                case 2:     // greedy
                    KnapsackGreedySearch greedy = new KnapsackGreedySearch();
                    greedy.MaxValuePerWeight(items, values, weights, capacity);
                    break;
                case 3:     // dyn prog
                    KnapsackDynProg dynprog = new KnapsackDynProg();
                    dynprog.Solve(items, values, weights, capacity);
                    break;
                case 4:

                    KnapsackBnB bnb = new KnapsackBnB();
                    bnb.Solve(items, values, weights, capacity);
 //                   bnb.Solve(t_i, t_v, t_w, 5);
           }

        }
        catch(FileNotFoundException e) {
            System.out.println("Could not process file");
        }


//        ExhaustiveMethod(t_i, t_v, t_w, 6);
//


    }


}
