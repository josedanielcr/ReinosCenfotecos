package com.mygdx.game.bl.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;
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

    //game objects
    private ArrayList<Celda> tablero;

    //variables
    public static final TextureAtlas cellAtlas = new TextureAtlas("cells.atlas");
    private String turn;
    private int gameTime;


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

        //Inicializar TextureRegions



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

        //Dibujar Tablero
        for (Celda c:tablero) {
            c.draw(batch);
        }


        gestorDado.getCofreJugador().draw(batch);

        gestorCelda.variarCelda(50);

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

