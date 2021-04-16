package com.mygdx.game.bl.celdas.Color;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.bl.celdas.Abstracta.IColor;

public class Blue implements IColor {
    public static final TextureAtlas cellAtlas = new TextureAtlas("cells/cells.atlas");
    private TextureRegion cellTextureRegion;
    private String state;
    private boolean castleRegion;

    public Blue(boolean castle) {
        this.castleRegion = castle;
        this.state = "blue";

        if (this.castleRegion) {
            this.cellTextureRegion = cellAtlas.findRegion("blueCastleCell");
        }
        else {
            this.cellTextureRegion = cellAtlas.findRegion("blueCell1");
        }

    }


    @Override
    public TextureRegion getColoredCell() {
        return cellTextureRegion;
    }

    @Override
    public void setColoredCell(TextureRegion pTR) {
        this.cellTextureRegion = pTR;
    }

    @Override
    public String getColor() {
        return state;
    }
}