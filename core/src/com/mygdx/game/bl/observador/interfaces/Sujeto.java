package com.mygdx.game.bl.observador.interfaces;

public interface Sujeto {
    void agregarObservador(Observer observer);
    void removerObservador(Observer observer);
    void notificarObservadores();
}
