package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.FinalizarRondaEventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.rondas.Ataque;
import edu.fiuba.algo3.modelo.rondas.RondaColocacion;
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
    private Button botonCancelar;
    private VentanaTarjetas ventanaTarjetas;
    private VentanaObjetivos ventanaObjetivos;
    private Label labelObjetivoGeneral;

    public MenuLateralDerecho(CampoDeJuego campoDeJuego, Juego juego) {
        this.campoDeJuego = campoDeJuego;
        this.juego = juego;
        crearLabelObjetivoGeneral();
        juego.getTurno().addObserver(this);
        this.formularioDeAccion = crearFormularioDeAccion();
        this.getChildren().addAll(crearDescripcionDeRonda(), this.formularioDeAccion);
        this.setResultadoDeAtaque(new VBox());
        this.setSpacing(50);
        this.setAlignment(Pos.CENTER);
        this.ventanaObjetivos = new VentanaObjetivos(juego);
        this.ventanaTarjetas = new VentanaTarjetas(juego);

    }


    private VBox crearFormularioDeAccion() {
        this.labelDeAyuda = new Label("");
        labelDeAyuda.getStyleClass().add("labelText");
        labelDeAyuda.getStyleClass().add("labelAyuda");
        labelDeAyuda.setWrapText(true);
        this.labelDeError = new Label("Cantidad de ejércitos inválida");
        labelDeError.getStyleClass().add("error");
        labelDeError.getStyleClass().add("labelError");
        labelDeError.setWrapText(true);
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

        crearBotonCancelar();

        VBox vBox = new VBox(labelDeAyuda, inputText, botones, botonCancelar, labelDeError);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setVisible(false);

        Button botonTarjetas = crearBotonTarjetas();
        botonTarjetas.setAlignment(Pos.BOTTOM_RIGHT);
        vBox.getChildren().add(botonTarjetas);

        Button botonObjetivos = crearBotonObjetivos();
        botonObjetivos.setAlignment(Pos.BOTTOM_RIGHT);
        vBox.getChildren().add(botonObjetivos);

        VBox layoutObjetivoGeneral = crearLabelObjetivoGeneral();
        vBox.getChildren().add(layoutObjetivoGeneral);

        return vBox;
    }

    private Button crearBotonObjetivos() {
        Button botonObjetivos = new Button("Ver objetivo secreto");
        botonObjetivos.setOnAction(e -> ventanaObjetivos.mostrarObjetivos());
        return botonObjetivos;
    }

    private Button crearBotonTarjetas() {
        Button botonTarjetas = new Button("Ver tarjetas");
        botonTarjetas.setOnAction(e -> ventanaTarjetas.mostrarTarjetas());
        return botonTarjetas;
    }

    public void actualizarLabelObjetivoGeneral() {
        labelObjetivoGeneral.setText("Objetivo general: \n Dominar 30 paises (" + juego.getTurno().obtenerJugadorTurnoActual().cantidadPaisesDominados() + "/30 dominados por " + juego.getTurno().obtenerJugadorTurnoActual().getNombre() + ")" );
    }

    private VBox crearLabelObjetivoGeneral() {
        labelObjetivoGeneral = new Label("Objetivo general: \n Dominar 30 paises (" + juego.getTurno().obtenerJugadorTurnoActual().cantidadPaisesDominados() + "/30 dominados por " + juego.getTurno().obtenerJugadorTurnoActual().getNombre() + ")" );
        labelObjetivoGeneral.setStyle("-fx-border-color: #fd954f;");
        labelObjetivoGeneral.setAlignment(Pos.CENTER);

        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(labelObjetivoGeneral);

        return vbox;
    }

    private void crearBotonCancelar() {
        botonCancelar = new Button("Cancelar");
        botonCancelar.getStyleClass().add("cancelButton");
        botonCancelar.setOnAction(e -> {
            campoDeJuego.setPaisSeleccionado(null);
            update();
        });
        botonCancelar.setVisible(false);
    }

    private VBox crearDescripcionDeRonda() {
        this.descripcionDeRonda = new Label(this.juego.getTurno().obtenerRondaActual().obtenerDescripcion());
        this.descripcionDeRonda.getStyleClass().add("labelText");

        this.descripcionDeTurno = new Label("Es el turno de: " + this.juego.getTurno().obtenerJugadorTurnoActual().getNombre());
        this.descripcionDeTurno.getStyleClass().add("labelText");

        return new VBox(this.descripcionDeRonda, this.descripcionDeTurno);
    }

    @Override
    public void update() {
        this.descripcionDeRonda.setText(this.juego.getTurno().obtenerRondaActual().obtenerDescripcion());
        this.descripcionDeTurno.setText("Es el turno de: " + this.juego.getTurno().obtenerJugadorTurnoActual().getNombre());
        campoDeJuego.mostrarPaisesDelJugadorActual();
        ocultarError();
        this.limpiarResultadoDeBatalla();
        this.actualizarLabelObjetivoGeneral();
        this.ventanaTarjetas.actualizarVistasTarjetas();
        if (this.juego.getTurno().obtenerRondaActual() instanceof RondaColocacion) {
            this.mostrarFormularioDeColocacion();
        }
        else if (this.juego.getTurno().obtenerRondaActual() instanceof Ataque) {
            this.mostrarFormularioDeAtaque();
        }
        else {
            this.mostrarFormularioDeReagrupe();
            limpiarResultadoDeBatalla();
        }
    }

    public void mostrarFormularioDeColocacion() {
        this.getChildren().get(0).setVisible(true);
        this.getChildren().get(1).setVisible(true);
        this.botonCancelar.setVisible(false);
        this.labelDeAyuda.setText("Haga click en su país para colocar ejército\nQueda/n por colocar " +
                ((RondaColocacion) juego.getTurno().obtenerRondaActual()).getCantidadEjercitosColocables() + " ejército/s");
        this.inputText.setVisible(false);
        this.botonAccion.setVisible(false);
    }

    public void mostrarFormularioDeAtaque() {
        this.getChildren().get(0).setVisible(true);
        this.getChildren().get(1).setVisible(true);
        this.inputText.setVisible(false);
        this.inputText.clear();
        this.botonAccion.setVisible(false);
        this.botonCancelar.setVisible(false);
        this.botonAccion.setText("Atacar!");
        this.labelDeAyuda.setText("Haga click sobre su país con el que desee atacar");
    }


    public void mostrarFormularioDeReagrupe() {
        this.getChildren().get(0).setVisible(true);
        this.getChildren().get(1).setVisible(true);
        this.inputText.setVisible(false);
        inputText.clear();
        this.botonCancelar.setVisible(false);
        this.botonAccion.setVisible(false);
        this.botonAccion.setText("Reagrupar!");
        this.labelDeAyuda.setText("Seleccione el país desde el que desea reagrupar.");
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

    public void mostrarErrorAtaqueYReagrupe(int cantidadMaxima) {
        labelDeError.setText("Cantidad inválida. La cantidad debe ser como máximo " + cantidadMaxima + " ejército/s");
        labelDeError.setVisible(true);
    }

    public void ocultarError() {
        labelDeError.setVisible(false);
    }

    public void setBotonCancelarVisible(boolean b) {
        botonCancelar.setVisible(b);
    }
}
