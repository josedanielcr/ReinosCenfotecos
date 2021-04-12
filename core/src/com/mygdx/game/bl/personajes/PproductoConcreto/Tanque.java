package com.mygdx.game.bl.personajes.PproductoConcreto;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.componente.Personaje;

import java.util.ArrayList;

public class Tanque extends Personaje implements PersonajeAbstracto {


    protected int vida;
    protected int ataque;
    protected int defensa;
    protected int movimiento;
    protected Rectangle rectangle;
    protected  String tipo;
    protected String ataqueEspecial;

    public Tanque(int movimiento) {
        this.vida = 2;
        this.ataque = 10;
        this.defensa = 10;
        this.movimiento = movimiento;
        this.rectangle = null;
        this.tipo = "Tanque";
        this.ataqueEspecial = null;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(int movimiento) {
        this.movimiento = movimiento;
    }

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

    @Override
    public String aplicarAtaqueEspecial() {
        return null;
    }

    public void setAtaqueEspecial(String ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    //TODO: hacer estas funciones ahorita
    @Override
    public String obtenerInformacionPersonaje() {
        return "Este personaje es un " +this.tipo+ " ,tiene una vida de " +this.vida + " ,tiene una defensa de " +this.defensa+ " ," +
                "tiene un ataque de " + this.ataque+ ", y un movimiento de "+ this.movimiento;
    }

}
