// Bo Warren
// CS 110
// Tile class

import java.io.*;

public class Tile
{
   // variables
   private String letter;
   private int row;
   private int collumn;
   public boolean flag = false;

   /**
      Tile constructor
      @param l The letter on the tile.
      @param row The row the tile is located in.
      @param collumn The collumn the tile is located in.
   */
   public Tile(char l, int row, int collumn) throws IOException
   {
      letter = Character.toString(l);
      this.letter = letter;
      this.row = row;
      this.collumn = collumn;
      this.flag = flag;
   }
   
   /**
      Alternate Tile constructor accepts Strings for letter.
      @param l The letter on the tile.
      @param row The row the tile is located in.
      @param collumn The collumn the tile is located in.
   */
   public Tile(String letter, int row, int collumn) throws IOException
   {
      this.letter = letter;
      this.row = row;
      this.collumn = collumn;
      this.flag = flag;
   }
   
   /**
      getRow method Returns the row the tile is in.
      @return row The row the tile is in.
   */
   public int getRow() throws IOException
   {
      return row;
   }
   
   /**
      getCollumn method Returns the collumn the tile is in.
      @return collumn The collumn the tile is in.
   */
   public int getCollumn() throws IOException
   {
      return collumn;
   }
   
   /**
      getFlag method Returns the boolean value of whether the tile is selected or not.
      @return flag The boolean value of whether the tile is selected or not.
   */
   public boolean getFlag()
   {
      return flag;
   }
   
   /**
      selectTile method Sets boolean flag to true.
   */
   public void selectTile()
   {
      flag = true;
   }
   
   /**
      setLetter method Sets the letter of the tile to the string parameter.
      @param letter The letter on the tile.
   */
   public void setLetter(String letter)
   {
      this.letter = letter;
   }
   
   /**
      Alternate setLetter method Sets the letter of the tile to the char parameter.
      @param l The letter on the tile.
   */
   public void setLetter(char l)
   {
      letter = Character.toString(l);
      this.letter = letter;
   }
   
   /**
      setRow method Sets the row of the tile to the int parameter.
      @param r The row on the tile.
   */
   public void setRow(int r)
   {
      row = r;
   }
   
   /**
      setCollumn method Sets the collumn of the tile to the int parameter.
      @param c The collumn on the tile.
   */
   public void setCollumn(int c)
   {
      collumn = c;
   }
   
   /**
      toString method Returns string of the letter on the tile.
      @return letter The letter on the tile.
   */
   @Override
   public String toString()
   {
      return letter;
   }
   
   /**
      equals method Checks to see if two objects are equal.
      @param other The other object.
      @return the boolean value of whether or not they are equal.
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
      else
         return false;
      
    }         

}
