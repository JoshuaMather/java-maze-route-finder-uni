import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;



public class Maze{
    public enum Direction{
        NORTH,SOUTH,EAST,WEST;
    }

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

    private Tile enterance;
    private Tile exit;
    private List<List<Tile>> tiles = new ArrayList<List<Tile>>();


    private Maze(){}

    public static Maze fromTxt(String givenFile) throws InvalidMazeException{
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
        /* to do
        check if tile is boundary tile
        check if tile exists - maybe do before call to this method
        needs fixing similar to get tile location
        */

        Boolean found = false;
        int i = 0;
        int j = 0;
        for(i=0; i<tiles.size(); i++){
            for(j=0; j<tiles.get(i).size();j++){
               if(tiles.get(i).get(j) == givenTile){
                   found = true;
                   break;
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
        return this.enterance;
    }

    public Tile getExit(){
        return this.exit;
    }

    public Tile getTileAtLocation(Coordinate givenCoordinate){
        return this.tiles.get(givenCoordinate.x).get(givenCoordinate.y);
    }

    public Coordinate getTileLocation(Tile givenTile){
        /*
        needs fixing 
        */

        int i = 0;
        int j = 0;
        for(i=0; i<tiles.size(); i++){
            for(j=0; j<tiles.get(i).size();j++){
               if(tiles.get(i).get(j) == givenTile){ // this never is true
                   break;
               }
            }
        }

        return new Coordinate(i, j);
    }


    public List<List<Tile>> getTiles(){
        return tiles;
    }


    private void setEnterance(Tile givenTile){
        this.enterance = givenTile;
    }


    private void setExit(Tile givenTile){
        this.exit = givenTile;
    }


    public String toString(){
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