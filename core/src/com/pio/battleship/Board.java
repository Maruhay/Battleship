package com.pio.battleship;

import java.util.LinkedList;

public class Board {

    private int width;
    private int height;
    public int [][] grid;
    private LinkedList <Ship> ships;

    Board ( int width, int height ) {

        this.width = width;
        this.height = height;

        if ( width > 0 && height > 0 ) {

            grid = new int [width][height];

            clear(); }

        ships = new LinkedList <Ship> ( ); }

    public void clear ( ) {

        for ( int x = 0; x < width; x++ ) {

            for ( int y = 0; y < height; y++ ) {

                grid[x][y] = 0; } } }

    // shadow: don't update grid's and ship's state
    // return:
    // 0 - invalid action
    // 1 - missed
    // 2 - hit
    // 3 - hit and sunk
    public int pick ( Coordinates coordinates, boolean shadow ) {

        if ( coordinates.getX() > width || coordinates.getY() > height ) {

            return 0; }

        if ( grid[coordinates.getX()][coordinates.getY()] > 0 ) {

            return 0; }

        if ( !shadow ) {

            grid[coordinates.getX()][coordinates.getY()] = 1; }

        for ( Ship ship : ships ) {

            int test = ship.pick( coordinates, shadow );

            if ( test > 1 ) {

                grid[coordinates.getX()][coordinates.getY()] = 2;

                return test; } }

        return 1; }

    public int pick ( Coordinates coordinates ) {

        return pick( coordinates, false ); }

    public void addShip ( Ship ship ) {

        ships.add( ship ); }

    public boolean areAllShipsSunk ( ){

        for ( Ship ship : ships ) {

            if ( !ship.isSunk() ) {

                return false; } }

        return true; }

    }
