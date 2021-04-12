package com.mygdx.game.bl.jugadores.interfaces;

public interface Cronometro {
    void agregarObservador(Jugador jugador);
    void removerObservador(Jugador jugador);
    void notificarObservadores();
    int getTiempoRestante();
}
