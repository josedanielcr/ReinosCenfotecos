package com.mygdx.game.bl.dados.producto;

public abstract class Dado {

    private int lado1;
    private int lado2;
    private int lado3;
    private int lado4;
    private int lado5;
    private int lado6;
    private String tipo;

    //CONSTRUCTOR

    public Dado(int lado1, int lado2, int lado3, int lado4, int lado5, int lado6, String tipo) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        this.lado4 = lado4;
        this.lado5 = lado5;
        this.lado6 = lado6;
        this.tipo = tipo;
    }

    //GETS

    public int getLado1() {
        return lado1;
    }

    public int getLado2() {
        return lado2;
    }

    public int getLado3() {
        return lado3;
    }

    public int getLado4() {
        return lado4;
    }

    public int getLado5() {
        return lado5;
    }

    public int getLado6() {
        return lado6;
    }

    public String getNombre() {
        return tipo;
    }

    //SETS

    public void setLado1(int lado1) {
        this.lado1 = lado1;
    }

    public void setLado2(int lado2) {
        this.lado2 = lado2;
    }

    public void setLado3(int lado3) {
        this.lado3 = lado3;
    }

    public void setLado4(int lado4) {
        this.lado4 = lado4;
    }

    public void setLado5(int lado5) {
        this.lado5 = lado5;
    }

    public void setLado6(int lado6) {
        this.lado6 = lado6;
    }

    public void setNombre(String tipo) {
        this.tipo = tipo;
    }

    //METODOS ABSTRACTOS

    public abstract String rollDice();

}
