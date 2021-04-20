package com.mygdx.game.bl.cofre;

import java.util.ArrayList;

public class Cofre {

    private ArrayList<String> dadosInfanteria;
    private ArrayList<String> dadosArtilleria;
    private ArrayList<String> dadosTanque;
    private ArrayList<String> dadosAtaque;
    private ArrayList<String> dadosAtaqueEspecial;

    public Cofre() {
        dadosInfanteria=new ArrayList<>();
        dadosArtilleria=new ArrayList<>();
        dadosTanque=new ArrayList<>();
        dadosAtaque=new ArrayList<>();
        dadosAtaqueEspecial=new ArrayList<>();
    }

    //GETS

    public ArrayList<String> getDadosInfanteria() {
        return dadosInfanteria;
    }

    public ArrayList<String> getDadosArtilleria() {
        return dadosArtilleria;
    }

    public ArrayList<String> getDadosTanque() {
        return dadosTanque;
    }

    public ArrayList<String> getDadosAtaque() {
        return dadosAtaque;
    }

    public ArrayList<String> getDadosAtaqueEspecial() {
        return dadosAtaqueEspecial;
    }

    //SETS

    public void setDadosInfanteria(ArrayList<String> dadosInfanteria) {
        this.dadosInfanteria = dadosInfanteria;
    }

    public void setDadosArtilleria(ArrayList<String> dadosArtilleria) {
        this.dadosArtilleria = dadosArtilleria;
    }

    public void setDadosTanque(ArrayList<String> dadosTanque) {
        this.dadosTanque = dadosTanque;
    }

    public void setDadosAtaque(ArrayList<String> dadosAtaque) {
        this.dadosAtaque = dadosAtaque;
    }

    public void setDadosAtaqueEspecial(ArrayList<String> dadosAtaqueEspecial) {
        this.dadosAtaqueEspecial = dadosAtaqueEspecial;
    }

    //METODOS

    public boolean guardarRoll(int ptipo) {
        boolean added=true;
        ArrayList<String> dados;

        switch(ptipo){
            case 1:
                dados=dadosInfanteria;
                if(campoCofre(1)){
                    dados.add("Infanteria");
                }else{
                    added=false;
                }
                break;
            case 2:
                dados=dadosArtilleria;
                if(campoCofre(1)){
                    dados.add("Artilleria");
                }else{
                    added=false;
                }
                break;
            case 3:
                dados=dadosTanque;
                if(campoCofre(1)){
                    dados.add("Tanque");
                }else{
                    added=false;
                }
                break;
            case 4:
                dados=dadosAtaque;
                if(campoCofre(2)){
                    dados.add("Ataque");
                }else{
                    added=false;
                }
                break;
            case 5:
                dados=dadosAtaqueEspecial;
                if(campoCofre(3)){
                    dados.add("AtaqueEspecial");
                }else{
                    added=false;
                }
                break;
        }

        return added;
    }

    public boolean campoCofre(int pcofre) {
        switch (pcofre) {
            case 1:
                if (dadosArtilleria.size()+dadosTanque.size()+dadosInfanteria.size() < 6) {
                    return true;
                }
                break;
            case 2:
                if (dadosAtaque.size() < 3) {
                    return true;
                }
                break;
            case 3:
                if (dadosAtaqueEspecial.size() < 2) {
                    return true;
                }
                break;
        }
        return false;
    }


    public int getDice(int pcofre) {
        int dice = 0;
        switch (pcofre) {
            case 0:
                dice=dadosInfanteria.size();
                break;
            case 1:
                dice=dadosArtilleria.size();
                break;
            case 2:
                dice=dadosTanque.size();
                break;
            case 3:
                dice=dadosAtaque.size();
                break;
            case 4:
                dice=dadosAtaqueEspecial.size();
                break;
        }
        return dice;
    }

    public void removeSummonDice(int tipo){
        switch(tipo){
            case 1:
                dadosInfanteria.remove(0);
                break;
            case 2:
                dadosArtilleria.remove(0);
                break;
            case 3:
                dadosTanque.remove(0);
                break;
        }
    }
}
