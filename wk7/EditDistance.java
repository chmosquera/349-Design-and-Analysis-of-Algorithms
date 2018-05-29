import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.*;
import java.lang.Integer;

// Assignment Week 6 -Edit Distance

class EditDistance
{

    public EditDistance() {}

    static public int MinPenalty(String seq_1,String seq_2, int toggle) {
        // add gap to beginning of each string
        // loop through strings

        String A = " " + seq_1;
        String B = " " + seq_2;

		// init score table - fill in first row/col
		int[][] score = new int[A.length()][B.length()];
		int gap_penalty = 2;
		for (int i = 0; i < A.length(); i++) {
		    score[i][0] = i*gap_penalty;
		}
		for (int i = 0; i < B.length(); i++) {
		    score[0][i] = i*gap_penalty;
        }

        // fill in score table
		int minPenalty = Integer.MAX_VALUE;
		for (int i = 1; i < A.length(); i++){
		    for (int j = 1; j < B.length(); j++) {
		        minPenalty = Integer.MAX_VALUE; 

		        // check all options                    // Option 1 diagnol   
		        if (A.charAt(i) == B.charAt(j)) {                     // match found
		            if (minPenalty > (score[i-1][j-1])) {
		                minPenalty = score[i-1][j-1];
		            }
		        } else {
		            if( minPenalty > (score[i-1][j-1] + 1)) {
		                minPenalty = score[i-1][j-1] + 1;
		            }
		        }
		        if (minPenalty > (score[i-1][j] + gap_penalty)) {     // Option 2 above
		            minPenalty = score[i-1][j] + gap_penalty;           
		        } if (minPenalty > (score[i][j-1] + gap_penalty)) {     // option 3 beside
		            minPenalty = score[i][j-1] + gap_penalty;
		        } 

		        // set min penalty for this square
		        score[i][j] = minPenalty;
		    }
		}
        
        System.out.println("Edit distance = " + minPenalty);

        if (toggle == 1) {

        	ArrayList<String> result = new ArrayList<String>();
        	int i = A.length()-1, j = B.length()-1;
        	char a = ' ', b = ' ', penalty = ' ';
        	while (i > 0 && j > 0) {

                        int beside = -100;
                        int above = -100;
        		int diagonal = -100;
                       
                        if (A.charAt(i) == B.charAt(j)) {
                            diagonal = 0;
                            penalty = '0';
                        } else {
                            diagonal = 1;
                            penalty = '1';
                        }

                        diagonal += score[i-1][j-1];
        		beside = score[i][j-1] + 2;
        		above = score[i-1][j] + 2;

                        if (beside < above) {
                            if (diagonal < beside) {
                                a = A.charAt(i);
                                b = B.charAt(j);
                                i--; j--;
                            } else {
                                a = '-';
                                b = B.charAt(j);
                                penalty = '2';
                                j--;
                            }
                        } else if (beside >= above) {
                            if (diagonal < above) {
                                a = A.charAt(i);
                                b = B.charAt(j);
                                i--; j--;
                            } else {
                                a = A.charAt(i);
                                b = '-';
                                penalty = '2';
                                i--;
                            }
                        }
        		result.add(a + " " + b + " " + penalty);
        	}

            while (i > 0 || j > 0) {
                if (j==0) {
                    a = A.charAt(i);
                    b = '-';
                    i--;
                }
                else if (i == 0) {
                    a = '-';
                    b = B.charAt(j);
                    j--;
               }

                result.add(a + " " + b + " 2");
           
            }
            for (int k = result.size()-1; k >= 0; k--) {
		System.out.println(result.get(k));
	    } 
	}


        return 0;
    }

    
    public static void main(String args[]) {

        if (args.length <= 0) {
            System.out.println("Usage: EditDistance <filename> <toggle>");
            return;
        }

        File file = new File(args[0]);
        Scanner scan;
        int toggle = 0;

        if (args.length > 1) {
            toggle = 1;
       }
            


        try {

           Scanner sc = new Scanner(file);
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();

            MinPenalty(s1, s2, toggle);

        }
        catch(FileNotFoundException e) {
            System.out.println("Could not process file");
        }

    }



}
