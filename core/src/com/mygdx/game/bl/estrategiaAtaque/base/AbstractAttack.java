package com.mygdx.game.bl.estrategiaAtaque.base;

public abstract class AbstractAttack implements IAttack{

    private String attackType;

    public AbstractAttack(String pAttackType) {
        this.attackType = pAttackType;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public abstract String executeAttack();
}
