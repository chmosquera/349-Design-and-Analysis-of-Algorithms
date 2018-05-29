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

class WgtIntScheduler_Demo
{

    public WgtIntScheduler_Demo() {}

    public int[] getOptSet(int[] stime, int[]ftime, int[]weight) {
        int[] sub_solns = new int[ftime.length];        // max value for each subproblem
        int[] predecessor = new int[ftime.length];     // the max value obtained at job i is

        /* ----- SETUP FOR DYNAMIC PROGRAMMING ---- */
        // fill array with ID numbers
        int[] sortedID = new int[ftime.length];
        for (int i = 0; i < sortedID.length; i++) {
            sortedID[i] = i+1;
        }

        System.out.println("Before sort: ");
        System.out.println("jobID: " + Arrays.toString(sortedID));
        System.out.println("ftime: " + Arrays.toString(ftime));
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
 
        
        System.out.println("After sort: ");
        System.out.println("jobID: " + Arrays.toString(sortedID));
        System.out.println("ftime: " + Arrays.toString(ftime));

        /* ------ DYNAMIC PROGRAMMING ------ */
        // Loop through jobs in sorted order - decide to do the job or not
        /*
        sub_solns[0] = 0;
        for (int i = 0; i < sortedID.length; i++) {
            System.out.println("\nShould we do job " + sortedID[i] + "? Let's check the values we get...");
            int max_value = 0;
            System.out.println("weight of job " + sortedID[i] + " is " + weight[sortedID[i]-1]);
            for (int j = 0; j <= i; j++) {
                //System.out.println("Previous job " + sortedID[j] + " has a max value of " + sub_solns[sortedID[j]-1]);
                if (sortedID[i] == sortedID[j]) {

                }
                if (sortedID[i] == sortedID[j] || ftime[sortedID[i]-1] <= stime[sortedID[j]-1] || stime[sortedID[i]-1] >= ftime[sortedID[j]-1]) {
                    System.out.println("Compatible: job " + sortedID[i] + "'s value plus max_value from previous job " + sortedID[j] + " is " + (weight[sortedID[i]-1] + sub_solns[sortedID[j]-1]));
                    if (max_value < weight[sortedID[i]-1] + sub_solns[sortedID[j]-1]) {    // if updating the max value, 
                        max_value = weight[sortedID[i]-1] + sub_solns[sortedID[j] -1];
                        predecessors[sortedID[i]-1] = sortedID[j];
                        System.out.println("Update the predecessor.. job " + sortedID[i] + " got its value from job " + sortedID[j]);
                    }
                } else System.out.println("job " + sortedID[i] + " is not compatible with job " + sortedID[j]);
            }
            System.out.print("max value of job " + sortedID[i] + " is " + max_value);
            sub_solns[sortedID[i]-1] = max_value;
            System.out.println(", job " + sortedID[i] + " got this value from job " + predecessors[sortedID[i]-1]);
        } */



            /* 
            Arrays.fill(predecessor, 0);
            for each job, the max value is max(the max value attained at the previous ftime, or the value of this job + the values of compatible jobs)
                // at job i's finish time, is job i compatible with jobs that have already finished? 
                for job j = i, j > 0, j--
                    // check compatibility
                    if job i is job j
                        max value we've found so far is job i's value
                    else if job i's start time >= job j's finish time, they are compatible
                        and if the max value is less 
                        max value = value of job  + value of job i and the value of its compatible set
                        predecessor of job i is job j

            sub_soln[sortedID[i]-1] = max_value
            */

        Arrays.fill(sub_solns, 0);
        Arrays.fill(predecessor, 0);
        int lastjob = 0;                    // last job known to give us highest value
        int total_max_value = 0;            // highest max value given by a compatible set 
        for (int i = 0; i < sortedID.length; i++) {
            System.out.println("\nShould we do job " + sortedID[i] + "? Let's check the values we get...");
            int max_value = 0;
            //System.out.println("weight of job " + sortedID[i] + " is " + weight[sortedID[i]-1]);
            for (int j = i; j >= 0; j--) {
                System.out.println("Previous job " + sortedID[j] + " has a max value of " + sub_solns[sortedID[j]-1]);
                System.out.println("start time of job " + sortedID[i] + " is " + stime[sortedID[i]-1] + ", and the finish time of job " + sortedID[j] + " is " + ftime[sortedID[j]-1]);
                if (sortedID[i] == sortedID[j]) {                            // job i is job j - so they're "sompatible"
                    max_value = weight[sortedID[i]-1];
                    // predecessor stays 0 -- don't update
                    System.out.println("The value of itself is " + max_value);
                }
                else if (stime[sortedID[i]-1] >= ftime[sortedID[j]-1]) {      // job i is not job j, so check if jobs are compatible
                    System.out.println("job " + sortedID[i] + " is compatible with job " + sortedID[j]);
                    if (max_value < (weight[sortedID[i]-1] + sub_solns[sortedID[j]-1])) {       // check if we found a new max value
                        max_value = weight[sortedID[i]-1] + sub_solns[sortedID[j]-1];
                        System.out.println("new max value is found, " + max_value);
                        predecessor[sortedID[i]-1] = sortedID[j];
                        System.out.println("predecssor of job " + sortedID[i]+  " is job " + sortedID[j]);
                    }

                }
            }

            System.out.println("--> max value of job " + sortedID[i] + " is " + max_value);
            System.out.println("--> predecessor of job " + sortedID[i] + " is " + predecessor[sortedID[i]-1]);
            sub_solns[sortedID[i]-1] = max_value;

            // update total max value
            if (max_value > total_max_value) {
                total_max_value = max_value;
                lastjob = sortedID[i];
            }
            System.out.println("--> total_max_value is " + total_max_value);
            System.out.println("--> last job is " + lastjob);
        }

        // TRACE BACK - which compatible jobs give us the maximum weight?
        //int[] idnumbers = new int[sortedID.length];        // id of job
       // int i = idnumbers.length;
       ArrayList<Integer> idnumbers_list = new ArrayList<Integer>();

        for (int i = lastjob; i < predecessor.length; i++) {
         
        }
        
        int i = lastjob;
        while (i != 0) {
            System.out.println("job " + i + " has predecessor job " + predecessor[i-1]);
            idnumbers_list.add(i);
            i = predecessor[i-1];
        }

        int[] idnumbers = new int[idnumbers_list.size()];
        for (i = 0; i < idnumbers_list.size(); i++) {
            idnumbers[i] = idnumbers_list.get(i);
        }
        Arrays.sort(idnumbers);        // sort results incr

        return idnumbers;
        
    }



}
