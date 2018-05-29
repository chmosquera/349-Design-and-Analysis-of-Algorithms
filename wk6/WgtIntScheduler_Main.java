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

class WgtIntScheduler_Main
{


    public static void main(String args[]) {

    int[] stime = {4, 3, 2, 10, 7};
    int[] ftime = {7, 10, 6, 13, 9};
    int[] weight = {6, 6, 5, 2, 8};

    WgtIntScheduler s = new WgtIntScheduler();

    int result[] = s.getOptSet(stime, ftime, weight);
       
        System.out.println("RESUTLS");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }


}
