package com.mygdx.game.bl.jugadores.concreto;

import com.mygdx.game.bl.jugadores.interfaces.Observer;
import com.mygdx.game.bl.jugadores.interfaces.Sujeto;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SujetoTimer implements Sujeto {

    private ArrayList<Observer> observers = new ArrayList<>();
    public static int tiempoRestante = 60;

    public SujetoTimer() {
        cronometro();
    }

    private void cronometro() {
        TimerTask task =new TimerTask() {
            int seconds = 60;
            int i = 0;
            @Override
            public void run() {
                i++;
                if (i % seconds == 0) {
                    tiempoRestante = 0;
                }
                else {
                    tiempoRestante = (seconds - i);
                }
                notificarObservadores();
                System.out.println(tiempoRestante);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);
    }

    public void resetTimer(){
        tiempoRestante=60;
    }

    @Override
    public void agregarObservador(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removerObservador(Observer observer) {
        for(int i= 0; i<observers.size(); i++){
            if(observers.get(i).equals(observer)){
                observers.remove(observer);
            }
        }
    }

    @Override
    public void notificarObservadores() {
        for(Observer o:observers){
            o.actualizar(tiempoRestante);
        }
    }
}
