package com.mygdx.game.ui.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ui.MyGdxGame;

public class EndScreen implements Screen {
    private MyGdxGame parent;
    private final SpriteBatch batchEnd;
    Texture backgroundEnd;
    public EndScreen(MyGdxGame myGame) {
        parent = myGame;
        backgroundEnd = new Texture("backgrounds/endbg.png");
        batchEnd = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batchEnd.begin();
        batchEnd.draw(backgroundEnd, 0,0);
        batchEnd.end();

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
