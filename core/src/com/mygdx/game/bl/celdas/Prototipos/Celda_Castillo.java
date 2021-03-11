package com.mygdx.game.bl.celdas.Prototipos;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;

public class Celda_Castillo extends Celda {
    final static TextureAtlas cellAtlas = new TextureAtlas("cells.atlas");
    private int lifePoints;
    private boolean castillo;

    //***********************************************************************************************
    // CONSTRUCTORES
    //***********************************************************************************************

    public Celda_Castillo(int id, int lifePoints) {
        this.setId(id);
        this.setBoundingBox(new Rectangle(posXCeldaInicial,posYCeldaInicial, width, height));
        this.setEstado("free");
        this.castillo = false;
        this.setCellTextureRegion(cellAtlas.findRegion("emptyCastleCell"));
        this.lifePoints = lifePoints;
    }

    //***********************************************************************************************
    // GETS & SETTERS
    //***********************************************************************************************

    public boolean isCastillo() {
        return castillo;
    }

    public void setCastillo(boolean castillo) {
        this.castillo = castillo;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }


    //***********************************************************************************************
    // IMPLEMENTACIÓN DE MÉTODOS ABSTRACTOS
    //***********************************************************************************************

    @Override
    public Celda clone() {
        return new Celda_Castillo(this.getId(),this.getLifePoints());
    }

    @Override
    public void setObjectCell(String codigo) {
        if (lifePoints>1) {
            if (codigo.equals("blue")) {
                setCellTextureRegion(cellAtlas.findRegion("blueCastleCell"));
            }
            else {
                setCellTextureRegion(cellAtlas.findRegion("redCastleCell"));
            }
            this.castillo = true;
        }
        else if (lifePoints==1) {
            if (codigo.equals("blue")) {
                setCellTextureRegion(cellAtlas.findRegion("blueCastleCell1"));
            } else {
                setCellTextureRegion(cellAtlas.findRegion("redCastleCell1"));
            }
        }
        else {
            setCellTextureRegion(cellAtlas.findRegion("deadCastleCell"));
        }
    }

}
