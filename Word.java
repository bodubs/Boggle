// Bo Warren
// CS 110
// Word class

import java.util.ArrayList;
import java.io.*;
public class Word
{
   // variables
   private String word = "";
   private int points = 0;
   
   /**
      Word constructor accepts ArrayList of Tile objects, creates
      a String out of the letters and determines the points the word
      is worth.
      @param tiles The ArrayList of tiles selected to make a word.
   */
   public Word(ArrayList<Tile> tiles) throws IOException
   {
      for (int i = 0; i < tiles.size(); i++)
         word += tiles.get(i);
      
      if (word.length() == 3 || word.length() == 4)
         points = 1;
      else if (word.length() == 5)
         points = 2;
      else if (word.length() == 6)
         points = 3;
      else if (word.length() == 7)
         points = 5;
      else if (word.length() <= 2)
         points = 0;
      else
         points = 11;
   
   }
   
   /**
      getPoints method Returns the points the word is worth.
      @return points The points the word is worth.
   */
   public int getPoints()
   {
      return points;
   }
   
   /**
      toString method Returns the word as a string.
      @return word The word object as a string.
   */
   @Override
   public String toString()
   {
      return word;
   }
   
   /**
      equals method Checks to see if two words are equal.
      @param other The other object.
      @return The boolean value of whether or not they are equal.
   */
   @Override
   public boolean equals(Object other)
   {
      if (other == null)
         return false;
      if (getClass() != other.getClass())
         return false;
      if (this == other)
         return true;
         
      Word otherWord = (Word)other;
      
      return (word.equals(otherWord)
               && points == otherWord.getPoints());
   }

}
