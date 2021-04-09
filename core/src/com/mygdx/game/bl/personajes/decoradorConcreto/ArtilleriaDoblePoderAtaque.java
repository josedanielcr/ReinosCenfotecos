package com.mygdx.game.bl.personajes.decoradorConcreto;

import com.mygdx.game.bl.personajes.PproductoConcreto.Personaje;
import com.mygdx.game.bl.personajes.decorador.ObjetoDecorado;

import java.util.ArrayList;

public class ArtilleriaDoblePoderAtaque extends ObjetoDecorado {

    @Override
    public int getVida() {
        return this.vida;
    }

    @Override
    public void setVida(int vida) {}

    @Override
    public int getAtaque() {
        return this.ataque * 2;
    }

    @Override
    public void setAtaque(int ataque) {}

    @Override
    public int getDefensa() {
        return this.defensa;
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
    public void infanteriaHealer1(Personaje personaje) {
    }

    @Override
    public void infanteriaSumar3Ataque(Personaje personaje) {

    }

    @Override
    public void artilleriaHealer2(ArrayList<Personaje> personajes) {
    }

    @Override
    public void infanteriaSumar3Defensa(Personaje personaje) {

    }

    @Override
    public void infanteriaBajarDefensa(Personaje personaje) {

    }


}
