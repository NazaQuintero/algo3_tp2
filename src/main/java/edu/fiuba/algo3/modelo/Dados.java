package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Dados implements Iterable<Dado> {
    private final ArrayList<Dado> dados = new ArrayList<>();
    private final int CANTIDAD_DEFECTO_DE_DADOS = 3;

    public Dados() {
        for (int i = 0; i <= CANTIDAD_DEFECTO_DE_DADOS ; i++) this.dados.add(new Dado());
    }

    public void lanzar() { for (Dado dado: dados) dado.lanzar(); }

    public int obtenerCantidad() {
        return this.dados.size() - 1;
    }

    public void removerUnDado() {
        this.dados.remove(dados.size() - 1);
    }

    public void agregarDado(Dado unDado) {
        this.dados.add(unDado);
    }

    public void ordenarDescendente() {
        dados.sort(Collections.reverseOrder());
    }

    public boolean estaOrdenadaDescendente() {
        return dados.stream().sorted(Collections.reverseOrder(Dado::compareTo)).collect(Collectors.toList()).equals(dados);
    }

    @Override
    public Iterator<Dado> iterator() {
        return this.dados.iterator();
    }
}
