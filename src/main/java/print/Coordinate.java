package print;

public class Coordinate {
    private int x;
    private int y;

    Coordinate(){
        x = 0;
        y = 0;
    }

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}