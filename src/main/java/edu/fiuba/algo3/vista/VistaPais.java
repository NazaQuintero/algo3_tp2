package edu.fiuba.algo3.vista;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VistaPais extends Rectangle {
    public VistaPais(int x, int y, String nombre) {
        super(x, y, 50, 50);
        setFill(Color.RED);
        this.getStyleClass().add("pais");

        this.setOnMouseClicked( e -> System.out.println("Pais: " + nombre));
    }
}
