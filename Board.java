// Bo Warren
// CS 110
// Board Class

import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class Board
{
   
   // initialize board ArrayList of ArrayLists
   ArrayList<ArrayList<Tile>> board;
   
   /**
      Board constructor Creates an ArrayList of ArrayList of Tile objects
      
   */
   public Board() throws IOException
   {
      // variables
      char side;
      String die;
   
      board = new ArrayList<>();
   
      // Creat ArrayList of all Dice
      ArrayList<String> dice = new ArrayList<String>();
      dice.add("RIFOBX");
      dice.add("IFEHEY");
      dice.add("DENOWS");
      dice.add("UTOKND");
      dice.add("HMSRAO");
      dice.add("LUPETS");
      dice.add("ACITOA");
      dice.add("YLGKUE");
      dice.add("QBMJOA");
      dice.add("EHISPN");
      dice.add("VETIGN");
      dice.add("BALIYT");
      dice.add("EZAVND");
      dice.add("RALESC");
      dice.add("UWILRG");
      dice.add("PACEMD");

      Random rand = new Random();
      ArrayList<Character> letters = new ArrayList<>();
   
      
      for (int i=0; i<16; i++) // for each die
      {
         die = dice.get(rand.nextInt(dice.size())); // choose random die
         side = die.charAt(rand.nextInt(5)); // choose random side
         letters.add(side); // add to letters ArrayList
         dice.remove(die); // remove from dice ArrayList
      }
   
      
      // Row0
      ArrayList<Tile> row0 = new ArrayList<Tile>(4);
      for (int i=0; i<4; i++) // for each element in row0 ArrayList
      {
         if (letters.get(i).equals('Q')) // if letter is Q
            row0.add( new Tile("Qu",0,i)); // create Tile object with Qu
         else
            row0.add( new Tile(letters.get(i),0,i)); // create Tile object and add to row
      }
      board.add(row0);
   
      
      // Row1
      ArrayList<Tile> row1 = new ArrayList<Tile>(4);
      for (int i=4; i<8; i++) // for each element in row1 ArrayList
      {
         if (letters.get(i).equals('Q')) // if letter is Q
            row1.add( new Tile("Qu",1,(i - 4))); // create Tile with Qu
         else
            row1.add( new Tile(letters.get(i),1,(i - 4))); // create Tile object and add to row
      }
      board.add(row1);
   
      
      // Row2
      ArrayList<Tile> row2 = new ArrayList<Tile>(4);
      for (int i=8; i<12; i++) // for each element in row2 ArrayList
      {
         if (letters.get(i).equals('Q')) // if letter is Q
            row2.add( new Tile("Qu",2,(i - 8))); // create Tile with Qu
         else
            row2.add( new Tile(letters.get(i),2,(i - 8))); // create Tile object and add to row
      }
      board.add(row2);
   
      
      // Row3
      ArrayList<Tile> row3 = new ArrayList<Tile>(4);
      for (int i=12; i<16; i++) // for each element in row3 ArrayList
      {
         if (letters.get(i).equals('Q')) // if letter is Q
            row3.add( new Tile("Qu",3,(i - 12))); // create Tile object with Qu
         else
            row3.add( new Tile(letters.get(i),3,(i - 12))); // create Tile object and add to row
      }
      board.add(row3);
   
   }
   
   /**
      toString method outputs the board in the form of a string.
      @return s The board in the form of a string
   */
   @Override
   public String toString()
   {
   
      String s = "";
      
      for (int i=0; i<4; i++) // for everyletter in a row
      {
         s += "\n"; // add new line at end of row
         
         for (int j=0; j<4; j++) // for each letter of the row
         {
            if (board.get(i).get(j).equals("Q")) // if letter a Q
               s += "Qu  "; // add Qu to string
            else
               s += board.get(i).get(j) + "   "; // add space in between letters
         }
      }
      
      return s;
   }
}