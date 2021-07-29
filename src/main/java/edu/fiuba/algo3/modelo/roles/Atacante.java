package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.Pais;

public class Atacante implements Rol {
    int cantidadEjercitos;

    public Atacante(int cantidadEjercitos){
        this.cantidadEjercitos = Math.min(cantidadEjercitos, 3);
    }

    public Dados tirarDados(Pais pais) {
        return new Dados(this.cantidadEjercitos);
    }


}
