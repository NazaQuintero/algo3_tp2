package edu.fiuba.algo3.modelo.batallas_de_dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Dados implements Iterable<Dado> {
    private final ArrayList<Dado> dados;

    public Dados(int cantidad) {
        dados = new ArrayList<>();
        for (int i = 0; i < cantidad ; i++) this.dados.add(new DadoRandom());
        this.ordenarDescendente();
    }

    public Dados() {
        dados  = new ArrayList<>();
    }

    public void lanzar() { for (Dado dado: dados) dado.lanzar(); }

    public int obtenerCantidad() {
        return this.dados.size();
    }

    public void removerUnDado() {
        this.dados.remove(dados.size() - 1);
    }

    public void agregarDado(Dado unDado) {
        this.dados.add(unDado);
    }

    public Dado obtenerDado(int index) {
        return this.dados.get(index);
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
