package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class VistaTarjeta {

    private final Image imagenTarjeta;
    private final ImageView view;
    private final Tarjeta tarjeta;

    public VistaTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
        this.imagenTarjeta = new Image(tarjeta.obtenerSimbolo() + ".png");
        this.view = new ImageView(imagenTarjeta);
        view.setPreserveRatio(true);
        view.setFitWidth(82);
        view.setFitHeight(250);
    }

    public ImageView obtenerImagen() {
        return this.view;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

}
