package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.batallas_de_dados.Dados;
import edu.fiuba.algo3.modelo.paises.Pais;

public class Atacante implements Rol {
    int cantidadEjercitos;

    public Atacante(int cantidadEjercitos){
        this.cantidadEjercitos = Math.min(cantidadEjercitos, 3);
    }

    public Dados tirarDados() {
        return new Dados(this.cantidadEjercitos);
    }

}
