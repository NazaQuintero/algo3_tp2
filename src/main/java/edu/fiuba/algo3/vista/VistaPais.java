package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.PaisEventHandler;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.paises.Pais;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class VistaPais extends StackPane implements Observer {
    private CampoDeJuego campoDeJuego;
    private final Pais pais;
    private ArrayList<VistaPais> vistaLimitrofes = new ArrayList<>();

    public VistaPais(Pais pais, CampoDeJuego campoDeJuego) {
        this.getStylesheets().add("styles.css");
        this.pais = pais;
        this.campoDeJuego = campoDeJuego;

        this.setLayoutX(pais.getPosX());
        this.setLayoutY(pais.getPosY());

        Circle ficha = new Circle(10, pais.dominadoPor().getColor());
        Label cantEjercitos = crearLabelCantEjercitos();
        Rectangle boton = crearBotonPais();

        this.getChildren().addAll(ficha, cantEjercitos, boton);

        pais.addObserver(this);
    }

    private Label crearLabelCantEjercitos(){
        Label cantEjercitos = new Label();
        cantEjercitos.getStyleClass().add("ejercito");
        cantEjercitos.setText(Integer.toString(pais.cantidadEjercitos()));
        return cantEjercitos;
    }

    private Rectangle crearBotonPais() {
        Rectangle boton = new Rectangle(50, 50);
        boton.setFill(new Color(0f,0f,0f,0));
        boton.getStyleClass().add("pais");
        boton.setOnMouseClicked(new PaisEventHandler(this, campoDeJuego));
        return boton;
    }

    @Override
    public void update() {
        Label l = (Label) this.getChildren().get(1);
        ((Circle) this.getChildren().get(0)).setFill(pais.dominadoPor().getColor());
        l.setText(Integer.toString(pais.cantidadEjercitos()));

        System.out.println("Hay "+ this.pais.cantidadEjercitos() + " ejercito/s en " + this.pais.getNombre() +
                " que pertenece a " + pais.dominadoPor().obtenerNombre());
    }

    public ArrayList<Pais> getLimitrofes() {
        return pais.getPaisesLimitrofes();
    }

    public void setVistaLimitrofe(VistaPais vistaPais){
        vistaLimitrofes.add(vistaPais);
    }
    
    public Pais getPais(){
        return pais;
    }

    public void resaltarLimitrofesAdversarios(){
        campoDeJuego.resaltarLimitrofesAdversarios(this);
    }

    public void resaltarLimitrofesPropios() {
        campoDeJuego.resaltarLimitrofesPropios(this);
    }

    public ArrayList<VistaPais> getVistaLimitrofes() { return vistaLimitrofes; }

    public void desactivar(){
        this.setDisable(true);
        String color = pais.dominadoPor().getColor().toString();
        ((Circle) this.getChildren().get(0)).setFill(Color.web(color, 0.4));
    }

    public void activar() {
        this.setDisable(false);
        ((Circle) this.getChildren().get(0)).setFill(pais.dominadoPor().getColor());
    }

    public void marcarComoSeleccionada() {
        this.setDisable(true);
        Color color = pais.dominadoPor().getColor();
        ((Circle) this.getChildren().get(0)).setFill(color.brighter());
    }

    public Jugador getJugadorDominante() {
        return this.pais.dominadoPor();
    }

}
