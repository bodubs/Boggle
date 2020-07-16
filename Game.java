// Bo Warren
// CS 110
// Game Class

import java.util.ArrayList;
import java.io.*;

public class Game
{
   // initialize ArrayLists
   ArrayList<Tile> selected;
   ArrayList<Word> words;
   
   // initialize objects
   Board b;
   Dictionary dict;
   Word w;
   
   // initialize variables
   int totalPoints;
   boolean exists;

   /**
      Game constructor
   */
   public Game() throws IOException
   {
      totalPoints = 0;
      b = new Board();
      selected = new ArrayList<>();
      words = new ArrayList<>();
      dict = new Dictionary("dict.txt");
   }
   
   /**
      isValidSelection method checks to see if the tile chosen is valid
      and adjacent to the previous tile selected.
      @param r The row the selected tile is in.
      @param c The collumn the selected tile is in.
      @return validSelect Whether or not the tile is a valid selection.
   */
   public boolean isValidSelection(int r, int c) throws IOException
   {
      // variables
      int preRow;
      int preCol;
      boolean validSelect = false;
   
      if (selected.size() == 0) // if first word selected
      {
         b.board.get(r).get(c).selectTile();
         validSelect = true;
      }
      else // if something previously selected
      {
         Tile pt = selected.get(selected.size() - 1); // copy the previous tile in selected ArrayList
         
         preRow = pt.getRow(); // get previous tiles row
         preCol = pt.getCollumn(); // get previous tiles collumn
      
         if ((r == preRow) || (r == (preRow + 1)) || (r == (preRow - 1))) // if the two tiles are in the same or adjacent rows
         {
            if ((c == preCol) || (c == (preCol + 1)) || (c == (preCol - 1))) // if the two tiles are in the same or adjacent collumns
            {
               b.board.get(r).get(c).selectTile();
               validSelect = true;
            }
            else
               validSelect = false;
         }
         else
            validSelect = false;
      }
      
      return validSelect;
   }
   
   /**
      addToSelected method adds the selected tile to the selected ArrayList
      @param r The row of the selected tile.
      @param c The collumn of the selected tile.
   */
   public void addToSelected(int r, int c)
   {
      selected.add(b.board.get(r).get(c));
   }
   
   /**
      removeFromSelected method adds the selected tile to the selected ArrayList
      @param r The row of the selected tile.
      @param c The collumn of the selected tile.
   */
   public void removeFromSelected(int r, int c) throws IOException
   {
      selected.remove(b.board.get(r).get(c));
   }
   
   /**
      setTotalPoints method sets the total points.
      @param n The number of points being set to.
   */
   public void setTotalPoints(int n)
   {
      totalPoints = n;
   }
   
   /**
      getSelectedTiles method returns the tiles selected.
      @return selected The ArrayList of selected tiles.
   */
   public ArrayList getSelectedTiles()
   {
      return selected;
   }
   
   /**
      clearSelected method clears the selected ArrayList
   */
   public void clearSelected()
   {
      selected.clear();
   }
   
   /**
      testSelected method tests if the tiles in the selected ArrayList
      make a valid word and creates a new word object with the tiles
      if valid.
   */
   public boolean testSelected() throws IOException
   {
      w = new Word(selected); // create new Word object with tiles in selected ArrayList
      if (dict.isValidWord(selected)) // if word is in dictionary
      {
         exists = false;
         for (int i=0; i < words.size(); i++)
         {
            if (words.get(i).toString().equals(w.toString()))
               exists = true;
         }
         if (!exists)
         {
            words.add(w); // add word to words ArrayList
            totalPoints += w.getPoints(); // add points to total points
            selected.clear();
            return true;
         }
         else
         {
            selected.clear();
            return false;
         }
      }
      else
      {
         selected.clear();
         return false;
      }
   }
   
   /**
      getTile method returns a tile object.
      @param r The row the tile object is located in.
      @param c The collumn the tile object is located in.
      @return t The tile object.
   */
   public Tile getTile(int r, int c) throws IOException
   {
      Tile t = b.board.get(r).get(c);
      return t;
   }
   
   /**
      toString method converts the game to a string and displays it.
      @return s The game in the form of a string.
   */
   @Override
   public String toString()
   {     
      String s = "";
      
      s += b;
      s += "\n\nselected[";
      
      for (int i=0; i<selected.size(); i++) // for each tile object in selected ArrayList
      {
         s += selected.get(i);
         if (i < (selected.size() - 1)) // if tile is not the last tile in ArrayList
            s += ", "; // add comma
      }
            
      s += "]\n\nwords: [";
      
      for (int i=0; i<words.size(); i++) // for each word in words ArrayList
      {
         s += words.get(i);
         if (i < (words.size() - 1)) // if word is not the last word in ArrayList
            s += ", ";
      }
      
      s += "]\n\nscore: " + totalPoints + "\n";
      
      return s;
   
   }

}