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

class TopSorter_Main
{

    // testing the program
    public static void main(String[] args)throws FileNotFoundException {

        ArrayList<Integer> result = new ArrayList<Integer>();

        TopSorter ts = new TopSorter();

		// test case 
		String file = "graph1.txt";
        try {
            result = ts.topSortGenerator(file);
        }
        catch (FileNotFoundException e) {
			System.out.println("File Not Found");
        } 

												// print result
		System.out.println("test 1: " + file);
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
		System.out.println();
		System.out.println();

		// test case 
		file = "graph2.txt";
        try {
            result = ts.topSortGenerator(file);
        }
        catch (FileNotFoundException e) {
			System.out.println("File Not Found");
        } 

												// print result
		System.out.println("test 2: " + file);
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
		System.out.println();
		System.out.println();

		// test case 
		file = "hard.txt";
        try {
            result = ts.topSortGenerator(file);
        }
        catch (FileNotFoundException e) {
			System.out.println("File Not Found");
        } 

												// print result
		System.out.println("test 2: " + file);
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
		System.out.println();
		System.out.println();

		// test case 
		file = "Bfstest1.txt";
        try {
            result = ts.topSortGenerator(file);
        }
        catch (FileNotFoundException e) {
			System.out.println("File Not Found");
        } 

												// print result
		System.out.println("test 2: " + file);
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
		System.out.println();
		System.out.println();

		// test case 
		file = "Bfstest2.txt";
        try {
            result = ts.topSortGenerator(file);
        }
        catch (FileNotFoundException e) {
			System.out.println("File Not Found");
        } 

												// print result
		System.out.println("test 2: " + file);
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
		System.out.println();
		System.out.println();

		// test case 
		file = "Bfstest3.txt";
        try {
            result = ts.topSortGenerator(file);
        }
        catch (FileNotFoundException e) {
			System.out.println("File Not Found");
        } 

												// print result
		System.out.println("test 2: " + file);
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
		System.out.println();
		System.out.println();

		// test case 
		file = "Bfstest4.txt";
        try {
            result = ts.topSortGenerator(file);
        }
        catch (FileNotFoundException e) {
			System.out.println("File Not Found");
        } 

												// print result
		System.out.println("test 2: " + file);
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
		System.out.println();
		System.out.println();

    }
}
