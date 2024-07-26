package carrerabuses;

import Master.master;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class Game {
    private Bus[] buses;
    private GamePanel panel;
    private int metaX = 890;
    private int metaSpeed = 10;
    private int metaMovementCount = 0;
    private boolean[] finished;
    private String winner;
    private master window;
    private int extraMoveCount = 0;
    private static final int EXTRA_MOVES = 100;
    private Clip audioClip;

    public Game(master window) {
        this.window = window;
        buses = new Bus[2];
        buses[0] = new Bus(100, 120, 5);
        buses[1] = new Bus(100, 330, 5);
        finished = new boolean[2];
        panel = new GamePanel(this);
        winner = null;
        initAudio();
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
        window.addKeyListener(new KeyHandler(this));
        window.setVisible(true);

        if (audioClip != null) {
            audioClip.setFramePosition(0);
            audioClip.start();
        }

        Timer timer = new Timer(100, e -> {
            metaMovementCount++;
            if (metaMovementCount >= 200) {
                moveMeta();
                metaMovementCount = 0;
            }
            moveBusesAutomatically();
            panel.repaint();
        });
        timer.start();
    }

    public void stopAudio() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
        }
    }

    private void moveMeta() {
        metaX += metaSpeed;
    }

    public void moveBusRojo() {
        if (!finished[0] && buses[0].getX() < metaX) {
            buses[0].moveRight();
            if (buses[0].getX() >= metaX) {
                finished[0] = true;
                if (winner == null) {
                    winner = "Ganó Bus Rojo";
                    window.setResultadoCarrera(winner);
                }
            }
        }
    }

    public void moveBusVerde() {
        if (!finished[1] && buses[1].getX() < metaX) {
            buses[1].moveRight();
            if (buses[1].getX() >= metaX) {
                finished[1] = true;
                if (winner == null) {
                    winner = "Ganó Bus Verde";
                    window.setResultadoCarrera(winner);
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
