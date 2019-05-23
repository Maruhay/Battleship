package com.pio.battleship;

import com.badlogic.gdx.InputProcessor;

import java.util.ArrayDeque;
import java.util.Queue;

public class EventListener implements InputProcessor {

    private Queue EventQueue = new ArrayDeque( );

    public boolean isEventAvailable ( ) {

        return !EventQueue.isEmpty(); }

    public Event getEvent ( ) {

        return (Event) EventQueue.poll(); }

    public boolean keyDown ( int keycode ) {

        Event MyEvent = new Event();

        MyEvent.Type = Event.EventType.KeyPressed;
        MyEvent.Code = keycode;

        EventQueue.add( MyEvent );

        return true; }

    public boolean keyUp ( int keycode ) {

        Event MyEvent = new Event();

        MyEvent.Type = Event.EventType.KeyReleased;
        MyEvent.Code = keycode;

        EventQueue.add( MyEvent );

        return true; }

    public boolean keyTyped ( char character ) {

        return false; }

    public boolean touchDown ( int x, int y, int pointer, int button ) {

        Event MyEvent = new Event();

        MyEvent.Type = Event.EventType.MouseKeyPressed;
        MyEvent.Code = button;
        MyEvent.PositionX = x;
        MyEvent.PositionY = y;

        EventQueue.add( MyEvent );

        return false; }

    public boolean touchUp ( int x, int y, int pointer, int button ) {

        Event MyEvent = new Event();

        MyEvent.Type = Event.EventType.MouseKeyReleased;
        MyEvent.Code = button;
        MyEvent.PositionX = x;
        MyEvent.PositionY = y;

        EventQueue.add( MyEvent );

        return true; }

    public boolean touchDragged ( int x, int y, int pointer ) {

        Event MyEvent = new Event();

        MyEvent.Type = Event.EventType.MouseMoved;
        MyEvent.PositionX = x;
        MyEvent.PositionY = y;

        EventQueue.add( MyEvent );

        return true; }

    public boolean mouseMoved ( int x, int y ) {

        Event MyEvent = new Event();

        MyEvent.Type = Event.EventType.MouseMoved;
        MyEvent.PositionX = x;
        MyEvent.PositionY = y;

        EventQueue.add( MyEvent );

        return true; }

    public boolean scrolled ( int amount ) {

        return false; }

    }
