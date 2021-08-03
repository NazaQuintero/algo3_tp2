package edu.fiuba.algo3.modelo.fichas;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.roles.Rol;

public interface Fichas {

    Jugador obtenerJugador();

    int obtenerCantidad();

    void modificarCantidad(int unaCantidad);

    Dados tirarDados(Pais pais);

    void asignarRol(Rol unRol);

    void agregarPais(Pais pais);

    void setDados(Dados dados);
}
