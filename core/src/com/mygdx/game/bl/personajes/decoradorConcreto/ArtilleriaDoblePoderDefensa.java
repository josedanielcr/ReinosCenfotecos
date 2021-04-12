package com.mygdx.game.bl.personajes.decoradorConcreto;

import com.mygdx.game.bl.personajes.PproductoConcreto.Personaje;
import com.mygdx.game.bl.personajes.decorador.ObjetoDecorado;

import java.util.ArrayList;

public class ArtilleriaDoblePoderDefensa extends ObjetoDecorado {

    @Override
    public int getVida() {
        return this.vida;
    }

    @Override
    public void setVida(int vida) {}

    @Override
    public int getAtaque() {
        return this.ataque;
    }

    @Override
    public void setAtaque(int ataque) {}

    @Override
    public int getDefensa() {
        return this.defensa * 2;
    }

    @Override
    public void setDefensa(int defensa) {}

    @Override
    public int getMovimiento() {
        return this.movimiento;
    }

    @Override
    public void setMovimiento(int movimiento) {}

    @Override
    public String getAtaqueEspecial() {
        return this.ataqueEspecial;
    }

    @Override
    public void setAtaqueEspecial(String ataqueEspecial) {
    }

}
