package com.mygdx.game.bl.proxy.Implementacion;

import com.mygdx.game.bl.cadenaResponsabilidad.abstracto.HandlerSummon;
import com.mygdx.game.bl.cadenaResponsabilidad.concreto.CeldaBordeAnalisis;
import com.mygdx.game.bl.cadenaResponsabilidad.concreto.CeldaColorCambio;
import com.mygdx.game.bl.cadenaResponsabilidad.concreto.CeldaLibreAnalisis;
import com.mygdx.game.bl.cadenaResponsabilidad.concreto.CeldaPersonajeSummon;
import com.mygdx.game.bl.cadenaResponsabilidad.objetos.Pattern;
import com.mygdx.game.bl.cadenaResponsabilidad.objetos.Task;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
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

    public String summon(int pIdCelda, String pIdPattern, String pJugador, int pType) {
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
            createTasks(pJugador, pType);

            for (Task t : tasks) {
                continuar = manejador.executeTask(t);
                if(!continuar) {
                    report = "Invalid cells. Select another starting cell or pattern.";
                    break;
                }
            }
            if (continuar) {
                report = "Summoning successful.";
                if (pJugador.equals("red")) {
                    gCell.setLastEnemySummonCell(fiveCellPattern.get(4).getId());
                }

            }

        }
        else {
            report = "Summoning pattern exceeds the up/down limit of board.";
        }
        return report;

    }

    @Override
    public PersonajeAbstracto displayStats(int pIdPersonaje, String pJugador) {
        PersonajeAbstracto pTemp = null;
        if (pJugador.equals("blue")) {
            pTemp = gPer.retornarPersonajeDecorador(pIdPersonaje);
        }
        if (pJugador.equals("red")) {
            pTemp = gPer.retornarPersonajeDecoradorEnemigue(pIdPersonaje);

        }
        return pTemp;
    }

    public void addTask(Task pTask) {
        tasks.add(pTask);
    }

    public void createTasks(String pJugador, int typeSummon) {
        tasks = new ArrayList<>();
        if (startCell != null) {
            addTask(new Task("free", startCell, fiveCellPattern, pJugador, typeSummon));
            addTask(new Task("border", startCell, fiveCellPattern, pJugador, typeSummon));
            addTask(new Task("color", startCell, fiveCellPattern, pJugador, typeSummon));
            addTask(new Task("summon", startCell, fiveCellPattern, pJugador, typeSummon));
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
        player1.add(new Pattern("Z2", 20, 40, 41, 61, 81));
        player1.add(new Pattern("Z1", 20, 40, 39, 59, 79));
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
        player2.add(new Pattern("Z1", -20, -40, -41, -61, -81));
        player2.add(new Pattern("Z2", -20, -40, -39, -59, -79));
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
            int idStartCell = startCell.getId();
            if (idStartCell>400 && idStartCell<1020) {
                idStartCell = idStartCell-1019;
            }
            if (idStartCell>=1020) {
                idStartCell = idStartCell-619;
            }
            Celda referenceCell = gCell.getCell(idStartCell+i);
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

    public String moveUnit(int pIdPersonaje, int pIdCelda, String pIdJugador, String pMovimiento) {
        String report = "";
        int nextMoveValue = getNextMoveValue(pMovimiento);

        if (pIdCelda+nextMoveValue>400) {
            report = "Movement action exceeds upper limit of board.";
        }
        else if (pIdCelda+nextMoveValue<1) {
            report = "Movement action exceeds lower limit of board.";
        }
        else if (pIdCelda%20==1) {
            if ((pIdCelda+nextMoveValue) % 20 == 0) {
                report = "Invalid action. Unit is at the border limit.";
            }
        }
        else if (pIdCelda%20==0) {
            if ((pIdCelda+nextMoveValue) % 20 == 1) {
                report = "Invalid action. Unit is at the border limit.";
            }
        }
        else {
            Celda targetCell = gCell.getCell(pIdCelda+nextMoveValue);
            if (targetCell.getIdPersonaje()==0) {
                if (targetCell.getCellColor().getColor().equals("free")) {
                    report = "Invalid move. Target cell is not claimed by a player.";
                }
                else {
                    if (pIdJugador.equals("blue")) {
                        gPer.retornarPersonajeDecorador(pIdPersonaje).setRectangle(targetCell.getBoundingBox());
                        targetCell.setIdPersonaje(pIdPersonaje);
                        gCell.getCell(pIdCelda).setIdPersonaje(0);
                        report="Successful move action.";
                    }
                    if (pIdJugador.equals("red")) {
                        gPer.retornarPersonajeDecoradorEnemigue(pIdPersonaje).setRectangle(targetCell.getBoundingBox());
                        targetCell.setIdPersonaje(pIdPersonaje);
                        gCell.getCell(pIdCelda).setIdPersonaje(0);
                        report="Successful move action.";
                    }

                }
            }
            else {
                report = "Target cell is occupied by another unit.";
            }
        }
        return report;
    }

    public int getNextMoveValue(String pMov) {
        int nextMoveValue = 0;
        switch (pMov) {
            case "up":
                nextMoveValue = 20;
                break;
            case "down":
                nextMoveValue = -20;
                break;
            case "left":
                nextMoveValue = -1;
                break;
            default:
                nextMoveValue = 1;
                break;
        }
        return nextMoveValue;
    }
}