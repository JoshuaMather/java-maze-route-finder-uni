package maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;



public class Maze{
    /**
     * Direction inner class for maze direction
     */
    public enum Direction{
        NORTH,SOUTH,EAST,WEST;
    }


    /**
     * Coordinate innner classe for cooridinates relating to tile position
     */
    public class Coordinate{
        private int x;
        private int y;

        public Coordinate(int givenx, int giveny){
            this.x = givenx;
            this.y = giveny;
        }

        public int getX(){
            return this.x;
        }

        public int getY(){
            return this.y;
        }

        public String toString(){
            String coordString = "";
            coordString = coordString + "(" + this.getX() + ", " + this.y + ")";

            return coordString;
        }
    }

    /**
     * attributes for Maze class
     */
    private Tile entrance;
    private Tile exit;
    private List<List<Tile>> tiles = new ArrayList<List<Tile>>();


    private Maze(){}

    public static Maze fromTxt(String givenFile) throws InvalidMazeException, FileNotFoundException{
        /**
         * Reads text file and creates a Maze instance based on it
         * Will throw errors if the maze is not of the right specifications
         */
        Maze m = new Maze();

        try(
            BufferedReader breader = new BufferedReader(
                new FileReader(givenFile)
            )
        ){
            String line = breader.readLine();
            int lineLength = line.length();
            Tile entranceTile = null;
            Tile exitTile = null;

            while(line!=null){
                List<Tile> tileList = new ArrayList<Tile>();

                for(int j=0; j<line.length();j++){
                    char tileChar = line.charAt(j);
                    Tile tile = Tile.fromChar(tileChar);
                    tileList.add(tile);
                    
                    if(tile.getType() == Tile.Type.ENTRANCE){
                        //m.setEntrance(tile);
                        if(entranceTile == null){
                            entranceTile = tile;
                        }else{
                            throw new MultipleEntranceException();
                        }

                    }
                    if(tile.getType() == Tile.Type.EXIT){
                        //m.setExit(tile);
                        if(exitTile == null){
                            exitTile = tile;
                        }else{
                            throw new MultipleEntranceException();
                        }

                    }
                }

                m.tiles.add(tileList);

                if(lineLength != line.length()){
                    throw new RaggedMazeException();
                }
                line = breader.readLine();
                
            }
            m.setEntrance(entranceTile);
            m.setExit(exitTile);


        }catch (FileNotFoundException e) {
            System.out.println("Error: Could not open " + givenFile);
            throw new FileNotFoundException();
       } catch (IOException e) {
            System.out.println("Error: IOException when reading "+ givenFile);
       }    

        if(m.getEntrance() == null){
            throw new NoEntranceException();
        }
        if(m.getExit() == null){
            throw new NoExitException();
        }

        return m;
    }


    public Tile getAdjacentTile(Tile givenTile, Direction givenDirection){
        /**
         * Gets the tile next to a given tile in a given direction
         */

        Boolean found = false;
        int i = 0;
        int j = 0;
        
        outerloop:
        for(i=0; i<tiles.size();i++){
            if(tiles.get(i).contains(givenTile)){
                for(j=0; j<tiles.get(i).size();j++){
                    if(tiles.get(i).get(j).equals(givenTile)){
                        found = true;
                        break outerloop;
                    }
                }
            }
        }

        /**
         * If at edge of maze will return null if direction leads outside the maze
         * Otherwise returns tile in given direction 
         */
        if(found == true){
            switch(givenDirection){
                case NORTH:
                    if(i==0){
                        return null;
                    }
                    return tiles.get(i-1).get(j);

                case SOUTH:
                    if(i==this.tiles.size()-1){
                        return null;
                    }
                    return tiles.get(i+1).get(j);

                case EAST:
                    if(j==this.tiles.get(i).size()-1){
                        return null;
                    }
                    return tiles.get(i).get(j+1);
                
                case WEST:
                    if(j==0){
                        return null;
                    }
                    return tiles.get(i).get(j-1);
                
                default:
                    return tiles.get(i).get(j);
            }
        } else{
            return tiles.get(i).get(j);
        }
    }

    public Tile getEntrance(){
        /**
         * Gets the entrance of a maze
         */
        return this.entrance;
    }

    public Tile getExit(){
        /**
         * Gets the exit of a maze
         */
        return this.exit;
    }

    public Tile getTileAtLocation(Coordinate givenCoordinate){
        /**
         * Get the tile at a given coordinate
         */
        int column = Maze.this.getTiles().get(0).size() - 1 - givenCoordinate.getY();
        int row = givenCoordinate.getX();
        return this.tiles.get(column).get(row);
    }

    public Coordinate getTileLocation(Tile givenTile){
        /**
         * Gets location coordinate of a given tile
         */

        int i = 0;
        int j = 0;

        outerloop:
        for(i=0; i<tiles.size();i++){
            if(tiles.get(i).contains(givenTile)){
                for(j=0; j<tiles.get(i).size();j++){
                    if(tiles.get(i).get(j).equals(givenTile)){
                        break outerloop;
                    }
                }
            }
        }
        i = Maze.this.getTiles().get(0).size() - 1 - i;
        return new Coordinate(j, i);
    }


    public List<List<Tile>> getTiles(){
        /**
         * Get the tiles for a maze
         */
        return this.tiles;
    }


    private void setEntrance(Tile givenTile) throws InvalidMazeException{
        /**
         * Sets the entrance of a maze
         * Will only do so if the entrance tile is in the maze
         */
        if(this.entrance == null){
            for(int i=0; i<this.getTiles().size(); i++){
                for(int j=0; j<this.getTiles().get(i).size();j++){
                    if(givenTile == this.getTiles().get(i).get(j)){
                        this.entrance = givenTile;
                    }
                }    
            }
        }else{
            throw new MultipleEntranceException();
        }
    }


    private void setExit(Tile givenTile) throws InvalidMazeException{
        /**
         * Sets the exit of a maze
         * Will only do so if the exit tile is in the maze
         */
        if(this.exit == null){
            for(int i=0; i<this.getTiles().size(); i++){
                for(int j=0; j<this.getTiles().get(i).size();j++){
                    if(givenTile == this.getTiles().get(i).get(j)){
                        this.exit = givenTile;
                    }
                }    
            }
        }else{
            throw new MultipleExitException();
        }
    }


    public String toString(){
        /**
         * Prints out the tiles for a mazes showing whole structure 
         */
        List<List<Tile>> theTiles = this.getTiles();

        String mazeString = "";
        for(int i=0; i<theTiles.size(); i++){
            mazeString = mazeString + theTiles.get(i).get(0).toString();
            for(int j=1; j<theTiles.get(i).size();j++){
                mazeString = mazeString + theTiles.get(i).get(j).toString();
            }
            mazeString = mazeString + "\n";

        }


        return mazeString;
    }
}