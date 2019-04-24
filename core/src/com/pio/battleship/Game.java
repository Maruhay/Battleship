package com.pio.battleship;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game {

    private int State;
    // TODO private Player [] Players;
    // TODO private int [][] ShipChoice;

    Game ( ) {

        State = 0;

        }

    public void process ( Event MyEvent ) {

        switch ( State ) {

            case 0: updateShipChoice( MyEvent, 0 ); break;
            case 1: updateShipChoice( MyEvent, 1 ); break;
            case 2: updateGameplay( MyEvent, 0 ); break;
            case 3: updateGameplay( MyEvent, 1 ); break;
            case 4: updateFinishScreen( MyEvent, 0 ); break;
            case 5: updateFinishScreen( MyEvent, 1 ); break;
            case 6: break;

            default: System.out.println( "Unknown state at Game.process(Event)" ); break; } }

    public void process ( float Time ) {

        switch ( State ) {

            case 0: updateShipChoice( Time, 0 ); break;
            case 1: updateShipChoice( Time, 1 ); break;
            case 2: updateGameplay( Time, 0 ); break;
            case 3: updateGameplay( Time, 1 ); break;
            case 4: updateFinishScreen( Time, 0 ); break;
            case 5: updateFinishScreen( Time, 1 ); break;
            case 6: break;

            default: System.out.println( "Unknown state at Game.process(Time)" ); break; } }

    public void render ( SpriteBatch Window ) {

        switch ( State ) {

            case 0: renderShipChoice( Window, 0 ); break;
            case 1: renderShipChoice( Window, 1 ); break;
            case 2: renderGameplay( Window, 0 ); break;
            case 3: renderGameplay( Window, 1 ); break;
            case 4: renderFinishScreen( Window, 0 ); break;
            case 5: renderFinishScreen( Window, 1 ); break;
            case 6: break;

            default: System.out.println( "Unknown state at Game.render(SpriteBatch)" ); break; } }

    public boolean isFinished ( ) {

        return State > 5; }

    public void updateShipChoice ( Event MyEvent, int Index ) {

        }

    public void updateGameplay ( Event MyEvent, int Index ) {

        }

    public void updateFinishScreen ( Event MyEvent, int Index ) {

        }

    public void updateShipChoice ( float Time, int Index ) {

        }

    public void updateGameplay ( float Time, int Index ) {

        }

    public void updateFinishScreen ( float Time, int Index ) {

        }

    public void renderShipChoice ( SpriteBatch Window, int Index ) {

        }

    public void renderGameplay ( SpriteBatch Window, int Index ) {

        }

    public void renderFinishScreen ( SpriteBatch Window, int Index ) {

        }

    }
