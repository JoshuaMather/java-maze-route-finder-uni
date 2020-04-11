package maze.routing;

/**
 * Exception if no route from enterance to exit
 */
@SuppressWarnings("serial")
public class NoRouteFoundException extends RuntimeException{
    public NoRouteFoundException(){
        super("No Route Found");
    }
}