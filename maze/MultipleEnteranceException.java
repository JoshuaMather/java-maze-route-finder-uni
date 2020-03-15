package maze;

/**
 * Exception for if maze has multiple enterances
 */
@SuppressWarnings("serial")
public class MultipleEnteranceException extends InvalidMazeException{
    public MultipleEnteranceException(){
        super("Multiple Enterances");
    }
}