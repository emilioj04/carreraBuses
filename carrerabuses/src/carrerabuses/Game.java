package carrerabuses;

import Master.master;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class Game {
    private Bus[] buses;
    private GamePanel panel;
    private int metaX = 890; // Posición inicial x de la meta
    private int metaSpeed = 10; // Velocidad de avance de la meta
    private int metaMovementCount = 0; // Contador de movimientos de la meta
    private boolean[] finished; // Indicador de si cada bus ha terminado
    private String winner; // Almacena el bus ganador
    private master window; // Referencia a la ventana principal
    private int extraMoveCount = 0; // Contador de movimientos adicionales
    private static final int EXTRA_MOVES = 100; // Número de movimientos adicionales
    private Clip audioClip; // Clip de audio para la reproducción

    public Game(master window) {
        this.window = window;
        buses = new Bus[2];
        buses[0] = new Bus(100, 120, 5); // Bus rojo
        buses[1] = new Bus(100, 330, 5); // Bus verde
        finished = new boolean[2]; // Inicializar el estado de los buses
        panel = new GamePanel(this);
        winner = null; // Inicializar sin ganador
        initAudio(); // Inicializar el audio
    }

    private void initAudio() {
        try {
            URL audioUrl = getClass().getResource("/audio/audio.wav");
            if (audioUrl != null) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioUrl);
                audioClip = AudioSystem.getClip();
                audioClip.open(audioStream);
            } else {
                System.err.println("No se pudo cargar el archivo de audio.");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        window.addKeyListener(new KeyHandler(this)); // Agregar KeyHandler al JFrame
        window.setVisible(true);

        if (audioClip != null) {
            audioClip.setFramePosition(0); // Rewind to the beginning
            audioClip.start(); // Start playing
        }

        Timer timer = new Timer(100, e -> {
            metaMovementCount++; // Incrementar el contador de movimientos de la meta
            if (metaMovementCount >= 200) {
                moveMeta(); // Mover la meta cada 200 iteraciones
                metaMovementCount = 0; // Reiniciar el contador
            }
            moveBusesAutomatically(); // Mover los buses automáticamente si han terminado
            panel.repaint(); // Volver a dibujar el panel
        });
        timer.start();
    }

    public void stopAudio() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
        }
    }

    private void moveMeta() {
        // Mover la meta hacia la derecha con velocidad metaSpeed
        metaX += metaSpeed;
    }

    public void moveBusRojo() {
        // Mover el bus rojo hacia la derecha si no ha alcanzado la meta
        if (!finished[0] && buses[0].getX() < metaX) {
            buses[0].moveRight();
            if (buses[0].getX() >= metaX) {
                finished[0] = true; // Marcar el bus rojo como terminado
                if (winner == null) {
                    winner = "Ganó Bus Rojo"; // Establecer ganador
                    window.setResultadoCarrera(winner); // Mostrar el resultado en la ventana
                }
            }
        }
    }

    public void moveBusVerde() {
        // Mover el bus verde hacia la derecha si no ha alcanzado la meta
        if (!finished[1] && buses[1].getX() < metaX) {
            buses[1].moveRight();
            if (buses[1].getX() >= metaX) {
                finished[1] = true; // Marcar el bus verde como terminado
                if (winner == null) {
                    winner = "Ganó Bus Verde"; // Establecer ganador
                    window.setResultadoCarrera(winner); // Mostrar el resultado en la ventana
                }
            }
        }
    }

    private void moveBusesAutomatically() {
        if (finished[0] || finished[1]) {
            extraMoveCount++;
            if (extraMoveCount <= EXTRA_MOVES) {
                if (finished[0] && buses[0].getX() < metaX + 100) {
                    buses[0].moveRight();
                }
                if (finished[1] && buses[1].getX() < metaX + 100) {
                    buses[1].moveRight();
                }
            }
        }
    }

    public Bus[] getBuses() {
        return buses;
    }

    public int getMetaX() {
        return metaX;
    }

    public GamePanel getPanel() {
        return panel;
    }
}
