package edu.fiuba.algo3;

import edu.fiuba.algo3.vista.MenuInicial;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final int ANCHO = 800;
    private static final int ALTO = 550;
    private static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) {

        String musicFile = "src/main/resources/musiquita.mp3";
        Media musica = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(musica);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        //mediaPlayer.play();

        MenuInicial menuInicial = new MenuInicial(stage);
        Scene presentacion = new Scene(menuInicial, ANCHO, ALTO);
        stage.setScene(presentacion);
        stage.setTitle("A.L.T.E.G.O.");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}