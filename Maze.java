import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;



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
    private List<List<Tile>> tiles;


    private Maze(){}

    public static Maze fromTxt(String givenFile){
        try(
            BufferedReader breader = new BufferedReader(
                new FileReader(givenFile)
            )
        ){
            String line = breader.readLine();
            while(line!=null){
                System.out.println(line);
                line = breader.readLine();
            }

        }catch (FileNotFoundException e) {
            System.out.println("Error: Could not open " + givenFile);
       } catch (IOException e) {
            System.out.println("Error: IOException when reading "+ givenFile);
       }    
        return new Maze();
    }

    /*public Tile getAdjacentTile(Tile givenTile, Direction givenDirection){

    }*/

    public Tile getEnterance(){
        return this.enterance;
    }

    public Tile getExit(){
        return this.exit;
    }

    public Tile getTileAtLocation(Coordinate givenCoordinate){
        return this.tiles.get(givenCoordinate.x).get(givenCoordinate.y);
    }

    /*public Coordinate getTileLocation(Tile givenTile){

    }*/


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