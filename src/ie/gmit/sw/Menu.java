package ie.gmit.sw;

import java.util.*;

public class Menu {
	private Scanner s = new Scanner(System.in);
	private String fileName, text;
	private FourSquareCipher fsc = new FourSquareCipher();
	private Parser par = new Parser();
	private Encrypter enc = new Encrypter();
	private Decrypter dec = new Decrypter();
	private FileManager fileManager = new FileManager();
	boolean keeprunning = true;

	//Menu options
	public void show() {
		while (keeprunning) {
			System.out.println("Select from the following options:");
			System.out.println("1: Encrypt");
			System.out.println("2: Decrypt");
			System.out.println("3: Encrypt and Decrypt");
			System.out.println("4: Cipher Options");
			System.out.println("5: Quit");
			String option = s.next();
			process(option);
		}
	}

	//Method to select user options
	private void process(String option) {
		try {
			int selection = Integer.parseInt(option);
			// Encrypt Options
			if (selection == 1) {
				System.out.println("Would you like to encrypt from file or URL:");
				System.out.println("1: File");
				System.out.println("2: URL");
				int fileOrUrl = s.nextInt();
				char fileOption = ' ';

				//encrypt file
				if (fileOrUrl == 1) {
					System.out.println("What file would you like to encrypt (No need to add .txt)");
					fileName = s.next();

					String txt;
					//start timer
					long startTime = System.nanoTime();
					//read file
					txt = fileManager.readFile(fileName);

					String parsedText = par.Parse(txt);
					String encryptedText = enc.encrypt(parsedText, fsc);
					// End the Timer
					long endTime = System.nanoTime();
					long totalTime = endTime - startTime;
					// Print the total Time
					double sec = (double) totalTime / 1000000000.0;
					System.out.println("File encrypted in: " + sec + " Seconds");
					System.out.println();

					// printing to a file
					System.out.println("Would you like to output to a file: (if so enter Y, otherwise press any other key)");
					fileOption = s.next().charAt(0);
					//print to file
					if (fileOption == 'Y' || fileOption == 'y') {
						System.out.println("What would you like to call the file (No need to add .txt)");
						String newfileName = s.next();
						fileManager.printToFile(newfileName, encryptedText);
					}
					
				//encrypt Url
				} else if (fileOrUrl == 2) {
					System.out.println("What URL would you like to encrypt");
					text = s.next();
					//get text from url
					String urlText = par.getTextFromUrl(text);
					long startTime = System.nanoTime();
					String encryptedText = enc.encrypt(urlText, fsc);
					// End the Timer
					long endTime = System.nanoTime();
					long totalTime = endTime - startTime;
					// Print the total Time
					double sec = (double) totalTime / 1000000000.0;
					System.out.println("File read, parsed and encrypted in: " + sec + " Seconds");
					System.out.println();

					// printing to a file
					System.out.println(
							"Would you like to output to a file: (if so enter Y, otherwise press any other key)");
					fileOption = s.next().charAt(0);
					if (fileOption == 'Y' || fileOption == 'y') {
						System.out.println("What would you like to call the file (No need to add .txt)");
						String newfileName = s.next();
						fileManager.printToFile(newfileName, encryptedText);
					}

				} else {
					System.out.println("Not valid option");
				}
			}
			// Decrypt
			else if (selection == 2) {
				char fileOption = ' ';

				System.out.println("Enter the file you wish to decrypt from: (No need to add .txt)");
				fileName = s.next();
				long startTime = System.nanoTime();
				//read encrypted file
				String encryptedText = fileManager.readFile(fileName);
				String decryptedText = dec.decrypt(encryptedText, fsc);
				// End the Timer
				long endTime = System.nanoTime();
				long totalTime = endTime - startTime;
				// Print the total Time
				double sec = (double) totalTime / 1000000000.0;
				System.out.println("File read, parsed and decrypted in: " + sec + " Seconds");
				System.out.println();

				System.out.println("Would you like to output to a file: (Enter Y), or to console screen (Enter C) , otherwise press any other key");
				fileOption = s.next().charAt(0);
				//print to file
				if (fileOption == 'Y' || fileOption == 'y') {
					System.out.println("What would you like to call the file (No need to add .txt)");
					String newfileName = s.next();
					fileManager.printToFile(newfileName, decryptedText);
				}
				//print to console
				if (fileOption == 'C' || fileOption == 'c') {
					System.out.println(decryptedText);
				}
			}
			// Encrypt and Decrypt Options
			else if (selection == 3) {
				System.out.println("Would you like to encrypt from file or URL:");
				System.out.println("1: File");
				System.out.println("2: URL");
				System.out.println("3: Console Input");
				int fileOrUrl = s.nextInt();
				char fileOption = ' ';

				// encrypt and decrypt file
				if (fileOrUrl == 1) {
					System.out.println("What file would you like to encrypt (No need to add .txt)");
					fileName = s.next();

					String txt;
					long startTime = System.nanoTime();

					//read file
					txt = fileManager.readFile(fileName);

					//parse, encrypt and decrypt
					String parsedText = par.Parse(txt);
					String encryptedText = enc.encrypt(parsedText, fsc);
					String decryptedText = dec.decrypt(encryptedText, fsc);

					// End the Timer
					long endTime = System.nanoTime();
					long totalTime = endTime - startTime;
					// Print the total Time
					double sec = (double) totalTime / 1000000000.0;
					System.out.println("File read, parsed, encrypted and decrypted in: " + sec + " Seconds");
					System.out.println();

					// printing to a file
					System.out.println("Would you like to output to a file: (Enter Y), or to console screen (Enter C) , otherwise press any other key");
					fileOption = s.next().charAt(0);
					//print to file
					if (fileOption == 'Y' || fileOption == 'y') {
						System.out.println("What would you like to call the encrypted file (No need to add .txt)");
						String newfileName = s.next();
						fileManager.printToFile(newfileName, encryptedText);

						System.out.println("What would you like to call the decrypted file (No need to add .txt)");
						newfileName = s.next();
						fileManager.printToFile(newfileName, decryptedText);
					}
					//print to console
					if (fileOption == 'C' || fileOption == 'c') {
						System.out.println(decryptedText);
					}
					
				// encrypt and decrypt url
				} else if (fileOrUrl == 2) {

					System.out.println("What URL would you like to encrypt");
					text = s.next();

					//get url text
					String urlText = par.getTextFromUrl(text);
					long startTime = System.nanoTime();
					//encrypt and depcrypt text from url
					String encryptedText = enc.encrypt(urlText, fsc);
					String decryptedText = dec.decrypt(encryptedText, fsc);

					// End the Timer
					long endTime = System.nanoTime();
					long totalTime = endTime - startTime;
					// Print the total Time
					double sec = (double) totalTime / 1000000000.0;
					System.out.println("Url read, parsed, encrypted and decrypted in: " + sec + " Seconds");
					System.out.println();

					// printing to a file
					System.out.println("Would you like to output to a file: (Enter Y), otherwise press any other key");
					fileOption = s.next().charAt(0);
					if (fileOption == 'Y' || fileOption == 'y') {
						System.out.println("What would you like to call the encrypted file (No need to add .txt)");
						String newfileName = s.next();
						fileManager.printToFile(newfileName, encryptedText);

						System.out.println("What would you like to call the decrypted file (No need to add .txt)");
						newfileName = s.next();
						fileManager.printToFile(newfileName, decryptedText);
					}
				//encrypt and depcrypt user input
				} else if (fileOrUrl == 3) {
					System.out.println("Enter String to be encrypted");
					s.nextLine();
					String userString = s.nextLine();
					
					long startTime = System.nanoTime();

					//parse,encrypt and decrypt
					String parsedText = par.Parse(userString);
					String encryptedText = enc.encrypt(parsedText, fsc);
					String decryptedText = dec.decrypt(encryptedText, fsc);

					// End the Timer
					long endTime = System.nanoTime();
					long totalTime = endTime - startTime;
					// Print the total Time
					double sec = (double) totalTime / 1000000000.0;
					System.out.println("String read, parsed, encrypted and decrypted in: " + sec + " Seconds");
					System.out.println();

					System.out.println("Would you like to output to a file: (Enter Y), or to console screen (Enter C) , otherwise press any other key");
					fileOption = s.next().charAt(0);
					//print to file
					if (fileOption == 'Y' || fileOption == 'y') {
						System.out.println("What would you like to call the encrypted file (No need to add .txt)");
						String newfileName = s.next();
						fileManager.printToFile(newfileName, encryptedText);

						System.out.println("What would you like to call the decrypted file (No need to add .txt)");
						newfileName = s.next();
						fileManager.printToFile(newfileName, decryptedText);
					}
					//print to console
					if (fileOption == 'C' || fileOption == 'c') {
						System.out.println(decryptedText);
					}
				} else {
					System.out.println("Not valid option");
				}
			}
			// Cipher Options
			else if (selection == 4) {
				System.out.println("Select from the following options:");
				System.out.println("1: Change Key");
				System.out.println("2: View Current Keys");
				System.out.println("3: Show Cipher");
				int cipherOption = s.nextInt();

				//Key Options
				if (cipherOption == 1) {
					int keyOption;
					System.out.println("Which type of key would you like to use:");
					System.out.println("1: Randomly Generated");
					System.out.println("2: Enter your keys manually:");
					keyOption = s.nextInt();

					//randomly generate Keys
					if (keyOption == 1) {
						fsc.init();
						System.out.println("New keys generated are:");
						fsc.showKeys();
					} 
					//Manually generate Keys through user Input
					else if (keyOption == 2) {
						String newKey1 = "", newKey2 = "";
						s.nextLine();
						
						//read in key 1
						while (newKey1.length() != 25) {
							System.out.println("Enter new key for quadrant 1 (Must be 25 characters in length)");
							newKey1 = s.nextLine().toUpperCase();
						}

						//read in key 2
						while (newKey2.length() != 25) {
							System.out.println("Enter new key for quadrant 2 (Must be 25 characters in length)");
							newKey2 = s.nextLine().toUpperCase();
						}

						//set manual keys as keys for cipher
						fsc.manualKey(newKey1, newKey2);
					}
				} 
				//show keys
				else if (cipherOption == 2) {
					System.out.println("You may want to write this key down to decrypt files encrypted using this key");
					fsc.showKeys();
				} 
				//print out cipher
				else if (cipherOption == 3) {
					fsc.printMatrix();
				}

			} else if(selection == 5){
				keeprunning = false;
			}
		} catch (Exception e) {
		}
	}
}
