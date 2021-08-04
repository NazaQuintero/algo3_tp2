package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.vista.VistaPais;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PaisEventHandler implements EventHandler<MouseEvent> {

    private final VistaPais vistaPais;
    private Pais pais;

    public PaisEventHandler(VistaPais vistaPais) {
        this.pais = vistaPais.getPais();
        this.vistaPais = vistaPais;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        pais.modificarCantidadEjercito(1);
        vistaPais.resaltarLimitrofes();

        pais.notifyObservers();
    }

}
