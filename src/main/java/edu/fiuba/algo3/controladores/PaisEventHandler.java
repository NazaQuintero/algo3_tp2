package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.excepciones.EjercitosInsuficientesException;
import edu.fiuba.algo3.modelo.excepciones.ElPaisNoEsLimitrofeException;
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
    private final VBox formularioDeAtaque;
    private Pais pais;

    public PaisEventHandler(VistaPais vistaPais, CampoDeJuego campoDeJuego, VBox formularioDeAtaque) {
        this.pais = vistaPais.getPais();
        this.vistaPais = vistaPais;
        this.campoDeJuego = campoDeJuego;
        this.formularioDeAtaque = formularioDeAtaque;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        if (campoDeJuego.getPaisSeleccionado() == null) {
            pais.modificarCantidadEjercito(1);
            vistaPais.resaltarLimitrofes();
            vistaPais.marcarComoSeleccionada();
            campoDeJuego.setPaisSeleccionado(pais);
        } else {
            VBox form = (VBox) campoDeJuego.getRight();
            form.setVisible(true);

            Button boton = (Button) form.getChildren().get(2);
            TextField textField = (TextField) form.getChildren().get(1);

            boton.setOnMouseClicked(new AtaqueEventHandler(campoDeJuego, pais, textField));
            form.getChildren().get(1).requestFocus();
        }

    }

}
