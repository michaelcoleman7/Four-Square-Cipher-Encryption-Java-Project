# Four-Square-Cipher-Encryption-Java-Project
2nd Year Semester 2 - Java Project which Encrypts and Decrypts large text files/books very efficiently

## About:
1. Program randomly generates keyword which is then populated into the array (Cipher).
2. The program allows user options to encrypt,decrypt, both encrypt and decrypt, cipher options and to exit applicaton.
3. Encyption allows the user to encypt file or url(url using jsoup by taking .text()), by taking a string, parsing and breaking up into bigrams of two,
which are sent to be encypted and returned encrypted and so on for the continuation of the the text. (if uneven length "X" appended)
User can print to file(but not console as encyption printed without new lines,"~" used instead, wont fit in console without using substring)
4. Decryption works in a similar way as encryption as it basically does the same but in the opposite way. 
User can print to file or console as new lines added in decryption
5. Encryption and decryption does both of the above at once, user can print to file or console. User can also encrypt and decrypt an inputted text from console
6. Cipher options allows the user to change the key(randomly using collection.shuffle() or manually), 
view the current keys in the cipher and to show the current cipher in full.

NOTICE: When prompted to enter file names, NO NEED to add .txt to end of a file e.g. input should be for both reading and creating files: warandpeace-leotolstoy 
-- Also times for all encrypting and decrypting are shown on console.
-- Txt files must be place in G00347650 (main folder) to be able to read and this is the location new files will be written to.

## Extras:
1. Encryption handles both spaces and new lines, saving them via boolean values and implementing them during decryption.
2. Encryption offers the user both the option to encrypt via a file or a url.
3. Decryption offers both the ability to output to a file or the console.
4. Encryption and decryption offers both of the above aswell as allow the user to encrypt and decrypt a string which they can enter,
which again can be saved via a file or shown on the console (short strings entered are usually done in microseconds e.g. 1.3634E-4 Seconds).
5. Allows user to access cipher options 
	- to either get a new random key for the cipher.
	- to manually add a key(can be used to decrypt a previously encrypted file for later use,obviously user has to know key).
	- use of hashsets to prevent the user from having duplicate characters when adding a new key (comparing to each keys arraylist).
	- User can view the current set keys in the cipher, user could write down and manually enter keys if using again to decrypt in the future.
	- User can also view the full cipher in full.

## Instructions:
CD (change directory in command line) into correct folder
To run jar - use following command (As JSoup is used for URL):
java -cp  .;./four-square.jar;./jsoup.jar ie.gmit.sw.Runner


Examples of urls to use are:  	http://randomtextgenerator.com/
				https://en.wikipedia.org/wiki/Four-square_cipher
