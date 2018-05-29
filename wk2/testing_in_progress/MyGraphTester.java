import java.util.*;
import java.io.*;

public class MyGraphTester {

   public static void main(String[] args) {
      MyGraph myGraph = new MyGraph();
      boolean isBipartite;
      ArrayList<HashSet<Integer>> connectCheckResult;


      try {
         System.out.println("\n-------Test 1--------");
         myGraph.readfile_graph("./Bfstest1.txt");
         isBipartite = myGraph.bipartiteCheck();
         connectCheckResult = myGraph.connectCheck();
         System.out.println(isBipartite);
         System.out.println(connectCheckResult);

         System.out.println("\n-------Test 2--------");
         myGraph = new MyGraph();
         myGraph.readfile_graph("./Bfstest2.txt");
         isBipartite = myGraph.bipartiteCheck();
         connectCheckResult = myGraph.connectCheck();
         System.out.println(isBipartite);
         System.out.println(connectCheckResult);

         System.out.println("\n-------Test 3--------");
         myGraph = new MyGraph();
         myGraph.readfile_graph("./Bfstest3.txt");
         isBipartite = myGraph.bipartiteCheck();
         connectCheckResult = myGraph.connectCheck();
         System.out.println(isBipartite);
         System.out.println(connectCheckResult);

         System.out.println("\n-------Test 4--------");
         myGraph = new MyGraph();
         myGraph.readfile_graph("./Bfstest4.txt");
         isBipartite = myGraph.bipartiteCheck();
         connectCheckResult = myGraph.connectCheck();
         System.out.println(isBipartite);
         System.out.println(connectCheckResult);

      }
      catch (FileNotFoundException e) {
         System.out.print("FILE NOT FOUND JAKE");
      }
      
   }
}
