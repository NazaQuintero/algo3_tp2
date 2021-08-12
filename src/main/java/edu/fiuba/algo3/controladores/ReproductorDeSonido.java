package edu.fiuba.algo3.controladores;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Objects;

public class ReproductorDeSonido {

    private final String fAudioPrincipal = "/sounds/main.mp3";
    private final String fAudioClick = "/sounds/buttonClick.mp3";
    private final String fAudioAtaque = "/sounds/ataque.mp3";
    private final String fAudioCanje = "/sounds/canje.mp3";
    private final String fAudioError = "/sounds/error.mp3";
    private final String fAudioVictoria = "/sounds/victoria.mp3";

    private final MediaPlayer reproductorPrincipal;
    private final AudioClip reproductorClick;
    private final AudioClip reproductorAtaque;
    private final AudioClip reproductorCanje;
    private final AudioClip reproductorError;
    private final AudioClip reproductorVictoria;

    private static ReproductorDeSonido instance;

    private ReproductorDeSonido() {
        this.reproductorPrincipal = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource(fAudioPrincipal)).toString()));
        this.reproductorClick = new AudioClip(Objects.requireNonNull(getClass().getResource(fAudioClick)).toString());
        this.reproductorAtaque = new AudioClip(Objects.requireNonNull(getClass().getResource(fAudioAtaque)).toString());
        this.reproductorCanje = new AudioClip(Objects.requireNonNull(getClass().getResource(fAudioCanje)).toString());
        this.reproductorError = new AudioClip(Objects.requireNonNull(getClass().getResource(fAudioError)).toString());
        this.reproductorVictoria = new AudioClip(Objects.requireNonNull(getClass().getResource(fAudioVictoria)).toString());
    }

    public static ReproductorDeSonido getInstance() {
        if(instance == null)
            instance = new ReproductorDeSonido();
        return instance;
    }

    public void playPrincipal(){
        reproductorPrincipal.setVolume(0.35);
        reproductorPrincipal.setCycleCount(MediaPlayer.INDEFINITE);
        reproductorPrincipal.play();
    }

    public void playClick(){
        reproductorClick.play();
    }
    public void playAtaque() { reproductorAtaque.play(); }
    public void playCanje() { reproductorCanje.play(); }
    public void playError() { reproductorError.play(); }
    public void playVictoria() { reproductorVictoria.play(); }

}
