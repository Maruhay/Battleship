package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;

public class DestroyerFactory extends ShipFactory {

    Ship getShip ( Coordinates shipBegin, Coordinates shipEnd, Color shipColor ) {

        int length = 3;
        Coordinates [] coordinates = new Coordinates [length];
        for ( Coordinates myCoordinates : coordinates ) myCoordinates = new Coordinates ( );

        if ( shipEnd.getY() == shipBegin.getY() ) {

            int direction = Math.min( Math.max( shipBegin.getX() - shipEnd.getX(), -1 ), 1 );

            for ( int i = 0; i < length; i++ ) {

                coordinates[i] = new Coordinates();
                coordinates[i].setX( shipEnd.getX() + direction * i );
                coordinates[i].setY( shipEnd.getY() ); } }

        else if ( shipEnd.getX() == shipBegin.getX() ) {

            int direction = Math.min( Math.max( shipBegin.getY() - shipEnd.getY(), -1 ), 1 );

            for ( int i = 0; i < length; i++ ) {

                coordinates[i] = new Coordinates();
                coordinates[i].setX( shipEnd.getX() );
                coordinates[i].setY( shipEnd.getY() + direction * i ); } }

        return new Destroyer( coordinates, shipColor ); }

    }
