package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public abstract class ShipFactory {

     abstract Ship getShip ( Coordinates shipBegin, Coordinates shipEnd, Color shipColor );

     }
