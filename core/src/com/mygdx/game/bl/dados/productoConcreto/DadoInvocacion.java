package com.mygdx.game.bl.dados.productoConcreto;

import com.mygdx.game.bl.dados.producto.Dado;

public class DadoInvocacion extends Dado {
    public DadoInvocacion(int lado1, int lado2, int lado3, int lado4, int lado5, int lado6, String tipo) {
        super(lado1, lado2, lado3, lado4, lado5, lado6, tipo);
    }

    @Override
    public String rollDice() {
        return null;
    }
}
