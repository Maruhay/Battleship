package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class AircraftCarrierFactory extends ShipFactory {

    Ship getShip ( Coordinates shipBegin, Coordinates shipEnd, Color shipColor ) {

        int length = 4;
        Coordinates [] coordinates = new Coordinates [length];
        for ( Coordinates myCoordinates : coordinates ) myCoordinates = new Coordinates ( );

        if ( shipEnd.getY() == shipBegin.getY() ) {

            int Direction = Math.min( Math.max( shipBegin.getX() - shipEnd.getX(), -1 ), 1 );

            for ( int i = 0; i < length; i++ ) {

                coordinates[i] = new Coordinates();
                coordinates[i].setX( shipEnd.getX() + Direction * i );
                coordinates[i].setY( shipEnd.getY() ); } }

        else if ( shipEnd.getX() == shipBegin.getX() ) {

            int Direction = Math.min( Math.max( shipBegin.getY() - shipEnd.getY(), -1 ), 1 );

            for ( int i = 0; i < length; i++ ) {

                coordinates[i] = new Coordinates();
                coordinates[i].setX( shipEnd.getX() );
                coordinates[i].setY( shipEnd.getY() + Direction * i ); } }

        return new AircraftCarrier( coordinates, shipColor ); }

    }
