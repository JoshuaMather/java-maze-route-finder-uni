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
        File theFile = new File(loadFile);
        if(theFile.length() == 0){
            System.out.println("File is empty");
            return null;
        }
        
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
    public void save(String saveFile) throws IOException{
        String mazeString = this.toString();

        try{
            File file = new File(saveFile);

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
    
            printWriter.print(mazeString);
    
            fileWriter.close();
            printWriter.close();
        }catch(IOException e){
            System.out.println("Error");
            throw new IOException();
          }
    

    }


    /**
     * Get next tile for South direction
     */
    public String southCheck(Tile currentTile){
        Tile nextTile;

        if(this.maze.getAdjacentTile(currentTile, Maze.Direction.SOUTH) != null){
            nextTile = this.maze.getAdjacentTile(currentTile, Maze.Direction.SOUTH);

            if(nextTile.getType() == Tile.Type.CORRIDOR){
                if(this.route.contains(nextTile) == false){
                    if(this.popped.contains(nextTile) == false){
                        this.route.push(nextTile);
                        return "corridor";
            
                    }
                }
            }else if(nextTile.getType() == Tile.Type.EXIT){
                this.route.push(nextTile);
                this.finished = true;
                return "exit";
            }
        }
        return "";
    }


    /**
     * Get next tile for North direction
     */
    public String northCheck(Tile currentTile){
        Tile nextTile;

        if(this.maze.getAdjacentTile(currentTile, Maze.Direction.NORTH) != null){
            nextTile = this.maze.getAdjacentTile(currentTile, Maze.Direction.NORTH);

            if(nextTile.getType() == Tile.Type.CORRIDOR){
                if(this.route.contains(nextTile) == false){
                    if(this.popped.contains(nextTile) == false){
                        this.route.push(nextTile);
                        return "corridor";
            
                    }
                }
            }else if(nextTile.getType() == Tile.Type.EXIT){
                this.route.push(nextTile);
                this.finished = true;
                return "exit";
            }
        }
        return "";
    }


    /**
     * Get next tile for East direction
     */
    public String eastCheck(Tile currentTile){
        Tile nextTile;
        
        if(this.maze.getAdjacentTile(currentTile, Maze.Direction.EAST) != null){
            nextTile = this.maze.getAdjacentTile(currentTile, Maze.Direction.EAST);

            if(nextTile.getType() == Tile.Type.CORRIDOR){
                if(this.route.contains(nextTile) == false){
                    if(this.popped.contains(nextTile) == false){
                        this.route.push(nextTile);
                        return "corridor";
            
                    }
                }
            }else if(nextTile.getType() == Tile.Type.EXIT){
                this.route.push(nextTile);
                this.finished = true;
                return "exit";
            }
        }
        return "";
    }


    /**
     * Get next tile for West direction
     */
    public String westCheck(Tile currentTile){
        Tile nextTile;

        if(this.maze.getAdjacentTile(currentTile, Maze.Direction.WEST) != null){
            nextTile = this.maze.getAdjacentTile(currentTile, Maze.Direction.WEST);

            if(nextTile.getType() == Tile.Type.CORRIDOR){
                if(this.route.contains(nextTile) == false){
                    if(this.popped.contains(nextTile) == false){
                        this.route.push(nextTile);
                        return "corridor";
            
                    }
                }
            }else if(nextTile.getType() == Tile.Type.EXIT){
                this.route.push(nextTile);
                this.finished = true;
                return "exit";
            }
        }
        return "";
    }


    /**
     * Method for steping through a maze
     * First checks if the route stack is empty, if it is then no route is able to be found as there would be no route to exit
     * Next gets the current tile, the one at the top of the stack
     * The next tile in the route is found by using the difference in coordinates between the current tile and the exit tile
     * This is done to try to find the quickest route though it can lead to dead ends and the route may not be the quickest
     * Once one of the checks works the step method returns a Booelan, true for the exit found and false for otherwise
     * If the next tile is the exit then the maze is finished and so the finished boolean is set to true
     * If no next tile is found then the top tile is popped off the stack allowing the route to backtrack
     */
    public boolean step() throws NoRouteFoundException{
        if(this.route.isEmpty()){
            throw new NoRouteFoundException();
        }

        if(this.finished == true){
            return true;
        }

        Tile nextTile;
        Tile poppedTile;
        Tile currentTile = this.route.peek();

        Maze.Coordinate currentCo = this.getMaze().getTileLocation(currentTile);
        Maze.Coordinate exitCo = this.getMaze().getTileLocation(this.getMaze().getExit());

        /**
         * Difference between coordinates for exit and current tile used to decide where to step next
         * If x positive check east first, if negative west
         * If y positive check north first, if negative south
         * x checked first, then y checked
         */
        int xDiff = exitCo.getX() - currentCo.getX();
        int yDiff = exitCo.getY() - currentCo.getY();

        if(xDiff >= 0){
            String e = eastCheck(currentTile);
            if(e == "corridor"){
                return false;
            }else if(e == "exit"){
                return true;
            }

            String w = westCheck(currentTile);
            if(w == "corridor"){
                return false;
            }else if(w == "exit"){
                return true;
            }
        }else{
            String w = westCheck(currentTile);
            if(w == "corridor"){
                return false;
            }else if(w == "exit"){
                return true;
            }

            String e = eastCheck(currentTile);
            if(e == "corridor"){
                return false;
            }else if(e == "exit"){
                return true;
            }
        }

        if(yDiff >= 0){
            String n = northCheck(currentTile);
            if(n == "corridor"){
                return false;
            }else if(n == "exit"){
                return true;
            }

            String s = southCheck(currentTile);
            if(s == "corridor"){
                return false;
            }else if(s == "exit"){
                return true;
            }
        }else{
            String s = southCheck(currentTile);
            if(s == "corridor"){
                return false;
            }else if(s == "exit"){
                return true;
            }

            String n = northCheck(currentTile);
            if(n == "corridor"){
                return false;
            }else if(n == "exit"){
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
     * However the exit becomes X if it is part of the route, this representation shows that the exit has been found 
     */
    public String toString(){
        String s = "";
        List<List<Tile>> theTiles = this.maze.getTiles();


        for(int i=0; i<theTiles.size(); i++){
            for(int j=0; j<theTiles.get(i).size();j++){
                if(theTiles.get(i).get(j).getType() == Tile.Type.CORRIDOR){
                    if(route.contains(theTiles.get(i).get(j))){
                        s = s + "*";
                    }else if(popped.contains(theTiles.get(i).get(j))){
                        s = s + "-";
                    }else{
                        s = s + ".";
                    }
                }else if(theTiles.get(i).get(j).getType() == Tile.Type.EXIT){
                    if(route.contains(theTiles.get(i).get(j))){
                        s = s + "X";
                    }else{
                        s = s + "x";
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