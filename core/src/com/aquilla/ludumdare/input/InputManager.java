package com.aquilla.ludumdare.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private boolean shoot;

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isShooting() {
        return shoot;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                up = true;
                return true;
            case Input.Keys.A:
                left = true;
                return true;
            case Input.Keys.S:
                down = true;
                return true;
            case Input.Keys.D:
                right = true;
                return true;
            case Input.Keys.SPACE:
                shoot = true;
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                up = false;
                return true;
            case Input.Keys.A:
                left = false;
                return true;
            case Input.Keys.S:
                down = false;
                return true;
            case Input.Keys.D:
                right = false;
                return true;
            case Input.Keys.SPACE:
                shoot = false;
                return false;
            default:
                return false;
        }
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
