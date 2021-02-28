package com.mygdx.game.bl.dados.productoConcreto;

import com.mygdx.game.bl.dados.producto.Dado;

public class DadoInvocacion extends Dado {

    public DadoInvocacion(String tipo) {
        super(tipo);
    }

    public DadoInvocacion(String lado1, String lado2, String lado3, String lado4, String lado5, String lado6, String tipo) {
        super(lado1, lado2, lado3, lado4, lado5, lado6, tipo);
    }

    @Override
    public String rollDice() {
        int die = ((int) (Math.random()*6)+1);
        switch (die){
            case 1:
                return getLado1();
            case 2:
                return getLado2();
            case 3:
                return getLado3();
            case 4:
                return getLado4();
            case 5:
                return getLado5();
            case 6:
                return getLado6();
            default:
                return null;
        }
    }

    @Override
    public void asignarLados() {
        setLado1("Infantería");
        setLado2("Infantería");
        setLado3("Infantería");
        setLado4("Artillería");
        setLado5("Artillería");
        setLado6("Tanque");
    }
}
