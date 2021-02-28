package com.mygdx.game.bl.dados.productoConcreto;

import com.mygdx.game.bl.dados.producto.Dado;

public class DadoMovimiento extends Dado {

    public DadoMovimiento(String tipo) {
        super(tipo);
    }

    public DadoMovimiento(String lado1, String lado2, String lado3, String lado4, String lado5, String lado6, String tipo) {
        super(lado1, lado2, lado3, lado4, lado5, lado6, tipo);
    }

    @Override
    public String rollDice() { //TODO no es la solucion mas elegante pero se puede castear el string a Int cuando se recibe
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
        setLado1("1");
        setLado2("2");
        setLado3("3");
        setLado4("4");
        setLado5("5");
        setLado6("6");
    }
}
