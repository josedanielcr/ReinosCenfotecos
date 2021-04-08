package com.mygdx.game.bl.jugadores.concreto;

import com.mygdx.game.bl.jugadores.interfaces.Cronometro;
import com.mygdx.game.bl.jugadores.interfaces.Jugador;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CronometroC implements Cronometro {

    private ArrayList<Jugador> observadores = new ArrayList<>();
    public static int tiempoRestante = 60;

    public CronometroC() {
        cronometro();
    }

    @Override
    public void agregarObservador(Jugador jugador) {
        observadores.add(jugador);
    }

    @Override
    public void removerObservador(Jugador jugador) {
      for(int i= 0; i<observadores.size(); i++){
          if(observadores.get(i).equals(jugador)){
              observadores.remove(jugador);
          }
      }
    }

    @Override
    public void notificarObservadores() {
        for(Jugador j: observadores){
            j.actualizar();
        }
    }

    @Override
    public int getTiempoRestante() {
        return tiempoRestante;
    }

    //--Funciones adicionales--
    private void cronometro(){
        TimerTask task = new TimerTask() {
            int seconds = 60;
            int i = 0;
            @Override
            public void run() {
                i++;
                if(i % seconds == 0) notificarObservadores();
                else tiempoRestante = (seconds - (i %seconds));
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);
    }
}
