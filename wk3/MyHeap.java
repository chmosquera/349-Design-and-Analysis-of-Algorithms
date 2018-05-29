import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// This is a very simple graph class,
// May get a compiler error due to use of array of ArrayLists

class MyHeap
{

    private int[] heap;
    private int capacity;
    private int size;

    public MyHeap() {
        this.capacity = 50;
        this.size = 0;
        heap = new int[this.capacity];
    }

    public MyHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        heap = new int[this.capacity];
    }

    // build heap bottom up
    public boolean buildHeap(int[] intarr) {

		// invalid - over heap capacity 
        if (intarr.length > this.capacity) {
            return false;
        } 

	// reset heap
	for (int i = 0; i < intarr.length; i++) {
		heap[i] = intarr[i];
	}
	size = intarr.length;

	// build heap bottom up
        for (int i = intarr.length/2; i >= 0; i--) {
			driftDown(i);
		}

        return true;
    }

    public boolean insert(int item) {

        // check if there's room to insert into heap
        if (this.size >= this.capacity) return false;

        heap[this.size] = item;     // insert item to end of heap
        driftUp(this.size);         // find correct position in heap for item 
        this.size++;
        return true;
    }

    public int findMin() {
        if(size > 0) {
            return heap[0];
   }

        return -1;
    }

    public int deleteMin() {
        int min;

		if (this.size <= 0) return -1;
        
        min = heap[0];                          // remove min
        heap[0] = heap[this.size-1];            // place last element into top of heap
		driftDown(0);							// drift down lsat element
		this.size--;

        return min;
    }

    public boolean isEmpty() {
		if (this.size == 0) return true;
		return false;
    }

    public boolean isFull() {
        if (this.size == this.capacity) return true;
        return false;
    }

    public void driftUp(int index) {
        int item = heap[index];         			// save item to insert
        int p_index = index/2;          			// index of parent

        // as long has a parent and is less than the parent, drift up
        while (index > 0 && item < heap[p_index]) {
            heap[index] = heap[p_index];            // swap locations of parent and item
            index = p_index;
            p_index = index/2;
        }
        heap[index] = item;                         // place item into correct spot
    }

    public void driftDown(int index) {
		int item = heap[index];						// save root of heap
		int smallerChild = -1;

		while (index < this.size/2) {				// while node has at least one child
			int l_child = 2 * index + 1;
			int r_child = l_child + 1;

			// compare item to it's children
			if (r_child < this.size && heap[r_child] < heap[l_child]) {	
				smallerChild = r_child;
			}
			else {
				smallerChild = l_child;
			}

			// item is in right spot
			if (item < heap[smallerChild]) break;	

			// drift down
			heap[index] = heap[smallerChild];
			index = smallerChild;
			
		}
		heap[index] = item;
    }

    public int getHeapCap() {
		return this.capacity;
    }

    public int getHeapSize() {
		return this.size;
    }

    public int[] heapSortDecreasing(int[] intArr) {
		buildHeap(intArr);
		int[] result = new int[intArr.length];

		int min; 
		for (int i = intArr.length-1; i >= 0; i--) {
			min = deleteMin();						
			result[i] = min;
		}
		
		return result;
    }


	public void print() {
		System.out.println("Printing heap ... ");
		for (int i = 0; i < size; i++) {
			System.out.print(heap[i] + " " );
		}
		System.out.println();
	}

}
