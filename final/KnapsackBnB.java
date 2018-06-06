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

        Node() {
            this.lvl = 0;					// ID in tree
            this.totval = 0;			                // total value
            this.totwgt = 0;
            this.bound = 0;
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
        int n = items.size(); 

        /* TESTING 
        System.out.println("Before sort ...");
        System.out.println("items:\t" + Arrays.toString(items.toArray()));
        System.out.println("v to w:\t: " + Arrays.toString(valToWgt.toArray()));
        System.out.println("After sort ...");
        System.out.println("items:\t" + Arrays.toString(items_sorted.toArray()));
        System.out.print("v to w:\t{");
        for (int i = 0; i < n; i++) {
//            System.out.print("i = " + i);
            //System.out.println("items_sorted.get(i) = " + items_sorted.get(i));
            System.out.print(valToWgt.get(items.get(items_sorted.get(i)-1)-1) + ", ");
        }
        System.out.println("}");
        */

        /* testing priority 1
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        Node nk = new Node(); nk.valToWgt = 0.9f;
        q.add(nk);
        Node nl = new Node(); nl.valToWgt = 4.5f;
        q.add(nl);
        Node nd = new Node(); nd.valToWgt = 3.5f;
        q.add(nd); 

        for (int i = 0; i < 3; i++) {
            Node qw = q.poll();
            System.out.println(qw.valToWgt);
        } */

        
        // setup all items into node
        System.out.println("List of items:");
        ArrayList<Item> itemsList = new ArrayList<Item>();
        for (int i = 0; i < n; i++) {
            float v2w = (float)values.get(i)/(float)weights.get(i);
            Item it = new Item(items.get(i), values.get(i), weights.get(i), v2w);
            System.out.println("items id = : " + it.id);
            itemsList.add(it);
            it.Print();
        }

        Collections.sort(itemsList);

        // Sorting
        System.out.println("List of items (sorted): ");
        for (int i = 0; i < n; i++) {
            (itemsList.get(i)).Print();
        }
        

        // setup
        Node root = new Node();
        root.lvl = -1;
        root.bound = capacity * (itemsList.get(0)).v2w;
        Node curState = new Node();

        PriorityQueue<Node> Q = new PriorityQueue<Node>();
        Q.add(root);
        int maxValue = 0;           // max value in knapsack found so far

        while ((curState = Q.poll()) != null) {    // are there more states in the tree to examine?
            int treeLvl = curState.lvl;               // current level in tree

            if (treeLvl >= n-1) {                     // are there more items we can pick up?
                continue;
            }

            // go to the next level (contains the next best val to wgt ratio)
            treeLvl++;
            System.out.println("Level: " + treeLvl);
            int itemId = (itemsList.get(curState.lvl + 1)).id - 1;     // offset by 1 bc item id's start at 1
            System.out.println("\tNext Level: " + treeLvl  + "\tMaxValue: " + maxValue);       // Test

            Node inState = new Node();
            Node exState = new Node();
            inState.lvl = exState.lvl = treeLvl;

            Item thisItem = itemsList.get(itemId);
            thisItem.Print();
            System.out.println("\tcurState: ");
            curState.Print();
            
            inState.totval = curState.totval + thisItem.val;                // state includes item
            inState.totwgt = curState.totwgt + thisItem.wgt;
            inState.bound = inState.totval + ((capacity - inState.totwgt) * (itemsList.get(treeLvl+1)).v2w);

            System.out.print("\tinState: ");
            inState.Print();

            exState.totval = curState.totval;                               // state excludes item
            exState.totwgt = curState.totwgt;
            exState.bound = curState.totval + ((capacity - exState.totwgt) * (itemsList.get(treeLvl+1)).v2w);
            
            System.out.println("\texState: " );
            exState.Print();

            //Update Maxvalue if this node is valid and increases maxValue
            if (inState.totval > maxValue && inState.totwgt <= capacity) {
                 maxValue = inState.totval;
            }
            System.out.println("\tUpdated Max value: " + maxValue);

            // Decide whether it is worth traversing down these states further
            if (inState.bound > maxValue) {
                Q.add(inState);
            }
            if (exState.bound > maxValue) {
                Q.add(exState);
            }
        }
        /*
        // setup
        Node root = new Node();                 // setup starting point of tree
        root.itemlvl = -1;
        root.bound = capacity * valToWgt.get(items.get(items_sorted.get(0)-1)-1);   // offset to get index of item
        Node curState = new Node();

        PriorityQueue<Node> Q = new PriorityQueue<Node>();
        Q.add(root);
        int maxValue = 0;           // max value in knapsack found so far

        
        while ((curState = Q.poll()) != null) {    // are there more states in the tree to examine?
            //System.out.println("QQQQQ:" +  Arrays.toString(Q.toArray()));
            
            int level = curState.itemlvl;       // level in tree
            if (level >= n-1) {       // are there more items to pick up to?
                System.out.println("max level has been reached at lvl: " + level);
                continue;
            }

            level = curState.itemlvl;
            System.out.println("Level: " + curState.itemlvl + "\tNext Level: " + (curState.itemlvl+1) + "\tMaxValue: " + maxValue);       // Test
            // check out the next level of tree && examine options
            //level++;
            Node inState = new Node();
            Node exState = new Node();
            inState.itemlvl = exState.itemlvl = level;

           // System.out.println("totwgt: " + curState.totwgt + " wgt: " + weights.get(level));
            System.out.println("\tcurState: ");
            curState.Print();
                
            inState.totval = curState.totval + values.get(level);       // include state
            inState.totwgt = curState.totwgt + weights.get(level);
            inState.bound = inState.totval + ((capacity - inState.totwgt) * valToWgt.get(items.get(items_sorted.get(level+1)-1)-1));

            inState.valToWgt = valToWgt.get(level);
            
            System.out.println("\tinState: " + "\ti: " + items.get(level) + "\tv: " + values.get(level) + "\tw: " + weights.get(level) + "\tv2w: " + valToWgt.get(level));
            inState.Print();


            exState.totval = curState.totval;                       // exclude state
            exState.totwgt = curState.totwgt;
            exState.bound = exState.totval + ((capacity - exState.totwgt) * valToWgt.get(items.get(items_sorted.get(level+1)-1)-1));
            exState.valToWgt = valToWgt.get(level);
            
            System.out.println("\texState: " + "\ti: " + items.get(level) + "\tv: " + values.get(level) + "\tw: " + weights.get(level) + "\tv2w: " + valToWgt.get(level));
            exState.Print();
            
            //Update Maxvalue if this node is valid and increases maxValue
            if (inState.totval > maxValue && inState.totwgt <= capacity) {
                 maxValue = inState.totval;
            }
            System.out.println("\tUpdated Max value: " + maxValue);

            // Decide whether it is worth traversing down these states further
            if (inState.bound > maxValue) {
                Q.add(inState);
            }
            if (exState.bound > maxValue) {
                Q.add(exState);
            }
        }
*/
    }
}
