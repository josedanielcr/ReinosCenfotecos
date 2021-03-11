package com.mygdx.game.bl.celdas.iPrototipo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Celda {
    public static final TextureAtlas cellAtlas = new TextureAtlas("cells.atlas");
    public static final int posXCeldaInicial = 50, posYCeldaInicial = 50, width = 30, height = 30;
    private int id;
    private Rectangle boundingBox;
    private TextureRegion cellTextureRegion;
    private String estado; //free, red, blue


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

    public TextureRegion getCellTextureRegion() {
        return cellTextureRegion;
    }

    public void setCellTextureRegion(TextureRegion cellTextureRegion) {
        this.cellTextureRegion = cellTextureRegion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setBoundingBoxX (int x) {
        this.boundingBox.x = x;
    }

    public void setBoundingBoxY (int y) {
        this.boundingBox.y = y;
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
        batch.draw(cellTextureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }

}
