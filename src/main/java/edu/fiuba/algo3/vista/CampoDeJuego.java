package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Globo;
import edu.fiuba.algo3.modelo.tarjetas.Simbolo;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;


public class CampoDeJuego extends HBox {

    private final Juego juego;
    private final ArrayList<VistaPais> vistasPaises = new ArrayList<>();
    private final ArrayList<VistaTarjeta> vistasTarjetas = new ArrayList<>();
    private Pais paisSeleccionado;

    public CampoDeJuego(Stage stage, Juego juego) {
        this.juego = juego;
        this.getStylesheets().add("styles.css");

        Button botonTarjetas = new Button("Ver tarjetas");
        botonTarjetas.setOnAction(e -> mostrarTETAS());

        Image imagen = new Image("tablero.png");
        ImageView imageView = new ImageView(imagen);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(800);

        Pane stackPane = new Pane();
        stackPane.getChildren().addAll(imageView, botonTarjetas);
        crearVistasPaises(stackPane);
        crearVistasTarjetas();// es el layout eso sin mostrarse igual, solo inicializarlas, seria una partecita del metodo del boton de ver tarjetas  tarjetasJugadorActual y no se que mierda es stackpane pero ahi se harian las vbistas de las tarjetetas  @camila

        this.getChildren().add(stackPane); // a ver ahi mandale npi xddd
        this.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(this, 1400, 900));

    }

    private void crearVistasTarjetas() {
        ArrayList<Tarjeta> tarjetasJugadorActual = new ArrayList<>();

        Pais pais = new Pais("Argentina");
        Simbolo simbolo = new Globo();
        Tarjeta tarjeta = new Tarjeta(pais, simbolo);
        tarjetasJugadorActual.add(tarjeta);
        tarjetasJugadorActual.add(tarjeta);
        tarjetasJugadorActual.add(tarjeta);
        tarjetasJugadorActual.add(tarjeta);
        tarjetasJugadorActual.add(tarjeta);

        for (Tarjeta t : tarjetasJugadorActual) vistasTarjetas.add(new VistaTarjeta(t));
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
        for (VistaPais vista : vistasPaises) {
            if (!vistaLimitrofes.contains(vista) || (vistaPais.getPais().dominadoPor() == vista.getPais().dominadoPor())){
               vista.desactivar();
            }
        }
    }

    public void mostrarPaises() { for (VistaPais vista : vistasPaises) vista.activar(); }

    public void setPaisSeleccionado(Pais pais) {
        this.paisSeleccionado = pais;
    }

    public Pais getPaisSeleccionado() {
        return paisSeleccionado;
    }

    public void mostrarTETAS() { // WTF no habia notado ese mnombre

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
            Button botonTarjeta = new Button("", v.obtenerImagen()); // garcias

            botonTarjeta.setMaxWidth(82);
            botonTarjeta.setMaxHeight(250);

            layoutTarjetas.setConstraints(botonTarjeta, j, i);
            layoutTarjetas.getChildren().add(botonTarjeta);
            j++;
        }

        HBox layoutBotones = new HBox();
        Button botonActivar = new Button("Activar tarjeta"); //poggerino
        Button botonCanjear = new Button("Canjear tarjetas"); // poggerinox
        Button botonCancelar = new Button("Cancelar"); // poggerinoxxx

        layoutBotones.setMargin(botonActivar, new Insets(20,40,20,40));
        layoutBotones.setMargin(botonCanjear, new Insets(20,40,20,40));
        layoutBotones.setMargin(botonCancelar, new Insets(20,40,20,40));

        botonCancelar.setOnAction(e -> ventanaTarjetas.close());
        layoutBotones.getChildren().addAll(botonActivar, botonCanjear, botonCancelar);

        BorderPane layout = new BorderPane(); // lei border pene :pogo: 0W0 no se si esto funcara probemo ok
        layout.setCenter(layoutTarjetas);
        layout.setBottom(layoutBotones);

        Scene scene = new Scene(layout, 500, 400);
        ventanaTarjetas.setScene(scene);
        ventanaTarjetas.showAndWait();
    }

}

// https://www.youtube.com/watch?v=c5vh7jtrW2Y&ab_channel=Tjokanche y esto que es