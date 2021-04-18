package com.mygdx.game.bl.cadenaResponsabilidad.objetos;

public class Pattern {
    private String type;
    private int idCell1;
    private int idCell2;
    private int idCell3;
    private int idCell4;
    private int idCell5;

    public Pattern() {
    }

    public Pattern(String type, int idCell1, int idCell2, int idCell3, int idCell4, int idCell5) {
        this.type = type;
        this.idCell1 = idCell1;
        this.idCell2 = idCell2;
        this.idCell3 = idCell3;
        this.idCell4 = idCell4;
        this.idCell5 = idCell5;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdCell1() {
        return idCell1;
    }

    public void setIdCell1(int idCell1) {
        this.idCell1 = idCell1;
    }

    public int getIdCell2() {
        return idCell2;
    }

    public void setIdCell2(int idCell2) {
        this.idCell2 = idCell2;
    }

    public int getIdCell3() {
        return idCell3;
    }

    public void setIdCell3(int idCell3) {
        this.idCell3 = idCell3;
    }

    public int getIdCell4() {
        return idCell4;
    }

    public void setIdCell4(int idCell4) {
        this.idCell4 = idCell4;
    }

    public int getIdCell5() {
        return idCell5;
    }

    public void setIdCell5(int idCell5) {
        this.idCell5 = idCell5;
    }
}
