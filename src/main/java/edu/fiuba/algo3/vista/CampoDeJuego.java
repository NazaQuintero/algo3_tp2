package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;


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

        stage.setScene(new Scene(this, 1400, 900));
    }

    private void crearPaises(Pane stackPane) {

        ArrayList<Pais> _paises = new ArrayList<>(MultitonPaises.obtenerTodosLosPaises());

        for(Pais pais: _paises) {
            stackPane.getChildren().add(new VistaPais(pais));
        }
    }

}
