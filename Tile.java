public class Tile{
    public enum Type{
        CORRIDOR,ENTERANCE,EXIT,WALL;
    }

    private Type type;

    private Tile(Type givenType){
        this.type = givenType;
    }

    protected static Tile fromChar(char givenchar){
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
        return this.type;
    }

    public boolean isNavigate(){
        if(this.type == Type.WALL){
            return false;

        } else{
            return true;
        }
    }

    public String toString(){
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