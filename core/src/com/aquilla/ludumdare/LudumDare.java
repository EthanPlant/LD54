package com.aquilla.ludumdare;

import com.aquilla.ludumdare.state.LoadingState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LudumDare extends Game {

	public static final int WIDTH = 434;
	public static final int HEIGHT = 244;
	public static final int SCALE = 3;
	public static final String TITLE = "Ludum Dare 54";

	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new LoadingState(this));
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}