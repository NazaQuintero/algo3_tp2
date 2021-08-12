package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.turnos.Turno;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;


public class CampoDeJuego extends BorderPane {

    private final Juego juego;
    private final ArrayList<VistaPais> vistasPaises = new ArrayList<>();
    private final MenuLateralDerecho menuLateralDerecho;
    private Pais paisSeleccionado;
    private VentanaVictoria ventanaVictoria;

    public CampoDeJuego(Stage stage, Juego juego) {
        this.juego = juego;
        this.menuLateralDerecho = new MenuLateralDerecho(this, juego);
        this.getStylesheets().add("styles.css");

        crearTablero();
        this.setRight(this.menuLateralDerecho);

        this.mostrarPaisesDelJugadorActual();
        this.mostrarMenuLateralDerecho();
        this.ventanaVictoria = new VentanaVictoria();

        stage.setScene(new Scene(this, 1500, 900));
        stage.centerOnScreen();
    }


    public Juego getJuego() {
        return juego;
    }

    private void crearTablero() {
        Pane pane = new Pane();
        pane.getChildren().add(crearVistaImagen());
        crearVistasPaises(pane);

        HBox anHbox = new HBox(pane);
        anHbox.setAlignment(Pos.CENTER);
        VBox aVbox = new VBox(anHbox);
        aVbox.setAlignment(Pos.CENTER);
        setMargin(this, new Insets(50,50,50,50));

        this.setCenter(aVbox);
    }

    private ImageView crearVistaImagen() {
        Image imagen = new Image("tablero.png");
        ImageView imageView = new ImageView(imagen);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(800);
        return imageView;
    }

    private void crearVistasPaises(Pane pane) {

        ArrayList<Pais> paises = new ArrayList<>(MultitonPaises.obtenerTodosLosPaises());

        paises.forEach(pais -> vistasPaises.add(new VistaPais(pais, this)));

        setVistasPaisesLimitrofes();
        pane.getChildren().addAll(vistasPaises);
    }

    private void setVistasPaisesLimitrofes() {
        vistasPaises.forEach(vistaPais -> vistasPaises.stream().filter(vistaPaisLimitrofe -> vistaPais.getLimitrofes().
            contains(vistaPaisLimitrofe.getPais())).forEach(vistaPais::setVistaLimitrofe));
    }

    public void resaltarLimitrofesAdversarios(VistaPais vistaPais) {
        this.vistasPaises.stream().filter( vista -> !vistaPais.getVistaLimitrofes().contains(vista) ||
                (vistaPais.getPais().dominadoPor() == vista.getPais().dominadoPor())).
                forEach(VistaPais::desactivar);
    }

    public void resaltarLimitrofesPropios(VistaPais vistaPais) {
        this.vistasPaises.stream().filter( vista -> !vistaPais.getVistaLimitrofes().contains(vista) ||
                (vistaPais.getPais().dominadoPor() != vista.getPais().dominadoPor())).
                forEach(VistaPais::desactivar);
    }

    public void mostrarPaises() { for (VistaPais vista : vistasPaises) vista.activar(); }

    public void mostrarPaisesDelJugadorActual() {
        this.mostrarPaises();
        this.vistasPaises.stream().filter(vista -> vista.getJugadorDominante() != this.juego.getTurno().obtenerJugadorTurnoActual()).
                forEach(VistaPais::desactivar);
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

    public void actualizarObjetivoGeneral() {
        menuLateralDerecho.actualizarLabelObjetivoGeneral();
    }

    public void checkearGanador() {
//        if (this.juego.estaTerminado()) {
        if(true) {
            ventanaVictoria.setGanador(this.getTurno().obtenerJugadorTurnoActual());
            ventanaVictoria.mostrar();

        }
    }
}
