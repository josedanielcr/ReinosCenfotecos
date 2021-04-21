package com.mygdx.game.bl.estrategiaAtaque.concreta;

import com.mygdx.game.bl.estrategiaAtaque.base.AbstractAttack;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerPersonaje;

public class AttackCastle extends AbstractAttack {
    private int atk;
    private int idCastle;
    private ControllerCelda gCell;
    private ControllerPersonaje gPer;

    public AttackCastle(int pAtk, int pIdCastle, ControllerCelda pGCell, ControllerPersonaje pGPer) {
        super("castle");
        this.atk=pAtk;
        this.idCastle=pIdCastle;
        this.gCell=pGCell;
        this.gPer=pGPer;
    }

    @Override
    public String executeAttack() {
        String report;
        boolean isEndGame = gCell.updateCastle(atk, idCastle);
        if (isEndGame) {
            report = "End of Game.";
        }
        else {
            report = "Castle was attacked successfully.";
        }
        return report;
    }
}
