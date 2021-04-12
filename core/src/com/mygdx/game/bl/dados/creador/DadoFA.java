package com.mygdx.game.bl.dados.creador;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.bl.dados.producto.Dado;

public interface DadoFA {
    public Dado crearDado(int pTipo, TextureRegion dadoTextureRegion);
}
