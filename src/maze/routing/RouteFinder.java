package maze.routing;

import maze.*;
import maze.routing.NoRouteFoundException;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.IOException;
import java.util.Collections;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class RouteFinder{
    private Maze maze;
    private Stack<Tile> route = new Stack<Tile>();
    private boolean finished;
    private Set<Tile> popped = new HashSet<Tile>();

    /**
     * Constructor takes a maze, sets its maze attribute to it and pushes the enterance onto the stack
     */
    public RouteFinder(Maze givenMaze){
        this.maze = givenMaze;
        this.route.push(maze.getEntrance());
    }


    /**
     * Gets the maze of the RouteFinder
     */
    public Maze getMaze(){
        return this.maze;
    }


    /**
     * Gets the route by putting the tiles from the stack into a list
     */
    public List<Tile> getRoute(){
        ArrayList<Tile> routeList = new ArrayList<Tile>();

        while(this.route.isEmpty() == false){
            routeList.add(this.route.pop());
        }

        Collections.reverse(routeList);

        /**
         * Have to push back onto the stack as tile were popped off to get them into the lits
         */
        for(int i=0; i<routeList.size(); i++){
            this.route.push(routeList.get(i));
        }

        return routeList;
    }


    /**
     * Gets boolean for if the maze has been solved
     */
    public boolean isFinished(){
        return this.finished;
    }


    /**
     * Method for loading a maze with its route state
     * File to load is passed in and a new maze instance is set using it
     * A new RouteFinder is created using the maze created
     * The file is read through and a string is populated with the text in the file
     * The step method is done on the new RouteFinder until the string matches the text representaion of the RouteFinder 
     * When they do match the route will be the same as the one stored in the text file
     * The RouteFinder object is then returned
     */
    public static RouteFinder load(String loadFile){  
        try(
            BufferedReader breader = new BufferedReader(
                new FileReader(loadFile)
            )
        ){
            Maze loadMaze = Maze.fromTxt("../mazes/"+loadFile);
            RouteFinder rf = new RouteFinder(loadMaze);
            String routeState = "";    

            String line = breader.readLine();

            while(line!=null){
                routeState = routeState + line + "\n";

                line = breader.readLine();
            }
            
            while(rf.toString().equals(routeState) == false){
                try{
                    rf.step();
                }catch(NoRouteFoundException nr){
                    System.out.println(nr);
                }
            }

            return rf;

        }catch (FileNotFoundException e) {
            System.out.println("Error: Could not open " + loadFile);
        }catch (IOException e) {
            System.out.println("Error: IOException when reading "+ loadFile);
        }catch(InvalidMazeException e){
            System.out.println(e);
        }
        return null;
    }


    /**
     * Method for saving a maze and route to text file
     * Name of file to save to is passed in
     * File is then written to with RouteFinder's string representation
     */
    public void save(String saveFile){
        String mazeString = this.toString();
        File file = new File(saveFile);

        try(FileWriter fileWriter = new FileWriter(file)){

            PrintWriter printWriter = new PrintWriter(fileWriter);
    
            printWriter.print(mazeString);
    
            fileWriter.close();
            printWriter.close();
        }catch(IOException e){
            System.out.println("Error");
          }
    

    }


    /**
     * Method for steping through a maze
     * First checks if the route stack is empty, if it is then no route is able to be found as there would be no route to exit
     * Next gets the current tile, the one at the top of the stack
     * The next tile in the route is then found by checking directions in the order South, East, North, West and once one of them works the step method returns a boolean
     * I chose this order as based on the example mazes this leads to the quickest route, it will work for any maze type though may not always be the quickest route
     *  If the next tile is the exit then the maze is finished and so the finished boolean is set to true
     * If no next tile is found then the top tile is popped off the stack allowing the route to backtrack
     */
    public boolean step() throws NoRouteFoundException{
        if(this.route.isEmpty()){
            throw new NoRouteFoundException();
        }

        Tile nextTile;
        Tile poppedTile;
        Tile currenTile = this.route.peek();

        /**
         * South check
         */
        if(this.maze.getAdjacentTile(currenTile, Maze.Direction.SOUTH) != null){
            nextTile = this.maze.getAdjacentTile(currenTile, Maze.Direction.SOUTH);

            if(nextTile.getType() == Tile.Type.CORRIDOR){
                if(this.route.contains(nextTile) == false){
                    if(this.popped.contains(nextTile) == false){
                        this.route.push(nextTile);
                        return false;
            
                    }
                }
            }else if(nextTile.getType() == Tile.Type.EXIT){
                this.route.push(nextTile);
                this.finished = true;
                return true;
            }
        }


        /**
         * East check
         */
        if(this.maze.getAdjacentTile(currenTile, Maze.Direction.EAST) != null){
            nextTile = this.maze.getAdjacentTile(currenTile, Maze.Direction.EAST);

            if(nextTile.getType() == Tile.Type.CORRIDOR){
                if(this.route.contains(nextTile) == false){
                    if(this.popped.contains(nextTile) == false){
                        this.route.push(nextTile);
                        return false;
            
                    }
                }
            }else if(nextTile.getType() == Tile.Type.EXIT){
                this.route.push(nextTile);
                this.finished = true;
                return true;
            }
        }


        /**
         * North check
         */
        if(this.maze.getAdjacentTile(currenTile, Maze.Direction.NORTH) != null){
            nextTile = this.maze.getAdjacentTile(currenTile, Maze.Direction.NORTH);

            if(nextTile.getType() == Tile.Type.CORRIDOR){
                if(this.route.contains(nextTile) == false){
                    if(this.popped.contains(nextTile) == false){
                        this.route.push(nextTile);
                        return false;
            
                    }
                }
            }else if(nextTile.getType() == Tile.Type.EXIT){
                this.route.push(nextTile);
                this.finished = true;
                return true;
            }
        }


        /**
         * West check
         */
        if(this.maze.getAdjacentTile(currenTile, Maze.Direction.WEST) != null){
            nextTile = this.maze.getAdjacentTile(currenTile, Maze.Direction.WEST);

            if(nextTile.getType() == Tile.Type.CORRIDOR){
                if(this.route.contains(nextTile) == false){
                    if(this.popped.contains(nextTile) == false){
                        this.route.push(nextTile);
                        return false;
            
                    }
                }
            }else if(nextTile.getType() == Tile.Type.EXIT){
                this.route.push(nextTile);
                this.finished = true;
                return true;
            }
        }
        

        poppedTile = this.route.pop();
        this.popped.add(poppedTile);
        
        return false;
    }


    /**
     * Method to get the string representation of the maze and current route state
     * If a tile is in the popped list then it is represented as a -
     * If a tile is in the route stack then it is represented as a *
     * The entrance and exit keep their representation as e and x respectively, allowing them to be identified when the file is loaded
     */
    public String toString(){
        String s = "";
        List<List<Tile>> theTiles = this.maze.getTiles();


        for(int i=0; i<theTiles.size(); i++){
            if(theTiles.get(i).get(0).getType() == Tile.Type.CORRIDOR){
                if(route.contains(theTiles.get(i).get(0))){
                    s = s + "*";
                }else if(popped.contains(theTiles.get(i).get(0))){
                    s = s + "-";
                }else{
                    s = s + ".";
                }
            }else{
                s = s + theTiles.get(i).get(0).toString();
            }
            

            for(int j=1; j<theTiles.get(i).size();j++){
                if(theTiles.get(i).get(j).getType() == Tile.Type.CORRIDOR){
                    if(route.contains(theTiles.get(i).get(j))){
                        s = s + "*";
                    }else if(popped.contains(theTiles.get(i).get(j))){
                        s = s + "-";
                    }else{
                        s = s + ".";
                    }
                }else{
                    s = s + theTiles.get(i).get(j).toString();
                }
            }
            s = s + "\n";
        }
        return s;
    }
}