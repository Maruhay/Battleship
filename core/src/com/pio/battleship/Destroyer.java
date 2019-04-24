package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class Destroyer implements ShipFactory {
    private Color color;
    private Deck[] decks = new Deck[3];

    @Override
    public int pick(Coordinates coordinates, boolean shadow) {
        return 0;
    }

    @Override
    public boolean isSunk() {
        return false;
    }
}
