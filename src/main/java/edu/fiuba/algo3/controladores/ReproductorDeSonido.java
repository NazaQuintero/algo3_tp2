package edu.fiuba.algo3.controladores;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ReproductorDeSonido {
    private static final String fAudioPrincipal = "src/main/resources/musiquita.mp3";
    private static final String fAudioClick = "src/main/resources/buttonSound.mp3";

    private static final MediaPlayer reproductorPrincipal = new MediaPlayer(new Media(new File(fAudioPrincipal).toURI().toString()));
    private static final AudioClip reproductorClick = new AudioClip(new File(fAudioClick).toURI().toString());

    public static void playPrincipal(){
        reproductorPrincipal.play();
    }
    public static void playClick(){
        reproductorClick.play();
    }

}
