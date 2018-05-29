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

class GraphStart_Main
{

    // testing the program
    public static void main(String[] args)throws FileNotFoundException {

        ArrayList<HashSet<Integer>> result = new ArrayList<HashSet<Integer>>();

        GraphStart g = new GraphStart();

        try {
            g.readfile_graph("graph1.txt");
        }
        catch (FileNotFoundException e) {
        }

        g.print_graph();
		g.print_indegree();

    }
}
