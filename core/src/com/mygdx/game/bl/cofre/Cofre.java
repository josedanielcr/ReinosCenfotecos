package com.mygdx.game.bl.cofre;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;
import java.util.ArrayList;

public class Cofre {

    private ArrayList<String> dadosInvocacion;
    private ArrayList<String> dadosMovimiento;
    private ArrayList<String> dadosAtaque;
    private ArrayList<String> dadosAtaqueEspecial;

//    //graphics
    private final static TextureAtlas cofreAtlas = new TextureAtlas("cofre.atlas");
    static TextureRegion cofreTextureRegion = cofreAtlas.findRegion("Cofre");

    //talvez no sea necesario
//    private Rectangle boundingBox;



    public void draw(Batch batch) { //arreglar esto con los valores de verdad
        batch.draw(cofreTextureRegion,900,100,100,100);
    }

}
