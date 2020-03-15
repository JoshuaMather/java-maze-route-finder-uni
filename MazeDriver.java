import maze.*;

/*
/mnt/d/University/Year\ 1/Computer\ Science/GitRepos/comp16412-coursework-2_p54507jm
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
        //System.out.println(m.getTileLocation(m.getTiles().get(0).get(0)));

        //System.out.println(m.getTileAtLocation(m.new Coordinate(0, 0)));
        }
        catch(Exception e){
            System.out.println(e);
        }

      

    }
}
