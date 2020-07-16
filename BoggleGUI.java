// Bo Warren
// CS 110
// BoggleGUI

import javafx.application.Application;
import javafx.application.Platform; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class BoggleGUI extends Application
{
   // initialize game
   Game g;
   
   // initialize Panes
   BorderPane pane;
   GridPane board;
   HBox top;
   HBox bottom;
   VBox scoreArea;
   VBox buttons;
   
   // initialize variables
   boolean stop = false;
   Text message;
   ArrayList<TilePane> tiles;
   ArrayList<String> wordArray;
   
   public void start(Stage primaryStage) throws IOException
   {  
      // Create Panes   
      pane = new BorderPane();
      board = new GridPane();
      top = new HBox();
      bottom = new HBox();
      scoreArea = new VBox();
      buttons = new VBox();
      
      // Title
      Text title = new Text("BOGGLE");
      title.setFont(Font.font("Ariel",24));
      
      // Top
      top.setStyle("-fx-background-color:red;");
      top.setPrefSize(50,50);
      top.setAlignment(Pos.CENTER);
      top.getChildren().add(title);
      
      // Bottom
      bottom.setPrefSize(50,50);
      bottom.setAlignment(Pos.CENTER);
      bottom.setStyle("-fx-background-color: red;");
      
      // Score Area
      String s = "Score: 0\n\nWords:\n"; // default score area text
      Text score = new Text(s);
      score.setFont(Font.font("Ariel",14));
      scoreArea.setStyle("-fx-background-color:red;");
      scoreArea.setPrefSize(100,100);
      scoreArea.getChildren().add(score);
      
      // Buttons
      buttons.setStyle("-fx-background-color:red;");
      buttons.setPrefSize(100,100);
      buttons.setAlignment(Pos.CENTER);
      buttons.setSpacing(10);  
          
      // test word
      Button test = new Button("Test Word");
      test.setAlignment(Pos.CENTER);
      test.setStyle("-fx-background-color: yellow;");
      test.setPrefSize(85,15);
      
      // clear
      Button clear = new Button("Clear");
      clear.setAlignment(Pos.CENTER);
      clear.setStyle("-fx-background-color: yellow;");
      clear.setPrefSize(85,15);
      
      // new game
      Button restart = new Button("New Game");
      restart.setAlignment(Pos.CENTER);
      restart.setStyle("-fx-background-color: yellow;");
      restart.setPrefSize(85,15);
      
      // end
      Button end = new Button("End Game");
      end.setAlignment(Pos.CENTER);
      end.setStyle("-fx-background-color: yellow;");
      end.setPrefSize(85,15);
      
      // add buttons
      buttons.getChildren().add(test);
      buttons.getChildren().add(clear);
      buttons.getChildren().add(restart);
      buttons.getChildren().add(end);
      
      // create game
      g = new Game();
      
      // create ArrayLists
      tiles = new ArrayList<>();
      wordArray = new ArrayList<>(); 
      
      // filling the board with tiles
      for (int i=0; i<4; i++) // for each row
      {
         for (int j=0; j<4; j++) // for each tile in row
         {
            TilePane tp = new TilePane(g.b.board.get(i).get(j)); // create new TilePane
            tp.setOnMouseClicked(this::handleClick);
            board.add(tp,i,j); // add to board
            tiles.add(tp); // add to tiles ArrayList
         }
      }
      
      // Test Word Button Functionality
      test.setOnAction( new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e)
         {
            if (!stop) { // if game not over
               try
               {  
                  // create string of the word being tested
                  String w = "";
                  for (int i=0; i < g.selected.size(); i++)
                  {
                     w += g.selected.get(i);
                  }
               
                  if (g.testSelected()) // if test valid
                  {
                     // Update score area
                     scoreArea.getChildren().clear();
                     String s = "Score: " + g.totalPoints + "\n\nWords:\n";
                     for (int i=0; i < g.words.size(); i++)
                     {
                        s += g.words.get(i).toString().toUpperCase() + "\n";
                     }
                     Text score = new Text(s);
                     scoreArea.getChildren().add(score);
                  
                     wordArray.add(w); // add to word ArrayList
                  }
                  else // if test invalid
                  {
                     // update message
                     bottom.getChildren().clear();
                     if (wordArray.contains(w)) // if word already selected
                        message = new Text("That word has already been selected.");
                     else // if invalid word
                        message = new Text("That is not a valid word.");
                     bottom.getChildren().add(message);
                  }   
               
                  // deselect TilePanes
                  for (int i=0; i<16; i++)
                  {
                     tiles.get(i).setUnselected();
                  }
               }
               catch (IOException z)
               {
                  // deselect TilePanes
                  for (int i=0; i<16; i++)
                  {
                     tiles.get(i).setUnselected();
                  }
               }
            }
         }
      });
      
      // Clear Button Functionality
      clear.setOnAction( new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e)
         {
            if (!stop) { // if game not over
               // deselect TilePanes
               for (int i=0; i<16; i++)
               {
                  tiles.get(i).setUnselected();
               }
            
               g.clearSelected(); // clear selected ArrayList
            }
         }
      });
      
      // New Game Button Functionality
      restart.setOnAction( new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e)
         {
            stop = false; // restart the game
            try
            {
               g.b = new Board(); // create new board
               
               // clear old game data
               tiles.clear();
               wordArray.clear();
               g.words.clear();
               bottom.getChildren().clear();
               
               // set board
               for (int i=0; i<4; i++) // for each row
               {
                  for (int j=0; j<4; j++) // for each collumn
                  {
                     TilePane tp = new TilePane(g.b.board.get(i).get(j));
                     tp.setOnMouseClicked(this::handleClick);
                     board.add(tp,i,j);
                     tiles.add(tp);      
                  }
               }
               
               // set score area
               g.setTotalPoints(0); // reset points
               scoreArea.getChildren().clear();
               String s = "Score: 0\n\nWords:\n"; // default score area text
               Text score = new Text(s);
               scoreArea.getChildren().add(score);
                  
            }
            catch (IOException z)
            {
               // stop game and display error message if try fails
               stop = true;
               bottom.getChildren().clear();
               message = new Text("ERROR.");
               bottom.getChildren().add(message);
            }
         } 
         
         /**
            handleClick method performs task when tiles are clicked on
            @param e The MouseEvent that takes place when a TilePane is clicked on.
         */
         public void handleClick(MouseEvent e)
         {  
            TilePane tp = (TilePane)(e.getSource());
            if (!stop) { // if game not over
               try
               {
                  bottom.getChildren().clear(); // clear message
                  if (g.getSelectedTiles().contains(g.getTile(tp.getRow(),tp.getCollumn()))) // if tile is selected
                  {
                     if (tp.getTile().equals(g.selected.get(g.selected.size()-1))) // if tile was last one selected
                     { 
                        // deselect tile
                        g.removeFromSelected(tp.getRow(), tp.getCollumn());
                        tp.setUnselected();
                     }
                  }
                  else // if tile not selected
                  {
                     if (g.isValidSelection(tp.getRow(), tp.getCollumn())) // if tile is adjacent to previous
                     {
                        // select tile
                        g.addToSelected(tp.getRow(), tp.getCollumn());
                        tp.setSelected();
                     }
                     else // if tile not adjacent to previous
                     {
                        // update message
                        message = new Text("Invalid selection.");
                        bottom.getChildren().add(message);
                     }
                  }
         
               }
               catch (IOException z)
               {
                  // update message
                  message = new Text("Invalid selection.");
                  bottom.getChildren().add(message);
               }
            }         
         }
      });
      
      // End Button Functionality
      end.setOnAction( new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e)
         {      
            bottom.getChildren().clear();
            message = new Text("Game has ended. Final Score: " + g.totalPoints);
            bottom.getChildren().add(message);
            stop = true;
         }
      });
      
      // Create the BorderPane
      pane.setCenter(board);
      pane.setTop(top);
      pane.setLeft(scoreArea);
      pane.setRight(buttons);
      pane.setBottom(bottom);
      
      // Create the Scene
      Scene scene = new Scene(pane);
      primaryStage.setScene(scene);
      primaryStage.show();
   
   }
   
   /**
      handleClick method performs task when tiles are clicked on
      @param e The MouseEvent that takes place when a TilePane is clicked on.
   */
   public void handleClick(MouseEvent e)
   {  
      TilePane tp = (TilePane)(e.getSource());
      if (!stop) { // if game not over
         try
         {
            bottom.getChildren().clear(); // clear message
            if (g.getSelectedTiles().contains(g.getTile(tp.getRow(),tp.getCollumn()))) // if tile is selected
            {
               if (tp.getTile().equals(g.selected.get(g.selected.size()-1))) // if tile was last one selected
               { 
                  // deselect tile
                  g.removeFromSelected(tp.getRow(), tp.getCollumn());
                  tp.setUnselected();
               }
            }
            else // if tile not selected
            {
               if (g.isValidSelection(tp.getRow(), tp.getCollumn())) // if tile is adjacent to previous
               {
                  // select tile
                  g.addToSelected(tp.getRow(), tp.getCollumn());
                  tp.setSelected();
               }
               else // if tile not adjacent to previous
               {
                  // update message
                  message = new Text("Invalid selection.");
                  bottom.getChildren().add(message);
               }
            }
         
         }
         catch (IOException z)
         {
            // update message
            message = new Text("Invalid selection.");
            bottom.getChildren().add(message);
         }
      }         
      
   }
   
   public static void main(String []args)
   {
      launch(args);
   }

}