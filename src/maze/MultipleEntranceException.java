package maze;

/**
 * Exception for if maze has multiple enterances
 */
@SuppressWarnings("serial")
public class MultipleEntranceException extends InvalidMazeException{
    public MultipleEntranceException(){
        super("Multiple Entrances");
    }
}