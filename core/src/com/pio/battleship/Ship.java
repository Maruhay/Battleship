package com.pio.battleship;

public interface Ship {

    boolean isSunk();
    int pick ( Coordinates Coordinates, boolean Shadow );

    }
