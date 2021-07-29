package edu.fiuba.algo3.modelo.observables;

public interface Subject {

    void addObserver(Observer obs);

    void removeObserver(Observer obs);

    void notifyObservers();

}
