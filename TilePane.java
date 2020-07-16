// Bo Warren
// CS 110
// TilePane class

import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import java.io.*;

public class TilePane extends HBox
{  
   // initialize variables
   int row;
   int collumn;
   boolean flagged;
   Tile tile;
   
   /**
      TilePane Constructor
      @param t The tile being converted to a TilePane
   */
   public TilePane(Tile t) throws IOException
   {
      tile = t;
      row = t.getRow();
      flagged = t.getFlag();
      collumn = t.getCollumn();
      Text letter = new Text(t.toString());
      letter.setFont(Font.font("Ariel",18));
      this.getChildren().add(letter);
      this.setAlignment(Pos.CENTER);
      this.setPrefSize(50,50);
      this.setStyle("-fx-border-width: 2.5;" +
                   "-fx-border-color: black;" +
                   "-fx-border-radius: 5;" +
                   "-fx-background-color: white;");
   }
   
   /**
      getRow method
      @return row The row the TilePane is in.
   */
   public int getRow()
   {
      return row;
   }
   
   /**
      getCollumn method
      @return collumn The collumn the TilePane is in.
   */
   public int getCollumn()
   {
      return collumn;
   }
   
   /**
      getFlagged method
      @return flagged The boolean value of whether or not the TilePane is selected.
   */
   public boolean getFlagged()
   {
      return flagged;
   }
   
   /**
      getTile method
      @return tile The Tile object attributes the TilePane holds.
   */
   public Tile getTile()
   {
      return tile;
   }
   
   /**
      setSelected method changes the look of the TilePane when selected.
   */
   public void setSelected()
   {
      this.setStyle("-fx-border-width: 2.5;" +
                    "-fx-border-color: black;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-color: yellow;");
   
   }
   
   /**
      setUnselected method changes the look of the TilePane when unselected.
   */
   public void setUnselected()
   {
      this.setStyle("-fx-border-width: 2.5;" +
                    "-fx-border-color: black;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-color: white;");
   
   }

}