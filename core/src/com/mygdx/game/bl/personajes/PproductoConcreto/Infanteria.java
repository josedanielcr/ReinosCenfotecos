package com.mygdx.game.bl.personajes.PproductoConcreto;


import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.componente.Personaje;

import java.util.ArrayList;

public class Infanteria extends Personaje{

    protected int idPersonaje;
    protected int vida;
    protected int ataque;
    protected int defensa;
    protected int movimiento;
    protected Rectangle rectangle;
    protected  String tipo;
    protected String ataqueEspecial;
    protected int rango;



    public Infanteria(int movimiento, int idPersonaje, String ataqueEspecial) {
        this.idPersonaje=idPersonaje;
        this.vida = 5;
        this.ataque = 3;
        this.defensa = 5;
        this.movimiento = movimiento;
        this.rectangle = null;
        this.tipo = "Infanteria";
        this.ataqueEspecial = ataqueEspecial;
        this.rango=1;
    }

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public void setAtaqueEspecial(String ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
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

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }
//TODO: hacer estas funciones ahorita

    public String obtenerInformacionPersonaje() {
        return "Este personaje es un " +this.getTipo()+ " ,tiene una vida de " +this.getVida() + " ,tiene una defensa de " +this.getDefensa()+ " ," +
                "tiene un ataque de " + this.getAtaque()+ ", y un movimiento de "+ this.getMovimiento() + " id: " + this.getIdPersonaje() +
                " ,un ataque especial " + this.getAtaqueEspecial() + ", finalmente un rango de " + this.getRango();
    }


    //metodos adcionales
   /* public void infanteriaSumar3Ataque(Personaje personaje) {
        if(personaje.getClass().getSimpleName().equals("Infanteria")){
            personaje.setAtaque(getAtaque() + 3);
        }
    }*/
}

