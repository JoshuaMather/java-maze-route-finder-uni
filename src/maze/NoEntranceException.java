package maze;

/**
 * Exception for if there are no enterances
 */
@SuppressWarnings("serial")
public class NoEntranceException extends InvalidMazeException{
    public NoEntranceException(){
        super("No Entrances");
    }
}