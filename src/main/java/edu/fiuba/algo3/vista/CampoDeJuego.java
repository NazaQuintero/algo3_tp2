package edu.fiuba.algo3.vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class CampoDeJuego extends HBox {

    public CampoDeJuego(Stage stage) {

        this.getStylesheets().add("styles.css");

        Image imagen = new Image("tablero.png");
        ImageView imageView = new ImageView(imagen);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(800);

        Pane stackPane = new Pane();
        stackPane.getChildren().add(imageView);
        crearPaises(stackPane);

        this.getChildren().add(stackPane);
        this.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(this, 1000, 800));
    }

    private void crearPaises(Pane stackPane) {

        VistaPais alaska = new VistaPais(10, 260, "Alaska");
        VistaPais yukon = new VistaPais(70, 230, "Yukon");
        VistaPais canada = new VistaPais(130, 170, "Canada");
        VistaPais groenlandia = new VistaPais(320, 150, "Groenlandia");


        stackPane.getChildren().addAll(alaska, yukon, canada, groenlandia);
    }

}
