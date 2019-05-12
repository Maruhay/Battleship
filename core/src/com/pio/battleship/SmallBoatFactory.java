package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class SmallBoatFactory extends ShipFactory {

    Ship getShip ( Coordinates shipBegin, Coordinates shipEnd, Color shipColor ) {

        return new SmallBoat( shipBegin, shipColor ); }

    }
