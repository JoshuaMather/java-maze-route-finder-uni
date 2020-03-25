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

import java.util.List;
import java.util.ArrayList;


public class Visualisation{
    private VBox mazeMap = new VBox();
    private List<HBox> hboxes = new ArrayList<HBox>();

    public Visualisation(Maze m){
        List<List<Tile>> tiles = m.getTiles();

        for(int i=0; i<tiles.size(); i++){
            hboxes.add(new HBox());
            for(int j=0; j<tiles.get(i).size();j++){
                if(tiles.get(i).get(j).getType() == Tile.Type.WALL){
                    Rectangle rectangle = new Rectangle(20,20);
                    rectangle.setArcHeight(10.0d);
                    rectangle.setArcWidth(10.0d);

                    hboxes.get(i).getChildren().add(rectangle);
                }
                else if(tiles.get(i).get(j).getType() == Tile.Type.ENTERANCE){
                    Label label = new Label("e");
                    label.setMinWidth(20);
                    label.setMinHeight(20);
                    hboxes.get(i).getChildren().add(label);

                }
                else if(tiles.get(i).get(j).getType() == Tile.Type.EXIT){
                    Label label = new Label("X");
                    label.setMinWidth(20);
                    label.setMinHeight(20);
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

    public VBox getMaze(){
        return this.mazeMap;
    }
   


}