package maze.routing;

import maze.*;
//import maze.visualisation.Visualisation;

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


    public boolean step(){
        /*
        check each direction
        move 
        if all null pop
        */

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
                        Maze.Coordinate c = this.maze.getTileLocation(nextTile);

                        // change visualisation
                        //System.out.println(this.vis.getHboxes().get(c.getX()));


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
                        Maze.Coordinate c = this.maze.getTileLocation(nextTile);

                        // change visualisation
                        //System.out.println(this.vis.getHboxes().get(c.getX()));


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
                        Maze.Coordinate c = this.maze.getTileLocation(nextTile);

                        // change visualisation
                        //System.out.println(this.vis.getHboxes().get(c.getX()));


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
                        Maze.Coordinate c = this.maze.getTileLocation(nextTile);

                        // change visualisation
                        //System.out.println(this.vis.getHboxes().get(c.getX()));


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
        // change visualisation to -
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