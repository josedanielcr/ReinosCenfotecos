package com.mygdx.game.tl;

import com.mygdx.game.bl.personajes.PfabricaAbstracta.PersonajeFA;
import com.mygdx.game.bl.personajes.PfabricaConcreta.Fabrica_artilleria;
import com.mygdx.game.bl.personajes.PfabricaConcreta.Fabrica_infanteria;
import com.mygdx.game.bl.personajes.PfabricaConcreta.Fabrica_tanque;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.PproductoConcreto.Infanteria;
import com.mygdx.game.bl.personajes.componente.Personaje;
import com.mygdx.game.bl.personajes.decoradorConcreto.InfanteriaSumar3Ataque;

import java.util.ArrayList;

public class ControllerPersonaje {

    private static ArrayList<PersonajeAbstracto> personajesArr = new ArrayList();


    private String crearFabricaPersonaje(PersonajeFA personaje) throws Exception{

        //El parametro que se pasaria seria el resultado de los dados de movimiento
        int valorDado = (int) Math.floor(Math.random()*6+1);
        PersonajeAbstracto objPersonaje = personaje.crearPersonaje(valorDado);
        personajesArr.add( objPersonaje);
        return objPersonaje.obtenerInformacionPersonaje();
    }


    public String procesarOpcion(int opcion){
        String msjRespuesta = "";
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
            }
        } catch (Exception e){
            msjRespuesta = "no sirvio esta picha";
        }
        return msjRespuesta;
    }


    public String retornarPersonajes(){
        StringBuilder infoPersonajes = new StringBuilder();
        for (PersonajeAbstracto personajeAbstracto : personajesArr) {
            infoPersonajes.append(personajeAbstracto.obtenerInformacionPersonaje()).append("\n");
            ;
        }
        return infoPersonajes.toString();
    }

    /**PRUEBAAAAA**/
    public void decorarPrueba() throws Exception {
        Infanteria tmp = new Infanteria(3);
        ArrayList<Personaje> ptmp = new ArrayList<>();
        ptmp.add(tmp);
        PersonajeFA personaje;
        personaje = new Fabrica_infanteria();
        String hola = crearFabricaPersonaje(personaje);
        Personaje i = (Personaje) personajesArr.get(0);
        i = new InfanteriaSumar3Ataque(i,ptmp);
    }
}
