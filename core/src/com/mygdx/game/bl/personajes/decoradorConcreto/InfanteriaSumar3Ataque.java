package com.mygdx.game.bl.personajes.decoradorConcreto;

import com.mygdx.game.bl.personajes.PproductoConcreto.Infanteria;
import com.mygdx.game.bl.personajes.PproductoConcreto.Personaje;
import com.mygdx.game.bl.personajes.decorador.ObjetoDecorado;

public class InfanteriaSumar3Ataque extends ObjetoDecorado{

    public InfanteriaSumar3Ataque(Personaje pPersonaje) {
        this.cPersonaje= pPersonaje;
    }


    @Override
    public int getAtaque() {
        return this.cPersonaje.getAtaque() +3;
    }

    @Override
    public int getVida() {
        System.out.println("hjkrgkhvkhk");
        return this.cPersonaje.getVida();
    }

    @Override
    public void setVida(int vida) {
        this.cPersonaje.setVida(vida);
    }

    @Override
    public void setAtaque(int ataque) {
        this.cPersonaje.setAtaque(ataque);
    }

    @Override
    public int getDefensa() {
        System.out.println("popo");
        return this.cPersonaje.getDefensa();
    }

    @Override
    public void setDefensa(int defensa) {
        this.cPersonaje.setDefensa(defensa);
    }

    @Override
    public int getMovimiento() {
        return this.cPersonaje.getMovimiento();
    }

    @Override
    public void setMovimiento(int movimiento) {
        this.cPersonaje.setMovimiento(movimiento);
    }

    @Override
    public String getAtaqueEspecial() {
        return this.cPersonaje.getAtaqueEspecial();
    }

    @Override
    public void setAtaqueEspecial(String ataqueEspecial) {
//        Infanteria i = (Infanteria) this.getcPersonaje();
//        i.infanteriaSumar3Ataque(i);
//        i.setAtaqueEspecial("summar 3 de ataque");
//        i.setAtaqueEspecial("holaaaaa");
    }


}
