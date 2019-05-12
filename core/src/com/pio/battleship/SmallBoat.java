package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class SmallBoat implements Ship {

    private Color color;
    private Deck deck;

    SmallBoat ( Coordinates coordinates, Color color ) {

        this.color = color;
        deck = new Deck ( );
        deck.coordinates = coordinates; }

    // shadow: don't update ship state
    // return:
    // 1 - missed
    // 2 - hit
    // 3 - hit and sunk
    @Override
    public int pick ( Coordinates coordinates, boolean shadow ) {

        if ( coordinates.getX() == deck.coordinates.getX() && coordinates.getY() == deck.coordinates.getY() ) {

            if ( !shadow ) {

                deck.sunk = true;

                return 3; }

            return 2; }

        return 1; }

    @Override
    public boolean isSunk ( ) {

        return deck.sunk; }

    }
