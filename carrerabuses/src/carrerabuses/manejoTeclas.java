package carrerabuses;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class manejoTeclas implements KeyListener {
    private Juego juego;
    private boolean lPresionada = false;
    private boolean ePresionada = false;

    public manejoTeclas(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int teclaCodigo = e.getKeyCode();

        switch (teclaCodigo) {
            case KeyEvent.VK_L:
                if (!lPresionada) {
                    juego.moverBusRojo();
                    lPresionada = true;
                }
                break;
            case KeyEvent.VK_E:
                if (!ePresionada) {
                    juego.moverBusVerde();
                    ePresionada = true;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int teclaCodigo = e.getKeyCode();
        switch (teclaCodigo) {
            case KeyEvent.VK_L:
                lPresionada = false;
                break;
            case KeyEvent.VK_E:
                ePresionada = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
