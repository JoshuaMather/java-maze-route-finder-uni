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
            coordString = coordString + "(" + this.x + " " + this.y + ")";

            return coordString;
        }
    }

    /**
     * attributes for Maze class
     */
    private Tile enterance;
    private Tile exit;
    private List<List<Tile>> tiles = new ArrayList<List<Tile>>();


    private Maze(){}

    public static Maze fromTxt(String givenFile) throws InvalidMazeException{
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
            Boolean enteranceExist = false;
            Boolean exitExist = false;
            String line = breader.readLine();
            int lineLength = line.length();

            while(line!=null){
                List<Tile> tileList = new ArrayList<Tile>();

                for(int j=0; j<line.length();j++){
                    char tileChar = line.charAt(j);
                    Tile tile = Tile.fromChar(tileChar);
                    tileList.add(tile);
                    
                    if(tile.getType() == Tile.Type.ENTERANCE){
                        if(enteranceExist==false){
                        m.enterance = tile;
                        enteranceExist = true;
                        }else{
                            throw new MultipleEnteranceException();
                        }
                    }
                    if(tile.getType() == Tile.Type.EXIT){
                        if(exitExist==false){
                        m.exit = tile;
                        exitExist = true;
                        }else{
                            throw new MultipleExitException();
                        }
                    }
                }

                m.tiles.add(tileList);

                if(lineLength != line.length()){
                    throw new RaggedMazeException();
                }
                line = breader.readLine();
                


            }

        }catch (FileNotFoundException e) {
            System.out.println("Error: Could not open " + givenFile);
       } catch (IOException e) {
            System.out.println("Error: IOException when reading "+ givenFile);
       }    

        if(m.enterance == null){
            throw new NoEnteranceException();
        }
        if(m.exit == null){
            throw new NoExitException();
        }

        return m;
    }


    public Tile getAdjacentTile(Tile givenTile, Direction givenDirection){
        /**
         * Gets the tile next to a given tile in a given direction
         */

        /* to do
        check if tile is boundary tile
        check if tile exists - maybe do before call to this method
        */

        Boolean found = false;
        int i = 0;
        int j = 0;
        
        outerloop:
        for(i=0; i<tiles.size();i++){
            if(tiles.get(i).contains(givenTile)){
                System.out.println(i);
                for(j=0; j<tiles.get(i).size();j++){
                    if(tiles.get(i).get(j).equals(givenTile)){
                        System.out.println("found"); 
                        found = true;
                        break outerloop;
                    }
                }
            }
        }

        if(found == true){
            switch(givenDirection){
                case NORTH:
                    return tiles.get(i-1).get(j);

                case SOUTH:
                    return tiles.get(i+1).get(j);

                case EAST:
                    return tiles.get(i).get(j+1);
                
                case WEST:
                    return tiles.get(i).get(j-1);
                
                default:
                    return tiles.get(i).get(j);
            }
        } else{
            return tiles.get(i).get(j);
        }
    }

    public Tile getEnterance(){
        /**
         * Gets the enterance of a maze
         */
        return this.enterance;
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
        return this.tiles.get(givenCoordinate.x).get(givenCoordinate.y);
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
                System.out.println(i);
                for(j=0; j<tiles.get(i).size();j++){
                    if(tiles.get(i).get(j).equals(givenTile)){
                        break outerloop;
                    }
                }
            }
        }
        return new Coordinate(i, j);
    }


    public List<List<Tile>> getTiles(){
        /**
         * Get the tiles for a maze
         */
        return this.tiles;
    }


    private void setEnterance(Tile givenTile){
        /**
         * Sets the enterance of a maze
         */
        this.enterance = givenTile;
    }


    private void setExit(Tile givenTile){
        /**
         * Sets the exit of a maze
         */
        this.exit = givenTile;
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