package maze;

public class Tile{
    /**
     * Inner class for type of tile
     */
    public enum Type{
        CORRIDOR,ENTRANCE,EXIT,WALL;
    }

    private Type type;

    /**
     * Constructor for maze passes in tile type
     */
    private Tile(Type givenType){
        this.type = givenType;
    }

    protected static Tile fromChar(char givenchar){
        /**
         * Creates a new inatance of tile based on character that represents tile
         * For exit X is used instead of x when the maze has been solved
         */
        if(givenchar == '#'){
            return new Tile(Type.WALL);

        } else if(givenchar == 'e'){
            return new Tile(Type.ENTRANCE);

        } else if(givenchar == 'x'	|| givenchar == 'X'){
            return new Tile(Type.EXIT);

        } else{
            return new Tile(Type.CORRIDOR);
        }
    }

    public Type getType(){
        /**
         * Gets type of given tile
         */
        return this.type;
    }

    public boolean isNavigable(){
        /**
         * Check if a tile can be navigated through
         */
        if(this.type == Type.WALL){
            return false;

        } else{
            return true;
        }
    }

    public String toString(){
        /**
         * Prints a representation of a tile
         */
        if(this.type == Type.WALL){
            return "#";

        } else if(this.type == Type.ENTRANCE){
            return "e";

        } else if(this.type == Type.EXIT){
            return "x";

        } else{
            return ".";
        }

    }


}