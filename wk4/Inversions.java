import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

// Assignment Week 4 - Counting Inversions

class Inversions
{

    public void Inversions() {}


    public static int invCounter(int[] ranking) {
        int cnt = 0;

        int temp[] = new int[ranking.length];                   // will contain sorted ranking
        cnt = mergeSort(ranking, temp, 0, ranking.length-1);

        return cnt;
    } 

    public static int mergeSort(int[] A, int[] temp, int left, int right) {
        int cnt = 0;

        if (right > left) {
        // Divide 
        int mid = (right+left)/2;           // mid index

        // Conquer
        cnt += mergeSort(A,temp, left, mid);          // left half
        cnt += mergeSort(A,temp, mid+1, right);       // right half

        // Merge
        cnt += merge(A, temp,left, right, mid+1);
        }

        return cnt;

    }

    public static int merge(int[] A, int[] temp, int left, int right, int mid) {

        int cnt = 0;
        
        int i = left, j = mid, k = left;
        while ((i <= mid-1) && (j <= right)) {
            if (A[i] <= A[j]) {             // order is correct 
                temp[k] = A[i];
                k++; i++;
            } else {                        // correct spot found - fix order
                temp[k] = A[j];             // perform mid - i swaps to get value into right spot
                k++; j++;
                cnt = cnt + (mid - i);  
            }
        }

        while (i <= mid-1) {                // Append rest of remaining subarray to result;
            temp[k] = A[i];
            k++; i++;
        }
        while (j <= right) {
            temp[k] = A[j];
            k++; j++;
        }
                                            // copy results back to original array
        for (int a = left; a <= right; a++) {
            A[a] = temp[a];
        }

        return cnt;
    }





}
