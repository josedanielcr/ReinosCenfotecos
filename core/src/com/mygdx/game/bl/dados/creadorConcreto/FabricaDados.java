package com.mygdx.game.bl.dados.creadorConcreto;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.bl.dados.creador.DadoFA;
import com.mygdx.game.bl.dados.producto.Dado;
import com.mygdx.game.bl.dados.productoConcreto.DadoAccion;
import com.mygdx.game.bl.dados.productoConcreto.DadoInvocacion;
import com.mygdx.game.bl.dados.productoConcreto.DadoMovimiento;

public class FabricaDados implements DadoFA {
    //discriminacion sobre que dado generar

    @Override
    public Dado crearDado(int pTipo) {
        if(pTipo==1){
            DadoInvocacion newDado = new DadoInvocacion("Invocación");
            newDado.asignarLados();
            return newDado;
        }else if(pTipo==2){
            DadoAccion newDado = new DadoAccion("Acción");
            newDado.asignarLados();
            return newDado;
        }else{
            DadoMovimiento newDado = new DadoMovimiento("Movimiento");
            newDado.asignarLados();
            return newDado;
        }
    }

}
