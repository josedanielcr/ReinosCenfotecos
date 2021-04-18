package com.mygdx.game.bl.personajes.PproductoConcreto;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.componente.Personaje;

import java.util.ArrayList;

public class Tanque  extends Personaje {

    //final static TextureAtlas personajeAtlas = new TextureAtlas("");
    protected int idPersonaje;
    protected int vida;
    protected int ataque;
    protected int defensa;
    protected int movimiento;
    protected Rectangle rectangle;
    protected TextureRegion tRegion;
    protected  String tipo;
    protected String ataqueEspecial;
    protected int rango;

    public Tanque( int idPersonaje, String ataqueEspecial, String personajeActivo, Rectangle boundingBox) {
        this.idPersonaje= idPersonaje;
        this.vida = 2;
        this.ataque = 10;
        this.defensa = 10;
        this.movimiento = 2;
        this.rectangle = boundingBox;
        this.tipo = "Tanque";
        this.ataqueEspecial = ataqueEspecial;
        this.rango=1;
        if(personajeActivo.equals("blue")){
            //this.tRegion = personajeAtlas.findRegion("");
        }
        if(personajeActivo.equals("red")){
            //this.tRegion = personajeAtlas.findRegion("");
        }
        //mientras milton pone el atlas :)
        this.tRegion = null;
    }


    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
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

    public void setAtaqueEspecial(String ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public TextureRegion gettRegion() {
        return tRegion;
    }


    public void settRegion(TextureRegion tRegion) {
        this.tRegion = tRegion;
    }


    public String obtenerInformacionPersonaje() {
        return "Este personaje es un " +this.getTipo()+ " ,tiene una vida de " +this.getVida() + " ,tiene una defensa de " +this.getDefensa()+ " ," +
                "tiene un ataque de " + this.getAtaque()+ ", y un movimiento de "+ this.getMovimiento() + " id: " + this.getIdPersonaje() +
                " ,un ataque especial " + this.getAtaqueEspecial() + ", un rango de " + this.getRango() + " ,Y un textureRegion de " + this.gettRegion();
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(tRegion, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

}
