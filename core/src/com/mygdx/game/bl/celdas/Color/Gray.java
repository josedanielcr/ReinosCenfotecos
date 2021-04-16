package com.mygdx.game.bl.celdas.Color;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.bl.celdas.Abstracta.IColor;
import org.w3c.dom.Text;

public class Gray implements IColor {
    public static final TextureAtlas cellAtlas = new TextureAtlas("cells/cells.atlas");
    private TextureRegion cellTextureRegion;
    private String state;
    private boolean castleRegion;

    public Gray(boolean castleRegion) {
        this.castleRegion = castleRegion;
        this.state = "free";

        if (this.castleRegion) {
            this.cellTextureRegion = cellAtlas.findRegion("emptyCastleCell");
        }
        else {
            this.cellTextureRegion = cellAtlas.findRegion("emptyCell1");
        }

    }

    @Override
    public TextureRegion getColoredCell() {
        return cellTextureRegion;
    }

    public void setColoredCell(TextureRegion pTR) {
        this.cellTextureRegion = pTR;
    }

    @Override
    public String getColor() {
        return state;
    }
}
