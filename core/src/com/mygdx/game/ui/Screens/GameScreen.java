package com.mygdx.game.ui.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.bl.cofre.Cofre;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerJugadores;
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

    //graphics
    private SpriteBatch batch;

    //world parameters
    private final int WORLD_WIDTH = 1600;
    private final int WORLD_HEIGHT = 900;

    //game objects

    private ArrayList<Celda> tablero;
    private Cofre cofre = new Cofre();

    //players

    String turnPlayer = "blue";
    String colorPlayer1 = "blue";
    String colorPlayer2 = "red";


    //TextureAtlas

    public static final TextureAtlas cellAtlas = new TextureAtlas("cells.atlas");

    private static int time = 0;

    private static ControllerJugadores cJug = new ControllerJugadores();

    public GameScreen(MyGdxGame myGame) {
        parent = myGame;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        // Especificar atlas de texturas

        // Contenedor de tiempo


        //Atributos de Gestores
        final int numInicialCeldasNormales = 1;
        final int numInicialCeldasCastillo = 1000;
        final int lifepointsCastillo = 3;

        //Inicializamos el gestor.
        gestorCelda = new ControllerCelda(numInicialCeldasNormales, numInicialCeldasCastillo, lifepointsCastillo);

        //Inicializar TextureRegions



        //Establecer objetos de juego
        tablero = gestorCelda.getCellArray();




        batch = new SpriteBatch();

    }


    @Override
    public void render(float deltaTime) {
        batch.begin();

        //Dibujar Tablero
        for (Celda c:tablero) {
            c.draw(batch);
        }






        cofre.draw(batch);

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

