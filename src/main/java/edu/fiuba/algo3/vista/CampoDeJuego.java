package edu.fiuba.algo3.vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class CampoDeJuego extends HBox {

    public CampoDeJuego(Stage stage) {

        this.getStylesheets().add("styles.css");

        Image imagen = new Image("tablero.png");
        ImageView imageView = new ImageView(imagen);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(800);

        Pane stackPane = new Pane();
        stackPane.getChildren().add(imageView);
        crearPaises(stackPane);

        this.getChildren().add(stackPane);
        this.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(this, 1000, 800));
    }

    private void crearPaises(Pane stackPane) {

        // America del Norte
        VistaPais alaska = new VistaPais(10, 260, "Alaska");
        VistaPais yukon = new VistaPais(70, 230, "Yukon");
        VistaPais canada = new VistaPais(130, 170, "Canada");
        VistaPais groenlandia = new VistaPais(320, 150, "Groenlandia");
        VistaPais oregon = new VistaPais(80, 290, "Oregon");
        VistaPais california = new VistaPais(130, 310, "Groenlandia");
        VistaPais mexico = new VistaPais(185, 340, "Mexico");
        VistaPais nuevaYork = new VistaPais(175, 260, "Nueva York");
        VistaPais terranova = new VistaPais(210, 230, "Terranova");
        VistaPais labrador = new VistaPais(240, 200, "Labrador");

        // America del Sur
        VistaPais colombia = new VistaPais(290, 390, "Colombia");
        VistaPais peru = new VistaPais(290, 440, "Peru");
        VistaPais brasil = new VistaPais(370, 430, "Brasil");
        VistaPais uruguay = new VistaPais(370, 500, "Uruguay");
        VistaPais argentina = new VistaPais(320, 510, "Argentina");
        VistaPais chile = new VistaPais(270, 510, "Chile");

        // Europa
        VistaPais islandia = new VistaPais(420, 260, "Islandia");
        VistaPais espania = new VistaPais(500, 390, "Espania");
        VistaPais granBretania = new VistaPais(520, 270, "Gran Bretania");
        VistaPais francia = new VistaPais(560, 340, "Francia");
        VistaPais alemania = new VistaPais(620, 330, "Alemania");
        VistaPais italia = new VistaPais(620, 390, "Italia");
        VistaPais polonia = new VistaPais(680, 310, "Polonia");
        VistaPais rusia = new VistaPais(680, 230, "Rusia");
        VistaPais suecia = new VistaPais(590, 190, "Suecia");

        // Africa
        VistaPais sahara = new VistaPais(580, 480, "Sahara");
        VistaPais egipto = new VistaPais(700, 450, "Egipto");
        VistaPais etiopia = new VistaPais(670, 480, "Etiopia");
        VistaPais zaire = new VistaPais(630, 520, "Zaire");
        VistaPais sudafrica = new VistaPais(680, 550, "Sudafrica");
        VistaPais madagascar = new VistaPais(770, 520, "Madagascar");


        stackPane.getChildren().addAll(alaska, yukon, canada, groenlandia, oregon, california, mexico, nuevaYork, terranova, labrador);

        stackPane.getChildren().addAll(colombia, peru, brasil, uruguay, argentina, chile);

        stackPane.getChildren().addAll(islandia, espania, granBretania, francia, alemania,italia, polonia, rusia, suecia);

        stackPane.getChildren().addAll(sahara, egipto, etiopia, zaire, sudafrica, madagascar);

    }

}
