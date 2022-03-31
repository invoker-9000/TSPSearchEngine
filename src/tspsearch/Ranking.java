package tspsearch;

import java.util.HashMap;
import java.util.Map.Entry;

import referencedclasses.InvertedIndexBuilder;
import referencedclasses.Node;

public class Ranking {
	
	public static HashMap<String,Binary_Heap<Node>> index = new HashMap<String, Binary_Heap<Node>>();
	
	
	// will convert the internal hashmap into a binaryheap so that the max occurence is on the root which will be used in our functionality
	public static void reStructure() {
		for (Entry<String, HashMap<String, Integer>> entry : InvertedIndexBuilder.index.entrySet()) { //to make sure there are no repeated words in the binary heap
			Binary_Heap<Node> Binary_Heap = new Binary_Heap<Node>();
			HashMap<String, Integer> hashMap = entry.getValue();
			for (Entry<String, Integer> i : hashMap.entrySet()) {
				Binary_Heap.insert(new Node(i.getKey(),i.getValue())); //create a max heap for the word in all the file based on its occurrence in each file
			}
			index.put(entry.getKey(), Binary_Heap); //modify the internal hashmap into binary heap 
		}
	}
	
	public static void search(String keyword, int numberOfResults) {
		Binary_Heap<Node> Binary_Heap = index.get(keyword); // searching the keyword in the index that we created using the binary heap
		if(Binary_Heap == (null)) { //nothing in binnary heap means word not found
			System.out.println("Match not found!!");
			return;
		}
		else
		{
			System.out.println("Retrieving first " + numberOfResults + " matches...."); 
		}
			
		for (int j = 0; j < numberOfResults; j++) {
			System.out.println((j+1)+" :"+ Binary_Heap.deleteMin()); //deleting the root node which has max occurrence of the word and printing it
		}
		reStructure();
	}
	
}
