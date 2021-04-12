package com.mygdx.game.tl;

import com.mygdx.game.bl.jugadores.concreto.CronometroC;
import com.mygdx.game.bl.jugadores.concreto.JugadorC;
import com.mygdx.game.bl.jugadores.interfaces.Cronometro;
import com.mygdx.game.bl.jugadores.interfaces.Jugador;

public class ControllerJugadores {

    private Cronometro cronometro;
    public static int tiempoRestante;

    public ControllerJugadores() {
        cronometro = new CronometroC();
    }

    public void inicializarJugadores(){
        agregarObservadores();
    }

    private void agregarObservadores(){
        Jugador jugador1;
        Jugador jugador2;
        cronometro.agregarObservador(jugador1 = new JugadorC(true));
        cronometro.agregarObservador(jugador2 = new JugadorC(false));
    }

    public int obtenerTiempoRestanteTurno(){
        tiempoRestante = cronometro.getTiempoRestante();
        return tiempoRestante;
    }
}
