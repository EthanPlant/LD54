package com.aquilla.ludumdare.ui;

import com.aquilla.ludumdare.assets.Assets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class HealthBar extends ProgressBar {
    private final BitmapFont font;

    public HealthBar(int width, int height, float maxHealth) {
        super(0.0f, maxHealth, 1f, false, new ProgressBarStyle());

        getStyle().background = UiUtil.getColoredDrawable(width, height, Color.LIGHT_GRAY);
        getStyle().knob = UiUtil.getColoredDrawable(0, height, Color.GREEN);
        getStyle().knobBefore = UiUtil.getColoredDrawable(width, height, Color.GREEN);

        setWidth(width);
        setHeight(height);

        setValue(maxHealth);
        setAnimateDuration(0.25f);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    private Drawable getDrawableFromHealth(int width) {
        if (getValue() <= getMaxValue() / 5) {
            return UiUtil.getColoredDrawable(width, (int) getHeight(), Color.RED);
        } else if (getValue() < getMaxValue() / 2) {
            return UiUtil.getColoredDrawable(width, (int) getHeight(), Color.YELLOW);
        }
        return UiUtil.getColoredDrawable(width, (int) getHeight(), Color.GREEN);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        getStyle().knob = getDrawableFromHealth(0);
        getStyle().knobBefore = getDrawableFromHealth((int) getStyle().knobBefore.getMinWidth());
        super.draw(batch, parentAlpha);
        batch.draw(Assets.getInstance().getTexture("healthbar_tex"), getX(), getY(), getWidth(), getHeight());
//        String healthString = (int) getValue() + "/" + (int) getMaxValue();
//        font.draw(batch, healthString, getX(), getY() + font.getLineHeight());
    }
}
