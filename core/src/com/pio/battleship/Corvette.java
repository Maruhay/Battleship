package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class Corvette implements Ship {

    private Color color;
    private Deck [] decks;

    Corvette ( Coordinates [] coordinates, Color color ) {

        this.color = color;
        decks = new Deck [2];

        for ( int i = 0; i < 2; i++ ) {

            decks[i] = new Deck ( );
            decks[i].coordinates = coordinates[i]; } }

    @Override
    public int pick(Coordinates coordinates, boolean shadow) {
        return 0;
    }

    @Override
    public boolean isSunk() {
        return false;
    }
}
