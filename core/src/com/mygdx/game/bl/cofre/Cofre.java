package com.mygdx.game.bl.cofre;

import java.util.ArrayList;

public class Cofre {

    private ArrayList<String> dadosInvocacion;
    private ArrayList<String> dadosAtaque;
    private ArrayList<String> dadosAtaqueEspecial;

    public Cofre() {
        dadosInvocacion=new ArrayList<>();
        dadosAtaque=new ArrayList<>();
        dadosAtaqueEspecial=new ArrayList<>();
    }

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

    //METODOS

    public void guardarRoll(String proll1, String proll2, String proll3) {

        if (proll1.equals("Infanteria") || proll1.equals("Artilleria") || proll1.equals("Tanque")) {
            if (campoCofre(1)) {
                dadosInvocacion.add(proll1);
            }
        }

        if (proll2.equals("Infanteria") || proll2.equals("Artilleria") || proll2.equals("Tanque")) {
            if (campoCofre(1)) {
                dadosInvocacion.add(proll2);
            }
        }

        if (proll3.equals("Ataque")) {
            if (campoCofre(2)) {
                dadosAtaque.add(proll1);
            }
        } else if (proll3.equals("AtaqueEspecial")) {
            if (campoCofre(3)) {
                dadosAtaqueEspecial.add(proll1);
            }
        }
    }

    public boolean campoCofre(int pcofre) {
        switch (pcofre) {
            case 1:
                if (dadosInvocacion.size() < 6) {
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


    //para que la parte grafica sepa cuanto hay en cada "cofre"

    public int getDice(int pcofre) {
        int dice = 0;
        switch (pcofre) {
            case 0:
                for (String dado : dadosInvocacion) {
                    if (dado.equals("Infanteria")) {
                        dice++;
                    }
                }
                break;
            case 1:
                for (String dado : dadosInvocacion) {
                    if (dado.equals("Artilleria")) {
                        dice++;
                    }
                }
                break;
            case 2:
                for (String dado : dadosInvocacion) {
                    if (dado.equals("Tanque")) {
                        dice++;
                    }
                }
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
}
