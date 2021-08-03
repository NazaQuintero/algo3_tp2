package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.vista.VistaPais;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PaisEventHandler implements EventHandler<ActionEvent> {

    Pais pais;
    VistaPais vistaPais;

    public PaisEventHandler(Pais pais, VistaPais vistaPais) {
        this.pais = pais;
        this.vistaPais = vistaPais;
        vistaPais.setOnMouseClicked(e -> handle(new ActionEvent()));
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(pais.cantidadEjercitos() == 0) {
            pais.colocarEjercito(new Ejercito(new Jugador("kukamonga")));
        } else {
            pais.modificarCantidadEjercito(1);
        }
        pais.notifyObservers();
        System.out.println("Hola desde el handler: " + pais.getNombre());
    }
}
