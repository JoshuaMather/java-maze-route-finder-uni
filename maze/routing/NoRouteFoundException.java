package maze.routing;

/**
 * Exception if no route from enterance to exit
 */
public class NoRouteFoundException extends Exception{
    public NoRouteFoundException(){
        super("No Route Found");
    }
}