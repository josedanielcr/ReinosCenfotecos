package com.mygdx.game.bl.personajes.PproductoConcreto;


import com.badlogic.gdx.math.Rectangle;

public abstract class Personaje {

    protected int vida;
    protected int ataque;
    protected int defensa;
    protected int movimiento;
    protected Rectangle rectangle;
    protected  String tipo;
    protected String ataqueEspecial;

    public Personaje() {
    }

    public Personaje(int movimiento) {
        this.movimiento = movimiento;
    }

    //--------getters and setters---------
    public abstract int getVida();

    public abstract void setVida(int vida);

    public abstract int getAtaque();

    public abstract void setAtaque(int ataque);

    public abstract int getDefensa();

    public abstract void setDefensa(int defensa);

    public abstract int getMovimiento();

    public abstract void setMovimiento(int movimiento);



    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public abstract String getAtaqueEspecial();

    public abstract void setAtaqueEspecial(String ataqueEspecial);


    //prueba
    @Override
    public String toString() {
        return "Personaje{" +
                "vida=" + vida +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", movimiento=" + movimiento +
                ", rectangle=" + rectangle +
                ", tipo='" + tipo + '\'' +
                ", ataqueEspecial='" + ataqueEspecial + '\'' +
                '}';
    }
}
