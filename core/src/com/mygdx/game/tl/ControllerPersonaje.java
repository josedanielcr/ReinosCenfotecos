package com.mygdx.game.tl;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.PfabricaAbstracta.PersonajeFA;
import com.mygdx.game.bl.personajes.PfabricaConcreta.Fabrica_artilleria;
import com.mygdx.game.bl.personajes.PfabricaConcreta.Fabrica_infanteria;
import com.mygdx.game.bl.personajes.PfabricaConcreta.Fabrica_tanque;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.componente.Personaje;
import com.mygdx.game.bl.personajes.decoradorConcreto.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ControllerPersonaje {

    private static ArrayList<PersonajeAbstracto> personajesArr = new ArrayList();
    private static ArrayList<PersonajeAbstracto> personajesArrEnemigo = new ArrayList();
    private static int idPersonaje;
    private int lastEnemySummonId;


    //controller real
    public PersonajeAbstracto crearPersonaje(int id, int tipoPersonaje, int tipo, String jugadorActivo, Rectangle boundingBox){

        PersonajeAbstracto personajeDeRetorno = null;
        PersonajeFA personaje;
        switch (tipoPersonaje){
            case 1:
                personaje = new Fabrica_infanteria();
                if(tipo==1){

                    personajeDeRetorno = crearFabricaPersonaje(id, personaje,  1,1, jugadorActivo, boundingBox);
                } else{

                    personajeDeRetorno = crearFabricaPersonaje(id, personaje, 1,2, jugadorActivo, boundingBox);

                }


                break;
            case 2:
                personaje = new Fabrica_artilleria();
                if(tipo==1)personajeDeRetorno = crearFabricaPersonaje(id, personaje, 2,1, jugadorActivo, boundingBox);
                else personajeDeRetorno = crearFabricaPersonaje(id, personaje,  2,2, jugadorActivo, boundingBox);
                break;
            case 3:
                personaje = new Fabrica_tanque();
                if(tipo==1)personajeDeRetorno = crearFabricaPersonaje(id, personaje,  3,1, jugadorActivo, boundingBox);
                else personajeDeRetorno = crearFabricaPersonaje(id, personaje,  3,2, jugadorActivo, boundingBox);
                break;
        }
        return personajeDeRetorno;
    }


    private PersonajeAbstracto crearFabricaPersonaje(int id, PersonajeFA personaje, int tipoPersonaje, int tipo, String jugadorActivo, Rectangle boundingBox) {//tipo es para ver si es enemigue o no
        PersonajeAbstracto objPersonaje = personaje.crearPersonaje(id, retornarAtaqueEspecial(tipoPersonaje),jugadorActivo, boundingBox);
        if(tipo==1) {
            personajesArr.add(objPersonaje);
        }
        if(tipo==2){
            personajesArrEnemigo.add(objPersonaje);
        }
        return objPersonaje;

    }


    public void crearPersonajeEnemigo(int id, Rectangle summonArea) {
        int random = valorPersonajeRandom();
        int tipoPersonaje;
        if (random<=13) {
            tipoPersonaje = 1;
        }
        else if (random<=19) {
            tipoPersonaje = 2;
        }
        else {
            tipoPersonaje = 3;
        }
        crearPersonaje(id, tipoPersonaje,2,"red",summonArea);
    }

    //metodos de impresion
    public String retornarPersonajes(){
        StringBuilder infoPersonajes = new StringBuilder();
        for (PersonajeAbstracto personajeAbstracto : personajesArr) {
            infoPersonajes.append(personajeAbstracto.obtenerInformacionPersonaje()).append("\n");

        }
        return infoPersonajes.toString();
    }

    public String retornarPersonajesEnemigos(){
        StringBuilder infoPersonajes = new StringBuilder();
        for (PersonajeAbstracto personajeAbstracto : personajesArrEnemigo) {
            infoPersonajes.append(personajeAbstracto.obtenerInformacionPersonaje()).append("\n");
        }
        return infoPersonajes.toString();
    }


    //decorador principal
    public ArrayList<PersonajeAbstracto> aplicarAtaqueEspecial(int idPropietarie){
        int [] idExternos = obtenerIdActuales(idPropietarie);
        String ataque = retornarAtaqueEpropietarie(idPropietarie);
        ArrayList<PersonajeAbstracto> personajeDecorados = new ArrayList<>();
        switch (ataque) {
            case "healer1":
                personajeDecorados.add(decorarHealer1(obtenerIdRandom(idPropietarie)));
                break;
            case "sumar3Ataque":
                personajeDecorados.add(decorarSumar3Ataque(obtenerIdRandom(idPropietarie)));
                break;
            case "sumar3Defensa":
                personajeDecorados.add(decorarSumar3Defensa(obtenerIdRandom(idPropietarie)));
                break;
                //este es el 5 casillas de distancia
            case "bajarDefensa":
                personajeDecorados.add(decorarBajarDefensa(obtenerIdRandom(idPropietarie)));
                break;
            case "healer2":
                personajeDecorados = decorarHealer2(idExternos);
                break;
            case "ataqueDosCasillas":
                personajeDecorados.add(decorarAtaqueDosCasillas(idPropietarie));
                break;
            case "doblePoderAtaque":
                personajeDecorados.add(decorarDoblePoderA(idPropietarie));
                break;
            case "doblePoderDefensa":
                personajeDecorados.add(decorarDobleDefensa(idPropietarie));
                break;
            case "unaVidaDobleM":
                personajeDecorados.add(decorarUnaVidaDobleM(idPropietarie));
                break;
            case "ataqueBomba":
                decorarAtaqueBomba(idPropietarie);
                break;
            case "proteccionAliade":
                decorarProteccionAliade(idPropietarie, idExternos);
                break;
            //este es el 5 casillas de distancia
            case "bajar2Defensa":
                personajeDecorados.add(decorarBajar2Defensa(obtenerIdRandom(idPropietarie)));
                break;
        }
        return personajeDecorados;
    }

    //decoradores especificos
    private PersonajeAbstracto decorarHealer1(int idExterno) {
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecorador(idExterno);
        int indexPersonaje = obtenerIndexPersonaje(personajeAbstracto);
        personajeAbstracto= new PersonajeMas1DeVida((Personaje) personajeAbstracto);
        personajesArr.set(indexPersonaje,personajeAbstracto);
        return personajeAbstracto;
    }

    private PersonajeAbstracto decorarSumar3Ataque(int idExterno) {
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecorador(idExterno);
        int indexPersonaje = obtenerIndexPersonaje(personajeAbstracto);
        personajeAbstracto= new InfanteriaSumar3Ataque((Personaje) personajeAbstracto);
        personajesArr.set(indexPersonaje,personajeAbstracto);
        return personajeAbstracto;
    }

    private PersonajeAbstracto decorarSumar3Defensa(int idExterno) {
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecorador(idExterno);
        int indexPersonaje = obtenerIndexPersonaje(personajeAbstracto);
        personajeAbstracto= new InfanteriaSumar3Defensa((Personaje) personajeAbstracto);
        personajesArr.set(indexPersonaje,personajeAbstracto);
        return personajeAbstracto;
    }

    private PersonajeAbstracto decorarBajarDefensa(int idExterno) {
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecoradorEnemigue(idExterno);
        int indexPersonaje = obtenerIndexPersonajeEnemigue(personajeAbstracto);
        personajeAbstracto= new InfanteriaBajarDefensa((Personaje) personajeAbstracto);
        personajesArr.set(indexPersonaje,personajeAbstracto);
        return personajeAbstracto;
    }

    private PersonajeAbstracto decorarAtaqueDosCasillas(int idPropietarie) {
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecorador(idPropietarie);
        int indexPersonaje = obtenerIndexPersonaje(personajeAbstracto);
        personajeAbstracto= new AtaqueAdosCasillas((Personaje) personajeAbstracto);
        personajesArr.set(indexPersonaje,personajeAbstracto);
        return personajeAbstracto;
    }

    private PersonajeAbstracto decorarDoblePoderA(int idPropietarie) {
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecorador(idPropietarie);
        int indexPersonaje = obtenerIndexPersonaje(personajeAbstracto);
        personajeAbstracto= new ArtilleriaDoblePoderAtaque((Personaje) personajeAbstracto);
        personajesArr.set(indexPersonaje,personajeAbstracto);
        return personajeAbstracto;
    }

    private PersonajeAbstracto decorarDobleDefensa(int idPropietarie) {
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecorador(idPropietarie);
        int indexPersonaje = obtenerIndexPersonaje(personajeAbstracto);
        personajeAbstracto= new ArtilleriaDoblePoderDefensa((Personaje) personajeAbstracto);
        personajesArr.set(indexPersonaje,personajeAbstracto);
        return personajeAbstracto;
    }

    private PersonajeAbstracto decorarUnaVidaDobleM(int idPropietarie) {
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecorador(idPropietarie);
        int indexPersonaje = obtenerIndexPersonaje(personajeAbstracto);
        personajeAbstracto= new TanqueUnaVidaPorDosMovimientos((Personaje) personajeAbstracto);
        personajesArr.set(indexPersonaje,personajeAbstracto);
        return personajeAbstracto;
    }

    private PersonajeAbstracto decorarBajar2Defensa(int idExterno) {
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecoradorEnemigue(idExterno);
        int indexPersonaje = obtenerIndexPersonaje(personajeAbstracto);
        personajeAbstracto= new ArtilleriaBajarDefensa((Personaje) personajeAbstracto);
        personajesArr.set(indexPersonaje,personajeAbstracto);
        return personajeAbstracto;
    }

    private ArrayList<PersonajeAbstracto> decorarHealer2(int[] idExternos) {
        ArrayList<PersonajeAbstracto> personajes = retornarPersonajesDecorador(idExternos, 1);
        int cont = 0;
        for(PersonajeAbstracto p: personajes){
            int indexPersonaje = obtenerIndexPersonaje(personajes.get(cont));
            p = new PersonajeMas1DeVida((Personaje) personajes.get(cont));
            personajesArr.set(indexPersonaje,p);
            personajes.set(cont,p);
            cont++;
        }
        return personajes;
    }

    private void decorarAtaqueBomba(int idPropietarie) {
        eliminarPersonaje(idPropietarie);
        ArrayList<PersonajeAbstracto> personajes = enemiguesRandom();
        eliminarEnemigues(personajes);
    }

    private ArrayList<PersonajeAbstracto> enemiguesRandom() {
        int divisor = 0;
        if(personajesArrEnemigo.size()<8){
            divisor = 2;
        } else{
            divisor = 4;
        }
        ArrayList<PersonajeAbstracto> pAbstract = new ArrayList<>();
        int cant = (int) Math.floor(personajesArrEnemigo.size()/divisor);
        int cont=0;
        for(PersonajeAbstracto p: personajesArrEnemigo){
            if(cont==cant){
                break;
            }
            pAbstract.add(p);
            cont++;
        }
        return pAbstract;
    }


    private void decorarProteccionAliade(int idPropietarie, int[] idExternos) {
        PersonajeAbstracto personaje = retornarPersonajeDecorador(idPropietarie);
        personaje = new TanqueProtector((Personaje) personaje);
        int indexPersonaje1 = obtenerIndexPersonaje(personaje);
        personajesArr.set(indexPersonaje1,personaje);
        ArrayList<PersonajeAbstracto> personajes = retornarPersonajesDecorador(idExternos, 1);
        for(PersonajeAbstracto p: personajes){
            int indexPersonaje = obtenerIndexPersonaje(p);
            p = new PersonajeProtegido((Personaje) p);
            personajesArr.set(indexPersonaje,p);
        }
    }

    /**/

    //metodos miscelanios
    private void eliminarPersonaje(int idPropietarie){
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecorador(idPropietarie);
        int indexPersonaje = obtenerIndexPersonaje(personajeAbstracto);
        personajesArr.remove(indexPersonaje);
    }

    private void eliminarEnemigues(ArrayList<PersonajeAbstracto> enemigues){
        for(int i=0; i<personajesArrEnemigo.size(); i++){
            for (PersonajeAbstracto enemigue : enemigues) {
                if (personajesArrEnemigo.get(i) == enemigue) {
                    personajesArrEnemigo.remove(enemigue);
                }
            }
        }
    }


    private int[] obtenerIdActuales(int idPropietarie){
        int [] id = new int[personajesArr.size()];
        int cont=0;
        for(PersonajeAbstracto p: personajesArr){
            int idTmp = p.getIdPersonaje();
            if(idTmp != idPropietarie){
                id[cont] = idTmp;
            }
            cont++;
        }
        return id;
    }

    public int obtenerIndexPersonaje(PersonajeAbstracto p){
        for(int i = 0; i<personajesArr.size(); i++){
            if(personajesArr.get(i) == p){
                return i;
            }
        }
        return 0;
    }
    public int obtenerIndexPersonajeEnemigue(PersonajeAbstracto p){
        for(int i = 0; i<personajesArrEnemigo.size(); i++){
            if(personajesArrEnemigo.get(i) == p){
                return i;
            }
        }
        return 0;
    }

    public int obtenerId(){
        idPersonaje+=1;
        return idPersonaje;
    }

    public int obtenerIdRandom(int idPropietarie){
        for (PersonajeAbstracto personajeAbstracto : personajesArr) {
            int id = personajeAbstracto.getIdPersonaje();
            if (id != idPropietarie) {
                return id;
            }
        }
        return 0;
    }

    public int valorPersonajeRandom(){
        return  ThreadLocalRandom.current().nextInt(1,21);
    }

    private String retornarAtaqueEspecial(int tipoPersonaje){
        return ataquesDisponibles(tipoPersonaje);
    }

    private String ataquesDisponibles(int tipoPersonaje){
        String [] ataques= null;
        switch (tipoPersonaje){
            case 1:
                ataques = new String[]{"healer1", "sumar3Ataque", "sumar3Defensa", "bajarDefensa"};
                int r1 = (int) (Math.random() * ataques.length);
                return ataques[r1];
            case 2:
                ataques = new String[]{"ataqueDosCasillas", "healer2", "doblePoderAtaque", "doblePoderDefensa", "bajar2Defensa"};
                int r2 = (int) (Math.random() * ataques.length);
                return ataques[r2];
            case 3:
                ataques = new String[] {"unaVidaDobleM", "ataqueBomba", "proteccionAliade"};
                int r3 = (int) (Math.random() * ataques.length);
                return ataques[r3];
        }
        return null;
    }

    public void eliminarAtaqueSP(int idPropietarie){
        for(PersonajeAbstracto p: personajesArr){
            if(p.getIdPersonaje() == idPropietarie){
                p.setAtaqueEspecial("");
            }
        }
    }

    private int retornarMovimientoPersonaje(int tipoPersonaje) {
        switch (tipoPersonaje){
            case 1:
                return ThreadLocalRandom.current().nextInt(1,7);
            case 2:
                return ThreadLocalRandom.current().nextInt(1,5);
            case 3:
                return ThreadLocalRandom.current().nextInt(1,3);
        }
        return 0;
    }

    private String retornarAtaqueEpropietarie(int id){
        String ataque = "";
        for(PersonajeAbstracto p: personajesArr){
            if(p.getIdPersonaje() == id){
                ataque = p.getAtaqueEspecial();
            }
        }
        return ataque;
    }

    public PersonajeAbstracto retornarPersonajeDecorador(int idPersonajes){
        for(PersonajeAbstracto p: personajesArr){
            if(p.getIdPersonaje() == idPersonajes){
                return p;
            }
        }
        return null;
    }


    public PersonajeAbstracto retornarPersonajeDecoradorEnemigue(int idPersonajes){
        for(PersonajeAbstracto p: personajesArrEnemigo){
            if(p.getIdPersonaje() == idPersonajes){
                return p;
            }
        }
        return null;
    }


    private ArrayList<PersonajeAbstracto> retornarPersonajesDecorador(int [] idPersonajes, int tipo){
        ArrayList<PersonajeAbstracto> personajeAbstractos = new ArrayList<>();
        for (int personaje : idPersonajes) {
            if(tipo==1){
                for (PersonajeAbstracto p : personajesArr) {
                    if (p.getIdPersonaje() == personaje) {
                        personajeAbstractos.add(p);
                    }
                }
            } else {
                for (PersonajeAbstracto p : personajesArrEnemigo) {
                    if (p.getIdPersonaje() == personaje) {
                        personajeAbstractos.add(p);
                    }
                }
            }
        }
        return personajeAbstractos;
    }

    public ArrayList<PersonajeAbstracto> getArrayPersonajes() {
        return personajesArr;
    }

    public ArrayList<PersonajeAbstracto> getArrayEnemigos() {
        return personajesArrEnemigo;
    }

    //FUNCIONES DE ATAQUE

    public String resolverAtaqueDefensa(int idPersonajeAtacado, int nuevaDefensa){
        int arrayAbuscar=1;
        PersonajeAbstracto pAtacado= null;
        pAtacado= retornarPersonajeDecoradorEnemigue(idPersonajeAtacado);
        if(pAtacado==null){
            pAtacado= retornarPersonajeDecorador(idPersonajeAtacado);
            arrayAbuscar=2;
        }

        pAtacado.setDefensa(nuevaDefensa);
        int indexAtacado;
        switch (arrayAbuscar){

            case 1:
                indexAtacado= obtenerIndexPersonajeEnemigue(pAtacado);
                personajesArrEnemigo.set(indexAtacado,pAtacado);
                break;
            case 2:
                indexAtacado= obtenerIndexPersonaje(pAtacado);
                personajesArr.set(indexAtacado,pAtacado);
                break;
        }
        return "Battle unit was attacked...its defense was lowered.";
    }

    public String matarPersonaje(int idPersonajeAtacado){
        int arrayAbuscar=1;
        PersonajeAbstracto pAtacado= null;
        pAtacado= retornarPersonajeDecoradorEnemigue(idPersonajeAtacado);
        if(pAtacado==null){
            pAtacado= retornarPersonajeDecorador(idPersonajeAtacado);
            arrayAbuscar=2;
        }
        switch (arrayAbuscar){
            case 1:
                personajesArrEnemigo.remove(pAtacado);
                break;
            case 2:
                personajesArr.remove(pAtacado);
                break;
        }
        return "Battle unit was destroyed.";
    }

    public String resolverAtaqueVida(int idPersonajeAtacado, int nuevaVida){
        int arrayAbuscar=1;
        PersonajeAbstracto pAtacado= null;
        pAtacado= retornarPersonajeDecoradorEnemigue(idPersonajeAtacado);
        if(pAtacado==null){
            pAtacado= retornarPersonajeDecorador(idPersonajeAtacado);
            arrayAbuscar=2;
        }

        pAtacado.setVida(nuevaVida);
        pAtacado.setDefensa(0);
        int indexAtacado;
        switch (arrayAbuscar){

            case 1:
                indexAtacado= obtenerIndexPersonajeEnemigue(pAtacado);
                personajesArrEnemigo.set(indexAtacado,pAtacado);
                break;
            case 2:
                indexAtacado= obtenerIndexPersonaje(pAtacado);
                personajesArr.set(indexAtacado,pAtacado);
                break;
        }
        return "Battle unit was attacked...life points reduced.";
    }

    public void setLastEnemySummonId(int pid){
        this.lastEnemySummonId=pid;
        System.out.println("lastEnemySummonId: "+pid);
    }

    public int getLastEnemySummonId() {
        return this.lastEnemySummonId;
    }
}
