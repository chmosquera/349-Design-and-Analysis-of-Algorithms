import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

// This is a very simple graph class,
// May get a compiler error due to use of array of ArrayLists

class MyGraph_Main
{

    // testing the program
    public static void main(String[] args)throws FileNotFoundException {

        ArrayList<HashSet<Integer>> result = new ArrayList<HashSet<Integer>>();

        MyGraph g = new MyGraph();

        // graph 1
        g.MyGraph();

        try {
            g.readfile_graph("graph1.txt");
        }
        catch (FileNotFoundException e) {
        }

        g.print_graph();

        // connect check 1
        result = g.connectCheck();
        System.out.println("Connect Check");
        for (int i = 0; i < result.size(); i++) {
            Iterator<Integer> it = result.get(i).iterator();

            while (it.hasNext()) {
                System.out.print(it.next() + " ");
            }
            System.out.println();
        }
        System.out.println();

        // bipartite check 1
        System.out.println("Bipartite Check");
        System.out.println(g.bipartiteCheck());

        // graph 2
        g.MyGraph();
        try {
            g.readfile_graph("graph2.txt");
        }
        catch (FileNotFoundException e) {
        }
    
        g.print_graph();

        // connect check 2
        result = g.connectCheck();
        System.out.println("Connect Check");
        for (int i = 0; i < result.size(); i++) {
            Iterator<Integer> it = result.get(i).iterator();

            while (it.hasNext()) {
                System.out.print(it.next() + " ");
            }
            System.out.println();
        }
        System.out.println();

        // bipartite check 2
        System.out.println("Bipartite Check");
        System.out.println(g.bipartiteCheck());
    }
}
