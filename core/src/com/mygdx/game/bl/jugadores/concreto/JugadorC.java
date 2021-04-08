package com.mygdx.game.bl.jugadores.concreto;

import com.mygdx.game.bl.jugadores.interfaces.Jugador;

public class JugadorC implements Jugador {

    //TODO: quedar por ver si se le tienen que agregar los otros atributos
    private boolean turnoActivo;

    public JugadorC(boolean turnoActivo) {
        this.turnoActivo = turnoActivo;
    }

    @Override
    public void actualizar() {
        this.turnoActivo = !turnoActivo;
        System.out.println("he cambiado a " + this.turnoActivo);
    }
}
