package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.observables.Observer;
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


public class CampoDeJuego extends BorderPane implements Observer {

    private final Juego juego;
    private final ArrayList<VistaPais> vistasPaises = new ArrayList<>();
    private ArrayList<VistaTarjeta> vistasTarjetas = new ArrayList<>();
    private Pais paisSeleccionado;

    private MenuLateralDerecho menuLateralDerecho;

    public CampoDeJuego(Stage stage, Juego juego) {
        this.juego = juego;
        this.menuLateralDerecho = new MenuLateralDerecho(this, juego);
        this.getStylesheets().add("styles.css");
        this.juego.getTurno().addObserver(this);
        this.juego.getTurno().addObserver(this);

        Button botonTarjetas = new Button("Ver tarjetas");
        botonTarjetas.setOnAction(e -> mostrarTarjetas());

        Image imagen = new Image("tablero.png");
        ImageView imageView = new ImageView(imagen);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(800);

        Pane stackPane = new Pane();
        stackPane.getChildren().addAll(imageView, botonTarjetas);
        crearVistasPaises(stackPane);
        crearVistasTarjetas();

        /*
        Button btnNew = new Button("New");
        Button btnPause = new Button("Pause");
        Button btnQuit = new Button("Quit");
        ToolBar toolBar = new ToolBar();
        toolBar.getItems().addAll(
                new Separator(),
                btnNew,
                btnPause,
                btnQuit
        );
        */

        HBox anHbox = new HBox(stackPane);
        anHbox.setAlignment(Pos.CENTER);
        VBox aVbox = new VBox(anHbox);
        aVbox.setAlignment(Pos.CENTER);
        setMargin(this, new Insets(50,50,50,50));
        this.setCenter(aVbox);
        this.setRight(this.menuLateralDerecho);
        this.mostrarPaisesDelJugadorActual();
        this.mostrarMenuLateralDerecho();

        //stage.setScene(new Scene(this, 1500, 900)); // esto lo voy a poner mas chico por ahora xq en mi pantalla se corta tod0
        stage.setScene(new Scene(this, 1000, 600));
        stage.centerOnScreen();
        this.getChildren().add(stackPane); // a ver ahi mandale npi xddd
        //stage.setScene(new Scene(this, 1400, 900)); // esto lo voy a poner mas chico por ahora xq en mi pantalla se corta tod0
        stage.setScene(new Scene(this, 1000, 600));

    }

    public void crearVistasTarjetas() {
        vistasTarjetas = new ArrayList<>();
        Jugador unJugador = this.juego.getTurno().obtenerJugadorTurnoActual();
        for (Tarjeta t : unJugador.obtenerTarjetas()) vistasTarjetas.add(new VistaTarjeta(t));
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

    public void mostrarTarjetas() {

        Stage ventanaTarjetas = new Stage();
        ventanaTarjetas.initModality(Modality.APPLICATION_MODAL);
        ventanaTarjetas.setTitle("a");
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

            layoutTarjetas.setConstraints(botonTarjeta, j, i);
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

        layoutBotones.setMargin(botonActivar, new Insets(20,40,20,40));
        layoutBotones.setMargin(botonCanjear, new Insets(20,40,20,40));
        layoutBotones.setMargin(botonCancelar, new Insets(20,40,20,40));

        botonCancelar.setOnAction(e -> ventanaTarjetas.close());
        layoutBotones.getChildren().addAll(botonActivar, botonCanjear, botonCancelar);

        BorderPane layout = new BorderPane();
        layout.setCenter(layoutTarjetasScroll);
        layout.setBottom(layoutBotones);

        Scene scene = new Scene(layout, 500, 400);
        ventanaTarjetas.setScene(scene);
        ventanaTarjetas.showAndWait();
    }


    public Turno getTurno() {
        return this.juego.getTurno();
    }

    private void mostrarMenuLateralDerecho() {
        this.menuLateralDerecho.mostrarFormularioDeColocacion();
    }

    @Override
    public void update() {
        if(this.juego.getTurno().obtenerRondaActual().obtenerDescripcion().equals("Ronda de colocación")) {
            this.menuLateralDerecho.mostrarFormularioDeColocacion();
        } else if (this.juego.getTurno().obtenerRondaActual().obtenerDescripcion().equals("Ronda de ataque")) {
            this.menuLateralDerecho.mostrarFormularioDeAtaque();
        } else {
            this.menuLateralDerecho.mostrarFormularioDeReagrupe();
        }
    }
}
