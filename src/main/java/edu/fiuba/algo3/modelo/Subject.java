package edu.fiuba.algo3.modelo;

public interface Subject {

    void addObserver(Observer obs);

    void removeObserver(Observer obs);

    void notifyObservers();

}
