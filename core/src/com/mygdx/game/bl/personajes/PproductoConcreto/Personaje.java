package com.mygdx.game.bl.personajes.PproductoConcreto;


import com.badlogic.gdx.math.Rectangle;
//componente del patron decorador
public abstract class Personaje {

    protected int vida;
    protected int ataque;
    protected int defensa;
    protected int movimiento;
    protected Rectangle rectangle;

    public Personaje() {
    }

    public Personaje(int movimiento) {
        this.movimiento = movimiento;
    }

    //--------getters and setters---------
    public abstract int getVida();

    public void setVida(int vida) {
        this.vida = vida;
    }

    public abstract int getAtaque();

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public abstract int getDefensa();


    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public abstract int getMovimiento();

    public void setMovimiento(int movimiento) {
        this.movimiento = movimiento;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }




}
