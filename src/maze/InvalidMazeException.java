package maze;

/**
 * Exception for if maze is not of the right form
 */
@SuppressWarnings("serial")
public class InvalidMazeException extends Exception{
    public InvalidMazeException(String error) {
        super(error);
    }

}