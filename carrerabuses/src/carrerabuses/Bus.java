package carrerabuses;

public class Bus {
    private int x, y, velocidad;

    public Bus(int x, int y, int velocidad) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad; 
    }

    public void moverBus() {
        x += velocidad;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getSpeed() {
        return velocidad;
    }
}
