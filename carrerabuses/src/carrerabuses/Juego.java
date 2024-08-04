package carrerabuses;

import Master.master;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class Juego {
    private Bus[] buses;
    private PanelJuego panel;
    private int metaX = 890;
    private int metaVelocidad = 10;
    private int contadorMovimientosMeta = 0;
    private boolean[] finalizado;
    private String ganador;
    private master pantalla;
    private int contadorMovimientosExtra = 0;
    private static final int MOVIMIENTOS_EXTRA = 100;
    private Clip clipAudio;

    public Juego(master pantalla) {
        this.pantalla = pantalla;
        buses = new Bus[2];
        buses[0] = new Bus(100, 120, 5);
        buses[1] = new Bus(100, 330, 5);
        finalizado = new boolean[2];
        panel = new PanelJuego(this);
        ganador = null;
        iniciarAudio();
    }

    private void iniciarAudio() {
        try {
            URL urlAudio = getClass().getResource("/audio/audio.wav");
            if (urlAudio != null) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(urlAudio);
                clipAudio = AudioSystem.getClip();
                clipAudio.open(audioStream);
            } else {
                System.err.println("No se pudo cargar el archivo de audio.");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void iniciar() {
        panel.addKeyListener(new manejoTeclas(this)); // Cambiado a panel
        pantalla.setVisible(true);      

        if (clipAudio != null) {
            clipAudio.setFramePosition(0);
            clipAudio.start();
        }

        Timer timer = new Timer(100, e -> {
            contadorMovimientosMeta++;
            if (contadorMovimientosMeta >= 200) {
                moverMeta();
                contadorMovimientosMeta = 0;
            }
            moverBusesAutomaticamente();
            panel.repaint();
        });
        timer.start();
    }

    public void detenerAudio() {
        if (clipAudio != null && clipAudio.isRunning()) {
            clipAudio.stop();
        }
    }

    private void moverMeta() {
        metaX += metaVelocidad;
    }

    public void moverBusRojo() {
        if (!finalizado[0] && buses[0].getX() < metaX) {
            buses[0].moverBus();
            if (buses[0].getX() >= metaX) {
                finalizado[0] = true;
                if (ganador == null) {
                    ganador = "Ganó Bus Rojo";
                    pantalla.establecerResultadoCarrera(ganador);
                }
            }
        }
    }

    public void moverBusVerde() {
        if (!finalizado[1] && buses[1].getX() < metaX) {
            buses[1].moverBus();
            if (buses[1].getX() >= metaX) {
                finalizado[1] = true;
                if (ganador == null) {
                    ganador = "Ganó Bus Verde";
                    pantalla.establecerResultadoCarrera(ganador);
                }
            }
        }
    }

    private void moverBusesAutomaticamente() {
        if (finalizado[0] || finalizado[1]) {
            contadorMovimientosExtra++;
            if (contadorMovimientosExtra <= MOVIMIENTOS_EXTRA) {
                if (finalizado[0] && buses[0].getX() < metaX + 100) {
                    buses[0].moverBus();
                }
                if (finalizado[1] && buses[1].getX() < metaX + 100) {
                    buses[1].moverBus();
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

    public PanelJuego getPanel() {
        return panel;
    }
    
}
