package com.mygdx.game.bl.dados.producto;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Dado {

    private String lado1;
    private String lado2;
    private String lado3;
    private String lado4;
    private String lado5;
    private String lado6;
    private String tipo;

    //CONSTRUCTOR

    public Dado(String tipo) {
        this.tipo = tipo;
    }

    public Dado(String lado1, String lado2, String lado3, String lado4, String lado5, String lado6, String tipo) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        this.lado4 = lado4;
        this.lado5 = lado5;
        this.lado6 = lado6;
        this.tipo = tipo;
    }

    //GETS

    public String getLado1() {
        return lado1;
    }

    public String getLado2() {
        return lado2;
    }

    public String getLado3() {
        return lado3;
    }

    public String getLado4() {
        return lado4;
    }

    public String getLado5() {
        return lado5;
    }

    public String getLado6() {
        return lado6;
    }

    public String getNombre() {
        return tipo;
    }

    //SETS

    public void setLado1(String lado1) {
        this.lado1 = lado1;
    }

    public void setLado2(String lado2) {
        this.lado2 = lado2;
    }

    public void setLado3(String lado3) {
        this.lado3 = lado3;
    }

    public void setLado4(String lado4) {
        this.lado4 = lado4;
    }

    public void setLado5(String lado5) {
        this.lado5 = lado5;
    }

    public void setLado6(String lado6) {
        this.lado6 = lado6;
    }

    public void setNombre(String tipo) {
        this.tipo = tipo;
    }

    //METODOS ABSTRACTOS

    public abstract String rollDie();
    public abstract void asignarLados();

}
