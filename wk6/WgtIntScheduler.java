import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;
import java.util.HashMap;
import java.util.Map;

// Assignment Week 5 - Rod Cutter

class WgtIntScheduler
{

    public WgtIntScheduler() {}

    public int[] getOptSet(int[] stime, int[]ftime, int[]weight) {
        int[] sub_solns = new int[ftime.length];        // max value for each subproblem
        int[] predecessor = new int[ftime.length];     // the max value obtained at job i is

        /* ----- SETUP FOR DYNAMIC PROGRAMMING ---- */
        // fill array with ID numbers
        int[] sortedID = new int[ftime.length];
        for (int i = 0; i < sortedID.length; i++) {
            sortedID[i] = i+1;
        }

        // sort by incr finish times 
        // keep IDs matched to their finish times
        // * note: id numbers start from 1 to n. So when indexing, must offset by -1
        for (int i = 0; i < ftime.length-1; i++) {
            for (int j = 0; j < ftime.length-i-1; j++) {
                if (ftime[j] > ftime[j+1]) {
                    int temp = sortedID[j];
                    sortedID[j] = sortedID[j+1];      // swap the ID numbers too.. add 1 offset
                    sortedID[j+1] = temp;
                }
            }   
        }
 
        
        /* beging algorithm to find compatble set of jobs that gives max value */
        Arrays.fill(sub_solns, 0);
        Arrays.fill(predecessor, 0);
        int lastjob = 0;                    // last job known to give us highest value
        int total_max_value = 0;            // highest max value given by a compatible set 
        for (int i = 0; i < sortedID.length; i++) {
            int max_value = 0;
            //System.out.println("weight of job " + sortedID[i] + " is " + weight[sortedID[i]-1]);
            for (int j = i; j >= 0; j--) {
                if (sortedID[i] == sortedID[j]) {                            // job i is job j - so they're "sompatible"
                    max_value = weight[sortedID[i]-1];
                    // predecessor stays 0 -- don't update
                }
                else if (stime[sortedID[i]-1] >= ftime[sortedID[j]-1]) {      // job i is not job j, so check if jobs are compatible
                    if (max_value < (weight[sortedID[i]-1] + sub_solns[sortedID[j]-1])) {       // check if we found a new max value
                        max_value = weight[sortedID[i]-1] + sub_solns[sortedID[j]-1];
                        predecessor[sortedID[i]-1] = sortedID[j];
                    }

                }
            }

            sub_solns[sortedID[i]-1] = max_value;

            // update total max value
            if (max_value > total_max_value) {
                total_max_value = max_value;
                lastjob = sortedID[i];
            }
        }

        // TRACE BACK - which compatible jobs give us the maximum weight?
       ArrayList<Integer> idnumbers_list = new ArrayList<Integer>();

        int i = lastjob;
        while (i != 0) {
            idnumbers_list.add(i);
            i = predecessor[i-1];
        }

        // put result into an array and sort in incr order
        int[] idnumbers = new int[idnumbers_list.size()];
        for (i = 0; i < idnumbers_list.size(); i++) {
            idnumbers[i] = idnumbers_list.get(i);
        }
        Arrays.sort(idnumbers);        // sort

        return idnumbers;
        
    }



}
