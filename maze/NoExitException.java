package maze;

/**
 * Exception for if there are no exits
 */
@SuppressWarnings("serial")
public class NoExitException extends InvalidMazeException{
    public NoExitException(){
        super("No Exits");
    }
}