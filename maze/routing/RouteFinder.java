package maze.routing;

import maze.*;
import java.util.Stack;
import java.util.List;

public class RouteFinder{
    private Maze maze;
    private Stack<Tile> route;
    private boolean finished;

    public RouteFinder(Maze givenMaze){
        this.maze = givenMaze;
        route.push(maze.getEnterance());
    }


    public Maze getMaze(){
        return this.maze;
    }


    public List<Tile> getRoute(){
        return this.route;
    }


    public boolean isFinished(){
        return this.finished;
    }


    // public static RouteFinder load(String string){

    // }


    public void save(String string){

    }


    public boolean step(){
        return true;
    }


    public String toString(){
        return "a";
    }
}