package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Pais;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VistaPais extends Rectangle {
    private String nombre;
    public VistaPais(int x, int y, String nombre) {
        super(x, y, 50, 50);
        setFill(new Color(0f,0f,0f,0.25));
        this.nombre = nombre;
        this.getStyleClass().add("pais");

        this.setOnMouseClicked( e -> System.out.println("Pais: " + nombre));
    }

    public VistaPais(Pais pais) {
        super(pais.getPosX(), pais.getPosY(), 50, 50);
        setFill(new Color(0f,0f,0f,0.25));
        this.nombre = pais.getNombre();
        this.getStyleClass().add("pais");
        this.setOnMouseClicked( e -> System.out.println("Pais: " + nombre));
    }
}
