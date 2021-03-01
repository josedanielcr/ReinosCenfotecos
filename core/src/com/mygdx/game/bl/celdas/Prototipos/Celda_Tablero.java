package com.mygdx.game.bl.celdas.Prototipos;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;

public class Celda_Tablero extends Celda {



    public Celda_Tablero(int id) {
        this.setId(id);
        this.setBoundingBox(new Rectangle(posXCeldaInicial,posYCeldaInicial, width, height));
        this.setEstado("free");
        this.setCellTextureRegion(cellAtlas.findRegion("emptyCell"));

    }

    @Override
    public Celda clone() {
        return new Celda_Tablero(this.getId());
    }

    @Override
    public void setTextureCastleCell(String color) {
        System.out.println("En esta celda no puede ir un castillo");
    }
}
