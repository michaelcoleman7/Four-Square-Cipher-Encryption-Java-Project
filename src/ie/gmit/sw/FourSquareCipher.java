package ie.gmit.sw;

import java.util.*;

public class FourSquareCipher {
	
	public FourSquareCipher(){
		init();
	}
	//matrix - 4 quadrants(5x5)
	private char[][] matrix = {
			{'A', 'B', 'C', 'D', 'E', 'Z', 'G', 'P', 'T', 'F'},
			{'F', 'G', 'H', 'I', 'K', 'O', 'I', 'H', 'M', 'U'},
			{'L', 'M', 'N', 'O', 'P', 'W', 'D', 'R', 'C', 'N'},
			{'Q', 'R', 'S', 'T', 'U', 'Y', 'K', 'E', 'Q', 'A'},
			{'V', 'W', 'X', 'Y', 'Z', 'X', 'V', 'S', 'B', 'L'},
			{'M', 'F', 'N', 'B', 'D', 'A', 'B', 'C', 'D', 'E'},
			{'C', 'R', 'H', 'S', 'A', 'F', 'G', 'H', 'I', 'K'},
			{'X', 'Y', 'O', 'G', 'V', 'L', 'M', 'N', 'O', 'P'},
			{'I', 'T', 'U', 'E', 'W', 'Q', 'R', 'S', 'T', 'U'},
			{'L', 'Q', 'Z', 'K', 'P', 'V', 'W', 'X', 'Y', 'Z'}
		};
	// arraylist from A-Z
	List <Character> alphabet = new ArrayList<Character>(
			Arrays.asList('A', 'B', 'C', 'D', 'E','F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P','Q', 'R', 'S', 'T', 'U','V', 'W', 'X', 'Y', 'Z'));
	
	private List<Character> key1,key2;

	//initialise four square cipher - o(n) running time as no matter how big input to keys, same time to populate
	public void init() {
		key1 = new ArrayList<Character>(alphabet);
		key2 = new ArrayList<Character>(alphabet);
		
		//randomly shuffle arrays
		Collections.shuffle(key1);
		Collections.shuffle(key2);
		
		//add keys to matrix
		populateMatrix();

	}
	
	
	//method to add keys manually to matrix - o(n) running time as no matter how big input to, same time to populate
	public void manualKey(String newKey1, String newKey2) {
		int i;
		List <Character> newKeyArray1 = new ArrayList<Character>();
		List <Character> newKeyArray2 = new ArrayList<Character>();
		
		//add manual key into new key array
		for(i=0; i<25;i++){
			newKeyArray1.add(newKey1.charAt(i));
		}
		
		//ensure no duplicate characters in arraylist - adapted from https://stackoverflow.com/questions/562894/java-detect-duplicates-in-arraylist
		Set<Character> set1 = new HashSet<Character>(newKeyArray1);
		//Hashset eliminates duplicates as it doesn't store duplicate values
		if(set1.size() < newKeyArray1.size()){
			System.out.println("Cannot add keys, as 1 or more keys has duplicate characters");
			return;
		}
		
		//add manual key into new key array
		for(i=0; i<25;i++){
			newKeyArray2.add(newKey2.charAt(i));
		}
		
		//ensure no duplicate characters in arraylist - adapted from same as above
		Set<Character> set2 = new HashSet<Character>(newKeyArray2);
		if(set2.size() < newKeyArray2.size()){
		    System.out.println("Cannot add keys, as 1 or more keys has duplicate characters");
		    return;
		}
		
		//set keys
		key1 = new ArrayList<Character>(newKeyArray1);
		key2 = new ArrayList<Character>(newKeyArray2);
		
		//add keys to matrix
		populateMatrix();
	}
	
	//Method to displays keys - o(n) running time as will not increase in size, would be log n, if key size were expandable
	public void showKeys() {
		System.out.print("Key 1: ");
		for(int i=0; i<25;i++){
			System.out.print(key1.get(i));
		}
		
		System.out.print("\nKey 2: ");
		
		for(int i=0; i<25;i++){
			System.out.print(key2.get(i));
		}
		System.out.println("\n");
	}
	
	//Method to add keys to matrix
	//o(n) running time as array size as would increase if array was bigger e.g. would have to go from more than (0-5),(5,10)
	private void populateMatrix() {
		int col=0,row=0,index=0;
		
		//set 2nd quadrant equal to key 1
		for(row = 0; row < 5; row++){
			for(col = 5; col < 10; col++){
				matrix[row][col]=(char) key1.get(index);
				index++;
			} 
		}
		
		index=0;
		//set 3rd quadrant equal to key 2
		for(row = 5; row <10 ; row++){
			for(col = 0; col < 5; col++){
				matrix[row][col]=(char) key2.get(index);
				index++;
			} 
		}
	}
	
	//Method to print out matrix -o(n) running time as array size as would increase if array was bigger e.g. would have to go from more than (0-5),(5,10)
	public void printMatrix() {
		for(int i = 0; i<10; i++)
		{
		    for(int j = 0; j<10; j++)
		    {
		        System.out.print(matrix[i][j]+" ");
		    }
		    System.out.println();
		}	
		System.out.println();
	}
	
	//Method to encrypt bigrams - o(n) as only the 2 bigram characters passed each time
	public String encrypt(String bigram) {
		String encBigram=null;
		
		int col=0,row=0,colFound1=0,rowFound1=0,colFound2=0,rowFound2=0;
		char char1, char2;
		
		//if text is uneven length a small z will be passed in and replaced with X
		if(bigram.charAt(1)=='z'){
			bigram=bigram.charAt(0)+""+'X';
		}

		//get encrypted chars from matrix 
		for(row = 0; row < 5; row++){
			for(col = 0; col < 5; col++){
				if(matrix[row][col]==(bigram.charAt(0))){
					 colFound1 = col;
					 rowFound1=row;
				}
			} 
		}
		
		for(row = 5; row < 10; row++){
			for(col = 5; col < 10; col++){
				if(matrix[row][col]==(bigram.charAt(1))){
					 colFound2 = col;
					 rowFound2 = row;
				}
			} 
		}
		
		//set chars = to intersection of rows
		char1 = matrix[rowFound1][colFound2];
		char2 = matrix[rowFound2][colFound1];	
		
		//chars set as a string
		encBigram = char1+""+char2;

		return encBigram;
	}

	//Method to decrypt bigrams - o(n) as only the 2 bigram characters passed each time
	public String decrypt(String bigram) {
		String decBigram=null;
		
		int col=0,row=0,colFound1=0,rowFound1=0,colFound2=0,rowFound2=0;
		char char1, char2;

		//get decrypted chars from matrix 
		for(row = 0; row < 5; row++){
			for(col = 5; col < 10; col++){
				if(matrix[row][col]==(bigram.charAt(0))){
					 colFound1 = col;
					 rowFound1= row;
				}
			} 
		}
		
		for(row = 5; row < 10; row++){
			for(col = 0; col < 5; col++){
				if(matrix[row][col]==(bigram.charAt(1))){
					 colFound2 = col;
					 rowFound2 = row;
				}
			} 
		}
		//set chars = to intersection of rows to get original characters
		char1 = matrix[rowFound1][colFound2];
		char2 = matrix[rowFound2][colFound1];

		//chars set as a string
		decBigram = char1+""+char2;
		
		return decBigram;
	}
}
