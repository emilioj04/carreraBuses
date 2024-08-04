package carrerabuses;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

public class PanelJuego extends JPanel {

    private Juego juego;
    private Image busRojoImagen;
    private Image busVerdeImagen;
    private Image backgroundImagen;
    private int imagenAncho = 100;
    private int imagenAlto = 50;

    public PanelJuego(Juego juego) {
        this.juego = juego;
        try {
            URL busRojoUrl = getClass().getResource("/images/busRojo.jpg");
            URL busVerdeUrl = getClass().getResource("/images/busVerde.jpg");
            URL backgroundUrl = getClass().getResource("/images/fondo.png");

            if (busRojoUrl != null) {
                busRojoImagen = ImageIO.read(busRojoUrl);
            } else {
                System.err.println("No se pudo cargar la imagen del bus rojo.");
            }

            if (busVerdeUrl != null) {
                busVerdeImagen = ImageIO.read(busVerdeUrl);
            } else {
                System.err.println("No se pudo cargar la imagen del bus verde.");
            }

            if (backgroundUrl != null) {
                backgroundImagen = ImageIO.read(backgroundUrl);
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

        if (backgroundImagen != null) {
            g.drawImage(backgroundImagen, 0, 0, getWidth(), getHeight(), this);
        }

        Bus[] buses = juego.getBuses();

        if (busRojoImagen != null) {
            g.drawImage(busRojoImagen, buses[0].getX(), buses[0].getY(), imagenAncho, imagenAlto, this);
        }
        
        if (busVerdeImagen != null) {
            g.drawImage(busVerdeImagen, buses[1].getX(), buses[1].getY(), imagenAncho, imagenAlto, this);
        }
    }
}
