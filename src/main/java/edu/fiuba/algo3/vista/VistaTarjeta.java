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
    private final Tarjeta tarjeta;

    public VistaTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
        this.imagenTarjeta = new Image(tarjeta.obtenerSimbolo()+".png"); // aca lanza la excepcion aaa
        this.view = new ImageView(imagenTarjeta);
        view.setPreserveRatio(true);
        view.setFitWidth(82);
        view.setFitHeight(250);
    }

    public ImageView obtenerImagen() {
        //return this.imagenTarjeta;
        return this.view;
    }

    public String obtenerNombrePais() { return tarjeta.obtenerPais().getNombre(); }

    @Override
    public void update() {

    }

}
