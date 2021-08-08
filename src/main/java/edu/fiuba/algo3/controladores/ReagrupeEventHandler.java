package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.vista.CampoDeJuego;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class ReagrupeEventHandler implements EventHandler<Event> {
    public ReagrupeEventHandler(CampoDeJuego campoDeJuego, Pais pais, TextField textField) {
    }

    @Override
    public void handle(Event event) {
        System.out.println("ReagrupeHandler called!");
    }
}
