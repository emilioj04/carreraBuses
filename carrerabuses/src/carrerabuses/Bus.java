package carrerabuses;

public class Bus {
    private int x, y, speed;

    public Bus(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getSpeed (){
        return speed;
    }   
    
}