package com.mygdx.game.ui.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.tl.*;
import com.mygdx.game.ui.MyGdxGame;

import java.util.ArrayList;


public class GameScreen implements Screen, InputProcessor {

    //Conexión con el orquestador
    private MyGdxGame parent;

    //screen
    private final Camera camera;
    private final Viewport viewport;
    private Vector3 temp;

    //Gestores
    private static ControllerCelda gestorCelda;
    private static ControllerObserver gestorObserver;
    private static ControllerDado gestorDado;
    private static ControllerPersonaje gestorPersonaje;
    private static ControllerProxy gestorProxy;

    //graphics
    private final SpriteBatch batch;
    private final Stage stage;
    private InputMultiplexer multiplexer;

    //world parameters
    private final int WORLD_WIDTH = 1250;
    private final int WORLD_HEIGHT = 900;

    //background
    Texture background;

    //game objects
    private final ArrayList<Celda> tablero;
    private ArrayList<PersonajeAbstracto> personajes1;
    private ArrayList<PersonajeAbstracto> personajes2;
    private Cofre cofre = new Cofre();


    //players
    String currentPlayer = "blue";

    public TextureRegion summonDie2;
    public TextureRegion summonDie;
    public TextureRegion actionDie;

    //buttons

    ImageButton btnRoll;
    ImageButton btnEndTurn;
    ImageButton btnSummon;
    ImageButton btnAtk;
    ImageButton btnSpAtk;
    ImageButton btnAddChest;

    //Labels

    Label lTimer;
    Label lTurnPlayer;
    Label chestInfanteria;
    Label chestArtilleria;
    Label chestTanque;
    Label chestAtk;
    Label chestSpAtk;
    Label comm;
    Label idCell;
    Label idPattern;

    //Texture atlas
    public final TextureAtlas diceAtlas = new TextureAtlas("dice/dice.atlas");
    public final TextureAtlas cellAtlas = new TextureAtlas("cells/cells.atlas");
    public final TextureAtlas buttonAtlas = new TextureAtlas("buttons/buttons.atlas");

    //Variables

    public int gameTime = 60;
    private static int time = 0;
    public int currentCell = 1;
    public int personajeSeleccionado = 1;
    private boolean rolled;
    private boolean notMovement;
    private boolean addedToChest=false;
    private boolean fullChest=false;

    int cantDadosArtilleria = 0;
    int cantDadosInfanteria = 0;
    int cantDadosTanque = 0;
    int cantDadosAtk = 0;
    int cantDadosSpAtk = 0;


    public GameScreen(MyGdxGame myGame) {
        parent = myGame;
        temp = new Vector3();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        // Especificar atlas de texturas

        //Atributos de Gestores
        final int numInicialCeldasNormales = 1;
        final int numInicialCeldasCastillo = 1000;
        final int lifepointsCastillo = 3;


        //Inicializamos los gestores.
        gestorCelda = new ControllerCelda(numInicialCeldasNormales, numInicialCeldasCastillo, lifepointsCastillo);
        gestorObserver = new ControllerObserver(this);
        gestorPersonaje = new ControllerPersonaje();
        gestorProxy = new ControllerProxy(gestorCelda, gestorPersonaje);
        gestorDado = new ControllerDado(this);

        //Inicializar background

        background = new Texture("backgrounds/bg.png");

        //Inicializar dados
        summonDie2 = diceAtlas.findRegion("summon");
        summonDie = diceAtlas.findRegion("summon");
        actionDie = diceAtlas.findRegion("movement");


        //Inicializar labels

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fonts/font-export.fnt"));
        labelStyle.fontColor = Color.WHITE;
        labelStyle.font = myFont;


        /* __________________________________________________________________________________________*/
        /* --------------                       Label Cronómetro                             --------*/
        /* ------------------------------------------------------------------------------------------*/

        lTimer = new Label("time", labelStyle);
        lTimer.setSize(130, 30);
        lTimer.setPosition(1100, 852);
        lTimer.setAlignment(Align.center);
        lTimer.setText(gameTime);

        /* __________________________________________________________________________________________*/
        /* --------------                      Label Turn Player                             --------*/
        /* ------------------------------------------------------------------------------------------*/

        lTurnPlayer = new Label("Player 1", labelStyle);
        lTurnPlayer.setSize(164, 30);
        lTurnPlayer.setPosition(907, 852);
        lTurnPlayer.setAlignment(Align.center);

        /* __________________________________________________________________________________________*/
        /* --------------                      Labels Chest Stats                            --------*/
        /* ------------------------------------------------------------------------------------------*/

        chestInfanteria = new Label("-1", labelStyle);
        chestInfanteria.setSize(31, 30);
        chestInfanteria.setPosition(1077, 136);
        chestInfanteria.setAlignment(Align.center);

        chestArtilleria = new Label("-1", labelStyle);
        chestArtilleria.setSize(31, 30);
        chestArtilleria.setPosition(1077, 86);
        chestArtilleria.setAlignment(Align.center);

        chestTanque = new Label("-1", labelStyle);
        chestTanque.setSize(31, 30);
        chestTanque.setPosition(1077, 40);
        chestTanque.setAlignment(Align.center);

        chestAtk = new Label("-1", labelStyle);
        chestAtk.setSize(31, 30);
        chestAtk.setPosition(1171, 125);
        chestAtk.setAlignment(Align.center);

        chestSpAtk = new Label("-1", labelStyle);
        chestSpAtk.setSize(31, 30);
        chestSpAtk.setPosition(1171, 58);
        chestSpAtk.setAlignment(Align.center);

        /* __________________________________________________________________________________________*/
        /* --------------                      Label Comm                                    --------*/
        /* ------------------------------------------------------------------------------------------*/

        comm = new Label("", labelStyle);
        comm.setSize(164, 30);
        comm.setPosition(475, 40);
        comm.setAlignment(Align.center);


        //Inicializar botones

        /* __________________________________________________________________________________________*/
        /* --------                         Botón de Roll Dados                              --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable rollUpImage =  new TextureRegionDrawable(buttonAtlas.findRegion("rollButtonUp"));
        TextureRegionDrawable rollDownImage =  new TextureRegionDrawable(buttonAtlas.findRegion("rollButtonDown"));
        ImageButton.ImageButtonStyle styleRoll = new ImageButton.ImageButtonStyle();
        styleRoll.up = rollUpImage;
        styleRoll.down = rollDownImage;
        btnRoll = new ImageButton(styleRoll);
        btnRoll.setPosition(75,40);
        btnRoll.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                if(!rolled) {
                    comm.setText("");
                    gestorDado.rodarDado();
                    rolled=true;
                }else{
                    comm.setText("Solo puede tirar los dados 1 vez por turno.");
                }
            }
        });

        /* __________________________________________________________________________________________*/
        /* -----------                         Botón de End Turn                             --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable endTurnUp =  new TextureRegionDrawable(buttonAtlas.findRegion("endTurnUp"));
        TextureRegionDrawable endTurnDown =  new TextureRegionDrawable(buttonAtlas.findRegion("endTurnDown"));
        ImageButton.ImageButtonStyle styleEnd = new ImageButton.ImageButtonStyle();
        styleEnd.up = endTurnUp;
        styleEnd.down = endTurnDown;
        btnEndTurn = new ImageButton(styleEnd);
        btnEndTurn.setPosition(1083,690);
        btnEndTurn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                comm.setText("");
                addedToChest=false;
                endTurn();
            }
        });

        /* __________________________________________________________________________________________*/
        /* -----------                         Botón de Summon                               --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable summonUp =  new TextureRegionDrawable(buttonAtlas.findRegion("summonUp"));
        TextureRegionDrawable summonDown =  new TextureRegionDrawable(buttonAtlas.findRegion("summonDown"));
        ImageButton.ImageButtonStyle styleSummon = new ImageButton.ImageButtonStyle();
        styleSummon.up = summonUp;
        styleSummon.down = summonDown;
        btnSummon = new ImageButton(styleSummon);
        btnSummon.setPosition(939,690);
        btnSummon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Accion ejecutada: Realizar convocacion."); // editar con el método correcto
            }
        });



        /* __________________________________________________________________________________________*/
        /* -----------                        Botón de Add To Chest                             --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable addUp =  new TextureRegionDrawable(buttonAtlas.findRegion("addUp"));
        final TextureRegionDrawable addDown =  new TextureRegionDrawable(buttonAtlas.findRegion("addDown"));
        ImageButton.ImageButtonStyle styleAdd = new ImageButton.ImageButtonStyle();
        styleAdd.up = addUp;
        styleAdd.down = addDown;
        btnAddChest = new ImageButton(styleAdd);
        btnAddChest.setPosition(929,122);
        btnAddChest.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                if(currentPlayer.equals("blue") && !addedToChest) {
                    if(gestorDado.addToChest(1)) {
                        summonDie = diceAtlas.findRegion("summon");
                        summonDie2 = diceAtlas.findRegion("summon");
                        if (notMovement) {
                            actionDie = diceAtlas.findRegion("movement");
                        }
                        if(!fullChest) {
                            comm.setText("Dados almacenados en el cofre.");
                        }else{
                            comm.setText("Error: Cofre lleno. Al menos 1 dado no se pudo guardar.");
                        }
                        addedToChest=true;
                    }else{
                        comm.setText("No hay dados que almacenar.");
                        addedToChest=false;
                    }
                }else{
                    if(!fullChest) {
                        comm.setText("Solo se pueden guardar dados 1 vez por turno.");
                    }
                }
            }
        });




        //Establecer objetos de juego
        tablero = gestorCelda.getCellArray();
        personajes1 = gestorPersonaje.getArrayPersonajes();
        personajes2 = gestorPersonaje.getArrayEnemigos();




        //setear la pantalla al input processor para responder a clicks

        multiplexer = new InputMultiplexer();
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);

        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);

        //add actors to stage

        stage.addActor(btnRoll);
        stage.addActor(btnEndTurn);
        stage.addActor(btnAddChest);
        stage.addActor(btnSummon);
        stage.addActor(lTimer);
        stage.addActor(lTurnPlayer);
        stage.addActor(chestInfanteria);
        stage.addActor(chestArtilleria);
        stage.addActor(chestTanque);
        stage.addActor(chestAtk);
        stage.addActor(chestSpAtk);
        stage.addActor(comm);
    }


    @Override
    public void render(float deltaTime) {
        batch.begin();

        //Dibujar background
        batch.draw(background, 0,0);

        //Dibujar Tablero
        for (Celda c:tablero) {
            c.draw(batch);
        }

        if (personajes1!=null) {
            for (PersonajeAbstracto p : personajes1) {
                p.draw(batch);
            }
        }


        if (personajes2!=null) {
            for (PersonajeAbstracto p2 : personajes2) {
                p2.draw(batch);
            }
        }
      
        //dibuja labels dinamicas
        lTimer.setText(gameTime);
        chestInfanteria.setText(cantDadosInfanteria);
        chestArtilleria.setText(cantDadosArtilleria);
        chestTanque.setText(cantDadosTanque);
        chestAtk.setText(cantDadosAtk);
        chestSpAtk.setText(cantDadosSpAtk);

        batch.draw(summonDie, 52,152);
        batch.draw(summonDie2, 136,152);
        batch.draw(actionDie, 92,87);

        //Dibujar celdas
        gestorCelda.changeColor(50, currentPlayer);

        batch.end();
        stage.act();
        stage.draw();

    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        temp.set(screenX, screenY, 0);
        camera.unproject(temp);

        for (Celda c : tablero) {
            if (c.getBoundingBox().contains(temp.x,temp.y)) {
                currentCell = c.getId();
                System.out.println("Celda seleccionada : "+currentCell);
            }
        }

        for (PersonajeAbstracto p : personajes1) {
            if (p.getRectangle().contains(temp.x,temp.y)) {
                personajeSeleccionado = p.getIdPersonaje();
                System.out.println("Personaje seleccionado : "+personajeSeleccionado);
            }
        }

        for (PersonajeAbstracto p : personajes2) {
            if (p.getRectangle().contains(temp.x,temp.y)) {
                personajeSeleccionado = p.getIdPersonaje();
                System.out.println("Personaje seleccionado : "+personajeSeleccionado);
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    public void changeTurn(){
        if(currentPlayer.equals("blue")){
            currentPlayer="red";
        }
        else if(currentPlayer.equals("red")){
            currentPlayer="blue";
            rolled=false;
        }

        if (currentPlayer.equals("blue") ) {
            lTurnPlayer.setText("Player 1");
        }else if (currentPlayer.equals("red") ) {
            lTurnPlayer.setText("Player 2");
        }
    }

    public void endTurn(){
        changeTurn();
        gestorObserver.resetTimer();
        rolled=false;
    }

    public void updateClock(int value){
        gameTime=value; //actualiza variable local para dibujar el tiempo restante del turno
    }

    public void renderDice(String rollInvo, String rollInvo2, String rollAccion){
        switch (rollInvo) {
            case "Infanteria":
                summonDie = diceAtlas.findRegion("infanteria");
                break;
            case "Artilleria":
                summonDie = diceAtlas.findRegion("artilleria");
                break;
            case "Tanque":
                summonDie = diceAtlas.findRegion("tanque");
                break;
        }

        switch(rollInvo2){
            case "Infanteria":
                summonDie2 = diceAtlas.findRegion("infanteria");
                break;
            case "Artilleria":
                summonDie2 = diceAtlas.findRegion("artilleria");
                break;
            case "Tanque":
                summonDie2 = diceAtlas.findRegion("tanque");
                break;
            default:
                summonDie2 = diceAtlas.findRegion("summon");
                break;
        }

        if (rollAccion.equals("Ataque")){
            actionDie = diceAtlas.findRegion("atk");
            notMovement=true;
        }else if (rollAccion.equals("AtaqueEspecial")){
            actionDie = diceAtlas.findRegion("spatk");
            notMovement=true;
        }else{
            notMovement=false;
            int movimiento = Integer.parseInt(rollAccion);
            for (int i=0;i<6;i++){
                if(i==movimiento){
                    actionDie = diceAtlas.findRegion(String.valueOf(i));
                }
            }
        }
        System.out.println("Roll de accion: "+rollAccion);
    }

    public void updateChest() {
        int[] dice = gestorDado.savedDice();
        cantDadosInfanteria=dice[0];
        cantDadosArtilleria=dice[1];
        cantDadosTanque=dice[2];
        cantDadosAtk=dice[3];
        cantDadosSpAtk=dice[4];
    }

    public void full() {
        fullChest=true;
    }
}


