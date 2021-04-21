package com.mygdx.game.bl.estrategiaAtaque.concreta;

import com.mygdx.game.bl.estrategiaAtaque.base.AbstractAttack;

public class AttackCastle extends AbstractAttack {
    private int atk;
    private int idCastle;

    public AttackCastle(int pAtk, int pIdCastle) {
        super("castle");
        this.atk=pAtk;
        this.idCastle=pIdCastle;
    }

    @Override
    public String executeAttack() {
        //Método de ataque de milton
        return null;
    }
}
