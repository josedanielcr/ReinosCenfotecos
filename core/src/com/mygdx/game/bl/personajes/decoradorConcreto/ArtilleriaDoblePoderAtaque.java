package com.mygdx.game.bl.personajes.decoradorConcreto;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.componente.Personaje;
import com.mygdx.game.bl.personajes.decorador.ObjetoDecorado;

import java.util.ArrayList;

public class ArtilleriaDoblePoderAtaque extends ObjetoDecorado{

    @Override
    public int getVida() {
        return 0;
    }

    @Override
    public void setVida(int vida) {

    }

    @Override
    public int getAtaque() {
        return 0;
    }

    @Override
    public void setAtaque(int ataque) {

    }

    @Override
    public int getDefensa() {
        return 0;
    }

    @Override
    public void setDefensa(int defensa) {

    }

    @Override
    public int getMovimiento() {
        return 0;
    }

    @Override
    public void setMovimiento(int movimiento) {

    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }

    @Override
    public void setRectangle(Rectangle rectangle) {

    }

    @Override
    public String getTipo() {
        return null;
    }

    @Override
    public void setTipo(String tipo) {

    }

    @Override
    public String getAtaqueEspecial() {
        return null;
    }

    @Override
    public String aplicarAtaqueEspecial() {
        return null;
    }
}
