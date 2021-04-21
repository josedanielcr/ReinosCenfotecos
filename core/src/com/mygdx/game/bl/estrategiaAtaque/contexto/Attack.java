package com.mygdx.game.bl.estrategiaAtaque.contexto;

import com.mygdx.game.bl.estrategiaAtaque.base.AbstractAttack;

public class Attack {

    private AbstractAttack strategy;
    private final int atk;
    private final int def;
    private final int life;
    private final int idCastle;
    private final int idDefending;


    public Attack(int pAtk, int pIdCastle) {
        this.atk = pAtk;
        this.idCastle = pIdCastle;
        this.def = 0;
        this.idDefending = 0;
        this.life = 0;
    }

    public Attack(int pAtk, int pDef, int pLife, int pIdDefending) {
        this.atk = pAtk;
        this.idCastle = 0;
        this.def = pDef;
        this.idDefending = pIdDefending;
        this.life = pLife;
    }

    public AbstractAttack getStrategy() {
        return strategy;
    }

    public void setStrategy(AbstractAttack strategy) {
        this.strategy = strategy;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getLife() {
        return life;
    }

    public int getIdCastle() {
        return idCastle;
    }

    public int getIdDefending() {
        return idDefending;
    }

    public boolean isUnit() {
        return idCastle == 0;
    }

    public boolean isCastle() {
        return idDefending == 0 && def == 0;
    }
}