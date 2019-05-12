package com.pio.battleship;

public class Event {

    public enum EventType {

        KeyPressed,
        KeyReleased,
        MouseMoved,
        MouseKeyPressed,
        MouseKeyReleased

        }

    public EventType Type;
    public int Code;
    public int PositionX;
    public int PositionY;

    Event ( ) {

        Type = EventType.KeyPressed;
        Code = 0;
        PositionX = 0;
        PositionY = 0; }

    }
