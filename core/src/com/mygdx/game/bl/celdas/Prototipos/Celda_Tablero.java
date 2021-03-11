package com.mygdx.game.bl.celdas.Prototipos;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;

public class Celda_Tablero extends Celda {
    private String codigoPersonaje;

    //***********************************************************************************************
    // CONSTRUCTORES
    //***********************************************************************************************

    public Celda_Tablero(int id) {
        this.setId(id);
        this.setBoundingBox(new Rectangle(posXCeldaInicial,posYCeldaInicial, width, height));
        this.setEstado("free");
        this.setCellTextureRegion(cellAtlas.findRegion("emptyCell1"));

    }

    //***********************************************************************************************
    // GETS & SETTERS
    //***********************************************************************************************

    public String getCodigoPersonaje() {
        return codigoPersonaje;
    }

    public void setCodigoPersonaje(String codigoPersonaje) {
        this.codigoPersonaje = codigoPersonaje;
    }

    //***********************************************************************************************
    // IMPLEMENTACIÓN DE MÉTODOS ABSTRACTOS
    //***********************************************************************************************

    @Override
    public Celda clone() {
        return new Celda_Tablero(this.getId());
    }

    @Override
    public void setObjectCell(String codigo) {
        this.codigoPersonaje = codigo;
    }
}
