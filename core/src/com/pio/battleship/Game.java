package com.pio.battleship;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game {

    private int State;
    // TODO private Player [] Players;
    // TODO private int [][] ShipChoice;

    // TODO
    Game ( ) {

        State = 0;

        }

    // TODO
    public void process ( Event MyEvent ) {

        switch ( State ) {

            case 0: updateShipChoice( MyEvent, 0 ); break;
            case 1: updateShipChoice( MyEvent, 1 ); break;
            case 2: updateGameplay( MyEvent, 0 ); break;
            case 3: updateGameplay( MyEvent, 1 ); break;
            case 4: updateFinishScreen( MyEvent, 0 ); break;
            case 5: updateFinishScreen( MyEvent, 1 ); break;

            default: System.out.println( "That's weird..." ); break; } }

    // TODO
    public void process ( float Time ) {

        System.out.println(Time); // TODO TEMP Print elapsed time

        switch ( State ) {

            case 0: updateShipChoice( Time, 0 ); break;
            case 1: updateShipChoice( Time, 1 ); break;
            case 2: updateGameplay( Time, 0 ); break;
            case 3: updateGameplay( Time, 1 ); break;
            case 4: updateFinishScreen( Time, 0 ); break;
            case 5: updateFinishScreen( Time, 1 ); break;

            default: System.out.println( "That's weird..." ); break; } }

    // TODO
    public void render ( SpriteBatch Window ) {

        switch ( State ) {

            case 0: renderShipChoice( Window, 0 ); break;
            case 1: renderShipChoice( Window, 1 ); break;
            case 2: renderGameplay( Window, 0 ); break;
            case 3: renderGameplay( Window, 1 ); break;
            case 4: renderFinishScreen( Window, 0 ); break;
            case 5: renderFinishScreen( Window, 1 ); break;

            default: System.out.println( "That's weird..." ); break; } }

    // TODO
    public boolean isFinished ( ) {

        return false;

        }

    // TODO
    public void updateShipChoice ( Event MyEvent, int Index ) {

        }

    // TODO
    public void updateGameplay ( Event MyEvent, int Index ) {

        }

    // TODO
    public void updateFinishScreen ( Event MyEvent, int Index ) {

        }

    // TODO
    public void updateShipChoice ( float Time, int Index ) {

        }

    // TODO
    public void updateGameplay ( float Time, int Index ) {

        }

    // TODO
    public void updateFinishScreen ( float Time, int Index ) {

        }

    // TODO
    public void renderShipChoice ( SpriteBatch Window, int Index ) {

        }

    // TODO
    public void renderGameplay ( SpriteBatch Window, int Index ) {

        }

    // TODO
    public void renderFinishScreen ( SpriteBatch Window, int Index ) {

        }

    }
