package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.ActivarTarjetaEventHandler;
import edu.fiuba.algo3.controladores.TarjetaEventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
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
    private Tarjeta tarjetaSeleccionada;
    private Button botonActivar;
    private Button botonCanjear;
    private Label errorLabel;

    public VentanaTarjetas(Juego juego) {
        this.juego = juego;
        crearVistasTarjetas();
    }

    public void mostrarTarjetas() {

        Stage ventanaTarjetas = new Stage();
        ventanaTarjetas.initModality(Modality.APPLICATION_MODAL);
        ventanaTarjetas.setTitle("Tarjetas");
        ventanaTarjetas.setMinWidth(200);

        GridPane layoutTarjetas = crearLayoutTarjetas();
        layoutTarjetas.getStylesheets().add("styles.css");

        ScrollPane layoutTarjetasScroll = new ScrollPane();
        layoutTarjetasScroll.setContent(layoutTarjetas);
        layoutTarjetasScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox layoutBotones = new HBox();
        botonActivar = new Button("Activar tarjeta");
        botonActivar.setOnMouseClicked(new ActivarTarjetaEventHandler(juego.getTurno().obtenerJugadorTurnoActual(), this));
        botonCanjear = new Button("Canjear tarjetas");
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
        ventanaTarjetas.showAndWait();
    }

    private void crearErrorLabel() {
        errorLabel = new Label("");
        errorLabel.getStyleClass().add("error");
        errorLabel.getStyleClass().add("labelError");
        errorLabel.setAlignment(Pos.CENTER);
        errorLabel.setVisible(false);
    }

    public void crearVistasTarjetas() {
        Jugador unJugador = this.juego.getTurno().obtenerJugadorTurnoActual();
        vistasTarjetas.clear();
        for (Tarjeta t : unJugador.obtenerTarjetas()) vistasTarjetas.add(new VistaTarjeta(t));
    }

    private GridPane crearLayoutTarjetas(){
        crearVistasTarjetas();  // Actualiza la lista de Tarjetas
        GridPane layoutTarjetas = new GridPane();
        layoutTarjetas.setPadding(new Insets(30, 30, 30, 30));
        layoutTarjetas.setVgap(30);
        layoutTarjetas.setHgap(20);

        int i = 0, j = 0;

        for (VistaTarjeta vistaTarjeta : vistasTarjetas) {
            if (j == 4) {
                j = 0;
                i++;
            }
            String nombrePais = vistaTarjeta.getTarjeta().obtenerPais().getNombre();
            Button botonTarjeta = new Button(nombrePais, vistaTarjeta.obtenerImagen());
            botonTarjeta.setContentDisplay(ContentDisplay.BOTTOM);
            botonTarjeta.setOnMouseClicked(new TarjetaEventHandler(this, vistaTarjeta));

            botonTarjeta.setMaxWidth(82);
            botonTarjeta.setMaxHeight(250);

            GridPane.setConstraints(botonTarjeta, j, i);
            layoutTarjetas.getChildren().add(botonTarjeta);
            j++;
        }
        return layoutTarjetas;
    }

    @Override
    public void update() {
        crearLayoutTarjetas();
        errorLabel.setVisible(false);
        // botonCanjear.setOnMouseClicked(new CanjearTarjetasEventHandler());
    }

    public void setTarjetaSeleccionada(Tarjeta tarjeta) {
        tarjetaSeleccionada = tarjeta;
    }

    public Tarjeta getTarjetaSeleccionada() {
        return tarjetaSeleccionada;
    }

    public void mostrarErrorYaFueActivada(String nombrePais) {
        errorLabel.setText("La tarjeta de " + nombrePais + " ya fue activada");
        errorLabel.setVisible(true);
    }

    public void mostrarErrorNingunaTarjetaSeleccionada() {
        errorLabel.setText("No hay ninguna tarjeta seleccionada");
        errorLabel.setVisible(true);
    }
}
