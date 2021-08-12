package edu.fiuba.algo3.controladores;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ReproductorDeSonido {

    private static final String fAudioPrincipal = "src/main/resources/sounds/main.mp3";
    private static final String fAudioClick = "src/main/resources/sounds/buttonClick.mp3";
    private static final String fAudioAtaque = "src/main/resources/sounds/ataque.mp3";
    private static final String fAudioCanje = "src/main/resources/sounds/canje.mp3";
    private static final String fAudioError = "src/main/resources/sounds/error.mp3";
    private static final String fAudioVictoria = "src/main/resources/sounds/victoria.mp3";

    private static final MediaPlayer reproductorPrincipal = new MediaPlayer(new Media(new File(fAudioPrincipal).toURI().toString()));
    private static final AudioClip reproductorClick = new AudioClip(new File(fAudioClick).toURI().toString());
    private static final AudioClip reproductorAtaque = new AudioClip(new File(fAudioAtaque).toURI().toString());
    private static final AudioClip reproductorCanje = new AudioClip(new File(fAudioCanje).toURI().toString());
    private static final AudioClip reproductorError = new AudioClip(new File(fAudioError).toURI().toString());
    private static final AudioClip reproductorVictoria = new AudioClip(new File(fAudioVictoria).toURI().toString());

    public static void playPrincipal(){
        reproductorPrincipal.setVolume(0.35);
        reproductorPrincipal.setCycleCount(MediaPlayer.INDEFINITE);
        reproductorPrincipal.play();
    }

    public static void playClick(){
        reproductorClick.play();
    }
    public static void playAtaque() { reproductorAtaque.play(); }
    public static void playCanje() { reproductorCanje.play(); }
    public static void playError() { reproductorError.play(); }
    public static void playVictoria() { reproductorVictoria.play(); }

}
