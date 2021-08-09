package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VistaTarjeta implements Observer {

    private final Image imagenTarjeta;
    private final ImageView view;

    public VistaTarjeta(Tarjeta tarjeta) {
        this.imagenTarjeta = new Image(tarjeta.nombrePais() + "_" + tarjeta.obtenerSimbolo() + ".png");
        //ImageView imageView = new ImageView(imagenTarjeta);
        this.view = new ImageView(imagenTarjeta);
        view.setPreserveRatio(true);
        view.setFitWidth(82);
        view.setFitHeight(250);
    }

    public ImageView obtenerImagen() {
        //return this.imagenTarjeta;
        return this.view;
    }

    @Override
    public void update() {

    }
}
