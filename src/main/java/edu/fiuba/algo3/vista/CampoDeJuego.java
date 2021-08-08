package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.turnos.Turno;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class CampoDeJuego extends BorderPane implements Observer {

    private final Juego juego;
    private final ArrayList<VistaPais> vistasPaises = new ArrayList<>();
    private Pais paisSeleccionado;

    private MenuLateralDerecho menuLateralDerecho;

    public CampoDeJuego(Stage stage, Juego juego) {
        this.juego = juego;
        this.menuLateralDerecho = new MenuLateralDerecho(this, juego);
        this.getStylesheets().add("styles.css");
        this.juego.getTurno().addObserver(this);

        Image imagen = new Image("tablero.png");
        ImageView imageView = new ImageView(imagen);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(800);

        Pane stackPane = new Pane();
        stackPane.getChildren().add(imageView);
        crearVistasPaises(stackPane);

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

        stage.setScene(new Scene(this, 1500, 900));
        stage.centerOnScreen();
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

    public void resaltarLimitrofes(VistaPais vistaPais) {
        ArrayList<VistaPais> vistaLimitrofes = vistaPais.getVistaLimitrofes();
        for (VistaPais vista : vistasPaises)
            if (!vistaLimitrofes.contains(vista) || (vistaPais.getPais().dominadoPor() == vista.getPais().dominadoPor()))
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

    @Override
    public void update() {
        if(this.juego.getTurno().obtenerRondaActual().obtenerDescripcion().equals("Ronda de colocación")) {
            this.menuLateralDerecho.mostrarFormularioDeColocacion();
        } else if (this.juego.getTurno().obtenerRondaActual().obtenerDescripcion().equals("Ronda de ataque")) {
            this.menuLateralDerecho.mostrarFormularioDeAtaque();
        } else {
            this.menuLateralDerecho.limpiarResultadoDeBatalla();
            this.menuLateralDerecho.mostrarFormularioDeReagrupe();
        }
    }
}
