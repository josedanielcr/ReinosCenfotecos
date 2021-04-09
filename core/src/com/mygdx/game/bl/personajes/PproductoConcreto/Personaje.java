package com.mygdx.game.bl.personajes.PproductoConcreto;


import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

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

    public String getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(String ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    //--Metodos sobre ataques especiales--

    //Infanteria
    public abstract void infanteriaHealer1(Personaje personaje);
    public abstract void infanteriaSumar3Ataque(Personaje personaje);
    public abstract void infanteriaSumar3Defensa(Personaje personaje);
    public abstract void infanteriaBajarDefensa(Personaje personaje);

    //Artilleria
    public abstract void artilleriaHealer2(ArrayList<Personaje> personajes);


    //Tanque
}
