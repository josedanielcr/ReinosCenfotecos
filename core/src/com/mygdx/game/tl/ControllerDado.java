package com.mygdx.game.tl;

import com.mygdx.game.bl.cofre.Cofre;
import com.mygdx.game.bl.dados.creadorConcreto.FabricaDados;
import com.mygdx.game.bl.dados.producto.Dado;
import com.mygdx.game.ui.Screens.GameScreen;

import java.util.ArrayList;

public class ControllerDado {

    private String[] rollsInvocacion;
    private String rollAccion ="";
    private String rollMovimiento="";
    private GameScreen gameScreen;

    //para probar
    private static Cofre cofreJugador;

    public ControllerDado(GameScreen gs) {
        cofreJugador = new Cofre();
        this.gameScreen=gs;
    }

    public void rodarDado() {
        crearDado(1);
        crearDado(2);

        if(rollAccion.equals("Movimiento")){
            crearDado(3);
            gameScreen.renderDice(rollsInvocacion[0],rollsInvocacion[1], getMovimiento());
            gameScreen.setRolledDice(rollsInvocacion[0],rollsInvocacion[1],null);
        }else {
            gameScreen.renderDice(rollsInvocacion[0], rollsInvocacion[1], rollAccion);
            gameScreen.setRolledDice(rollsInvocacion[0], rollsInvocacion[1], rollAccion);
        }
    }

    private void crearDado(int pOpcion){
        Dado dado;
        FabricaDados fabrica = new FabricaDados();

        switch (pOpcion) {
            case 1:
                rollsInvocacion = new String[2];
                dado = fabrica.crearDado(1);
                rollsInvocacion[0]=dado.rollDie();
                rollsInvocacion[1]=dado.rollDie();
                break;
            case 2:
                dado = fabrica.crearDado(2);
                rollAccion =dado.rollDie();
                break;
            case 3:
                dado = fabrica.crearDado(3);
                rollMovimiento=dado.rollDie();
                break;
            default:
                System.out.println("No sirvió");
        }
    }

    private String getMovimiento() {
        return rollMovimiento;
    }

    public boolean addToChest(int ptipo){
        if(guardarRoll(ptipo)) {
            gameScreen.updateChest();
            return true;
        }else{
            return false;
        }
    }

    private boolean guardarRoll(int ptipo) {
        if(cofreJugador.guardarRoll(ptipo)) {
            return true;
        }else{
            gameScreen.full();
            return false;
        }
    }

    public int[] savedDice() {
        int[] dice = new int[5];
        dice[0]=cofreJugador.getDice(0);
        dice[1]=cofreJugador.getDice(1);
        dice[2]=cofreJugador.getDice(2);
        dice[3]=cofreJugador.getDice(3);
        dice[4]=cofreJugador.getDice(4);
        return dice;
    }

    public void summon(int ptipo) {
        int discount=0;
        switch (ptipo) {
            case 1:
                for(int i=0;i<2;i++) {
                    if(rollsInvocacion[i].equals("Infanteria")){
                        discount++;
                    }
                }
                for(int i=0;i<(2-discount);i++){
                    cofreJugador.removeSummonDice(1);
                }
                break;
            case 2:
                for(int i=0;i<2;i++) {
                    if(rollsInvocacion[i].equals("Artilleria")){
                        discount++;
                    }
                }
                for(int i=0;i<3-discount;i++){
                    cofreJugador.removeSummonDice(2);
                }
                break;
            case 3:
                for(int i=0;i<2;i++) {
                    if(rollsInvocacion[i].equals("Tanque")){
                        discount++;
                    }
                }
                for(int i=0;i<4-discount;i++){
                    cofreJugador.removeSummonDice(3);
                }
                break;
        }

    }

    public Cofre getCofreJugador(){
        return cofreJugador;
    }
}
