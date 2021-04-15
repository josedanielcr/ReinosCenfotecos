package com.mygdx.game.ui;

import com.mygdx.game.tl.ControllerJugadores;
import com.mygdx.game.tl.ControllerPersonaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

public class Main {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static ControllerJugadores controllerJugadores;


    public static void main(String[] args) throws Exception {
        boolean salir = false;
        do {
            mostrarMenu();
            int opcion = leerOpcion();
            salir = ejecutar(opcion);
        } while (!salir);
    }

    private static boolean ejecutar(int opcion) throws Exception {
        ControllerPersonaje controllerPersonaje = new ControllerPersonaje();
        if(opcion == 1){
            controllerPersonaje.crearPersonaje(1,3);
            System.out.println(controllerPersonaje.retornarPersonajes());
        }
        if(opcion == 2){
           controllerPersonaje.crearPersonaje(2,5);
           System.out.println(controllerPersonaje.retornarPersonajes());
        }
        if(opcion == 3){
            controllerPersonaje.crearPersonaje(3,2);
            System.out.println(controllerPersonaje.retornarPersonajes());
        }
        if(opcion == 4){
            controllerPersonaje.crearPersonajeEnemigo();
            System.out.println(controllerPersonaje.retornarPersonajesEnemigos());
        }
        if(opcion == 5){
           int [] ids = {2};
           int id = 1;
           controllerPersonaje.aplicarAtaqueEspecial(id,ids);
            System.out.println(controllerPersonaje.retornarPersonajes());
        }
        if(opcion == 6){
            System.out.println(controllerJugadores.obtenerTiempoRestanteTurno());
        }

        if(opcion == 7){
            //controllerPersonaje.decorarPrueba(1);
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
