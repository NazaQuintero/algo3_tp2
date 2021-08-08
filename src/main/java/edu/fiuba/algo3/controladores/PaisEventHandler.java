package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.rondas.Ronda;
import edu.fiuba.algo3.vista.CampoDeJuego;
import edu.fiuba.algo3.vista.MenuLateralDerecho;
import edu.fiuba.algo3.vista.VistaPais;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

        Ronda ronda = this.campoDeJuego.getTurno().obtenerRondaActual();
        MenuLateralDerecho menuLateral = (MenuLateralDerecho) campoDeJuego.getRight();
        VBox form = (VBox) menuLateral.getChildren().get(1);
        TextField textField = (TextField) form.getChildren().get(1);
        HBox botones = (HBox) form.getChildren().get(2);
        Button botonAccion = (Button) botones.getChildren().get(0);
        Button botonFinalizarRonda = (Button) botones.getChildren().get(1);
        FinalizarRondaEventHandler finalizarRondaEventHandler = new FinalizarRondaEventHandler(campoDeJuego);
        botonFinalizarRonda.setOnMouseClicked(finalizarRondaEventHandler);

        if (ronda.obtenerDescripcion() == "Ronda de colocación") {
            System.out.println("Estamos en Ronda de Colocacion rey");
            campoDeJuego.mostrarPaisesDelJugadorActual();
            pais.modificarCantidadEjercito(1);
        } else if(ronda.obtenerDescripcion() == "Ronda de ataque") {
            System.out.println("Estamos en Ronda de ataque papu");

            if (campoDeJuego.getPaisSeleccionado() == null) {
                campoDeJuego.mostrarPaises();
                vistaPais.resaltarLimitrofes();
                vistaPais.marcarComoSeleccionada();
                campoDeJuego.setPaisSeleccionado(pais);
                campoDeJuego.setTop(crearVBoxLabel());
            } else {
                campoDeJuego.getTop().setVisible(false);

                AtaqueEventHandler ataqueEventHandler = new AtaqueEventHandler(campoDeJuego, pais, textField);
                botonAccion.setOnMouseClicked(ataqueEventHandler);

                textField.setOnKeyPressed(ataqueEventHandler);
                textField.requestFocus();
            }

        } else {
            System.out.println("No queda otra que ragrupar mi ciela");
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
