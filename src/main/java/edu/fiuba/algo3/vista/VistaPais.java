package edu.fiuba.algo3.vista;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VistaPais extends BorderPane {
    public VistaPais(int x, int y, String nombre) {
        this.getStylesheets().add("styles.css");
        Rectangle caja = new Rectangle(70,70);
        caja.setFill(Color.TRANSPARENT);
        caja.getStyleClass().add("pais");

        caja.setOnMouseClicked( e -> System.out.println("Pais: " + nombre));

        /*caja.hoverProperty().addListener( e -> {
            caja.setFill(Color.rgb(255, 255, 255, 0.50));
        });*/

        caja.setLayoutX(x);
        caja.setLayoutY(y);
        this.getChildren().add(caja);
    }
}
