package edu.fiuba.algo3.vista;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CampoDeJuego extends BorderPane {

    public CampoDeJuego(Stage stage) {

        ImageView tablero = new ImageView();
        Image image = new Image("tablero.png");
        tablero.setFitWidth(800);
        tablero.setFitHeight(600);

        tablero.setImage(image);
        this.setCenter(tablero);
    }
}
