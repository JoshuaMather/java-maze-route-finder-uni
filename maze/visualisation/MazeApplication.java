package maze.visualisation;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class MazeApplication extends Application{

    @Override
    public void start (Stage stage){

        VBox root = new VBox();

        Scene scene = new Scene(root,200,200);

        stage.setScene(scene);
        stage.setTitle("Maze Application");
        stage.show();
    }
}