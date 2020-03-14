@SuppressWarnings("serial")
public class NoExitException extends InvalidMazeException{
    public NoExitException(){
        super("No Exits");
    }
}