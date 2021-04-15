package com.mygdx.game.tl;

import com.mygdx.game.bl.personajes.PfabricaAbstracta.PersonajeFA;
import com.mygdx.game.bl.personajes.PfabricaConcreta.Fabrica_artilleria;
import com.mygdx.game.bl.personajes.PfabricaConcreta.Fabrica_infanteria;
import com.mygdx.game.bl.personajes.PfabricaConcreta.Fabrica_tanque;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.PproductoConcreto.Infanteria;
import com.mygdx.game.bl.personajes.componente.Personaje;
import com.mygdx.game.bl.personajes.decoradorConcreto.ArtilleriaDoblePoderAtaque;
import com.mygdx.game.bl.personajes.decoradorConcreto.InfanteriaSumar3Ataque;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ControllerPersonaje {

    private static ArrayList<PersonajeAbstracto> personajesArr = new ArrayList();
    private static ArrayList<PersonajeAbstracto> personajesArrEnemigo = new ArrayList();
    private static int idPersonaje;

    private PersonajeAbstracto crearFabricaPersonaje(PersonajeFA personaje) throws Exception{//agregar parametro tipo int que seria el valor del dado

        //El parametro que se pasaria seria el resultado de los dados de movimiento
        int valorDado = (int) Math.floor(Math.random()*6+1);//seria el de arturo
        PersonajeAbstracto objPersonaje = personaje.crearPersonaje(valorDado,obtenerId());
        personajesArr.add( objPersonaje);
        System.out.println(objPersonaje.obtenerInformacionPersonaje());
        return objPersonaje;
    }

    private int obtenerId(){

        idPersonaje+=1;
        return idPersonaje;
    }

    public void crearPersonajeEnemigo() throws Exception {
        PersonajeAbstracto personajeAbstracto=  crearPersonaje(valorPersonajeRandom());
        personajesArrEnemigo.add(personajeAbstracto);


    }
    public PersonajeAbstracto crearPersonaje(int opcion){
        PersonajeFA personaje = null;
        PersonajeAbstracto personajeAbstracto=null;
        String msjRespuesta = "";
        try {
            switch (opcion){
                case 1:
                    personaje = new Fabrica_artilleria();
                   personajeAbstracto= crearFabricaPersonaje(personaje);
                    break;
                case 2:
                    personaje = new Fabrica_infanteria();
                    personajeAbstracto=crearFabricaPersonaje(personaje);
                    break;
                case 3:
                    personaje = new Fabrica_tanque();
                    personajeAbstracto=crearFabricaPersonaje(personaje);
                    break;
            }
        } catch (Exception e){
            msjRespuesta = e.toString();
        }
        return personajeAbstracto;
    }

    public int valorPersonajeRandom(){

        return  ThreadLocalRandom.current().nextInt(1,4);

    }


    public String procesarOpcion(int opcion){
        PersonajeAbstracto msjRespuesta = null;
        PersonajeFA personaje;

        try {
            switch (opcion){
                case 1:
                    personaje = new Fabrica_artilleria();
                    msjRespuesta = crearFabricaPersonaje(personaje);
                    break;
                case 2:
                    personaje = new Fabrica_infanteria();
                    msjRespuesta = crearFabricaPersonaje(personaje);
                    break;
                case 3:
                    personaje = new Fabrica_tanque();
                    msjRespuesta = crearFabricaPersonaje(personaje);
                    break;

                case 8:
                    crearPersonajeEnemigo();
                    System.out.println(retornarPersonajesEnemigos());
                    break;
            }
        } catch (Exception e){
             e.toString();
        }
        return "";
    }


    public String retornarPersonajes(){
        StringBuilder infoPersonajes = new StringBuilder();
        for (PersonajeAbstracto personajeAbstracto : personajesArr) {
            infoPersonajes.append(personajeAbstracto.obtenerInformacionPersonaje()).append("\n");
            ;
        }
        return infoPersonajes.toString();
    }
    public String retornarPersonajesEnemigos(){
        StringBuilder infoPersonajes = new StringBuilder();
        for (PersonajeAbstracto personajeAbstracto : personajesArrEnemigo) {
            infoPersonajes.append(personajeAbstracto.obtenerInformacionPersonaje()).append("\n");
            ;
        }
        return infoPersonajes.toString();
    }

    /**MÉTODOS DEL DECORADOR**/



    public void decorarPrueba(int id) throws Exception {
        PersonajeAbstracto personajeAbstracto;
        for (int i=0; i<personajesArr.size();i++) {
            if(personajesArr.get(i).getIdPersonaje()==id){
               personajeAbstracto= personajesArr.get(i);
               personajeAbstracto=  new ArtilleriaDoblePoderAtaque((Personaje) personajeAbstracto);
               personajesArr.set(i,personajeAbstracto);

            }

        }
        System.out.println(personajesArr.get(0).obtenerInformacionPersonaje());

    }





}
