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
    private final ArrayList<VistaTarjeta> vistasTarjetas = new ArrayList<>();
    private ScrollPane layoutTarjetasScroll;
    private HBox layoutBotones;
    private Label infoLabel;

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

        layoutBotones = new HBox();
        crearBotonActivar();
        crearBotonCanjear();
        crearBotonCancelar(ventanaTarjetas);

        crearInfoLabel();
        VBox vBox = new VBox(layoutBotones, infoLabel);
        vBox.getStylesheets().add("styles.css");

        BorderPane layout = new BorderPane();
        layout.setCenter(layoutTarjetasScroll);
        layout.setBottom(vBox);

        Scene scene = new Scene(layout, 500, 400);
        ventanaTarjetas.setScene(scene);
        ventanaTarjetas.show();
    }

    private void crearBotonActivar() {
        Button botonActivar = new Button("Activar tarjeta");
        botonActivar.setOnMouseClicked(new ActivarTarjetaEventHandler(juego.getTurno().obtenerJugadorTurnoActual(), this));
        HBox.setMargin(botonActivar, new Insets(20,40,10,40));
        layoutBotones.getChildren().add(botonActivar);
    }

    private void crearBotonCanjear() {
        Button botonCanjear = new Button("Canjear tarjetas");
        botonCanjear.setOnMouseClicked(new CanjearTarjetasEventHandler(juego.getTurno().obtenerJugadorTurnoActual(), this));
        HBox.setMargin(botonCanjear, new Insets(20,40,10,40));
        layoutBotones.getChildren().add(botonCanjear);
    }

    private void crearBotonCancelar(Stage ventanaTarjetas) {
        Button botonCancelar = new Button("Cancelar");
        botonCancelar.setOnAction(e -> ventanaTarjetas.close());
        HBox.setMargin(botonCancelar, new Insets(20,40,10,40));
        layoutBotones.getChildren().add(botonCancelar);
    }

    private void crearInfoLabel() {
        infoLabel = new Label("");
        infoLabel.setAlignment(Pos.CENTER);
        infoLabel.setVisible(false);
    }

    public void actualizarVistasTarjetas() {
        Jugador unJugador = this.juego.getTurno().obtenerJugadorTurnoActual();
        vistasTarjetas.clear();
        for (Tarjeta tarjeta : unJugador.getTarjetas()) vistasTarjetas.add(new VistaTarjeta(this, tarjeta));
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
        infoLabel.setVisible(false);
    }

    private void updateTarjetas(){
        layoutTarjetasScroll.setContent(crearLayoutTarjetas());
    }

    public void mostrarError(String error){
        infoLabel.getStyleClass().clear();
        infoLabel.getStyleClass().add("errorTarjetas");
        infoLabel.setText(error);
        infoLabel.setVisible(true);
    }

    public void mostrarMensajeValido(String mensaje){
        infoLabel.getStyleClass().clear();
        infoLabel.getStyleClass().add("tarjetasValidas");
        infoLabel.setText(mensaje);
        infoLabel.setVisible(true);
    }

    public void ocultarError(){
        infoLabel.setVisible(false);
    }

    public ArrayList<Tarjeta> getTarjetasSeleccionadas(){
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        vistasTarjetas.stream().filter(VistaTarjeta::estaSeleccionada).forEach(vTarjeta -> tarjetas.add(vTarjeta.getTarjeta()));
        return tarjetas;
    }

    public void deseleccionarVistaTarjeta(Tarjeta tarjeta) {
        /*
        for (VistaTarjeta vista: vistasTarjetas){
            if (vista.getTarjeta() == tarjeta) vista.cambiarSeleccion();
        }

         */
        vistasTarjetas.stream().filter(vistaTarjeta -> vistaTarjeta.getTarjeta() == tarjeta).
                forEach(VistaTarjeta::cambiarSeleccion);
    }

    public Jugador getJugador(){ return juego.getTurno().obtenerJugadorTurnoActual(); }

}
