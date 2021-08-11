package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.batallas_de_dados.Dados;
import edu.fiuba.algo3.modelo.batallas_de_dados.ProcesadorResultado;
import edu.fiuba.algo3.modelo.batallas_de_dados.Resultado;
import edu.fiuba.algo3.modelo.excepciones.CantidadDeEjercitosInValidaException;
import edu.fiuba.algo3.modelo.excepciones.ElPaisNoEsLimitrofeException;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.vista.CampoDeJuego;
import edu.fiuba.algo3.vista.MenuLateralDerecho;
import edu.fiuba.algo3.vista.VistaDados;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class AtaqueEventHandler implements EventHandler<Event>  {

    private final CampoDeJuego campoDeJuego;
    private final MenuLateralDerecho menuLateralDerecho;
    private final Pais atacante;
    private final Pais defensor;
    private final TextField inputText;

    public AtaqueEventHandler(CampoDeJuego campoDeJuego, Pais defensor, TextField inputText) {
        this.campoDeJuego = campoDeJuego;
        this.menuLateralDerecho = (MenuLateralDerecho) campoDeJuego.getRight();
        this.atacante = campoDeJuego.getPaisSeleccionado();
        this.defensor = defensor;
        this.inputText = inputText;
    }

    @Override
    public void handle(Event event) {

        if (event.getEventType() == MouseEvent.MOUSE_CLICKED || ((KeyEvent) event).getCode() == KeyCode.ENTER) {
            try {
                menuLateralDerecho.limpiarResultadoDeBatalla();
                int cantidadDeEjercitos = Integer.parseInt(inputText.getText());
                Resultado resultado = atacante.atacarA(defensor, cantidadDeEjercitos);
                menuLateralDerecho.update();
                mostrarAtaque();
                ProcesadorResultado.obtenerInstancia().procesar(resultado);
                campoDeJuego.setPaisSeleccionado(null);
                campoDeJuego.mostrarPaisesDelJugadorActual();
            } catch (ElPaisNoEsLimitrofeException ignored) {}

            catch (NumberFormatException | CantidadDeEjercitosInValidaException e) {
                inputText.getStyleClass().add("invalid");
                inputText.clear();
                inputText.requestFocus();

                menuLateralDerecho.mostrarErrorAtaqueYReagrupe(atacante.cantidadEjercitos()-1);
            }
        }
    }

    private void mostrarAtaque() {
        Dados dadosAtacante = atacante.getEjercito().getDados();
        Dados dadosDefensor = defensor.getEjercito().getDados();

        VistaDados vistaDadosAtacante = new VistaDados(dadosAtacante);
        VistaDados vistaDadosDefensor = new VistaDados(dadosDefensor);

        HBox hBox1 = new HBox(crearLabel(atacante), crearLabel(defensor));
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox(vistaDadosAtacante, vistaDadosDefensor);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(20);
        VBox vBox = new VBox(hBox1, hBox2);
        vBox.setSpacing(20);

        menuLateralDerecho.setResultadoDeAtaque(vBox);

        atacante.getEjercito().setDados(null);
        defensor.getEjercito().setDados(null);
    }

    private Label crearLabel(Pais pais) {
        Label label = new Label(pais.getNombre());
        label.getStyleClass().add("labelText");
        return label;
    }

}
