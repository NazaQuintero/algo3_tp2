package edu.fiuba.algo3.modelo.fichas;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.roles.Rol;

public interface Fichas {

    Jugador obtenerJugador();

    int obtenerCantidad();

    void modificarCantidad(int unaCantidad);

    int pedirCantidadAlJugador();

    Dados tirarDados(Pais pais);

    void asignarRol(Rol unRol);

//    void rolAtacante();
//
//    void rolDefensor();

    void agregarPais(Pais pais);
}
