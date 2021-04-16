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


    //TextureAtlas

    public static final TextureAtlas cellAtlas = new TextureAtlas("cells/cells.atlas");
    public final TextureAtlas diceAtlas = new TextureAtlas("dice/dice.atlas");

    //Controllers & Game Variables

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


    @Override
    public void render(float deltaTime) {
        batch.begin();

        //Dibujar background
        batch.draw(background, 0,0);


        //Dibujar Tablero
        for (Celda c:tablero) {
            c.draw(batch);
        }

        //DibujarDados
        batch.draw(summonDie, 52,152);
        batch.draw(actionDie, 136,152);
        batch.draw(movementDie, 92,87);

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

