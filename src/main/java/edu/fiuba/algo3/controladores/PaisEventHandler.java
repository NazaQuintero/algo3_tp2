package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.vista.CampoDeJuego;
import edu.fiuba.algo3.vista.VistaPais;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PaisEventHandler implements EventHandler<MouseEvent> {

    private final CampoDeJuego campoDeJuego;
    private final VistaPais vistaPais;
    private final Pais pais;

    public PaisEventHandler(VistaPais vistaPais, CampoDeJuego campoDeJuego) {
        this.pais = vistaPais.getPais();
        this.vistaPais = vistaPais;
        this.campoDeJuego = campoDeJuego;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        if (campoDeJuego.getPaisSeleccionado() == null) {
            pais.modificarCantidadEjercito(1);
            vistaPais.resaltarLimitrofes();
            vistaPais.marcarComoSeleccionada();
            campoDeJuego.setPaisSeleccionado(pais);
            campoDeJuego.setTop(crearVBoxLabel());
        } else {
            campoDeJuego.getTop().setVisible(false);
            VBox form = (VBox) campoDeJuego.getRight();
            form.setVisible(true);

            Button boton = (Button) form.getChildren().get(2);
            TextField textField = (TextField) form.getChildren().get(1);

            boton.setOnMouseClicked(new AtaqueEventHandler(campoDeJuego, pais, textField));
            textField.setOnKeyPressed(new AtaqueEventHandler(campoDeJuego, pais, textField));
            textField.requestFocus();
        }

    }

    private VBox crearVBoxLabel() {
        Label label = new Label("Seleccione el pais a atacar");
        label.getStyleClass().add("labelText");
        VBox vBox = new VBox(label);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }

}
