package com.mygdx.game.bl.personajes.PproductoConcreto;


import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
//componente concreto
public class Infanteria extends Personaje implements PersonajeAbstracto {

    private static final String TIPO = "Infanteria";

    public Infanteria(int movimiento) {
        super(movimiento);
        this.vida = 5;
        this.ataque = 3;
        this.defensa = 5;
    }

    @Override
    public int getVida() {
        return 5;
    }

    @Override
    public int getAtaque() {
        return 3;
    }

    @Override
    public int getDefensa() {
        return 5;
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

