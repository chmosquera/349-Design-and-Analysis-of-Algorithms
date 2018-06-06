/* Name: Tejasvi Chalamcherla
 * Professor Kearns
 * Knapsack Project
 * 06/03/18
 * Referenced: https://www.geeksforgeeks.org/branch-and-bound-set-2-implementation-of-01-knapsack/
 * https://stackoverflow.com/questions/4432774/how-do-i-make-2-comparable-methods-in-only-one-class
 */
import java.util.*;
import java.io.*;
import java.lang.*;

class Item{
	double value;
	double weight;
	double ratio;
	int identifier;

	public Item(int value, int weight, double ratio, int identifier){
		this.value = value;
		this.weight = weight;
		this.ratio = ratio;
		this.identifier = identifier;
	}
}

class Node implements Comparable<Node>{
	int level;
	double value;
	double weight; 
	double bound; 
	ArrayList<Item> includedItems;

	public Node(){
		this.level = 0;
		this.weight = 0;
		this.value = 0;
		this.bound = 0;
		includedItems = new ArrayList<Item>();
	}

	public Node(Node node){
		this.level = node.level + 1;
		this.weight = node.weight;
		this.value = node.value;
		this.bound = node.bound;
		includedItems = new ArrayList<Item>(node.includedItems);
	}

	public int compareTo(Node node){
		if(this.bound > node.bound)
			return -1;
		else if (this.bound < node.bound)
			return 1;
		return 0;
	}
}

public class BranchBound{
 
	public void doBranchBound(int n, int capacity, int[] values, int[] weight, int[] identifier){
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		ArrayList<Item> itemArr = new ArrayList<Item>();

		//getting ratios of value to weight for each item
		for(int i = 0; i < n; i++){
			double ratio = (values[i] * 1.0) / weight[i];
			Item it = new Item(values[i], weight[i], ratio, identifier[i]);
			itemArr.add(it);
		}
		Collections.sort(itemArr, new Comparator<Item>(){
			public int compare(Item a, Item b){
				return Double.compare(b.ratio, a.ratio);
			}
		});

		Node head = new Node();
		Node tempBest = new Node();
		Node node; 

		bound(n, capacity, head, itemArr);
		q.offer(head);

		while(q.size() > 0){
			node = q.poll();
			if(node.bound > tempBest.value && node.level < n - 1){
				Node parser = new Node(node);
				Item it = itemArr.get(node.level);
				parser.weight += it.weight;

				// if less than capacity weight then include
				if(parser.weight <= capacity){
					(parser.includedItems).add(it);
					parser.value += it.value;
					bound(n, capacity, parser, itemArr);
					if(parser.value > tempBest.value)
						tempBest = parser;
					if(parser.bound > tempBest.value)
						q.offer(parser);
				}

				Node excludedItems = new Node(node);
				bound(n, capacity, excludedItems, itemArr);
				if(excludedItems.bound > tempBest.value)
					q.offer(excludedItems);
			}
		}/* end of while */


		// print out the values in the knapsack 
		System.out.println("Using Branch and Bound the best feasible solution found: Value " +
        	(int)tempBest.value + ", Weight " + (int)tempBest.weight);
		System.out.println(" as specified for the other approaches.");
		
		ArrayList<Integer> knapsack = new ArrayList<Integer>();
		for(int i = 0; i < (tempBest.includedItems).size(); i++){
			knapsack.add(((tempBest.includedItems).get(i)).identifier);
		}
		Collections.sort(knapsack);
		for(int i = 0; i < knapsack.size(); i++){
			System.out.print(knapsack.get(i) + " ");
		}
	}

	public void bound(int n, int capacity, Node node, ArrayList<Item> itemArr){
		int tempLevel = node.level;
		double tempWeight = node.weight;
		double tempBound = node.value;
		Item it = itemArr.get(tempLevel);

		while(tempLevel < n){
			it = itemArr.get(tempLevel);
			if(tempWeight + it.weight <= capacity){
				tempWeight += it.weight;
				tempBound += it.value;
				tempLevel++;
			}
			else
				break;
		} 

		tempBound += (capacity - tempWeight) * it.ratio;
		node.bound = tempBound;

	}
}