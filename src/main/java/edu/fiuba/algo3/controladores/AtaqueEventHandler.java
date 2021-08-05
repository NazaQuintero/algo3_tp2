package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.batallasDeDados.ProcesadorResultado;
import edu.fiuba.algo3.modelo.excepciones.EjercitosInsuficientesException;
import edu.fiuba.algo3.modelo.excepciones.ElPaisNoEsLimitrofeException;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.vista.CampoDeJuego;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class AtaqueEventHandler implements EventHandler<MouseEvent>  {

    private final CampoDeJuego campoDeJuego;
    private final Pais defensor;
    private final TextField inputText;

    public AtaqueEventHandler(CampoDeJuego campoDeJuego, Pais defensor, TextField inputText) {
        this.campoDeJuego = campoDeJuego;
        this.defensor = defensor;
        this.inputText = inputText;
    }

    @Override
    public void handle(MouseEvent event) {
        try {
            int cantidadDeEjercitos = Integer.parseInt(inputText.getText());
            ProcesadorResultado.obtenerInstancia().procesar(campoDeJuego.getPaisSeleccionado().atacarA(defensor, cantidadDeEjercitos));
            System.out.println("lalala");
        } catch (ElPaisNoEsLimitrofeException | NumberFormatException | EjercitosInsuficientesException e) {
            Label errorLabel = crearErrorLabel();
            VBox vBox = (VBox) campoDeJuego.getRight();
            if(!vBox.getChildren().contains(errorLabel)) {
                vBox.getChildren().add(errorLabel);
            }
        }
    }

    private Label crearErrorLabel() {
        Label label = new Label("Cantidad de ejércitos inválida");
        label.getStyleClass().add("error");
        return label;
    }
}
