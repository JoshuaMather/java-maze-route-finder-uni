package maze;

/**
 * Exception for is maze has multiple exits
 */
@SuppressWarnings("serial")
public class MultipleExitException extends InvalidMazeException{
    public MultipleExitException(){
        super("Multiple Exits");
    }
}