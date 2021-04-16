package com.mygdx.game.ui.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.bl.cofre.Cofre;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerDado;
import com.mygdx.game.tl.ControllerObserver;
import com.mygdx.game.ui.MyGdxGame;

import java.util.ArrayList;


public class GameScreen implements Screen {

    //Conexión con el orquestador
    private MyGdxGame parent;

    //screen
    private Camera camera;
    private Viewport viewport;

    //Gestores
    private static ControllerCelda gestorCelda;
    private static ControllerObserver gestorObserver;
    private static ControllerDado gestorDado;

    //graphics
    private SpriteBatch batch;

    //world parameters
    private final int WORLD_WIDTH = 1600;
    private final int WORLD_HEIGHT = 900;

    //background
    Texture background;

    //game objects
    private ArrayList<Celda> tablero;
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


    //Texture atlas
    public final TextureAtlas diceAtlas = new TextureAtlas("dice/dice.atlas");
    public static final TextureAtlas cellAtlas = new TextureAtlas("cells/cells.atlas");

    //variables

    private String turn;
    private int gameTime;
    private static int time = 0;


    public GameScreen(MyGdxGame myGame) {
        parent = myGame;
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


        //Inicializar botones


        //Establecer objetos de juego
        tablero = gestorCelda.getCellArray();



        batch = new SpriteBatch();

    }

    public void changeTurn(){
        if(turn.equals("jugador")){
            turn="cpu";
        }else{
            turn="jugador";
        }
    }

    public void updateClock(int value){
        gameTime=value; //actualiza variable local para dibujar el tiempo restante del turno
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

        gestorDado.getCofreJugador().draw(batch);

        //DibujarDados
        batch.draw(summonDie, 52,152);
        batch.draw(actionDie, 136,152);
        batch.draw(movementDie, 92,87);

        gestorCelda.changeColor(50, turnPlayer);

        batch.end();

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


    }

    @Override
    public void show() {

    }
}


