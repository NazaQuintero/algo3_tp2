package edu.fiuba.algo3.modelo.fichas;

import edu.fiuba.algo3.modelo.batallas_de_dados.Dados;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.observables.Subject;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.roles.Rol;

public interface Fichas extends Subject {

    Jugador obtenerJugador();

    int obtenerCantidad();

    void modificarCantidad(int unaCantidad);

    Dados tirarDados(Pais pais);

    void asignarRol(Rol unRol);

    void agregarPais(Pais pais);

    void setDados(Dados dados);

    Dados getDados();

}
