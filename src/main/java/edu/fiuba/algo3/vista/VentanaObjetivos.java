package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.objetivos.Objetivo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VentanaObjetivos {

    private final Juego juego;
    private ArrayList<VistaObjetivo> vistasObjetivos = new ArrayList<>();

    public VentanaObjetivos(Juego juego) {
        this.juego = juego;
        crearVistasObjetivos();
    }

    public void mostrarObjetivos() {
        crearVistasObjetivos();

        Stage ventanaObjetivos = new Stage();
        ventanaObjetivos.getIcons().add(new Image("icono.png"));
        ventanaObjetivos.initModality(Modality.APPLICATION_MODAL);
        ventanaObjetivos.setTitle("Objetivo Secreto");

        HBox layoutObjetivos = new HBox();
        layoutObjetivos.setSpacing(50);

        VistaObjetivo objetivoSecreto = vistasObjetivos.get(1);

        Label labelObjetivoSecreto = new Label(objetivoSecreto.obtenerDescripcion(), objetivoSecreto.obtenerImagen());
        labelObjetivoSecreto.setContentDisplay(ContentDisplay.TOP);
        labelObjetivoSecreto.setStyle("-fx-border-color: #8792a3; -fx-background-color: #e7e9ec;");
        labelObjetivoSecreto.setMinSize(600, 200);
        labelObjetivoSecreto.setAlignment(Pos.CENTER);

        layoutObjetivos.setAlignment(Pos.CENTER);
        layoutObjetivos.getChildren().add(labelObjetivoSecreto);

        Scene scene = new Scene(layoutObjetivos, 600, 200);
        ventanaObjetivos.setScene(scene);
        ventanaObjetivos.show();

    }

    public void crearVistasObjetivos() {
        vistasObjetivos = new ArrayList<>();
        Jugador unJugador = this.juego.getTurno().obtenerJugadorTurnoActual();

        unJugador.obtenerObjetivos().forEach(objetivo -> vistasObjetivos.add(new VistaObjetivo(objetivo)));

    }

}
