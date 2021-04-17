package com.mygdx.game.bl.personajes.PproductoAbstracto;

import com.badlogic.gdx.math.Rectangle;

public interface PersonajeAbstracto  {
     String obtenerInformacionPersonaje();
     int getIdPersonaje();
     String getAtaqueEspecial();
     int getVida();
     void setVida(int vida);
     int getAtaque();
     void setAtaque(int ataque);
     int getDefensa();
     void setDefensa(int defensa);
     int getMovimiento();
     void setMovimiento(int movimiento);
     Rectangle getRectangle();
     void setRectangle(Rectangle rectangle);
     String getTipo();
     void setTipo(String tipo);
     int getRango();
     void setRango(int pRango);
}
