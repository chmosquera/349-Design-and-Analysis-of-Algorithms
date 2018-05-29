import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

// This is a very simple graph class,
// May get a compiler error due to use of array of ArrayLists

class MyHeap_Main
{

/* 	this goes in MyHeap.java
	public void print() {
		System.out.println("Printing heap ... ");
		for (int i = 0; i < size; i++) {
			System.out.print(heap[i] + " " );
		}
		System.out.println();
	}
*/

	public static void main(String[] args) {
		MyHeap myheap = new MyHeap();
		int[] result;

		myheap.insert(9);
		myheap.insert(1);
		myheap.insert(5);
		myheap.insert(10);
		myheap.print();
		myheap.deleteMin();
		myheap.print();

		// test build heap
		System.out.println("test buildheap:");
		int[] a1 = {5, 9, 3, 2, 7, 4};
		myheap.buildHeap(a1);
		myheap.insert(1);
		myheap.insert(8);
		myheap.print();

		// test heapsort	
		System.out.println("test heapsort:");		
		result = myheap.heapSortDecreasing(a1);
		myheap.print();
		System.out.println("After heap's been sorted ... ");
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " " );
		}
		System.out.println();

		// test heap too big
		System.out.println();
		System.out.println("test heap to big:");
		myheap = new MyHeap(7);
		//System.out.println(myheap.buildHeap(a1));
		System.out.println(myheap.insert(1));
		System.out.println(myheap.findMin());
		System.out.println(myheap.isFull());
	}
}
