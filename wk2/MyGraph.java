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

class MyGraph
{
	static final int MAXV = 200;
	static final int MAXDEGREE = 19900;
	public boolean directed;
	public ArrayList<Integer>[] edges = new ArrayList[MAXV+1];
	public int degree[] = new int [MAXV+1];
	public int nvertices;
	public int nedges;

	MyGraph()  {                     // constructor
		nvertices = nedges = 0;
		for(int i=0;i<=MAXV;i++)  {
			degree[i] = 0;
         edges[i] = new ArrayList<Integer>();
      }   
	}
		
   void readfile_graph(String filename) throws FileNotFoundException     {
      int x,y;
      FileInputStream in = new FileInputStream(new File(filename));
      Scanner sc = new Scanner(in);
      directed =  (  1 == sc.nextInt()  );      // 1 directed, anything else undirected
      nvertices = sc.nextInt();
      int m = sc.nextInt();                     // m is the number of edges in the file
      for(int i=1;i<=m;i++)	{
         x=sc.nextInt();
         y=sc.nextInt();
         insert_edge(x,y,directed);
      }  
 
      // order edges (book convention) to ease debugging
      for(int i=1;i<=nvertices;i++)	{
         Collections.sort(edges[i]);
      }  
   }
  
   void insert_edge(int x, int y, boolean directed)	{
      // not worrying about capacity		         
      edges[x].add(y);
      degree[x]++;
      
      if (!directed)  {       // adding "mirror" edge since not directed
         edges[y].add(x);
         degree[y]++;
         nedges++;         
      }
   }

   void remove_edge(int x, int y)  {
		if(degree[x]<0)
			System.out.println("Warning: no edge --" + x + ", " + y);
		edges[x].remove((Integer)y);
		degree[x]--;
	}

	
    void print_graph()	{
        if (directed) 
            System.out.printf("Directed Graph\n"); 
        else 
            System.out.printf("Undirected Graph\n"); 
		for(int i=1;i<=nvertices;i++)   {
		    System.out.printf("%d: ",i);
		    for(int j=0;j<=degree[i]-1;j++)
			System.out.printf(" %d",edges[i].get(j));
			System.out.printf("\n");
		}
	}

    // use BFS to find connected components
    ArrayList<HashSet<Integer>> connectCheck() {

        ArrayList<HashSet<Integer>> result = new ArrayList<HashSet<Integer>>();
	ArrayDeque<Integer> queue = new ArrayDeque<>();         // keep track of which elements to visit next
	boolean[] visited = new boolean [MAXV+1];		// keep track of which vertices have been visited
                
        int cnt = 0;                                            // counter for # of connected components

        for (int i = 0; i < edges.length; i++) {                // iterate through all adj. vertices
            if (!edges[i].isEmpty()) {
                for (int j = 0; j < edges[i].size(); j++) {
                    if (!visited[edges[i].get(j)]) {
                        queue.add(edges[i].get(j));
                        HashSet<Integer> h = new HashSet();     
                        cnt++;
                        while (!queue.isEmpty()) {
                            int v = queue.poll();
                            if (!visited[v]) {
                                visited[v] = true;
                                h.add(v);                       // add connected vertices to hashset
                                for (int k = 0; k < edges[v].size(); k++) {
                                    queue.add(edges[v].get(k));
                                }
                            }
                        }
                        result.add(h);                          // hashset now contains a set of connected vertices
                    }   
                }
            }
        }
                                                                // insert number of connections as hashset into the result
        HashSet<Integer> h = new HashSet();
        h.add(cnt);
        result.add(0, h);

        return result;
    }

    // check if graph is bipartite
    boolean bipartiteCheck() {
       
        int[] color = new int[MAXV + 1];                            // default values 0

        for (int i = 0; i < edges.length; i++) {
            if (!edges[i].isEmpty()) {
                if (color[i] == 0) {                                // assign color if no color yet
                    color[i] = 1;                                   // color code: 0 - none, 1 - red, 2 - blue
                }

                // check colors of all adjacent vertices
                for (int j = 0; j < edges[i].size(); j++) {
                    if (color[edges[i].get(j)] == 0) {              // assign color to adj vert opposite of this vert's color
                        if (color[i] == 1) {
                            color[edges[i].get(j)] = 2;
                        } else {
                            color[edges[i].get(j)] = 1;
                        }
                    }
                    else if (color[edges[i].get(j)] == color[i]) {  // or compare adj vertice's colors
                        return false;                               // if vertex has same color as it's adj, NOT BIPARTITE
                    }
                    
                }
            }
        }

        return true;

    }

}
