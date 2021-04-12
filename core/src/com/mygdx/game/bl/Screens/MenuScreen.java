package com.mygdx.game.bl.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ui.MyGdxGame;

public class MenuScreen implements Screen {
    private MyGdxGame parent;
    Stage stage = new Stage(new ScreenViewport());

    public MenuScreen(MyGdxGame myGame) {
        parent = myGame;

        Gdx.input.setInputProcessor(stage);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        //skin de botones
        Skin skin = new Skin(Gdx.files.internal("skin/glassy/skin/glassy-ui.json"));

        //crear botones
        TextButton newGame = new TextButton("Nuevo Juego", skin);
        TextButton preferences = new TextButton("Preferencias", skin);
        TextButton exit = new TextButton("Salir", skin);

        //agregar botones a tabla
        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();

        //Agregar listeners de botones
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(MyGdxGame.APPLICATION);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(MyGdxGame.PREFERENCES);
            }
        });

    }

    @Override
    public void render(float delta) {
        //limpiar la pantalla lista para el siguiente set de imágenes por dibujar
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //le dice al stage que ejecute acciones y que se autodibuje
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //cambia el viewport del stage cuando el tamaño de pantalla varía
        stage.getViewport().update(width, height, true);
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
        //desecha assets cuando ya no se necesiten
        stage.dispose();
    }


}
