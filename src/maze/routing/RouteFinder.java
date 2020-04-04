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

    public RouteFinder(Maze givenMaze){
        this.maze = givenMaze;
        this.route.push(maze.getEnterance());
    }


    public Maze getMaze(){
        return this.maze;
    }


    public List<Tile> getRoute(){
        ArrayList<Tile> routeList = new ArrayList<Tile>();

        while(this.route.isEmpty() == false){
            routeList.add(this.route.pop());
        }

        Collections.reverse(routeList);

        for(int i=0; i<routeList.size(); i++){
            this.route.push(routeList.get(i));
        }

        return routeList;
    }


    public boolean isFinished(){
        return this.finished;
    }


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

    public Set getPopped(){
        return this.popped;
    }

    public Stack getStack(){
        return this.route;
    }

    public void routePush(Tile givenTile){
        this.route.push(givenTile);
    }

    public void save(String saveFile){
        System.out.println(saveFile);
        String mazeString = this.toString();
        System.out.println(mazeString);
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