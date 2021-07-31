package edu.fiuba.algo3.vista;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class CampoDeJuego extends BorderPane {

    public CampoDeJuego(Stage stage) {

        this.getStylesheets().add("styles.css");
        this.getStyleClass().add("board");

        crearPaises();
    }

    private void crearPaises() {

        VistaPais alaska = new VistaPais(45, 405, "Alaska");
        VistaPais yukon = new VistaPais(155, 330, "Yukon");

        this.getChildren().addAll(alaska, yukon);
    }

}
