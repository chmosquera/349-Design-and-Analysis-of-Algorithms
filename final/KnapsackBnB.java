import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;

// Final Assignment - Knapsack Algorithms - Dynamic Progrmaming

class KnapsackBnB
{

    public class Node implements Comparable<Node>{

        int lvl, totval, totwgt;
        float bound, valToWgt;
        ArrayList<Integer> itemsCollected;

        Node() {
            this.lvl = 0;					// ID in tree
            this.totval = 0;			                // total value
            this.totwgt = 0;
            this.bound = 0;
            this.itemsCollected = new ArrayList<Integer>();
        }

        public int compareTo(Node o) {
            if (this.valToWgt > o.valToWgt) return -1;
            else if (this.valToWgt < o.valToWgt) return 1;
            return 0;
        }
    
        void Print() {
            System.out.println("\t\tTree Level: \t" + lvl);
            System.out.println("\t\tTotal Value: \t" + totval);
            System.out.println("\t\tTotal Weight: \t" + totwgt);
            System.out.println("\t\tUpper Bound: \t" + bound);
            System.out.println();
        }
    }

    public class Item implements Comparable<Item>{
        
        int id, val, wgt;
        float v2w;

        Item(int id, int val, int wgt, float v2w) {
            this.id = id;
            this.val = val;
            this.wgt = wgt;
            this.v2w = v2w;
        }

        public int compareTo(Item o) {
            if (this.v2w > o.v2w) return -1;
            else if (this.v2w < o.v2w) return 1;
            return 0;
        }
        void Print() {
            System.out.println("Item: " + id + "\tval: " + val + "\twgt: " + wgt + "\tv2w: " + v2w);
        }
    }

    public KnapsackBnB() {}

    public void Solve(ArrayList<Integer> items, ArrayList<Integer> values, ArrayList<Integer> weights, int capacity) {
        // timer
        long start = System.currentTimeMillis();
        long end = start + 60*10*1000;         // after 5 minutes

        int n = items.size(); 

        // setup all items into node
        ArrayList<Item> itemsList = new ArrayList<Item>();
        for (int i = 0; i < n; i++) {
            float v2w = (float)values.get(i)/(float)weights.get(i);
            Item it = new Item(items.get(i), values.get(i), weights.get(i), v2w);
            itemsList.add(it);
        }

        Collections.sort(itemsList);

        // setup
        Node solution = new Node();
        Node root = new Node();
        root.lvl = -1;
        root.bound = capacity * (itemsList.get(0)).v2w;
        Node curState = new Node();

        PriorityQueue<Node> Q = new PriorityQueue<Node>();
        Q.add(root);
        int maxValue = 0;           // max value in knapsack found so far

        while ((curState = Q.poll()) != null) {    // are there more states in the tree to examine?
            int treeLvl = curState.lvl;               // current level in tree

            treeLvl++;
            if (treeLvl >= n-1) {                     // are there more items we can pick up?
                continue;
            }

            // go to the next level (contains the next best val to wgt ratio)
            int itemId = (itemsList.get(curState.lvl + 1)).id - 1;     // # of item we might pick up

            Node inState = new Node();
            Node exState = new Node();
            inState.lvl = exState.lvl = treeLvl;

            Item thisItem = itemsList.get(treeLvl);
            
            inState.totval = curState.totval + thisItem.val;                // state includes item
            inState.totwgt = curState.totwgt + thisItem.wgt;
            inState.bound = inState.totval + ((capacity - inState.totwgt) * (itemsList.get(treeLvl+1)).v2w);
            inState.itemsCollected = new ArrayList<Integer>(curState.itemsCollected);

            exState.totval = curState.totval;                               // state excludes item
            exState.totwgt = curState.totwgt;
            exState.bound = curState.totval + ((capacity - exState.totwgt) * (itemsList.get(treeLvl+1)).v2w); 
            exState.itemsCollected = new ArrayList<Integer>(curState.itemsCollected);

            // if item fits, add to knapsack
            if (inState.totwgt <= capacity) {
                inState.itemsCollected.add(itemId + 1);

                // update solution if this state gives more optimal solution
                if (inState.totval > solution.totval){  
                    solution = inState;
                }
            }

            // Decide whether it is worth traversing down these states further
            if (inState.bound > solution.totval) {
                Q.add(inState);
            }
            if (exState.bound > solution.totval) {
                Q.add(exState);
            }

            // timer so algorithm doesn't run forever
//            System.out.println(System.currentTimeMillis());
            if (System.currentTimeMillis() > end) break;
        }

        Collections.sort(solution.itemsCollected);        // sort item no. by incr. order
        System.out.println("Using Branch and Bound the best feasible solution found: Value " + solution.totval + ", Weight " + solution.totwgt);
        System.out.println(" as specified for the other approaches. ");
        System.out.println(Arrays.toString(solution.itemsCollected.toArray()));

    }
}
