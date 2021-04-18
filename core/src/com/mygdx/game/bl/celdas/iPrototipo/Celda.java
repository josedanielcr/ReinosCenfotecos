package com.mygdx.game.bl.celdas.iPrototipo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.celdas.Abstracta.IColor;

public abstract class Celda {
    public static final int posXCeldaInicial = 248, posYCeldaInicial = 195, width = 30, height = 30;
    private int id;
    private Rectangle boundingBox;
    private IColor cellColor;
    private int lifePoints;
    private boolean castillo;
    private int idPersonaje;


    //***********************************************************************************************
    // GETS & SETTERS
    //***********************************************************************************************

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    public IColor getCellColor() {
        return cellColor;
    }

    public void setCellColor(IColor cellColor) {
        this.cellColor = cellColor;
    }

    public void setBoundingBoxX (int x) {
        this.boundingBox.x = x;
    }

    public void setBoundingBoxY (int y) {
        this.boundingBox.y = y;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public boolean isCastillo() {
        return castillo;
    }

    public void setCastillo(boolean castillo) {
        this.castillo = castillo;
    }

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    //***********************************************************************************************
    // MÉTODOS ABSTRACTOS
    //***********************************************************************************************

    /**
     * Método abstracto que define el objeto que ocupa una celda específica.
     * @param codigo El String que identifica al objeto.
     */
    public abstract void setObjectCell(String codigo);

    /**
     * Método abstracto de clonación de prototipos.
     * @return Retorna el clon del prototipo Celda.
     */
    public abstract Celda clone();

    //***********************************************************************************************
    // MÉTODOS DE CLASE
    //***********************************************************************************************

    /**
     * Método que dibuja una celda en la pantalla de juego.
     * @param batch El batch del objeto a dibujar.
     */
    public void draw(Batch batch) {
        batch.draw(cellColor.getColoredCell(), boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }


}
