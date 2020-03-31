package maze.routing;

import maze.*;
//import maze.visualisation.Visualisation;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.IOException;


public class RouteFinder{
    private Maze maze;
    private Stack<Tile> route = new Stack<Tile>();
    private boolean finished;
    private Set<Tile> popped = new HashSet<Tile>();

    public RouteFinder(Maze givenMaze){
        this.maze = givenMaze;
        route.push(maze.getEnterance());
    }


    public Maze getMaze(){
        return this.maze;
    }


    // public List<Tile> getRoute(){
    //     /*
    //     return as list
    //      */
    //     ArrayList<Tile> routeList;



    //     return routeList;
    // }


    public boolean isFinished(){
        return this.finished;
    }


    public void save(String string){

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
        return s;
    }
}