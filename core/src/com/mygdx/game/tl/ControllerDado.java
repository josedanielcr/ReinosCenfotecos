package com.mygdx.game.tl;

import com.mygdx.game.bl.dados.creadorConcreto.FabricaDados;
import com.mygdx.game.bl.dados.producto.Dado;

public class ControllerDado {

    private static final String[] rollsInvocacion = new String[2];
    private static String rollsAccion="";
    private static  int rollMovimiento=0;

    public static void rodarDado() {
        crearDado(1);
        crearDado(2);
        System.out.println(invocacion());
        System.out.println(accion());
    }

    private static void crearDado(int pOpcion){
        Dado dado;
        FabricaDados fabrica = new FabricaDados();

        switch (pOpcion) {
            case 1:
                dado = fabrica.crearDado(1);
                rollsInvocacion[0]=dado.rollDice();
                rollsInvocacion[1]=dado.rollDice();
                break;
            case 2:
                dado = fabrica.crearDado(2);
                rollsAccion=dado.rollDice();
                break;
            case 3:
                dado = fabrica.crearDado(3);
                rollMovimiento=Integer.parseInt(dado.rollDice());
                break;
            default:
                System.out.println("No sirvió");
        }
    }

    private static int movimiento() {
        return rollMovimiento;
    }

    private static String invocacion() {
        String msj = "Rolls de invocación:\n";
        for (String rol:rollsInvocacion) {
            msj=msj+rol+"\n";
        }

        return msj;
    }

    private static String accion() {
        String msj = "Roll de acción:\n";
        msj=msj+rollsAccion;

        return msj;
    }

}
