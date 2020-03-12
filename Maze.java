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

    public static Maze fromTxt(String givenFile){
        Maze m = new Maze();

        try(
            BufferedReader breader = new BufferedReader(
                new FileReader(givenFile)
            )
        ){
            String line = breader.readLine();
            int i=0;
            while(line!=null){
                System.out.println(line);

                List<Tile> tileList = new ArrayList<Tile>();

                for(int j=0; j<line.length();j++){
                    char tileChar = line.charAt(j);
                    Tile tile = Tile.fromChar(tileChar);
                    tileList.add(tile);
                    
                    if(tile.getType() == Tile.Type.ENTERANCE){
                        m.enterance = tile;
                    }
                    if(tile.getType() == Tile.Type.EXIT){
                        m.exit = tile;
                    }
                }

                m.tiles.add(tileList);
                line = breader.readLine();


            }

        }catch (FileNotFoundException e) {
            System.out.println("Error: Could not open " + givenFile);
       } catch (IOException e) {
            System.out.println("Error: IOException when reading "+ givenFile);
       }    
        return m;
    }

    public static void tilesFromTxt(){
       // List<List<Tile>> thetiles;


    }

    public void setTile(Tile givenTile, Coordinate givenCoordinate){
        this.tiles.get(givenCoordinate.x).add(givenCoordinate.y, givenTile);

        //setEnterance(Tile.fromChar('#'));

    }

    public Tile getAdjacentTile(Tile givenTile, Direction givenDirection){
        /* to do
        check if tile is boundary tile
        check if tile exists - maybe do before call to this method
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
        int i = 0;
        int j = 0;
        for(i=0; i<tiles.size(); i++){
            for(j=0; j<tiles.get(i).size();j++){
               if(tiles.get(i).get(j) == givenTile){
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
        String mazeString = "";
        for(int i=0; i<tiles.size(); i++){
            for(int j=0; j<tiles.get(i).size();j++){
                if(j==tiles.get(i).size()){
                    mazeString = mazeString + tiles.get(i).get(j).toString()+"\n";
                } else{
                    mazeString = mazeString + tiles.get(i).get(j).toString();
                }
            }

        }

        return mazeString;
    }
}