package com.mygdx.game.bl.personajes.componente;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;

import java.util.ArrayList;

public abstract class Personaje implements PersonajeAbstracto {
    //--------getters and setters---------
    public abstract   String obtenerInformacionPersonaje();

    public abstract   int getIdPersonaje();
    public abstract int getVida();
    public abstract void setVida(int vida);
    public abstract int getAtaque();
    public abstract void setAtaque(int ataque);
    public abstract int getDefensa();
    public abstract void setDefensa(int defensa);
    public abstract int getMovimiento();
    public abstract void setMovimiento(int movimiento);
    public abstract Rectangle getRectangle();
    public abstract void setRectangle(Rectangle rectangle);
    public abstract String getTipo();
    public abstract void setTipo(String tipo);
    public abstract String getAtaqueEspecial();
    public abstract int getRango();
    public abstract void setRango(int pRango);
    public abstract void settRegion(TextureRegion tRegion);
    public abstract TextureRegion gettRegion();




   // public abstract String aplicarAtaqueEspecial();
}
