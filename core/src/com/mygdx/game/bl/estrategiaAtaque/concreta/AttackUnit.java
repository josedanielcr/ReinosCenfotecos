package com.mygdx.game.bl.estrategiaAtaque.concreta;

import com.mygdx.game.bl.estrategiaAtaque.base.AbstractAttack;

public class AttackUnit extends AbstractAttack {
    private int atk;
    private int def;
    private int life;
    private int idDefending;

    public AttackUnit(int pAtk, int pDef, int pLife, int pIdDefending) {
        super("unit");
        this.atk = pAtk;
        this.def = pDef;
        this.life = pLife;
        this.idDefending = pIdDefending;
    }



    @Override
    public String executeAttack() {
        //Método de ataque de Melissa y JD
        return null;
    }
}
