package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.observables.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MenuLateralDerecho extends VBox implements Observer {

    private final CampoDeJuego campoDeJuego;
    private final Juego juego;
    private Label descripcionDeRonda;

    public MenuLateralDerecho(CampoDeJuego campoDeJuego, Juego juego) {
        this.campoDeJuego = campoDeJuego;
        this.juego = juego;
        juego.getTurno().addObserver(this);
        this.getChildren().addAll(crearDescripcionDeRonda(), crearFormularioDeAtaque());
        this.setSpacing(50);
        this.setAlignment(Pos.CENTER);
    }


    private VBox crearFormularioDeAtaque() {
        Label label1 = new Label("Ingrese la cantidad de ejércitos para atacar: ");
        label1.getStyleClass().add("labelText");
        Label label2 = new Label("Cantidad de ejércitos inválida");
        label2.getStyleClass().add("error");
        label2.setVisible(false);

        TextField inputText = new TextField();
        inputText.getStyleClass().add("cantEjercitos");
        Button botonAtacar = new Button("Atacar!");
        botonAtacar.getStyleClass().add("startButton");

        VBox vBox = new VBox(label1, inputText, botonAtacar, label2);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setVisible(false);
        return vBox;
    }

    private VBox crearDescripcionDeRonda() {
        this.descripcionDeRonda = new Label(this.juego.getTurno().obtenerRondaActual().obtenerDescripcion());
        this.descripcionDeRonda.getStyleClass().add("labelText");
        VBox vBox = new VBox(this.descripcionDeRonda);

        return vBox;
    }

    @Override
    public void update() {
        this.descripcionDeRonda.setText(this.juego.getTurno().obtenerRondaActual().obtenerDescripcion());
    }
}
