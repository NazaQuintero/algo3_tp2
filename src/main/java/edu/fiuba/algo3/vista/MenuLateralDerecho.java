package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.FinalizarRondaEventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.rondas.Colocacion;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuLateralDerecho extends VBox implements Observer {

    private final CampoDeJuego campoDeJuego;
    private final Juego juego;
    private final VBox formularioDeAccion;
    private Label descripcionDeRonda;
    private Label descripcionDeTurno;
    private Button botonAccion;
    private Button botonFinalizarRonda;
    private TextField inputText;
    private Label labelDeAyuda;
    private Label labelDeError;
    private VBox resultadoDeAtaque;

    public MenuLateralDerecho(CampoDeJuego campoDeJuego, Juego juego) {
        this.campoDeJuego = campoDeJuego;
        this.juego = juego;
        juego.getTurno().addObserver(this);
        this.formularioDeAccion = crearFormularioDeAccion();
        this.getChildren().addAll(crearDescripcionDeRonda(), this.formularioDeAccion);
        this.setResultadoDeAtaque(new VBox());
        this.setSpacing(50);
        this.setAlignment(Pos.CENTER);
    }

    private VBox crearFormularioDeAccion() {
        this.labelDeAyuda = new Label("");
        labelDeAyuda.getStyleClass().add("labelText");
        this.labelDeError = new Label("Cantidad de ejércitos inválida");
        labelDeError.getStyleClass().add("error");
        labelDeError.setVisible(false);

        this.inputText = new TextField();
        inputText.getStyleClass().add("cantEjercitos");

        this.botonAccion = new Button("Atacar!");
        botonAccion.getStyleClass().add("startButton");

        this.botonFinalizarRonda = new Button("Finalizar Ronda");
        botonFinalizarRonda.getStyleClass().add("exitButton");
        this.botonFinalizarRonda.setOnMouseClicked(new FinalizarRondaEventHandler(campoDeJuego));

        HBox botones = new HBox(botonAccion, botonFinalizarRonda);
        botones.setSpacing(10);

        VBox vBox = new VBox(labelDeAyuda, inputText, botones, labelDeError);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setVisible(false);
        return vBox;
    }

    private VBox crearDescripcionDeRonda() {
        this.descripcionDeRonda = new Label(this.juego.getTurno().obtenerRondaActual().obtenerDescripcion());
        this.descripcionDeRonda.getStyleClass().add("labelText");

        this.descripcionDeTurno = new Label("Es el turno de: " + this.juego.getTurno().obtenerJugadorTurnoActual().obtenerNombre());
        this.descripcionDeTurno.getStyleClass().add("labelText");

        return new VBox(this.descripcionDeRonda, this.descripcionDeTurno);
    }

    @Override
    public void update() {
        this.descripcionDeRonda.setText(this.juego.getTurno().obtenerRondaActual().obtenerDescripcion());
        this.descripcionDeTurno.setText("Es el turno de: " + this.juego.getTurno().obtenerJugadorTurnoActual().obtenerNombre());
        campoDeJuego.mostrarPaisesDelJugadorActual();
        if (juego.getTurno().obtenerRondaActual().obtenerDescripcion().equals("Ronda de colocación")) {
            this.labelDeAyuda.setText("Haga click en su pais \n para colocar ejercito \n Queda/n por colocar " +
                    ((Colocacion) juego.getRonda()).getCantidadEjercitosColocables() + " ejército/s");
        }
    }

    public void mostrarFormularioDeColocacion() {
        this.getChildren().get(0).setVisible(true);
        this.getChildren().get(1).setVisible(true);
        this.labelDeAyuda.setText("Haga click en su pais \n para colocar ejercito \n Queda/n por colocar " +
                ((Colocacion) juego.getRonda()).getCantidadEjercitosColocables() + " ejército/s");
        this.inputText.setVisible(false);
        this.botonAccion.setVisible(false);
    }

    public void mostrarFormularioDeAtaque() {
        this.getChildren().get(0).setVisible(true);
        this.getChildren().get(1).setVisible(true);
        this.inputText.setVisible(false);
        this.botonAccion.setVisible(false);
        this.botonAccion.setText("Atacar!");
        this.labelDeAyuda.setText("Haga click sobre su pais con \n el que desee atacar");
    }


    public void mostrarFormularioDeReagrupe() {
        this.getChildren().get(0).setVisible(true);
        this.getChildren().get(1).setVisible(true);
        this.inputText.setVisible(false);
        this.botonAccion.setVisible(false);
        this.botonAccion.setText("Reagrupar!");
        this.labelDeAyuda.setText("Seleccione el Pais desde el \n que desea reagrupar.");
    }

    public void setResultadoDeAtaque(VBox resultadoDeAtaque) {
        this.resultadoDeAtaque = resultadoDeAtaque;
        this.getChildren().add(resultadoDeAtaque);
    }

    public void limpiarResultadoDeBatalla() {
        try {
            this.getChildren().remove(2);
        } catch (Exception ignored) {

        }
    }

    public void setAccion(EventHandler<Event> eventHandler) {
        this.botonAccion.setOnMouseClicked(eventHandler);
        this.inputText.setOnKeyPressed(eventHandler);
    }

    public void setTextoLabelAyuda(String texto) {
        this.labelDeAyuda.setText(texto);
    }

    public void setInputTextVisible(boolean b) {
        this.inputText.setVisible(b);
    }

    public void setBotonAccionVisible(boolean b) {
        this.botonAccion.setVisible(b);
    }

    public void mostrarErrorColocacion() {
        labelDeError.setText("No quedan más ejércitos por colocar");
        labelDeError.setVisible(true);
    }

    public void mostrarErrorAtaque() {
        labelDeError.setText("Cantidad de ejércitos inválida");
        labelDeError.setVisible(true);
    }

    public void ocultarError() {
        labelDeError.setVisible(false);
    }

}
