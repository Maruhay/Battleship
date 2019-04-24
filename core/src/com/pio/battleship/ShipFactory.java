package com.pio.battleship;

public interface ShipFactory {
     int pick(Coordinates coordinates, boolean shadow);
     boolean isSunk();

}
