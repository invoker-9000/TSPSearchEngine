package referencedclasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;

public class HTMLToText {
	
	/**
	 * @param file HTML file that is to be converted to plain text
	 * @return Plain text of given HTML file
	 * Also Writes the plain text to a file in $currentdirectory/Text1 folder
	 * @throws IOException
	 */
	
	public static void createIndFiles(File file) throws IOException {
		
		String html="";  // just for next line presentation
		String fileName = file.getName();
		String destPath = "F:\\eclipse-workspace\\TSPSearchEngine\\src\\W3_Webpages\\Text" + "\\" + fileName.substring(0, fileName.length() - 5) + ".txt"; // this is done
																																	//to store the file in the text folder and 
																																	//get the path as well as change the 
																																	//extension form html to txt

		 Scanner scnr = new Scanner(file); // file = original file html file
		 while(scnr.hasNextLine()){
			   String line = scnr.nextLine().toLowerCase(); //to make the case in-sensetive we are converting the string into lower
			   
			   html+= line + "\n"; // to store the lines on the next line 
		 }
		 
		  String htmlText = Jsoup.parse(html).wholeText(); // to remove the HTML tags form the text
		  FileWriter writer = new FileWriter(destPath);
		  writer.write(htmlText);			 // write the string into a new file and store it in the destination file
		  writer.close();
	}

}
