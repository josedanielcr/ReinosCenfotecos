package com.mygdx.game.bl.jugadores.concreto;

import com.mygdx.game.ui.Screens.GameScreen;
import com.mygdx.game.bl.jugadores.interfaces.Observer;

public class ObserverTimer implements Observer {
    private String nombre;
    GameScreen gs;

    public ObserverTimer(String nombre, GameScreen gs) {
        setNombre(nombre);
        setGs(gs);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GameScreen getGs() {
        return gs;
    }

    public void setGs(GameScreen gs) {
        this.gs = gs;
    }

    private void updateTurn(int value){
        if(value==0){
            gs.changeTurn();
        }else{
            gs.updateClock(value); //dibuja
        }
    }

    @Override
    public void actualizar(int value) {
        updateTurn(value);
    }
}
