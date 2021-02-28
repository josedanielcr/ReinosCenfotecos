package com.mygdx.game.bl.dados.creadorConcreto;

import com.mygdx.game.bl.dados.creador.DadoFabricaAbstracta;
import com.mygdx.game.bl.dados.producto.Dado;

public class FabricaDados implements DadoFabricaAbstracta {
    //discriminacion sobre que dado generar

    @Override
    public Dado crearDado() {
        return null;
    }
}
