package com.mygdx.game.bl.jugadores.interfaces;

public interface Sujeto {
    void agregarObservador(Observer observer);
    void removerObservador(Observer observer);
    void notificarObservadores();
}
