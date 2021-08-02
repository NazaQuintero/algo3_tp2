package edu.fiuba.algo3.vista;

import com.google.gson.Gson;
import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.PosicionPais;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class CampoDeJuego extends HBox {

    static final String ARCHIVO_PAISES = "posicion-paises.json";

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
        Gson gson = new Gson();

        String json2 = null;

        try {
            InputStream is = App.class.getClassLoader().getResourceAsStream(ARCHIVO_PAISES);
            json2 = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException | NullPointerException e) { e.printStackTrace();}

        PosicionPais[] vistaPais  = gson.fromJson(json2, PosicionPais[].class);

        ArrayList<VistaPais> paises = new ArrayList<>();
        for(PosicionPais pais: vistaPais) paises.add(new VistaPais(pais));

        stackPane.getChildren().addAll(paises);

    }

}
