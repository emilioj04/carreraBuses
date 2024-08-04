package carrerabuses;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

public class GamePanel extends JPanel {

    private Juego game;
    private Image busRojoImage;
    private Image busVerdeImage;
    private Image backgroundImage;
    private int imageWidth = 100; 
    private int imageHeight = 50; 

    public GamePanel(Juego game) {
        this.game = game;
        try {
            // imagenes
            URL busRojoUrl = getClass().getResource("/images/busRojo.jpg");
            URL busVerdeUrl = getClass().getResource("/images/busVerde.jpg");
            URL backgroundUrl = getClass().getResource("/images/fondo.png"); 

            if (busRojoUrl != null) {
                busRojoImage = ImageIO.read(busRojoUrl);
            } else {
                System.err.println("No se pudo cargar la imagen del bus rojo.");
            }

            if (busVerdeUrl != null) {
                busVerdeImage = ImageIO.read(busVerdeUrl);
            } else {
                System.err.println("No se pudo cargar la imagen del bus verde.");
            }

            if (backgroundUrl != null) {
                backgroundImage = ImageIO.read(backgroundUrl);
            } else {
                System.err.println("No se pudo cargar la imagen de fondo.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    // Dibujar fondo
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        Bus[] buses = game.getBuses();

        // Dibujar buses
        if (busRojoImage != null) {
            g.drawImage(busRojoImage, buses[0].getX(), buses[0].getY(), imageWidth, imageHeight, this);
        }
        
        if (busVerdeImage != null) {
            g.drawImage(busVerdeImage, buses[1].getX(), buses[1].getY(), imageWidth, imageHeight, this);
        }
    }
}
