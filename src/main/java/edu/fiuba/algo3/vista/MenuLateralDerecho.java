package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.AtaqueEventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.observables.Observer;
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

        Button botonFinalizarRonda = new Button("Finalizar Ronda");
        botonFinalizarRonda.getStyleClass().add("exitButton");

        HBox botones = new HBox(botonAccion, botonFinalizarRonda);

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

        VBox vBox = new VBox(this.descripcionDeRonda, this.descripcionDeTurno);

        return vBox;
    }

    @Override
    public void update() {
        this.descripcionDeRonda.setText(this.juego.getTurno().obtenerRondaActual().obtenerDescripcion());
        this.descripcionDeTurno.setText("Es el turno de: " + this.juego.getTurno().obtenerJugadorTurnoActual().obtenerNombre());
        campoDeJuego.mostrarPaisesDelJugadorActual();
    }

    public void mostrar() {
        this.getChildren().get(0).setVisible(true);
        this.getChildren().get(1).setVisible(true);
    }

    public void mostrarFormularioDeColocacion() {
        this.getChildren().get(0).setVisible(true);
        this.getChildren().get(1).setVisible(true);
        this.labelDeAyuda.setText("Haga click en su pais \n para colocar ejercito");
        this.inputText.setVisible(false);
        this.botonAccion.setVisible(false);
    }

    public void mostrarFormularioDeAtaque() {
        this.getChildren().get(0).setVisible(true);
        this.getChildren().get(1).setVisible(true);
        this.labelDeAyuda.setText("Ingrese la cantidad de ejércitos: ");
        this.inputText.setVisible(true);
        this.botonAccion.setVisible(true);
        this.botonAccion.setText("Atacar!");
    }


    public void mostrarFormularioDeReagrupe() {
        this.getChildren().get(0).setVisible(true);
        this.getChildren().get(1).setVisible(true);
        this.inputText.setVisible(true);
        this.botonAccion.setVisible(true);
        this.botonAccion.setText("Reagrupar!");
    }

    public void setResultadoDeAtaque(VBox resultadoDeAtaque) {
        this.resultadoDeAtaque = resultadoDeAtaque;
        this.getChildren().add(resultadoDeAtaque);
    }

    public void limpiarResultadoDeBatalla() {
        if(this.getChildren().get(2) != null) {
            this.getChildren().remove(2);
        }
    }
}
