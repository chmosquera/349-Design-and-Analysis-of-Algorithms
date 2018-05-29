import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Arrays;

// Assignment Week 3 - Top Sort

class TopSorter
{

    public void TopSorter() {}


    // uses Source Removal Algorithm -- no recursion
    public ArrayList<Integer> topSortGenerator(String file) throws FileNotFoundException  {
        ArrayList<Integer> result = new ArrayList<Integer>();

		// setup graph
		GraphStart graph = new GraphStart();
		graph.readfile_graph(file);

		int cnt = 0;												// vertices visited
		// loop through all vertices to be sorted
		for (int j = 1; j <= graph.nvertices; j++) {
			ArrayDeque<Integer> queue = new ArrayDeque<Integer>();	// vertices to visit next

			// find vertices of in-degree 0 and add to queue
			for (int i = 1; i <= graph.nvertices; i++) {
				if (graph.indegree[i] == 0) {
					queue.add(i);
				}
			}

			// loop through queue of vertices of in-degree 0
			while (!queue.isEmpty()) {
				int v = queue.poll();								// source (v) has been sorted
				graph.indegree[v]--;								// remove source
				result.add(v);

				// loop through adjacent vertices of source vertex
				// decrement their indegree
				for (int i = 0; i < graph.edges[v].size(); i++) {
					int adj_v = graph.edges[v].get(i);
					graph.indegree[adj_v]--;
				}
				cnt++;
			}
		}

		// check if graph was only partially top sorted - fill unsorted vertices with -1
		if (cnt < graph.nvertices) {
			for (int i = 0; i < graph.nvertices - cnt; i++) {
				result.add(-1);
			}
		}
        return result;
    }

}
