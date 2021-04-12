package com.mygdx.game.bl.personajes.PproductoConcreto;


import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;

import java.util.ArrayList;

public class Artilleria extends Personaje implements PersonajeAbstracto {

    public Artilleria(int movimiento) {
        super(movimiento);
        this.vida = 4;
        this.ataque = 6;
        this.defensa = 10;
        this.tipo = "Artilleria";
    }

    @Override
    public int getVida() {
        return this.vida;
    }

    @Override
    public void setVida(int vida) {
        this.vida = vida;
    }

    @Override
    public int getAtaque() {
        return this.ataque;
    }

    @Override
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    @Override
    public int getDefensa() {
        return this.defensa;
    }

    @Override
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    @Override
    public int getMovimiento() {
        return this.movimiento;
    }

    @Override
    public void setMovimiento(int movimiento) {
        this.movimiento =  movimiento;
    }

    @Override
    public String getAtaqueEspecial() {
        return this.ataqueEspecial;
    }

    @Override
    public void setAtaqueEspecial(String ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }


    public void artilleriaHealer2(ArrayList<Personaje> personajes) {
        for(Personaje p: personajes){
            p.setVida(getVida() + 1);
        }
    }

    //TODO: hacer estas funciones ahorita
    @Override
    public String obtenerInformacionPersonaje() {
        return "Este personaje es un " +this.tipo+ " ,tiene una vida de " +this.vida + " ,tiene una defensa de " +this.defensa+ " ," +
                "tiene un ataque de " + this.ataque+ ", y un movimiento de "+ this.movimiento;
    }

    @Override
    public String obtenerTipoPersonaje() {
        return null;
    }

}
