package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.batallas_de_dados.Dado;
import edu.fiuba.algo3.modelo.batallas_de_dados.Dados;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class VistaDados extends VBox {

    public VistaDados(Dados dados) {
        for (Dado dado : dados) this.getChildren().add(vistaDado(dado));
    }

    private ImageView vistaDado(Dado dado) {
        Image imagenDado = new Image("dado" + dado.obtenerValor() + ".PNG");
        ImageView imageView = new ImageView(imagenDado);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
