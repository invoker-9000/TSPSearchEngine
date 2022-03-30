package tspsearch;

import java.io.File;

import referencedclasses.HTMLToText;
import referencedclasses.InvertedIndexBuilder;
import referencedclasses.Suggestions;

public class PreProcessor {
	public static void preProcessor() throws Exception{
		System.out.println("Preparing the database ./././. ");
		System.out.println("Converting W3 HTML files to .txt files...");
//		System.out.println("Debuging");
		
		File folder = new File("F:\\eclipse-workspace\\TSPSearchEngine\\src\\W3_Webpages");
		File[] files = folder.listFiles();
		
		// converting HTML files to TEXT for processing
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile())
			HTMLToText.createIndFiles(files[i]);
		}
		
		// Build record-level-inverted index using hashMap.
		System.out.println("Creating the inverted index from the converted text files ...");
		File path = new File("F:\\eclipse-workspace\\TSPSearchEngine\\src\\W3_Webpages\\Text");
		File[] filesList = path.listFiles();
		for (File file : filesList) {
			InvertedIndexBuilder.invertedIndex(file);
		}
		
		// ReStructure inverted index to use binary heap.
		System.out.println("Restructuring the inverted index to use Binary Heaps...");
		Ranking.reStructure();
		
		//Constructs a TST for suggestions/autocomplete.
		System.out.println("Construsting a TST with all words.....");
		for (File file : filesList) {
			Suggestions.buildTST(file);
		}
		
		System.out.println("Pre-Processing of html files is completed !!!");
	}
}
