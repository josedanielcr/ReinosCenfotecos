package com.mygdx.game.tl;

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
    private static int contGeneral=0;

    //controller real
    public PersonajeAbstracto crearPersonaje(int tipoPersonaje, int dadoCantMoviendo, int tipo, String personajeActivo){

        PersonajeAbstracto personajeDeRetorno = null;
        PersonajeFA personaje;
        try {
            switch (tipoPersonaje){
                case 1:
                    personaje = new Fabrica_infanteria();
                    if(tipo==1){

                        personajeDeRetorno = crearFabricaPersonaje(personaje,  1,1, personajeActivo);
                    } else{

                        personajeDeRetorno = crearFabricaPersonaje(personaje, 1,2, personajeActivo);

                    }


                    break;
                case 2:
                    personaje = new Fabrica_artilleria();
                    if(tipo==1)personajeDeRetorno = crearFabricaPersonaje(personaje, 2,1, personajeActivo);
                    else personajeDeRetorno = crearFabricaPersonaje(personaje,  2,2, personajeActivo);
                    break;
                case 3:
                    personaje = new Fabrica_tanque();
                    if(tipo==1)personajeDeRetorno = crearFabricaPersonaje(personaje,  3,1, personajeActivo);
                    else personajeDeRetorno = crearFabricaPersonaje(personaje,  3,2, personajeActivo);
                    break;
            }
        } catch (Exception e){
            e.toString();
        }
        return personajeDeRetorno;
    }


    private PersonajeAbstracto crearFabricaPersonaje(PersonajeFA personaje, int tipoPersonaje, int tipo, String personajeActivo) throws Exception{//tipo es para ver si es enemigue o no

        PersonajeAbstracto objPersonaje = personaje.crearPersonaje(obtenerId(), retornarAtaqueEspecial(tipoPersonaje),personajeActivo );
        if(tipo==1) {
            personajesArr.add(objPersonaje);
        }
        if(tipo==2){
            personajesArrEnemigo.add(objPersonaje);
        }
        return objPersonaje;

    }




    public void crearPersonajeEnemigo() throws Exception {
        int tipoPersonaje = valorPersonajeRandom();
        int cantMovimiento = retornarMovimientoPersonaje(tipoPersonaje);
        PersonajeAbstracto personajeAbstracto= crearPersonaje(cantMovimiento,tipoPersonaje,2, "red");
        //personajesArrEnemigo.add(personajeAbstracto);
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
    public ArrayList<PersonajeAbstracto> aplicarAtaqueEspecial(int idPropietarie, int[] idExternos){

        String ataque = retornarAtaqueEpropietarie(idPropietarie);
        ArrayList<PersonajeAbstracto> personajeDecorados = new ArrayList<>();
        switch (ataque){
            case "healer1":
                personajeDecorados.add(decorarHealer1(idExternos[0]));
                break;
            case "sumar3Ataque":
                personajeDecorados.add(decorarSumar3Ataque(idExternos[0]));
                break;
            case "sumar3Defensa":
                personajeDecorados.add(decorarSumar3Defensa(idExternos[0]));
                break;
            case "bajarDefensa":
                personajeDecorados.add(decorarBajarDefensa(idExternos[0]));
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
                decorarAtaqueBomba(idPropietarie,idExternos);
                break;
            case "proteccionAliade":
                decorarProteccionAliade(idPropietarie,idExternos);
                break;
            case "bajar2Defensa":
                personajeDecorados.add(decorarBajar2Defensa(idExternos[0]));
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
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecorador(idExterno);
        int indexPersonaje = obtenerIndexPersonaje(personajeAbstracto);
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
        PersonajeAbstracto personajeAbstracto = retornarPersonajeDecorador(idExterno);
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

    private void decorarAtaqueBomba(int idPropietarie, int[] idExternos) {
        eliminarPersonaje(idPropietarie);
        ArrayList<PersonajeAbstracto> personajes = retornarPersonajesDecorador(idExternos, 2);
        eliminarEnemigues(personajes);
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

    private int obtenerIndexPersonaje(PersonajeAbstracto p){
        for(int i = 0; i<personajesArr.size(); i++){
            if(personajesArr.get(i) == p){
                return i;
            }
        }
        return 0;
    }

    private int obtenerId(){
        idPersonaje+=1;
        return idPersonaje;
    }

    public int valorPersonajeRandom(){
        return  ThreadLocalRandom.current().nextInt(1,4);
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

    private PersonajeAbstracto retornarPersonajeDecorador(int idPersonajes){
        for(PersonajeAbstracto p: personajesArr){
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



}


//    /*MÉTODOS DEL DECORADOR*/
//    public void decorarPrueba(int id) throws Exception {
//        PersonajeAbstracto personajeAbstracto;
//        for (int i=0; i<personajesArr.size();i++) {
//            if(personajesArr.get(i).getIdPersonaje()==id){
//               personajeAbstracto= personajesArr.get(i);
//               personajeAbstracto=  new ArtilleriaDoblePoderAtaque((Personaje) personajeAbstracto);
//               personajesArr.set(i,personajeAbstracto);
//            }
//        }
//        System.out.println(personajesArr.get(0).obtenerInformacionPersonaje());
//    }


//  case 8:
//                    crearPersonajeEnemigo();
//                    System.out.println(retornarPersonajesEnemigos());
//                    break;