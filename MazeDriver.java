
/*
/mnt/d/University/Year\ 1/Computer\ Science/GitRepos/comp16412-coursework-2_p54507jm
*/

public class MazeDriver{

    public static void main(String args[]){
        String file = "maze2.txt";
        Maze m = Maze.fromTxt(file);

        //System.out.println(m.getTiles());
        //System.out.println(m.getEnterance());
        m.getTileAtLocation(m.new Coordinate(0, 0));
        //System.out.println(m.getTileAtLocation(m.new Coordinate(0, 0)));

    }
}
