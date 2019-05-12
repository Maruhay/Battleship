package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class SmallBoat implements Ship {

    private Color color;
    private Deck deck;

    SmallBoat ( Coordinates coordinates, Color color ) {

        this.color = color;
        deck = new Deck ( );
        deck.coordinates = coordinates; }

    @Override
    public int pick(Coordinates coordinates, boolean shadow) {
        return 0;
    }

    @Override
    public boolean isSunk() {
        return false;
    }
}
