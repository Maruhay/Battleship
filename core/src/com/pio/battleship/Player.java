package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class Player {

    private Color color;
    private Board board;

    Player ( Color color ) {

        this.color = color;
        board = new Board ( 10, 10 ); }

    public Color getColor ( ) {

        return color; }

    public Board getBoard ( ) {

        return board; }

    }
