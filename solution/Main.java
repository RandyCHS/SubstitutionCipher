import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

class Main {
  private static Scanner scanner;
  private static final char UC_A = 'A';
  private static final char LC_A = 'a';
  
  public static void main(String[] args) {
    scanner = new Scanner(new InputStreamReader(System.in, Charset.forName("UTF-8")));

    // ask for the key
    System.out.print("key: ");
    String key = scanner.nextLine();

    // check to make sure the key is a sequence of 26 characters that are non-repeating
    if (!validKey(key)) {
      System.out.println("\nKey must be one continuous string of 26 unique alphabetic characters.");
      System.exit(1);
    }

    // ask for the plain text
    System.out.print("\nplaintext:  ");
    String plaintext = scanner.nextLine();

    // convert the plaintext using the key
    String ciphertext = "";
    char[] keyCharArray = key.toCharArray();
    for (char ch: plaintext.toCharArray()) {
      if (Character.isUpperCase(ch)) {
        char newValue = keyCharArray[ch - UC_A];
        ciphertext += (char) Character.toUpperCase(newValue);        
      } else if (Character.isLowerCase(ch)) {
        char newValue = keyCharArray[ch - LC_A];
        ciphertext += (char) Character.toLowerCase(newValue);
      } else {
        ciphertext += ch;
      }
    }
    System.out.println("\nciphertext: " + ciphertext);
    
    System.exit(0);
  }

  private static boolean validKey(String key) {
    char[] keyCharArray = key.toCharArray();

    // check for length
    if (key.length() != 26) {
      // wrong length so invalid key
      return false;
    } else {
      boolean[] letters = new boolean[26];
      for (char ch : keyCharArray) {
        // check to see if we only have letters in our key
        if (Character.isUpperCase(ch)) {
          if (!letters[ch - UC_A]) {
            // first time we see this letter, keep track
            letters[ch - UC_A] = true;
          } else {
            // second time we see this letter so invalid key
            return false;
          }
        } else if (Character.isLowerCase(ch)) {
          if (!letters[ch - LC_A]) {
            // first time we see this letter, keep track
            letters[ch - LC_A] = true;
          } else {
            // second time we see this letter so invalid key
            return false;
          }
        } else {
          // not a letter so invalid key
          return false;
        }
      }
      return true;
    }    
  }
}