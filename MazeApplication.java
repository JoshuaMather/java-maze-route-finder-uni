/*
/mnt/d/University/Year\ 1/Computer\ Science/GitRepos/comp16412-coursework-2_p54507jm
javac --module-path ./lib/ --add-modules=javafx.controls
java --module-path ./lib/ --add-modules=javafx.controls

/mnt/c/Program\ Files/Java/jdk-11.0.6/bin/javac.exe --module-path ./lib/ --add-modules=javafx.controls MazeApplication.java
/mnt/c/Program\ Files/Java/jdk-11.0.6/bin/java.exe --module-path ./lib/ --add-modules=javafx.controls MazeApplication
*/

/**
 * imports for the maze application
 */
import maze.*;
import maze.visualisation.Visualisation;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;



public class MazeApplication extends Application{
    private Maze m;
    private String file = "";
    private VBox mazeMap = new VBox();

    public void start(Stage stage){
        VBox root = new VBox();

        HBox hbox1 = new HBox();
        Button loadMap = new Button("Load Map");
        
        hbox1.getChildren().addAll(loadMap);


        HBox hbox2 = new HBox();
        Button loadRoute = new Button("Load Route");
        Button saveRoute = new Button("Save Route");
        hbox2.getChildren().addAll(loadRoute, saveRoute);

        
        HBox hbox4 = new HBox();
        Button step = new Button("Step");
        hbox4.getChildren().addAll(step);


        root.getChildren().addAll(hbox1, hbox2, mazeMap, hbox4);

        Scene scene = new Scene(root);



        VBox root2 = new VBox();
        
        HBox labelBox = new HBox();
        Label label = new Label("Enter a maze file");
        labelBox.getChildren().addAll(label);

        HBox inputBox = new HBox();
        TextField tf = new TextField();
        Button submit = new Button("Submit");

        submit.setOnAction(e->{
            file = tf.getText();
            try{
                Maze m = Maze.fromTxt(file);
                Visualisation v = new Visualisation(m);

                this.mazeMap = v.getMaze();
                root.getChildren().clear();
                root.getChildren().addAll(hbox1, hbox2, mazeMap, hbox4);
                stage.setScene(scene);
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
        });

        inputBox.getChildren().addAll(tf, submit);

        root2.getChildren().addAll(labelBox, inputBox);

        Scene inputScene = new Scene(root2,200,200);

        loadMap.setOnAction(e->{
            stage.setScene(inputScene);
        });


        stage.setScene(inputScene);
        stage.setTitle("Maze Application");
        stage.show();

    }

    public static void main(String args[]){
        launch(args);
    }
}