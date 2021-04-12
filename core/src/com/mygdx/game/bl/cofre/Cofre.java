package com.mygdx.game.bl.cofre;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Cofre {

    private ArrayList<String> dadosInvocacion;
    private ArrayList<String> dadosAtaque;
    private ArrayList<String> dadosAtaqueEspecial;

    //graphics
    private final static TextureAtlas cofreAtlas = new TextureAtlas("cofre.atlas");
    static TextureRegion cofreTextureRegion = cofreAtlas.findRegion("Cofre");

    //GETS

    public ArrayList<String> getDadosInvocacion() {
        return dadosInvocacion;
    }

    public ArrayList<String> getDadosAtaque() {
        return dadosAtaque;
    }

    public ArrayList<String> getDadosAtaqueEspecial() {
        return dadosAtaqueEspecial;
    }

    public static TextureAtlas getCofreAtlas() {
        return cofreAtlas;
    }

    public static TextureRegion getCofreTextureRegion() {
        return cofreTextureRegion;
    }

    //SETS

    public void setDadosInvocacion(ArrayList<String> dadosInvocacion) {
        this.dadosInvocacion = dadosInvocacion;
    }

    public void setDadosAtaque(ArrayList<String> dadosAtaque) {
        this.dadosAtaque = dadosAtaque;
    }

    public void setDadosAtaqueEspecial(ArrayList<String> dadosAtaqueEspecial) {
        this.dadosAtaqueEspecial = dadosAtaqueEspecial;
    }

    public static void setCofreTextureRegion(TextureRegion cofreTextureRegion) {
        Cofre.cofreTextureRegion = cofreTextureRegion;
    }

    public void draw(Batch batch) { //arreglar esto con los valores de verdad
        batch.draw(cofreTextureRegion,900,100,100,100);
    }

    //METODOS

    public boolean guardarRoll(String proll) {
        boolean added=false;

        if(proll.equals("Infanteria") || proll.equals("Artilleria") || proll.equals("Tanque")){
            if(campoCofre(1)) {
                dadosInvocacion.add(proll);
                added = true;
            }
        }else if(proll.equals("Ataque")) {
            if(campoCofre(2)) {
                dadosAtaque.add(proll);
                added = true;
            }
        }else{
            if(campoCofre(3)) {
                dadosAtaqueEspecial.add(proll);
                added = true;
            }
        }

        return added;
    }

    public boolean campoCofre(int pcofre) {
        switch (pcofre){
            case 1:
                if(dadosInvocacion.size()<6){
                    return true;
                }
                break;
            case 2:
                if(dadosAtaque.size()<3){
                    return true;
                }
                break;
            case 3:
                if(dadosAtaqueEspecial.size()<2){
                    return true;
                }
                break;
        }
        return false;
    }


    //para que la parte grafica sepa cuanto hay en cada "cofre"

    public int displayCampo(int pcofre) {
        if (pcofre==1){
            return dadosInvocacion.size();
        }else if(pcofre==2){
            return dadosAtaque.size();
        }else{
            return dadosAtaqueEspecial.size();
        }
    }

}
