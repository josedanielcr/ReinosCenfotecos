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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.bl.cofre.Cofre;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerDado;
import com.mygdx.game.tl.ControllerObserver;
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
    private Cofre cofre = new Cofre();

    //players

    String turnPlayer = "blue";
    String colorPlayer1 = "blue";
    String colorPlayer2 = "red";

    //dice

    int actionDieResult = 1;
    int summonDieResult = 1;
    int movementDieResult = 1;

    public TextureRegion actionDie;
    public TextureRegion summonDie;
    public TextureRegion movementDie;

    //buttons

    ImageButton btnRoll;
    ImageButton btnEndTurn;
    ImageButton btnSummon;
    ImageButton btnAtk;
    ImageButton btnSpAtk;
    ImageButton btnAddChest;

    //Labels

    Label lTimer;

    //Texture atlas
    public final TextureAtlas diceAtlas = new TextureAtlas("dice/dice.atlas");
    public final TextureAtlas cellAtlas = new TextureAtlas("cells/cells.atlas");
    public final TextureAtlas buttonAtlas = new TextureAtlas("buttons/buttons.atlas");

    //variables

    private String turn;
    private int gameTime = 60;
    private static int time = 0;
    public int currentCell = 1;
    public String currentPlayer = "blue";


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
        turn = "jugador"; //TODO que aqui empiece en neutro o algo, y una vez que le de start game empiece a contar
        gameTime=0;

        //Inicializamos los gestores.
        gestorCelda = new ControllerCelda(numInicialCeldasNormales, numInicialCeldasCastillo, lifepointsCastillo);
        gestorObserver = new ControllerObserver(this);
        gestorDado = new ControllerDado();

        //Inicializar background

        background = new Texture("backgrounds/bg.png");

        //Inicializar dados

        actionDie = diceAtlas.findRegion("movement");
        summonDie = diceAtlas.findRegion("artilleria");
        movementDie = diceAtlas.findRegion("1");


        //Inicializar labels

        /*Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont("R");
        labelStyle.fontColor = Color.WHITE;
        labelStyle.font = myFont;*/


        /* __________________________________________________________________________________________*/
        /* --------------                         Cronómetro                                 --------*/
        /* ------------------------------------------------------------------------------------------*/
        /*lTimer = new Label("60",);
        lTimer.setSize(100, 30);
        lTimer.setPosition(1104, 852);*/


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
                System.out.println("Accion ejecutada: Tirar Dados");
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
                System.out.println("Accion ejecutada: Final del turno."); // editar con el método correcto
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
        /* -----------                        Botón de Add Chest                             --------*/
        /* ------------------------------------------------------------------------------------------*/
        TextureRegionDrawable addUp =  new TextureRegionDrawable(buttonAtlas.findRegion("addUp"));
        TextureRegionDrawable addDown =  new TextureRegionDrawable(buttonAtlas.findRegion("addDown"));
        ImageButton.ImageButtonStyle styleAdd = new ImageButton.ImageButtonStyle();
        styleAdd.up = addUp;
        styleAdd.down = addDown;
        btnAddChest = new ImageButton(styleAdd);
        btnAddChest.setPosition(929,122);
        btnAddChest.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Accion ejecutada: Se agregaron dados de turno a cofre."); // editar con el método correcto
            }
        });




        //Establecer objetos de juego
        tablero = gestorCelda.getCellArray();




        //setear la pantalla al input processor para responder a clicks

        multiplexer = new InputMultiplexer();
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);

        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);

        //add elements to stage

        stage.addActor(btnRoll);
        stage.addActor(btnEndTurn);
        stage.addActor(btnAddChest);
        stage.addActor(btnSummon);
        /*stage.addActor(lTimer);*/


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


        //Dibujar Dados
        batch.draw(summonDie, 52,152);
        batch.draw(actionDie, 136,152);
        batch.draw(movementDie, 92,87);

        //Dibujar


        gestorCelda.changeColor(50, turnPlayer);

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


        /*if (btnRoll.isPressed()) {
            System.out.println("Botón de Roll presionado");
        }*/

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
        if(currentPlayer.equals("red")){
            currentPlayer="blue";
        }
    }

    public void updateClock(int value){
        gameTime=value; //actualiza variable local para dibujar el tiempo restante del turno
    }
}


