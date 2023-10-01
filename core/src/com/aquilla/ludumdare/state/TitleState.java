package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TitleState extends State implements InputProcessor {

    private final Animation<TextureRegion> animation;
    private float frameCount;

    public TitleState(LudumDare game) {
        super(game);

        Texture tex = Assets.getInstance().getTexture("titlescreen");
        TextureRegion[][] tmp = TextureRegion.split(tex, tex.getWidth() / 20, tex.getHeight());
        TextureRegion[] frames = new TextureRegion[20];
        System.arraycopy(tmp[0], 0, frames, 0, 20);
        animation = new Animation<>(0.07f, frames);
        frameCount = 0;
        Gdx.input.setInputProcessor(this);
        getAssets().getMusic("titlescreen_bgm").play();
        getAssets().getMusic("titlescreen_bgm").setLooping(true);
    }

    @Override
    public void update(float delta) {
        frameCount += delta;
    }

    @Override
    public void draw() {
        getGame().getBatch().begin();
        getGame().getBatch().draw(animation.getKeyFrame(frameCount), 0, 0, LudumDare.WIDTH, LudumDare.HEIGHT);
        getGame().getBatch().end();
    }

    @Override
    public boolean keyDown(int keycode) {
        setState(new GameState(getGame()));
        getAssets().getSound("menu_select_sfx").play();
        getAssets().getMusic("titlescreen_bgm").stop();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
