package com.mygdx.game.bl.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.ui.MyGdxGame;

public class LoadingScreen implements Screen {
    private MyGdxGame parent;


    public LoadingScreen(MyGdxGame myGame) {
        parent = myGame;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        parent.changeScreen(MyGdxGame.MENU);
    }

    @Override
    public void resize(int width, int height) {

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
}
