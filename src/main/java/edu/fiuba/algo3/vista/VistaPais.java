package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.PaisEventHandler;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.paises.Pais;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class VistaPais extends Rectangle implements Observer {
    Pais pais;
    int fichas = 0;
    Label label = new Label(Integer.toString(fichas));

    public VistaPais(Pais pais) {
        super(pais.getPosX(), pais.getPosY(), 50, 50);
        this.pais = pais;
        pais.addObserver(this);
        setFill(new Color(0f,0f,0f,0));
        this.getStyleClass().add("pais");
        this.setOnMouseClicked(e -> new PaisEventHandler(pais, this));
    }

    @Override
    public void update() {
        this.fichas = pais.cantidadEjercitos();
        this.label.setText(Integer.toString(this.fichas));
        System.out.println("Hay "+ this.fichas + " ejercito en \n Pais:" + this.pais.getNombre() + "pertenece a " +
                pais.dominadoPor().obtenerNombre());
    }
}
