package com.mygdx.game.ui;

import com.mygdx.game.tl.ControllerJugadores;
import com.mygdx.game.tl.ControllerPersonaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static ControllerJugadores controllerJugadores;


    public static void main(String[] args) throws IOException {
        boolean salir = false;
        do {
            mostrarMenu();
            int opcion = leerOpcion();
            salir = ejecutar(opcion);
        } while (!salir);
    }

    private static boolean ejecutar(int opcion) {
        ControllerPersonaje controllerPersonaje = new ControllerPersonaje();
        if(opcion == 4){
           out.println( controllerPersonaje.retornarPersonajes());
        } else {
           out.println(controllerPersonaje.procesarOpcion(opcion));
        }
        if(opcion == 5){
            controllerJugadores = new ControllerJugadores();
            controllerJugadores.inicializarJugadores();
        }
        if(opcion == 6){
            System.out.println(controllerJugadores.obtenerTiempoRestanteTurno());
        }
        return false;
    }

    static void mostrarMenu() {//inicio menu
        out.println();
        out.println("1.Crear Artillero");
        out.println("2.Crear Infanteria");
        out.println("3.Crear Tanque");
        out.println("4. Imprimir personajes");
        out.println("5. verificar turnos");
        out.println("6. obtener tiempo restante del turno");
        out.println();
    }//fin menu

    static int leerOpcion() throws IOException {// inicio opcion
        out.print("Seleccione una opción: ");
        return Integer.parseInt(in.readLine());
    }
}
