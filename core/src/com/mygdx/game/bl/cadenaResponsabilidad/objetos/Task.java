package com.mygdx.game.bl.cadenaResponsabilidad.objetos;

import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import java.util.ArrayList;

public class Task {
    private String type;
    private Celda startCell;
    private ArrayList<Celda> cellsPattern;
    private String colorJugador;

    public Task(String type, Celda startCell, ArrayList<Celda> cellsPattern, String colorJugador) {
        this.type = type;
        this.startCell = startCell;
        this.cellsPattern = cellsPattern;
        this.colorJugador = colorJugador;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Celda getStartCell() {
        return startCell;
    }

    public void setStartCell(Celda startCell) {
        this.startCell = startCell;
    }

    public ArrayList<Celda> getCellsPattern() {
        return cellsPattern;
    }

    public void setCellsPattern(ArrayList<Celda> cellsPattern) {
        this.cellsPattern = cellsPattern;
    }

    public String getColorJugador() {
        return colorJugador;
    }

    public void setColorJugador(String colorJugador) {
        this.colorJugador = colorJugador;
    }
}

