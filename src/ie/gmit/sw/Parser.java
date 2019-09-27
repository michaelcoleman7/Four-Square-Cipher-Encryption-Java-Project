package ie.gmit.sw;

import org.jsoup.nodes.Document;
import org.jsoup.*;
import java.io.*;

public class Parser {
	
	//Method to parse Text - (n log n) running time
	public String Parse(String text) throws Exception{
		StringBuilder sb = new StringBuilder();
		Reader stringReader = new StringReader(text);
		BufferedReader br = new BufferedReader(stringReader);
		String line = null;
		
		while((line = br.readLine())!=null){
			line= line.toUpperCase().replaceAll("[^A-Z ]", "");
			//Replace characters	
			line=line.replace('J', 'I');
			line=line.replace("--", " ");
			line=line.replace("-", " ");

			sb.append(line+"~");// [~] - symbol used for new line
		}
			
		br.close();
		return sb.toString();
	}

	//Method to take in text from url and return parsed - o(n) running time as takes longer based on string returned from url's size
	//adapted from https://stackoverflow.com/questions/21656673/getting-text-from-a-website-using-jsoup
	// and https://jsoup.org/cookbook/extracting-data/attributes-text-html
	public String getTextFromUrl(String url) throws Exception{
		String urlText="";
		 try {
			 	//get text from url using jsoup
	            Document doc = Jsoup.connect(url).get();	    
	            urlText=doc.text();	             
	            
	        } catch (IOException e) {
	            System.out.println("Unable to read from URL");
	        } 
		return Parse(urlText);
	}
}
