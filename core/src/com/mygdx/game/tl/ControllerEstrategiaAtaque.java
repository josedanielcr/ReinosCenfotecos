package com.mygdx.game.tl;

import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.bl.estrategiaAtaque.base.AbstractAttack;
import com.mygdx.game.bl.estrategiaAtaque.concreta.AttackCastle;
import com.mygdx.game.bl.estrategiaAtaque.concreta.AttackUnit;
import com.mygdx.game.bl.estrategiaAtaque.contexto.Attack;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;

public class ControllerEstrategiaAtaque {
    private final ControllerCelda gCell;
    private final ControllerPersonaje gPer;

    public ControllerEstrategiaAtaque(ControllerCelda gCell, ControllerPersonaje gPer) {
        this.gCell = gCell;
        this.gPer = gPer;
    }

    public String executeAttack(String pIdJugador, int pIdCelda, int pIdPersonaje, int pWinCondition1, int pWinCondition2, int idCastillo1, int idCastillo2) {
        String report = "";

        if (pIdJugador.equals("blue")) {
            PersonajeAbstracto attackingUnit = gPer.retornarPersonajeDecorador(pIdPersonaje);
            if (pIdCelda==pWinCondition2) {
                Attack atk = new Attack(attackingUnit.getAtaque(), idCastillo2);
                selectStrategy(atk);
                report = atk.getStrategy().executeAttack();
            }
            else {
                int range = attackingUnit.getRango();
                PersonajeAbstracto defendingUnit = getIdDefending(range, pIdCelda, pIdJugador);
                if (defendingUnit!=null) {
                    Attack atk=new Attack(attackingUnit.getAtaque(), defendingUnit.getDefensa(), defendingUnit.getVida(), defendingUnit.getIdPersonaje());
                    selectStrategy(atk);
                    report = atk.getStrategy().executeAttack();
                }
                else {
                    report = "There is not an enemy unit inside the range of attack.";
                }
            }
        }
        if (pIdJugador.equals("red")) {
            PersonajeAbstracto attackingUnit = gPer.retornarPersonajeDecorador(pIdPersonaje);
            if (pIdCelda==pWinCondition1) {
                Attack atk = new Attack(attackingUnit.getAtaque(), idCastillo1);
                selectStrategy(atk);
                report = atk.getStrategy().executeAttack();
            }
            else {
                int range = attackingUnit.getRango();
                PersonajeAbstracto defendingUnit = getIdDefending(range, pIdCelda, pIdJugador);
                if (defendingUnit!=null) {
                    Attack atk=new Attack(attackingUnit.getAtaque(), defendingUnit.getDefensa(), defendingUnit.getVida(), defendingUnit.getIdPersonaje());
                    selectStrategy(atk);
                    report = atk.getStrategy().executeAttack();
                }
                else {
                    report = "There is not an enemy unit inside the range of attack.";
                }
            }
        }


        return report;
    }

    private void selectStrategy(Attack pAtk)  {
        AbstractAttack strategy = null;
        if (pAtk.isCastle()) {
            strategy = new AttackCastle(pAtk.getAtk(), pAtk.getIdCastle(), gCell, gPer);
        }
        if (pAtk.isUnit()) {
            strategy = new AttackUnit(pAtk.getAtk(), pAtk.getDef(), pAtk.getLife(), pAtk.getIdDefending(), gCell, gPer);
        }
        pAtk.setStrategy(strategy);
    }

    private PersonajeAbstracto getIdDefending(int pRange, int pIdCelda, String pJugador) {
        PersonajeAbstracto defendingUnit=null;
        boolean unitFound = false;
        Celda cellVision1 = gCell.getCell(pIdCelda+(20*pRange));
        Celda cellVision2 = gCell.getCell(pIdCelda+(-20*pRange));//PARECE OK
        Celda cellVision3 = gCell.getCell(pIdCelda+(pRange)); //PARECE OK
        Celda cellVision4 = gCell.getCell(pIdCelda+(-pRange));

        if (cellVision1!=null) {
            int idDefending = cellVision1.getIdPersonaje();
            if (idDefending!=0) {
                if (pJugador.equals("blue")) {
                    defendingUnit=gPer.retornarPersonajeDecoradorEnemigue(idDefending);
                }
                if (pJugador.equals("red")) {
                    defendingUnit=gPer.retornarPersonajeDecorador(idDefending);
                }
            }
            if (defendingUnit!=null) {
                unitFound = true;
            }
        }
        if (cellVision2!=null && !unitFound) {
            int idDefending = cellVision2.getIdPersonaje();
            if (idDefending!=0) {
                if (pJugador.equals("blue")) {
                    defendingUnit=gPer.retornarPersonajeDecoradorEnemigue(idDefending);
                }
                if (pJugador.equals("red")) {
                    defendingUnit=gPer.retornarPersonajeDecorador(idDefending);
                }
            }
            if (defendingUnit!=null) {
                unitFound = true;
            }
        }
        if (cellVision3!=null && !unitFound) {
            int idDefending = cellVision3.getIdPersonaje();
            if (idDefending!=0) {
                if (pJugador.equals("blue")) {
                    defendingUnit=gPer.retornarPersonajeDecoradorEnemigue(idDefending);
                }
                if (pJugador.equals("red")) {
                    defendingUnit=gPer.retornarPersonajeDecorador(idDefending);
                }
            }
            if (defendingUnit!=null) {
                unitFound = true;
            }
        }
        if (cellVision4!=null && !unitFound) {
            int idDefending = cellVision4.getIdPersonaje();
            if (idDefending!=0) {
                if (pJugador.equals("blue")) {
                    defendingUnit=gPer.retornarPersonajeDecoradorEnemigue(idDefending);
                }
                if (pJugador.equals("red")) {
                    defendingUnit=gPer.retornarPersonajeDecorador(idDefending);
                }
            }
        }

        return defendingUnit;
    }
}
