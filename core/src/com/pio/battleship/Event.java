package com.pio.battleship;

public class Event {

    public enum EventType {

        KeyPressed,
        KeyReleased,
        MouseMoved,
        MouseKeyPressed,
        MouseKeyReleased

        }

    public EventType Type = EventType.KeyPressed;
    public int Code = 0;
    public int PositionX = 0;
    public int PositionY = 0;

    }
