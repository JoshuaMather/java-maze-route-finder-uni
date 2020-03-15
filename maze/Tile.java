package maze;

public class Tile{
    /**
     * Inner class for type of tile
     */
    public enum Type{
        CORRIDOR,ENTERANCE,EXIT,WALL;
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
         */
        if(givenchar == '.'){
            return new Tile(Type.CORRIDOR);

        } else if(givenchar == 'e'){
            return new Tile(Type.ENTERANCE);

        } else if(givenchar == 'x'){
            return new Tile(Type.EXIT);

        } else{
            return new Tile(Type.WALL);

        }
    }

    public Type getType(){
        /**
         * Gets type of given tile
         */
        return this.type;
    }

    public boolean isNavigate(){
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
        if(this.type == Type.CORRIDOR){
            return ".";

        } else if(this.type == Type.ENTERANCE){
            return "e";

        } else if(this.type == Type.EXIT){
            return "x";

        } else{
            return "#";

        }

    }


}