package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class AircraftCarrier implements ShipFactory{
    private Color color;
    private Deck[] decks = new Deck[4];

    @Override
    public int pick(Cordinate cordinate,boolean shadow) {
        return 0;
    }

    @Override
    public boolean isSunk() {
        return false;
    }
}