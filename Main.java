import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		System.out.println("Do you want do encrypt(e) or crack(c) a message?");
		Scanner scanner = new Scanner(System.in);
		char mode = scanner.nextLine().charAt(0);
		
		if(mode == 'e') {
			System.out.print("Which message do you want to encrypt?: ");
			String message = scanner.nextLine();
			System.out.print("Which key do you want to use for the encryption (a number from 1 - 26)?: ");
			int key = scanner.nextInt();
			System.out.println(encode(message, key));
		} else if(mode == 'c'){
			System.out.print("Which encrypted String do you want to crack?: ");
			String encodedString = scanner.nextLine();
			crackCaesar(encodedString);
		}
		scanner.close();
	}
	
	private static void crackCaesar(String encodedString) {
		List<String> wordList = Collections.emptyList();
		try {
			wordList = Files.readAllLines(Paths.get("english-words.txt"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Something went wrong while trying to load the list of english words!");
			e.printStackTrace();
		}
		
		char[] encryptedChars = encodedString.toCharArray();
		char[] decryptedChars = new char[encryptedChars.length];

        for(int i = 0; i < 26; i++){
            String decryptedStringOnlyAlphabetic = "";
            
            for(int j = 0; j < encryptedChars.length; j++)
            {
            	decryptedChars[j] = (char) (((encryptedChars[j] - 32 + 127 - i) % 127) + 32);
            	
            	if(Character.isAlphabetic(decryptedChars[j]) || decryptedChars[j] == ' ') {
            		decryptedStringOnlyAlphabetic += decryptedChars[j];
            	}
            }

            String decryptedString = new String(decryptedChars);
            String[] decryptedWordsOnlyAlphabetic = decryptedStringOnlyAlphabetic.split(" ");
            
            for(String s : decryptedWordsOnlyAlphabetic) {
            	if(wordList.contains(s)) {
            		System.out.println("Possible crack found: \"" + decryptedString + "\"");
            		break;
            	}
            }
        }
	}
	
	private static String encode(String decryptedString, int key) {
		char[] decryptedChars = decryptedString.toCharArray();
		char[] encryptedChars = new char[decryptedChars.length];

		for( int i = 0; i < decryptedChars.length; i++ )
		{
			encryptedChars[i] = (char) (((decryptedChars[i] - 32 + key) % 127) + 32);
		}
		
		return new String(encryptedChars);
	}
}
