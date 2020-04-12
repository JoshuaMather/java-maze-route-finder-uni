package maze.visualisation;

import maze.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Array; 
import java.util.Arrays; 


public class Visualisation{
    /**
     * VBox that contains HBoxes which represent a line for a maze
     */
    private VBox mazeMap = new VBox();
    private List<HBox> hboxes = new ArrayList<HBox>();

    public Visualisation(){}


    /**
     * Maze is visualised using a string the represents a maze and its route (though the route can be empty)
     * The string is split based on new line character
     * It is then put into an 2D Arraylist by character 
     * This list is cycled through and based on the character a type of label is created:
     * Entrance - a label with text e
     * Exit - a label with text x
     * Wall - a filled rectangle
     * Corridor - label with * for route, - for popped tile and empty for tiles not yet accessed
     * The labels are added to HBoxes line by line and these HBoxes are stored in a list
     * The list is lopped through adding each HBox to the VBox
     */
    public void visualiseFromString(String mazeString){
        String[] lines = mazeString.split("\\r?\\n");
        ArrayList<ArrayList<Character>> mazeChars = new ArrayList<ArrayList<Character>>();

        int a=0;
        for (String line : lines) {
            ArrayList<Character> stringChars = new ArrayList<Character>();
            for(int b=0; b<line.length();b++){
                stringChars.add(line.charAt(b));
            }
            mazeChars.add(stringChars);
            a=a+1;
         }

         for(int i=0; i<mazeChars.size(); i++){
            hboxes.add(new HBox());
            for(int j=0; j<mazeChars.get(i).size();j++){
                if(mazeChars.get(i).get(j) == '#'){
                    Rectangle rectangle = new Rectangle(20,20);
                    rectangle.setArcHeight(10.0d);
                    rectangle.setArcWidth(10.0d);

                    hboxes.get(i).getChildren().add(rectangle);
                }
                else if(mazeChars.get(i).get(j) == 'e'){
                    Label label = new Label("e");
                    label.setMinWidth(20);
                    label.setMinHeight(20);
                    hboxes.get(i).getChildren().add(label);

                }
                else if(mazeChars.get(i).get(j) == 'x'){
                    Label label = new Label("x");
                    label.setMinWidth(20);
                    label.setMinHeight(20);
                    hboxes.get(i).getChildren().add(label);

                }
                else if(mazeChars.get(i).get(j) == 'X'){
                    Label label = new Label("X");
                    label.setMinWidth(20);
                    label.setMinHeight(20);
                    label.setTextFill(Color.BLUE);
                    hboxes.get(i).getChildren().add(label);

                }
                else if(mazeChars.get(i).get(j) == '*'){
                    Label label = new Label("*");
                    label.setMinWidth(20);
                    label.setMinHeight(20);
                    label.setTextFill(Color.BLUE);
                    hboxes.get(i).getChildren().add(label);

                }
                else if(mazeChars.get(i).get(j) == '-'){
                    Label label = new Label("-");
                    label.setMinWidth(20);
                    label.setMinHeight(20);
                    label.setTextFill(Color.RED);
                    hboxes.get(i).getChildren().add(label);

                }
                else{
                    Label label = new Label();
                    label.setMinWidth(20);
                    label.setMinHeight(20);
                    hboxes.get(i).getChildren().add(label);
                }
                
            }
        }

        for(int k=0; k<hboxes.size(); k++){
            mazeMap.getChildren().add(hboxes.get(k));
        }
        
    }


    /**
     * Clears the VBox attribute
     */
    public void clearVBox(){
        this.mazeMap.getChildren().clear();
    }


     /**
     * Clears the VBox attribute
     */
    public void clearHBoxes(){
        for(int i=0; i<hboxes.size(); i++){
            this.hboxes.get(i).getChildren().clear();
        }
    }


    /**
     * Returns the VBox that visualises the maze
     */
    public VBox getMaze(){
        return this.mazeMap;
    }


    /**
     * Returns the list of HBoxes
     */
    public List<HBox> getHboxes(){
        return this.hboxes;
    }
   


}