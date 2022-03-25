package priority;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import referencedclasses.InvertedIndexBuilder;
import referencedclasses.Node;

public class Ranking {
	
	public static HashMap<String,Binary_Heap<Node>> index = new HashMap<String, Binary_Heap<Node>>();
	
	public static void reStructure() {
		for (Entry<String, HashMap<String, Integer>> entry : InvertedIndexBuilder.index.entrySet()) {
			Binary_Heap<Node> Binary_Heap = new Binary_Heap<Node>();
			HashMap<String, Integer> hashMap = entry.getValue();
			for (Entry<String, Integer> i : hashMap.entrySet()) {
				Binary_Heap.insert(new Node(i.getKey(),i.getValue()));
			}
			index.put(entry.getKey(), Binary_Heap);
		}
	}
	
	public static void search(String keyword, int numberOfResults) {
		Binary_Heap<Node> Binary_Heap = index.get(keyword);
		if(Binary_Heap == (null)) {
			System.out.println("Match not found!!");
			return;
		}
		else
		{
			System.out.println("Retrieving first " + numberOfResults + " matches....");
		}
			
		for (int j = 0; j < numberOfResults; j++) {
			System.out.println((j+1)+" :"+ Binary_Heap.deleteMin());
		}
	}
	
//	public static void main(String[] args) throws Exception {
//		File path = new File("C:\\Users\\LENOVO\\git\\repository\\SearchEngine\\src\\Udemy\\Text");
//		File[] listOfFiles = path.listFiles();
//		for (File file : listOfFiles) {
//			InvertedIndexBuilder.invertedIndex(file);
//		}
//		reStructure();
//		search("web", 10);
//	}
}
