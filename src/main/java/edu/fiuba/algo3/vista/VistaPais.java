package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.PaisEventHandler;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.fichas.Fichas;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class VistaPais extends StackPane implements Observer {
    Pais pais;
    Fichas ejercito;

    public VistaPais(Pais pais) {
        this.getStylesheets().add("styles.css");
        this.pais = pais;
        this.ejercito = pais.getEjercito();
        this.setLayoutX(pais.getPosX());
        this.setLayoutY(pais.getPosY());

        Circle ficha = new Circle(10, pais.dominadoPor().color());
        Label cantEjercitos = crearLabelCantEjercitos();
        Rectangle boton = crearBotonPais();

        this.getChildren().addAll(ficha, cantEjercitos, boton);

        pais.addObserver(this);
    }

    private Label crearLabelCantEjercitos(){
        Label cantEjercitos = new Label();
        cantEjercitos.getStyleClass().add("ejercito");
        cantEjercitos.setText(Integer.toString(ejercito.obtenerCantidad()));
        return cantEjercitos;
    }

    private Rectangle crearBotonPais(){
        Rectangle boton = new Rectangle(50, 50);
        boton.setFill(new Color(0f,0f,0f,0));
        boton.getStyleClass().add("pais");
        boton.setOnMouseClicked(new PaisEventHandler(pais));
        return boton;
    }

    @Override
    public void update() {
        Label l = (Label) this.getChildren().get(1);
        l.setText(Integer.toString(pais.cantidadEjercitos()));

        System.out.println("Hay "+ this.ejercito.obtenerCantidad() + " ejercito/s en " + this.pais.getNombre() +
                " que pertenece a " + pais.dominadoPor().obtenerNombre());
    }
}
