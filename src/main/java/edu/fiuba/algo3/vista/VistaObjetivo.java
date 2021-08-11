package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.objetivos.Objetivo;
import edu.fiuba.algo3.modelo.observables.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VistaObjetivo implements Observer {

    private Image imagenObjetivo;
    private ImageView view;
    private Objetivo objetivo;

    public VistaObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
        this.imagenObjetivo = new Image("Objetivo.png");
        this.view = new ImageView(imagenObjetivo);
        view.setPreserveRatio(true);
        view.setFitWidth(100);
        view.setFitHeight(100);
    }

    public ImageView obtenerImagen() {
        return this.view;
    }

    public String obtenerDescripcion() { return objetivo.obtenerDescripcion(); }

    @Override
    public void update() {}
}
