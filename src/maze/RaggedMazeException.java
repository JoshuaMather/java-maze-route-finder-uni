package maze;

/**
 * Exxception for if the maze is ragged
 */
@SuppressWarnings("serial")
public class RaggedMazeException extends InvalidMazeException{
    public RaggedMazeException(){
        super("Ragged Maze");
    }
}