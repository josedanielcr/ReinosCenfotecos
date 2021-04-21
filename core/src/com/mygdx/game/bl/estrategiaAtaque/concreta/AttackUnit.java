package com.mygdx.game.bl.estrategiaAtaque.concreta;

import com.mygdx.game.bl.estrategiaAtaque.base.AbstractAttack;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerPersonaje;

public class AttackUnit extends AbstractAttack {
    private int atk;
    private int def;
    private int life;
    private int idDefending;
    private ControllerCelda gCell;
    private ControllerPersonaje gPer;

    public AttackUnit(int pAtk, int pDef, int pLife, int pIdDefending, ControllerCelda pGCell, ControllerPersonaje pGPer) {
        super("unit");
        this.atk = pAtk;
        this.def = pDef;
        this.life = pLife;
        this.idDefending = pIdDefending;
        this.gCell=pGCell;
        this.gPer=pGPer;
    }

    @Override
    public String executeAttack() {
        String msj="";
        if(atk>def){
            int nuevaVida = life + (def-atk);
            if(nuevaVida>0){
                msj = gPer.resolverAtaqueVida(idDefending, nuevaVida);
            }else {
                msj = gPer.matarPersonaje(idDefending);
                gCell.removeUnitReference(idDefending);
            }
        }
        if(atk<=def){
            int nuevaDefensa = def-atk;
            msj = gPer.resolverAtaqueDefensa(idDefending, nuevaDefensa);

        }
        return msj;
    }
}
