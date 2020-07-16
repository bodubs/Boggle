// Bo Warren
// CS 110
// Dictionary class

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Dictionary
{
   // variables
   private File file;
   // create ArrayList for dictionary file
   ArrayList<String> dict = new ArrayList<>();
   
   /**
      Dictionary constructor accepts a file name in the form of
      a String and adds each word to the dict ArrayList
      @param f The file name in the form of a String.
   */
   public Dictionary(String f) throws IOException
   {
      file = new File(f); // create new file
      Scanner infile = new Scanner(file); // read the file in Scanner
      while (infile.hasNext()) // read each line of the file
      {
         dict.add(infile.nextLine()); // add to dict ArrayList
      }
   }

   /**
      isValidWord method Determines if word chosen is in the dictionary file.
      @param tiles The ArrayList of tile letters chosen by the user.
      @return valid The boolean value that determines if the word is in the dictionary.
   */
   public boolean isValidWord(ArrayList<Tile> tiles) throws IOException
   {
      // variables
      boolean valid = false;
      String word = "";
      
      for (int i = 0; i < tiles.size(); i++) // for each element in ArrayList tiles
         word += tiles.get(i); // add to the String word
      
      for (int i=0; i < dict.size(); i++) // for each element in ArrayList dict
      {
         if (dict.get(i).equalsIgnoreCase(word)) // if word is in ArrayList dict
            valid = true; // set valid to true
      }
      
      return valid;
         
   }

}
