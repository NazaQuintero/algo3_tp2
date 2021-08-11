package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.TarjetaEventHandler;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


public class VistaTarjeta extends StackPane {

    private final Image imagenTarjeta;
    private final ImageView view;
    private final Tarjeta tarjeta;
    private final CheckBox check;
    private final Button button;

    public VistaTarjeta(VentanaTarjetas ventanaTarjetas, Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
        this.imagenTarjeta = new Image(tarjeta.obtenerSimbolo() + ".png");
        this.view = new ImageView(imagenTarjeta);
        this.check = new CheckBox();
        this.button = new Button(tarjeta.obtenerPais().getNombre(), view);

        view.setPreserveRatio(true);
        view.setFitWidth(82);
        view.setFitHeight(250);

        this.button.setContentDisplay(ContentDisplay.BOTTOM);
        this.button.setOnMouseClicked(new TarjetaEventHandler(ventanaTarjetas, this));

        this.button.setMaxWidth(82);
        this.button.setMaxHeight(250);
        this.setMaxWidth(82);
        this.setMaxHeight(250);

        check.setMouseTransparent(true);

        StackPane.setAlignment(check, Pos.TOP_RIGHT);
        this.getChildren().addAll(button, check);
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void cambiarSeleccion(){
        this.check.setSelected(!this.check.isSelected());
    }

    public boolean estaSeleccionada(){
        return check.isSelected();
    }

}
