package com.mygdx.game.ui;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.tl.ControllerPersonaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

public class Main {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;


    public static void main(String[] args) throws Exception {
        boolean salir = false;
        do {
            mostrarMenu();
            int opcion = leerOpcion();
            salir = ejecutar(opcion);
        } while (!salir);
    }

    private static boolean ejecutar(int opcion) {
        ControllerPersonaje controllerPersonaje = new ControllerPersonaje();
        if(opcion == 1){
            controllerPersonaje.crearPersonaje(controllerPersonaje.obtenerId(),1, 1,"blue",null);
            System.out.println(controllerPersonaje.retornarPersonajes());
        }
        if(opcion == 2){
            controllerPersonaje.crearPersonaje(controllerPersonaje.obtenerId(),2, 1,"blue",null);
            System.out.println(controllerPersonaje.retornarPersonajes());
        }
        if(opcion == 3){
            controllerPersonaje.crearPersonaje(controllerPersonaje.obtenerId(),3, 1,"blue",null);
            System.out.println(controllerPersonaje.retornarPersonajes());
        }
        if(opcion == 4){
            controllerPersonaje.crearPersonajeEnemigo(controllerPersonaje.obtenerId(),null);
            System.out.println(controllerPersonaje.retornarPersonajesEnemigos());
        }
        if(opcion == 5){

            int idAtacante = 1;
            int idAtacado = 2;
            System.out.println(controllerPersonaje.ataqueRealizado(1,2,"red"));
            //controllerPersonaje.aplicarAtaqueEspecial(id);
           // System.out.println(controllerPersonaje.retornarPersonajes());
        }

        if(opcion == 7){
            //controllerPersonaje.decorarPrueba(1);
        }
        return false;
    }

    static void mostrarMenu() {//inicio menu
        out.println();
        out.println("1.Crear Infantaria");
        out.println("2.Crear Artilleria");
        out.println("3.Crear Tanque");
        out.println("4.Crear personajes enemigues");
        out.println("5.Aplicar Decorador");
       // out.println("6. obtener tiempo restante del turno");
        out.println();
    }//fin menu

    static int leerOpcion() throws IOException {// inicio opcion
        out.print("Seleccione una opción: ");
        return Integer.parseInt(in.readLine());
    }
}