package edu.fiuba.algo3.modelo;

import java.util.HashMap;

public class Jugador {

    private final int id;
    private String color = "";
    private Objetivo secreto;
    private Objetivo general = new General();
    private Rol rol = new RolIndefinido();
    private Turno turno = new SinTurno();
    private HashMap<String, Pais> paisesDominados = new HashMap<>();
    private Usuario usuario;

    public Jugador(int id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public Jugador() { // despues lo volamo
        this.id = 0;
    }

    public String mostrarColor() {
        return color;
    }

    public int cantidadPaisesDominados() {
        return paisesDominados.size();
    }

    public int obtenerId() {
        return this.id;
    }

    public void colocarEjercitos(Pais pais) throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        if (paisesDominados.containsKey(pais.obtenerNombre())) { //qsy
            try {
                this.turno.colocarEjercitos(pais);
            } catch (ElJugadorNoTieneTurnoException e) {
                throw new ElJugadorNoTieneTurnoException();
            }
        } else if (pais.estaLibre()) pais.colocarEjercito(new Ejercito(this));
    }

    public void asignarColor(String color) {
        this.color = color;
    }

    public Objetivo obtenerObjetivoSecreto() {
        return this.secreto;
    }

    public Objetivo obtenerObjetivoGeneral() {
        return this.general;
    }

    public Dados tirarDados(Pais pais) {
        return this.rol.tirarDados(pais);
    }

    public int pedirCantidad() {
        return usuario.pedirCantidad(); // por ahora, despues se la pedimos al usuario
    }

    public void rolAtacante() {
        rol = new Atacante();
    }

    public void rolDefensor() {
        rol = new Defensor();
    }

    public void setTurno(Turno unTurno) {
        this.turno = unTurno;
    }

    public void finalizarRonda() {
        this.turno.finalizarRonda();
    }

    public void atacarA(Pais paisAtacante, Pais paisDefensor) throws ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException {
        try {
            turno.atacarA(paisAtacante, paisDefensor);
        } catch (ElJugadorNoTieneTurnoException e) {
            throw new ElJugadorNoTieneTurnoException();
        }
    }

    public void reagrupar(Pais origen, Pais destino) throws NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException, ElPaisNoEsLimitrofeException {
        try {
            this.turno.reagrupar(origen, destino);
        } catch (ElJugadorNoTieneTurnoException e) {
            throw new ElJugadorNoTieneTurnoException();
        }
    }

    public void agregarPais(Pais pais) {
        this.paisesDominados.putIfAbsent(pais.obtenerNombre(), pais);
    }

    public void quitarPais(Pais defensor) {
        this.paisesDominados.remove(defensor.obtenerNombre());
    }

    /*public void finalizarReagrupe() {
        this.turno.finalizarReagrupe();
//        this.turno.cambiarRonda();
    }

    public void finalizarColocacion() {
        this.turno.finalizarColocacion();
    }*/
}
