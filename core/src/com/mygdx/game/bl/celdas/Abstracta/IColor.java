package com.mygdx.game.bl.celdas.Abstracta;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface IColor {
    TextureRegion getColoredCell();
    void setColoredCell(TextureRegion pTR);
    String getColor();

}
