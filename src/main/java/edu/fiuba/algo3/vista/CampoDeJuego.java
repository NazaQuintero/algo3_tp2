package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;


public class CampoDeJuego extends BorderPane {

    private final Juego juego;
    private final ArrayList<VistaPais> vistasPaises = new ArrayList<>();
    private final VentanaTarjetas ventanaTarjetas;
    private final VentanaObjetivos ventanaObjetivos;
    private Pais paisSeleccionado;

    private MenuLateralDerecho menuLateralDerecho;

    public CampoDeJuego(Stage stage, Juego juego) {
        this.juego = juego;
        this.menuLateralDerecho = new MenuLateralDerecho(this, juego);
        this.getStylesheets().add("styles.css");

        Image imagen = new Image("tablero.png");
        ImageView imageView = new ImageView(imagen);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(800);

        Pane stackPane = new Pane();
        stackPane.getChildren().add(imageView);
        crearVistasPaises(stackPane);

        HBox anHbox = new HBox(stackPane);
        anHbox.setAlignment(Pos.CENTER);
        VBox aVbox = new VBox(anHbox);
        aVbox.setAlignment(Pos.CENTER);
        setMargin(this, new Insets(50,50,50,50));
        this.setCenter(aVbox);
        this.setRight(this.menuLateralDerecho);

        this.mostrarPaisesDelJugadorActual();
        this.mostrarMenuLateralDerecho();

        this.ventanaTarjetas = new VentanaTarjetas(juego);
        Button botonTarjetas = new Button("Ver tarjetas");
        botonTarjetas.setOnAction(e -> ventanaTarjetas.mostrarTarjetas());

        this.ventanaObjetivos = new VentanaObjetivos(juego);
        Button botonObjetivos = new Button("Ver objetivos");
        botonObjetivos.setOnAction(e -> ventanaObjetivos.mostrarObjetivos());

        //stage.setScene(new Scene(this, 1500, 900));
        stage.setScene(new Scene(this, 1000, 700));
        stage.centerOnScreen();

        this.setCenter(stackPane);
        this.setTop(botonTarjetas);
        this.setBottom(botonObjetivos);
    }



    private void crearVistasPaises(Pane stackPane) {

        ArrayList<Pais> _paises = new ArrayList<>(MultitonPaises.obtenerTodosLosPaises());

        for (Pais pais : _paises) vistasPaises.add(new VistaPais(pais, this));

        for (VistaPais vistaPais : vistasPaises) {
            for (VistaPais vistaPaisLimitrofe : vistasPaises) {
                if (vistaPais.getLimitrofes().contains(vistaPaisLimitrofe.getPais()))
                    vistaPais.setVistaLimitrofe(vistaPaisLimitrofe);
            }
        }
        stackPane.getChildren().addAll(vistasPaises);
    }

    public void resaltarLimitrofesAdversarios(VistaPais vistaPais) {
        ArrayList<VistaPais> vistaLimitrofes = vistaPais.getVistaLimitrofes();
        for (VistaPais vista : vistasPaises)
            if (!vistaLimitrofes.contains(vista) || (vistaPais.getPais().dominadoPor() == vista.getPais().dominadoPor()))
               vista.desactivar();
    }

    public void resaltarLimitrofesPropios(VistaPais vistaPais) {
        ArrayList<VistaPais> vistaLimitrofes = vistaPais.getVistaLimitrofes();
        for (VistaPais vista : vistasPaises)
            if (!vistaLimitrofes.contains(vista) || (vistaPais.getPais().dominadoPor() != vista.getPais().dominadoPor()))
                vista.desactivar();
    }

    public void mostrarPaises() { for (VistaPais vista : vistasPaises) vista.activar(); }

    public void mostrarPaisesDelJugadorActual() {
        this.mostrarPaises();
        Jugador unJugador = this.juego.getTurno().obtenerJugadorTurnoActual();
        for (VistaPais vista: vistasPaises) {
            if(vista.getJugadorDominante() != unJugador) {
                vista.desactivar();
            }
        }
    }

    public void setPaisSeleccionado(Pais pais) {
        this.paisSeleccionado = pais;
    }

    public Pais getPaisSeleccionado() {
        return paisSeleccionado;
    }




    public Turno getTurno() {
        return this.juego.getTurno();
    }

    private void mostrarMenuLateralDerecho() {
        this.menuLateralDerecho.mostrarFormularioDeColocacion();
    }

}
