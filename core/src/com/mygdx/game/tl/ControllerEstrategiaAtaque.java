package com.mygdx.game.tl;

import com.mygdx.game.bl.estrategiaAtaque.base.AbstractAttack;
import com.mygdx.game.bl.estrategiaAtaque.concreta.AttackCastle;
import com.mygdx.game.bl.estrategiaAtaque.contexto.Attack;

public class ControllerEstrategiaAtaque {
    private final ControllerCelda gCell;
    private final ControllerPersonaje gPer;

    public ControllerEstrategiaAtaque(ControllerCelda gCell, ControllerPersonaje gPer) {
        this.gCell = gCell;
        this.gPer = gPer;
    }

    public String executeAttack(int pIdJugador, int pIdCelda, int pIdPersonaje, int pWinCondition1, int pWinCondition2) {
        String report="";

        Attack atk = new Attack(4, 100);
        atk.setStrategy(new AttackCastle(25, 100));
        report = atk.getStrategy().executeAttack();

        return report;
    }
}
