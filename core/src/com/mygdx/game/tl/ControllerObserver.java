package com.mygdx.game.tl;

import com.mygdx.game.bl.Screens.GameScreen;
import com.mygdx.game.bl.jugadores.concreto.ObserverTimer;
import com.mygdx.game.bl.jugadores.concreto.SujetoTimer;
import com.mygdx.game.bl.jugadores.interfaces.Observer;

public class ControllerObserver {
    private final SujetoTimer timer;
    private final ObserverTimer obsTimer;

    public ControllerObserver(GameScreen gs){
        timer = new SujetoTimer();
        obsTimer = new ObserverTimer("timer",gs);
        newTimerObs(obsTimer);
    }

    public void newTimerObs(Observer o) {
        timer.agregarObservador(o);
    }

    public void removeTimerObs() {
        timer.removerObservador(obsTimer);
    }

    public void resetTimer(){
        this.timer.resetTimer();
    }

}
