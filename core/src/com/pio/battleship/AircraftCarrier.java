package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class AircraftCarrier implements Ship {

    private Color color;
    private Deck [] decks;

    AircraftCarrier ( Coordinates [] coordinates, Color color ) {

        this.color = color;
        decks = new Deck [4];

        for ( int i = 0; i < 4; i++ ) {

            decks[i] = new Deck ( );
            decks[i].coordinates = coordinates[i]; } }

    @Override
    public int pick( Coordinates coordinates, boolean shadow ) {
        return 0;
    }

    @Override
    public boolean isSunk() {
        return false;
    }
}
