package com.mygdx.game.bl.personajes.decoradorConcreto;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.componente.Personaje;
import com.mygdx.game.bl.personajes.decorador.ObjetoDecorado;

import java.util.ArrayList;

public class InfanteriaBajarDefensa extends ObjetoDecorado{//le baja en 1 la defensa al bicho que este a 5 casillas


    public InfanteriaBajarDefensa(Personaje pPersonaje) {//aqui recibe el personaje que quiere decorar, sin son varios lo que hace es que le aplica el decorador en un aiteracion en el controller
        this.cPersonaje = pPersonaje;

    }
    @Override
    public String obtenerInformacionPersonaje() {
       return "Este personaje es un " +this.getTipo()+ " ,tiene una vida de " +this.getVida() + " ,tiene una defensa de " +this.getDefensa()+ " ," +
                "tiene un ataque de " + this.getAtaque()+ ", y un movimiento de "+ this.getMovimiento() + " id: " + this.getIdPersonaje();
    }

    @Override
    public int getIdPersonaje() {
        return this.cPersonaje.getIdPersonaje();
    }

    @Override
    public int getVida() {
        return this.cPersonaje.getVida() ;
    }

    @Override
    public void setVida(int vida) {
        this.cPersonaje.setVida(vida);
    }

    @Override
    public int getAtaque() {
        return this.cPersonaje.getAtaque();
    }

    @Override
    public void setAtaque(int ataque) {
        this.cPersonaje.setAtaque(ataque);
    }

    @Override
    public int getDefensa() {
        return this.cPersonaje.getDefensa()-1;
    }

    @Override
    public void setDefensa(int defensa) {
        this.cPersonaje.setDefensa(defensa);
    }

    @Override
    public int getMovimiento() {
        return this.cPersonaje.getMovimiento();
    }

    @Override
    public void setMovimiento(int movimiento) {
        this.cPersonaje.setMovimiento(movimiento);
    }

    @Override
    public Rectangle getRectangle() {
        return this.cPersonaje.getRectangle();
    }

    @Override
    public void setRectangle(Rectangle rectangle) {
        this.cPersonaje.setRectangle(rectangle);
    }

    @Override
    public String getTipo() {
        return this.cPersonaje.getTipo();
    }

    @Override
    public void setTipo(String tipo) {
        this.cPersonaje.setTipo(tipo);
    }

    @Override
    public String getAtaqueEspecial() {
        return this.cPersonaje.getAtaqueEspecial();
    }

    @Override
    public int getRango() {
        return this.cPersonaje.getRango();
    }

    @Override
    public void setRango(int pRango) { this.cPersonaje.setRango(pRango);
    }

}
