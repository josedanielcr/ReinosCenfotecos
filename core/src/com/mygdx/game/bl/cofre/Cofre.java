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

    public boolean guardarRoll(String proll1, String proll2, String proll3) {
        boolean added=true;
        ArrayList<String> dados;

        if (proll1.equals("Infanteria") || proll1.equals("Artilleria") || proll1.equals("Tanque")) {
            if (campoCofre(1)) {
                if(proll1.equals("Infanteria")){
                    dados=dadosInfanteria;
                }else if(proll1.equals("Artilleria")){
                    dados=dadosArtilleria;
                }else{
                    dados=dadosTanque;
                }
                dados.add(proll1);
            }else{
                added=false;
            }
        }

        if (proll2.equals("Infanteria") || proll2.equals("Artilleria") || proll2.equals("Tanque")) {
            if (campoCofre(1)) {
                if(proll2.equals("Infanteria")){
                    dados=dadosInfanteria;
                }else if(proll2.equals("Artilleria")){
                    dados=dadosArtilleria;
                }else{
                    dados=dadosTanque;
                }
                dados.add(proll2);
            }else{
                added=false;
            }
        }

        if (proll3.equals("Ataque")) {
            if (campoCofre(2)) {
                dadosAtaque.add(proll1);
            }else{
                added=false;
            }
        } else if (proll3.equals("AtaqueEspecial")) {
            if (campoCofre(3)) {
                dadosAtaqueEspecial.add(proll1);
            }else{
                added=false;
            }
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
