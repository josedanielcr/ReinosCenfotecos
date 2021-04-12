package com.mygdx.game.bl.personajes.decoradorConcreto;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.PproductoConcreto.Infanteria;
import com.mygdx.game.bl.personajes.componente.Personaje;
import com.mygdx.game.bl.personajes.decorador.ObjetoDecorado;

import java.util.ArrayList;

public class InfanteriaSumar3Ataque extends ObjetoDecorado{

    private ArrayList<Personaje> personajes;


    public InfanteriaSumar3Ataque(Personaje pPersonaje,ArrayList<Personaje> p) {
        this.cPersonaje = pPersonaje;
        this.personajes = p;
    }

    @Override
    public int getVida() {
        return this.cPersonaje.getVida() + 1000;
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
        return this.cPersonaje.getDefensa();
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
    public String aplicarAtaqueEspecial() {
        Infanteria i = (Infanteria) this.getcPersonaje();
        i.infanteriaSumar3Ataque(personajes.get(0));
        personajes.get(0).setTipo("skdjfhsdklfhsdjkl");
        System.out.println(personajes.get(0).getTipo());
        return "aplicado";
    }
}
