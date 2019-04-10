package com.pio.battleship;

import com.badlogic.gdx.InputProcessor;

public class EventListener implements InputProcessor {

    // TODO EVENT BUFFER

    // TODO
    public boolean isEventAvailable ( ) {

        return false;

        }

    // TODO
    public Event getEvent ( ) {

        return new Event();

        }

    // TODO
    public boolean keyDown (int keycode) {

        return false;

        }

    // TODO
    public boolean keyUp (int keycode) {

        return false;

        }

    // TODO
    public boolean keyTyped (char character) {

        return false;

        }

    public boolean touchDown (int x, int y, int pointer, int button) {

        return false; }

    public boolean touchUp (int x, int y, int pointer, int button) {

        return false; }

    public boolean touchDragged (int x, int y, int pointer) {

        return false; }

    public boolean mouseMoved (int x, int y) {

        return false; }

    public boolean scrolled (int amount) {

        return false; }

    }
