package maze;

/**
 * Exception for if maze is not of the right form
 */
@SuppressWarnings("serial")
public class InvalidMazeException extends RuntimeException{
    public InvalidMazeException(String error) {
        super(error);
    }

}