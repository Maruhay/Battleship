package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class Destroyer implements Ship {

    private Color color;
    private Deck[] decks;

    Destroyer ( Coordinates [] coordinates, Color color ) {

        this.color = color;
        decks = new Deck [3];

        for ( int i = 0; i < 3; i++ ) {

            decks[i] = new Deck ( );
            decks[i].coordinates = coordinates[i]; } }

    // shadow: don't update ship state
    // return:
    // 1 - missed
    // 2 - hit
    // 3 - hit and sunk
    @Override
    public int pick ( Coordinates coordinates, boolean shadow ) {

        int result = 1;

        for ( Deck deck : decks ) {

            if ( coordinates.getX() == deck.coordinates.getX() && coordinates.getY() == deck.coordinates.getY() ) {

                if ( !shadow ) {

                    deck.sunk = true; }

                if ( isSunk() ) {

                    result = 3; }

                else {

                    result = 2; }

                break; } }

        return result; }

    @Override
    public boolean isSunk ( ) {

        for ( Deck deck : decks ) {

            if ( !deck.sunk ) {

                return false; } }

        return true; }

    }
