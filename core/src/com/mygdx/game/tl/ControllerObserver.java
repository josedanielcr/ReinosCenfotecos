package com.mygdx.game.tl;

import com.mygdx.game.ui.Screens.GameScreen;
import com.mygdx.game.bl.observador.concreto.ObserverTimer;
import com.mygdx.game.bl.observador.concreto.SujetoTimer;
import com.mygdx.game.bl.observador.interfaces.Observer;

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

    public void resetTimer() {
        timer.reset();
    }

}
