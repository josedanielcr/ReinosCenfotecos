package com.mygdx.game.bl.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.bl.cofre.Cofre;
import com.mygdx.game.tl.ControllerCelda;

import java.util.ArrayList;


public class GameScreen implements Screen {

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


    public GameScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        // Especificar atlas de texturas

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

