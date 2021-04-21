package com.mygdx.game.bl.celdas.Prototipos;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.celdas.Color.Gray;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;

public class Celda_Castillo extends Celda {
    final static TextureAtlas cellAtlas = new TextureAtlas("cells/cells.atlas");

    //***********************************************************************************************
    // CONSTRUCTORES
    //***********************************************************************************************

    public Celda_Castillo(int id, int lifePoints) {
        this.setId(id);
        this.setBoundingBox(new Rectangle(posXCeldaInicial,posYCeldaInicial, width, height));
        this.setCellColor(new Gray(true));
        this.setLifePoints(lifePoints);
        this.setCastillo(false);
        this.setIdPersonaje(0);
    }

    //***********************************************************************************************
    // GETS & SETTERS
    //***********************************************************************************************


    //***********************************************************************************************
    // IMPLEMENTACIÓN DE MÉTODOS ABSTRACTOS
    //***********************************************************************************************

    @Override
    public Celda clone() {
        return new Celda_Castillo(this.getId(),this.getLifePoints());
    }

    @Override
    public void setObjectCell(String codigo) {
         if (this.getLifePoints()<1) {
            if (codigo.equals("blue")) {
                this.getCellColor().setColoredCell(cellAtlas.findRegion("emptyCastleCell"));
            }
            if (codigo.equals("red")) {
                this.getCellColor().setColoredCell(cellAtlas.findRegion("emptyCastleCell"));
            }
        }
    }

}
