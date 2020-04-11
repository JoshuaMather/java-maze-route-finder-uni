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
import java.io.FileNotFoundException;

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
    /**
     * Attributes to store states of various components
     */
    private Maze m;
    private String file = "";
    private VBox mazeMap = new VBox();
    private Visualisation vis;
    private RouteFinder rf;


    /**
     * Method for visualising the maze application and handling events
     */
    public void start(Stage stage){
        /**
         * Main VBox that stores all JavaFX components on the main scene
         */
        VBox root = new VBox();

        /**
         * HBoxes that conatain the buttons for loading, saving and stepping
         */
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


        /**
         * Action when step button is pressed
         * Only runs if maze is not finished
         * Performs RouteFinder step method
         * Updates visualisation of maze using string representation of RouteFinder
         */
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

        /**
         * Main scene with the maze and buttons
         */
        Scene scene = new Scene(root);


        /**
         * VBox for loading an empty maze (a maze with no route state)
         */
        VBox root2 = new VBox();
        
        /**
         * Label for instructions, text field for file input and submit button
         */
        HBox labelBox = new HBox();
        Label label = new Label("Enter a maze file");
        labelBox.getChildren().addAll(label);

        HBox inputBox = new HBox();
        TextField tf = new TextField();
        Button submit = new Button("Submit");


        /**
         * Action for submit button
         * Searches mazes folder for specified text file
         * Visualises empty maze using string for new maze object created from file
         * Creates a new RouteFinder instance
         * Sets the scene as the main maze scene
         */
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

        /**
         * Button for going back to the main scene
         */
        HBox backBox1 = new HBox();
        Button back1 = new Button("Back");
        back1.setOnAction(e->{
            stage.setScene(scene);
        });
        backBox1.getChildren().addAll(back1);

        root2.getChildren().addAll(labelBox, inputBox, backBox1);

        /**
         * Scene for getting empty maze
         */
        Scene inputScene = new Scene(root2,200,200);


        /**
         * VBox for saving state of a maze and its route
         */
        VBox saveRoot = new VBox();
        
        /**
         * Label for instructions, text field for file input and submit button
         */
        HBox labelBox2 = new HBox();
        Label label2 = new Label("Enter name for text file to save to");
        labelBox2.getChildren().addAll(label2);

        HBox inputBox2 = new HBox();
        TextField tf2 = new TextField();
        Button submit2 = new Button("Submit");

        /**
         * Action for submitting file to save to
         * Passes file to RuoteFinder save method where file is saved to routes folder
         * Sets scene to main scene
         */
        submit2.setOnAction(e->{
            try{
                String saveFile = "../routes/"+tf2.getText();
                rf.save(saveFile);

                stage.setScene(scene);
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
        });

        inputBox2.getChildren().addAll(tf2, submit2);

        /**
         * Button for going back to the main scene
         */
        HBox backBox2 = new HBox();
        Button back2 = new Button("Back");
        back2.setOnAction(e->{
            stage.setScene(scene);
        });
        backBox2.getChildren().addAll(back2);

        saveRoot.getChildren().addAll(labelBox2, inputBox2, backBox2);

        /**
         * Scene foe saving a maze state
         */
        Scene saveScene = new Scene(saveRoot);


        /**
         * VBox for loading a maze and its route state
         */
        VBox loadRoot = new VBox();
        
        /**
         * Label for instructions, text field for file input and submit button
         */
        HBox labelBox3 = new HBox();
        Label label3 = new Label("Enter a route file to load route");
        labelBox3.getChildren().addAll(label3);

        HBox inputBox3 = new HBox();
        TextField tf3 = new TextField();
        Button submit3 = new Button("Submit");

        /**
         * Action for submitting file to load
         * Gets file from routes folder, if it exists, and passes it to RouteFinder load method
         * Updates visualisation using string representation from RouteFinder
         * Sets scene to main maze scene
         */
        submit3.setOnAction(e->{
            try{
                String loadFile = "../routes/"+tf3.getText();
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

        /**
         * Button for going back to the main scene
         */
        HBox backBox3 = new HBox();
        Button back3 = new Button("Back");
        back3.setOnAction(e->{
            stage.setScene(scene);
        });
        backBox3.getChildren().addAll(back3);

        loadRoot.getChildren().addAll(labelBox3, inputBox3, backBox3);

        /**
         * Scene for loading a maze with its route
         */
        Scene loadScene = new Scene(loadRoot);
        

        /**
         * Actions for buttons on main scene that change scene to one that handles a specific function
         */
        loadMap.setOnAction(e->{
            stage.setScene(inputScene);
        });

        saveRoute.setOnAction(e->{
            stage.setScene(saveScene);
        });

        loadRoute.setOnAction(e->{
            stage.setScene(loadScene);
        });

        /**
         * Initially sets scene to the main scene
         */
        stage.setScene(scene);
        stage.setTitle("Maze Application");
        stage.show();

    }

    public static void main(String args[]){
        launch(args);
    }
}