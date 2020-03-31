package maze;

/**
 * Exception for if there are no enterances
 */
@SuppressWarnings("serial")
public class NoEnteranceException extends InvalidMazeException{
    public NoEnteranceException(){
        super("No Enterances");
    }
}