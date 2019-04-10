package com.pio.battleship;

public interface ShipFactory {
     int pick(Cordinate cordinate,boolean shadow);
     boolean isSunk();

}
