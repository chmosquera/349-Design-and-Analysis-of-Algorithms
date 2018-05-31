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


        }
        catch(FileNotFoundException e) {
            System.out.println("Could not process file");
        }

    }



}
