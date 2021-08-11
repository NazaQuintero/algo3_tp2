package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.ActivarTarjetaEventHandler;
import edu.fiuba.algo3.controladores.CanjearTarjetasEventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VentanaTarjetas implements Observer {

    private final Juego juego;
    private ArrayList<VistaTarjeta> vistasTarjetas = new ArrayList<>();
    private Button botonActivar;
    private Button botonCanjear;
    private Label errorLabel;
    private ScrollPane layoutTarjetasScroll;

    public VentanaTarjetas(Juego juego) {
        this.juego = juego;
        actualizarVistasTarjetas();
    }

    public void mostrarTarjetas() {

        Stage ventanaTarjetas = new Stage();
        ventanaTarjetas.initModality(Modality.APPLICATION_MODAL);
        ventanaTarjetas.setTitle("Tarjetas");
        ventanaTarjetas.setMinWidth(200);

        GridPane layoutTarjetas = crearLayoutTarjetas();

        this.layoutTarjetasScroll = new ScrollPane();
        layoutTarjetasScroll.setContent(layoutTarjetas);
        layoutTarjetasScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox layoutBotones = new HBox();
        botonActivar = new Button("Activar tarjeta");
        botonActivar.setOnMouseClicked(new ActivarTarjetaEventHandler(juego.getTurno().obtenerJugadorTurnoActual(), this));
        botonCanjear = new Button("Canjear tarjetas");
        botonCanjear.setOnMouseClicked(new CanjearTarjetasEventHandler(juego.getTurno().obtenerJugadorTurnoActual(), this));
        Button botonCancelar = new Button("Cancelar");
        botonCancelar.setOnAction(e -> ventanaTarjetas.close());

        HBox.setMargin(botonActivar, new Insets(20,40,20,40));
        HBox.setMargin(botonCanjear, new Insets(20,40,20,40));
        HBox.setMargin(botonCancelar, new Insets(20,40,20,40));

        layoutBotones.getChildren().addAll(botonActivar, botonCanjear, botonCancelar);
        crearErrorLabel();
        VBox vBox = new VBox(layoutBotones, errorLabel);
        vBox.getStylesheets().add("styles.css");

        BorderPane layout = new BorderPane();
        layout.setCenter(layoutTarjetasScroll);
        layout.setBottom(vBox);

        Scene scene = new Scene(layout, 500, 400);
        ventanaTarjetas.setScene(scene);
        ventanaTarjetas.show();
    }

    private void crearErrorLabel() {
        errorLabel = new Label("");
        errorLabel.getStyleClass().add("error");
        errorLabel.getStyleClass().add("labelError");
        errorLabel.setAlignment(Pos.CENTER);
        errorLabel.setVisible(false);
    }

    public void actualizarVistasTarjetas() {
        Jugador unJugador = this.juego.getTurno().obtenerJugadorTurnoActual();
        vistasTarjetas.clear();
        for (Tarjeta tarjeta : unJugador.obtenerTarjetas()) vistasTarjetas.add(new VistaTarjeta(this, tarjeta));
    }

    private GridPane crearLayoutTarjetas(){
        actualizarVistasTarjetas();
        GridPane layoutTarjetas = new GridPane();
        layoutTarjetas.getStylesheets().add("styles.css");
        layoutTarjetas.setPadding(new Insets(30, 30, 30, 30));
        layoutTarjetas.setVgap(30);
        layoutTarjetas.setHgap(20);

        int i = 0, j = 0;

        for (VistaTarjeta vistaTarjeta : vistasTarjetas) {
            if (j == 4) {
                j = 0;
                i++;
            }

            GridPane.setConstraints(vistaTarjeta, j, i);
            layoutTarjetas.getChildren().add(vistaTarjeta);
            j++;
        }
        return layoutTarjetas;
    }

    @Override
    public void update() {
        updateTarjetas();
        errorLabel.setVisible(false);
    }

    private void updateTarjetas(){
        layoutTarjetasScroll.setContent(crearLayoutTarjetas());
    }

    public void mostrarError(String error){
        errorLabel.setText(error);
        errorLabel.setVisible(true);
    }

    public void ocultarError(){
        errorLabel.setVisible(false);
    }

    public ArrayList<Tarjeta> getTarjetasSeleccionadas(){
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        for (VistaTarjeta vista: vistasTarjetas){
            if (!vista.estaSeleccionada()) continue;
            tarjetas.add(vista.getTarjeta());
        }
        return tarjetas;
    }

    public void deseleccionarVistaTarjeta(Tarjeta tarjeta) {
        for (VistaTarjeta vista: vistasTarjetas){
            if (vista.getTarjeta() == tarjeta) vista.cambiarSeleccion();
        }
    }

    public Jugador getJugador(){ return juego.getTurno().obtenerJugadorTurnoActual(); }

}
