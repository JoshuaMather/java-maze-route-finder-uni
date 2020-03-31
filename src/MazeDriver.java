import maze.*;

/*
/mnt/d/University/Year\ 1/Computer\ Science/GitRepos/comp16412-coursework-2_p54507jm
javac --module-path ./lib/ --add-modules=javafx.controls
java --module-path ./lib/ --add-modules=javafx.controls
*/

public class MazeDriver{

    public static void main(String args[]){
        String file = "maze2.txt";
        try{
        Maze m = Maze.fromTxt(file);
        //System.out.println(m.getTiles());
        //System.out.println(m.getEnterance());
        //m.getTileAtLocation(m.new Coordinate(0, 0));

        System.out.println(m.toString());

        Tile tile = m.getTiles().get(0).get(0);
        System.out.println(tile);
        System.out.println(m.getAdjacentTile(tile, Maze.Direction.SOUTH));

        // System.out.println(m.getTileAtLocation(m.new Coordinate(0, 0)));
        }
        catch(Exception e){
            System.out.println(e);
        }

      

    }
}
