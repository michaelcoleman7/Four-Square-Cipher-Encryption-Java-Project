package ie.gmit.sw;

import java.io.*;

public class FileManager {
	//Method to write decrypted and encrypted text to files -(n log n) as increases in size based on size of string
	public void printToFile(String fileName, String text) {
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName + ".txt"), "utf-8"));
			//write to file
			writer.write(text);
			//close writer
			writer.close();
		} catch (Exception e) {
			System.out.println("Unable to print to file");
		}

	}

	//method to read in file specified by user- (n log n) as increases in size based on length of file
	public String readFile(String fileName) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName + ".txt"))));

			String line = null;

			//read in file line by line
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

			//close reader
			br.close();

		} catch (Exception e) {

			System.out.println("Unable to read file: " + fileName + ".txt");
			return null;
		}

		return sb.toString();
	}
}
