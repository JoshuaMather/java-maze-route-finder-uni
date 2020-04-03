/*
commands for running on my own computer
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
import maze.routing.RouteFinder;
import maze.routing.NoRouteFoundException;

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
    private Visualisation vis;
    private RouteFinder rf;

    public void start(Stage stage){
        VBox root = new VBox();

        HBox hbox1 = new HBox();
        Button loadMap = new Button("Load Empty Map");
        Button loadRoute = new Button("Load Map With Route");

        
        hbox1.getChildren().addAll(loadMap, loadRoute);


        HBox hbox2 = new HBox();
        Button saveRoute = new Button("Save Route");
        hbox2.getChildren().addAll(saveRoute);


        HBox hbox4 = new HBox();
        Button step = new Button("Step");
        hbox4.getChildren().addAll(step);

        step.setOnAction(e->{
            if(rf.isFinished() == false){
                try{
                    if(rf.step() == true){
                        System.out.println("Maze finished");
                    }
    
                    vis.clearHBoxes();
                    vis.clearVBox();
                    vis.visualiseFromString(rf.toString());
                    this.mazeMap = vis.getMaze();
                    root.getChildren().set(2, mazeMap);
                }catch(NoRouteFoundException nr){
                    System.out.println(nr);
                }
                
                
            }else{
                System.out.println("Maze is finished - no more steps to take");
            }
        });


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
                m = Maze.fromTxt("../mazes/"+file);
                Visualisation v = new Visualisation();
                vis = v;
                vis.visualiseFromString(m.toString());
                RouteFinder r = new RouteFinder(m);
                rf = r;

                this.mazeMap = vis.getMaze();
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


        VBox saveRoot = new VBox();
        
        HBox labelBox2 = new HBox();
        Label label2 = new Label("Enter name for text file to save to");
        labelBox2.getChildren().addAll(label2);

        HBox inputBox2 = new HBox();
        TextField tf2 = new TextField();
        Button submit2 = new Button("Submit");

        submit2.setOnAction(e->{
            try{
                String saveFile = "../mazes/"+tf2.getText();
                rf.save(saveFile);

                stage.setScene(scene);
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
        });

        inputBox2.getChildren().addAll(tf2, submit2);

        saveRoot.getChildren().addAll(labelBox2, inputBox2);

        Scene saveScene = new Scene(saveRoot);



        VBox loadRoot = new VBox();
        
        HBox labelBox3 = new HBox();
        Label label3 = new Label("Enter a maze file to load route");
        labelBox3.getChildren().addAll(label3);

        HBox inputBox3 = new HBox();
        TextField tf3 = new TextField();
        Button submit3 = new Button("Submit");

        submit3.setOnAction(e->{
            try{
                String loadFile = "../mazes/"+tf3.getText();
                rf = RouteFinder.load(loadFile);

                Visualisation v = new Visualisation();
                vis = v;
                vis.visualiseFromString(rf.toString());

                this.mazeMap = vis.getMaze();
                root.getChildren().clear();
                root.getChildren().addAll(hbox1, hbox2, mazeMap, hbox4);
                stage.setScene(scene);
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
        });

        inputBox3.getChildren().addAll(tf3, submit3);

        loadRoot.getChildren().addAll(labelBox3, inputBox3);

        Scene loadScene = new Scene(loadRoot);
        

        loadMap.setOnAction(e->{
            stage.setScene(inputScene);
        });

        saveRoute.setOnAction(e->{
            stage.setScene(saveScene);
        });

        loadRoute.setOnAction(e->{
            stage.setScene(loadScene);
        });


        stage.setScene(scene);
        stage.setTitle("Maze Application");
        stage.show();

    }

    public static void main(String args[]){
        launch(args);
    }
}