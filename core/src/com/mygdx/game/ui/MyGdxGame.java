package com.mygdx.game.ui;

import com.badlogic.gdx.Game;
import com.mygdx.game.bl.Screens.GameScreen;

public class MyGdxGame extends Game {
	GameScreen gameScreen;

	@Override
	public void create() {
		gameScreen = new GameScreen();
		setScreen((gameScreen));
	}

	@Override
	public void dispose() {
		gameScreen.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		gameScreen.resize(width, height);
	}
}