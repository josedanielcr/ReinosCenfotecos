package com.mygdx.game.bl.personajes.PproductoConcreto;

import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;

public class Tanque extends Personaje implements PersonajeAbstracto {

    private static final String TIPO = "Tanque";

    public Tanque(int movimiento) {
        super(movimiento);
        this.vida = 2;
        this.ataque = 10;
        this.defensa = 10;
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
