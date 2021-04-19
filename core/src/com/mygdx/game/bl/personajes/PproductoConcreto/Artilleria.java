package com.mygdx.game.bl.personajes.PproductoConcreto;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.componente.Personaje;


public class Artilleria extends Personaje {

 final static TextureAtlas personajeAtlas = new TextureAtlas("personajes/personajes.atlas");
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
    protected String duenno;


    public Artilleria( int idPersonaje, String ataqueEspecial, String personajeActivo, Rectangle boundingBox) {
        this.idPersonaje=idPersonaje;
        this.vida = 4;
        this.ataque = 6;
        this.defensa = 10;
        this.movimiento = 4;
        this.rectangle = boundingBox;
        this.tipo = "Artilleria";
        this.ataqueEspecial = ataqueEspecial;
        this.rango=1;
        if(personajeActivo.equals("blue")){
           this.tRegion = personajeAtlas.findRegion("artilleriaBlue");
        }
        if(personajeActivo.equals("red")){
            this.tRegion = personajeAtlas.findRegion("artilleriaRed");
        }
        this.duenno = personajeActivo;
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

    public TextureRegion gettRegion() {
        return tRegion;
    }

    public void settRegion(TextureRegion tRegion) {
        this.tRegion = tRegion;
    }

    /* @Override
    public String aplicarAtaqueEspecial() {
        return null;
    }*/

    public void setAtaqueEspecial(String ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public String getDuenno() {
        return duenno;
    }

    public void setDuenno(String duenno) {
        this.duenno = duenno;
    }

    //TODO: hacer estas funciones ahorita

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
