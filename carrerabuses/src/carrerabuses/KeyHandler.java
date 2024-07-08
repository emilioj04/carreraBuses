package carrerabuses;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private Game game;
    private boolean lPressed = false; 
    private boolean ePressed = false; 

    public KeyHandler(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        // Manejar mediante teclas "L" y "E"
        switch (keyCode) {
            case KeyEvent.VK_L:
                if (!lPressed) {
                    game.moveBusRojo(); 
                    lPressed = true; 
                }
                break;
            case KeyEvent.VK_E:
                if (!ePressed) {
                    game.moveBusVerde(); 
                    ePressed = true; 
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_L:
                lPressed = false; 
                break;
            case KeyEvent.VK_E:
                ePressed = false; 
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
