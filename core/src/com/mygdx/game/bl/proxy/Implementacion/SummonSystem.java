package com.mygdx.game.bl.proxy.Implementacion;

import com.mygdx.game.bl.cadenaResponsabilidad.abstracto.HandlerSummon;
import com.mygdx.game.bl.cadenaResponsabilidad.concreto.CeldaBordeAnalisis;
import com.mygdx.game.bl.cadenaResponsabilidad.concreto.CeldaColorCambio;
import com.mygdx.game.bl.cadenaResponsabilidad.concreto.CeldaLibreAnalisis;
import com.mygdx.game.bl.cadenaResponsabilidad.concreto.CeldaPersonajeSummon;
import com.mygdx.game.bl.cadenaResponsabilidad.objetos.Pattern;
import com.mygdx.game.bl.cadenaResponsabilidad.objetos.Task;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.bl.personajes.componente.Personaje;
import com.mygdx.game.bl.proxy.Interface.ISummonSystem;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerPersonaje;

import java.util.ArrayList;

public class SummonSystem implements ISummonSystem {
    private ArrayList<Task> tasks;
    private Pattern selectedPattern;
    private ArrayList<Celda> fiveCellPattern;
    private ArrayList<Pattern> player1;
    private ArrayList<Pattern> player2;
    private HandlerSummon manejador;
    private Celda startCell;
    private ControllerCelda gCell;
    private ControllerPersonaje gPer;


    public SummonSystem(ControllerCelda gCell, ControllerPersonaje gPer) {
        this.gCell = gCell;
        this.gPer = gPer;
        this.fiveCellPattern = new ArrayList<>();
        iniciarProceso();
        createFirstPlayerPatterns();
        createSecondPlayerPatterns();
    }

    private HandlerSummon configurarCadena() {
        HandlerSummon freeCell = new CeldaLibreAnalisis(gCell,gPer);
        HandlerSummon cellBorder = new CeldaBordeAnalisis(gCell, gPer);
        HandlerSummon changeColors = new CeldaColorCambio(gCell, gPer);
        HandlerSummon summoning = new CeldaPersonajeSummon(gCell, gPer);

        freeCell.setSuccessor(cellBorder);
        cellBorder.setSuccessor(changeColors);
        changeColors.setSuccessor(summoning);
        return freeCell;
    }

    public void iniciarProceso() {
        manejador = configurarCadena();

    }

    public String summon(int pIdCelda, String pIdPattern, String pJugador) {
        boolean validate;
        String report="";

        this.startCell = gCell.getCell(pIdCelda);
        boolean continuar = true;

        if (selectedPattern==null) {
            selectedPattern = new Pattern();
        }
        selectedPattern = getPattern(pIdPattern, pJugador);

        validate = createArrayCellsPattern();

        if (validate) {
            createTasks(pJugador);

            for (Task t : tasks) {
                continuar = manejador.executeTask(t);
                if(!continuar) {
                    report = "Celdas inválidas. Seleccione otra celda de inicio u otro patrón de convocación.";
                    break;
                }
            }
            if (continuar) {
                report = "Convocación exitosa.";
            }

        }
        else {
            report = "Error: El patrón de convocación excede el límite superior/inferior del tablero.";
        }
        return report;

    }

    @Override
    public Personaje displayStats(int pIdPersonaje, String pJugador) {
        return null;
    }

    public void addTask(Task pTask) {
        tasks.add(pTask);
    }

    public void createTasks(String pJugador) {
        if (tasks==null) {
            tasks = new ArrayList<>();
        }
        if (startCell != null) {
            addTask(new Task("free", startCell, fiveCellPattern, pJugador));
            addTask(new Task("border", startCell, fiveCellPattern, pJugador));
            addTask(new Task("color", startCell, fiveCellPattern, pJugador));
            addTask(new Task("summon", startCell, fiveCellPattern, pJugador));
        }
    }

    public void createFirstPlayerPatterns() {
        if (player1==null) {
            player1 = new ArrayList<>();
        }
        player1.add(new Pattern("I",20,40,60,80,100));
        player1.add(new Pattern("T", 20, 40, 60, 59, 61));
        player1.add(new Pattern("L1", 20, 40, 60, 80, 79));
        player1.add(new Pattern("L2", 20, 40, 60, 80, 81));
        player1.add(new Pattern("Z2", 20, 40, 61, 81, 101));
        player1.add(new Pattern("Z1", 20, 40, 59, 79, 99));
        player1.add(new Pattern("U", 20, 40, 41, 42, 22));
    }


    public void createSecondPlayerPatterns() {
        if (player2==null) {
            player2 = new ArrayList<>();
        }
        player2.add(new Pattern("I",-20, -40, -60, -80, -100));
        player2.add(new Pattern("T", -20, -40, -60, -59, -61));
        player2.add(new Pattern("L1", -20, -40, -60, -80, -81));
        player2.add(new Pattern("L2", -20, -40, -60, -80, -79));
        player2.add(new Pattern("Z1", -20, -40, -61, -81, -101));
        player2.add(new Pattern("Z2", -20, -40, -59, -79, -99));
        player2.add(new Pattern("U", -20, -40, -39, -38, -18));
    }



    public Pattern getPattern(String pIdPattern, String pJugador) {
        Pattern foundPattern = null;
        if (pJugador.equals("blue")) {
            for (Pattern p : player1) {
                if(p.getType().equals(pIdPattern)) {
                    foundPattern = p;
                    break;
                }

            }
        }
        if (pJugador.equals("red")) {
            for (Pattern p : player2) {
                if(p.getType().equals(pIdPattern)) {
                    foundPattern = p;
                    break;
                }
            }
        }
        return foundPattern;
    }

    public boolean createArrayCellsPattern() {
        boolean patternBuilding = true;
        fiveCellPattern = new ArrayList<>();
        int [] positions;
        positions = new int[]{selectedPattern.getIdCell1(), selectedPattern.getIdCell2(), selectedPattern.getIdCell3(), selectedPattern.getIdCell4(), selectedPattern.getIdCell5()};

        for (int i : positions) {
            Celda referenceCell = gCell.getCell(startCell.getId()+i);
            if (referenceCell!=null) {
                fiveCellPattern.add(referenceCell);
            }
            else {
                patternBuilding = false;
                break;
            }
        }
        return patternBuilding;
    }
}