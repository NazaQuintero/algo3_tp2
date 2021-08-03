package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.paises.Pais;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PaisEventHandler implements EventHandler<MouseEvent> {

    Pais pais;

    public PaisEventHandler(Pais pais) {
        this.pais = pais;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        pais.modificarCantidadEjercito(1);
        pais.notifyObservers();
    }

}
