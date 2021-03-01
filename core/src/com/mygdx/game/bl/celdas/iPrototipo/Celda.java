package com.mygdx.game.bl.celdas.iPrototipo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.PfabricaAbstracta.PersonajeFA;

public abstract class Celda {
    public static final TextureAtlas cellAtlas = new TextureAtlas("cells.atlas");
    public static final int posXCeldaInicial = 50, posYCeldaInicial = 50, width = 16, height = 16;
    private int id;
    private Rectangle boundingBox;
    private TextureRegion cellTextureRegion;
    private PersonajeFA personaje;
    private String estado; //free, red, blue


    public abstract Celda clone();

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

    public PersonajeFA getPersonaje() {
        return personaje;
    }

    public void setPersonaje(PersonajeFA personaje) {
        this.personaje = personaje;
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

    public void draw(Batch batch) {
        batch.draw(cellTextureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }

    public abstract void setTextureCastleCell(String color);

}
