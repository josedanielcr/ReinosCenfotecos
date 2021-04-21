package com.mygdx.game.bl.estrategiaAtaque.concreta;

import com.mygdx.game.bl.estrategiaAtaque.base.AbstractAttack;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;

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
        String msj="";
        if(atk>def){
            int nuevaVida = atk - def;
            if(nuevaVida==0){
                //TODO: llamar en el gestor a resolverAtaqueDefensa(int idPersonajeAtacado, int nuevaDefensa)
            }else {
                //TODO: llamar en el gestor a matarPersonaje(int idPersonajeAtacado)
            }
        }
        if(atk<=def){
            int nuevaDefensa =def-atk;
            //TODO: llamar en el gestor a resolverAtaqueVida(int idPersonajeAtacado, int nuevaVida)

        }
        return msj;
    }
}
