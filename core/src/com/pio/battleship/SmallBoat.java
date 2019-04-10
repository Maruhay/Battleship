package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class SmallBoat implements ShipFactory {
    private Color color;
    private Deck[] decks = new Deck[1];

    @Override
    public int pick(Cordinate cordinate, boolean shadow) {
        return 0;
    }

    @Override
    public boolean isSunk() {
        return true;
    }
}
