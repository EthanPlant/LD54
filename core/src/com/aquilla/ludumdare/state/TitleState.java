package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TitleState extends State {

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
}
