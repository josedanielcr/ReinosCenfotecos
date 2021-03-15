package com.mygdx.game.bl.personajes.PproductoConcreto;

import com.badlogic.gdx.math.Rectangle;

import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
//componente concreto
public class Artilleria extends Personaje implements PersonajeAbstracto {

    private static final String TIPO = "Artilleria";


    public Artilleria(int movimiento) {
        super(movimiento);
        this.vida = 4;
        this.ataque = 6;
        this.defensa = 10;
    }

    @Override
    public int getVida() {
        return 4;
    }

    @Override
    public int getAtaque() {
        return 6;
    }

    @Override
    public int getDefensa() {
        return 10;
    }

    @Override
    public int getMovimiento() {
        return super.movimiento;
    }

    public static String getTIPO() {
        return TIPO;
    }

    //TODO: hacer estas funciones ahorita
    @Override
    public String obtenerInformacionPersonaje() {
        return "Este personaje es un " +getTIPO()+ " ,tiene una vida de " +this.vida + " ,tiene una defensa de " +this.defensa+ " ," +
                "tiene un ataque de " + this.ataque+ ", y un movimiento de "+ this.movimiento;
    }

    @Override
    public String obtenerTipoPersonaje() {
        return null;
    }

}
