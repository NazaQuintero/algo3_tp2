package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VentanaTarjetas {

    private final Juego juego;
    private ArrayList<VistaTarjeta> vistasTarjetas = new ArrayList<>();


    public VentanaTarjetas(Juego juego) {
        this.juego = juego;
        crearVistasTarjetas();
    }

    public void mostrarTarjetas() {

        Stage ventanaTarjetas = new Stage();
        ventanaTarjetas.initModality(Modality.APPLICATION_MODAL);
        ventanaTarjetas.setTitle("Tarjetas");
        ventanaTarjetas.setMinWidth(200);

        GridPane layoutTarjetas = new GridPane();
        layoutTarjetas.setPadding(new Insets(30, 30, 30, 30));
        layoutTarjetas.setVgap(30);
        layoutTarjetas.setHgap(20);

        int i = 0;
        int j = 0;

        for (VistaTarjeta v : vistasTarjetas) {
            if (j == 4) {
                j = 0;
                i++;
            }
            Button botonTarjeta = new Button(v.obtenerNombrePais(), v.obtenerImagen());
            botonTarjeta.setContentDisplay(ContentDisplay.BOTTOM);

            botonTarjeta.setMaxWidth(82);
            botonTarjeta.setMaxHeight(250);

            GridPane.setConstraints(botonTarjeta, j, i);
            layoutTarjetas.getChildren().add(botonTarjeta);
            j++;
        }

        ScrollPane layoutTarjetasScroll = new ScrollPane();
        layoutTarjetasScroll.setContent(layoutTarjetas);
        layoutTarjetasScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox layoutBotones = new HBox();
        Button botonActivar = new Button("Activar tarjeta");
        Button botonCanjear = new Button("Canjear tarjetas");
        Button botonCancelar = new Button("Cancelar");

        HBox.setMargin(botonActivar, new Insets(20,40,20,40));
        HBox.setMargin(botonCanjear, new Insets(20,40,20,40));
        HBox.setMargin(botonCancelar, new Insets(20,40,20,40));

        botonCancelar.setOnAction(e -> ventanaTarjetas.close());
        layoutBotones.getChildren().addAll(botonActivar, botonCanjear, botonCancelar);

        BorderPane layout = new BorderPane();
        layout.setCenter(layoutTarjetasScroll);
        layout.setBottom(layoutBotones);

        Scene scene = new Scene(layout, 500, 400);
        ventanaTarjetas.setScene(scene);
        ventanaTarjetas.showAndWait();
    }

    public void crearVistasTarjetas() {
        Jugador unJugador = this.juego.getTurno().obtenerJugadorTurnoActual();
        for (Tarjeta t : unJugador.obtenerTarjetas()) vistasTarjetas.add(new VistaTarjeta(t));
    }

}
