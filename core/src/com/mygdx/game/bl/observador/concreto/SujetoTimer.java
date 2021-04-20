package com.mygdx.game.bl.observador.concreto;

import com.mygdx.game.bl.observador.interfaces.Observer;
import com.mygdx.game.bl.observador.interfaces.Sujeto;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SujetoTimer implements Sujeto {

    private ArrayList<Observer> observers = new ArrayList<>();
    public int tiempoRestante = 60;
    boolean newTurn=false;
    Timer timer;
    int seconds = 60;
    int i = 0;

    public SujetoTimer() {
        cronometro();
    }

    private void cronometro() {
        TimerTask task =new TimerTask() {
            @Override
            public void run() {
                timerRun();
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 1000);
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

    public void reset() {
        tiempoRestante=60;
        i=0;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timerRun();
            }
        };
        timer.cancel();
        timer = new Timer();
        timer.schedule(task,0,1000);
    }

    public void timerRun() {
        i++;
        if (i % seconds == 0) {
            tiempoRestante = 0;
            newTurn=true;
        }
        else {
            tiempoRestante = (seconds - i);
        }
        notificarObservadores();
        if(newTurn){
            newTurn=false;
            tiempoRestante=10;
            i=0;
            reset();
        }
    }
}
