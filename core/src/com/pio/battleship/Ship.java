package com.pio.battleship;

public interface Ship {

    // shadow: don't update ship state
    // return:
    // 1 - missed
    // 2 - hit
    // 3 - hit and sunk
    int pick ( Coordinates coordinates, boolean shadow );

    boolean isSunk ( );

    }
