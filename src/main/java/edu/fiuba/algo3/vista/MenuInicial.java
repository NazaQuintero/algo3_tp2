package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.SeleccionDeJugadoresEventHandler;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuInicial extends VBox {
//    private static final String RUTA_ICONO = "logo.png";
    private static final int ANCHO = 250;
    private static final int ALTO = 250;
    private Stage stage;

    public MenuInicial(Stage stage) {
        this.stage = stage;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        this.getStylesheets().add("styles.css");

        Button startButton = new Button();
        Button exitButton = new Button();
        exitButton.setText("Salir");
        startButton.setText("Jugar");

        startButton.getStyleClass().add("startButton");
        exitButton.getStyleClass().add("exitButton");

        SeleccionDeJugadoresEventHandler seleccionDeJugadoresEventHandler = new SeleccionDeJugadoresEventHandler(stage);
        startButton.setOnAction(seleccionDeJugadoresEventHandler);
        exitButton.setOnAction(e -> Platform.exit());

        HBox botonera = new HBox(startButton, exitButton);
        botonera.setAlignment(Pos.CENTER);
        botonera.setSpacing(20);

        Label etiqueta = new Label("Bienvenidos a A.L.T.E.G.O.!");
        etiqueta.getStyleClass().add("title");

        this.getChildren().addAll(etiqueta, botonera);

        this.getStyleClass().add("body");

    }


}
