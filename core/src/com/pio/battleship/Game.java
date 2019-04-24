package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Game {

    private int State;
    private Player [] Players;
    private int [][] ShipChoice;
    private Coordinates ShipBegin;
    private Coordinates ShipEnd;

    Game ( ) {

        State = 0;
        Players = new Player [2];
        // TODO ShipChoice
        ShipBegin = null;
        ShipEnd = null;

        }

    public void process ( Event MyEvent ) {

        switch ( State ) {

            case 0: updateShipChoice( MyEvent, 0 ); break;
            case 1: updateShipChoice( MyEvent, 1 ); break;
            case 2: updateGameplay( MyEvent, 0 ); break;
            case 3: updateGameplay( MyEvent, 1 ); break;
            case 4: updateFinishScreen( MyEvent, 1 ); break;
            case 5: updateFinishScreen( MyEvent, 0 ); break;
            case 6: break;

            default: System.out.println( "Unknown state at Game.process(Event)" ); break; } }

    public void process ( float Time ) {

        switch ( State ) {

            case 0: updateShipChoice( Time, 0 ); break;
            case 1: updateShipChoice( Time, 1 ); break;
            case 2: updateGameplay( Time, 0 ); break;
            case 3: updateGameplay( Time, 1 ); break;
            case 4: updateFinishScreen( Time, 1 ); break;
            case 5: updateFinishScreen( Time, 0 ); break;
            case 6: break;

            default: System.out.println( "Unknown state at Game.process(Time)" ); break; } }

    public void render ( SpriteBatch Window ) {

        Window.begin();

        switch ( State ) {

            case 0: renderShipChoice( Window, 0 ); break;
            case 1: renderShipChoice( Window, 1 ); break;
            case 2: renderGameplay( Window, 0 ); break;
            case 3: renderGameplay( Window, 1 ); break;
            case 4: renderFinishScreen( Window, 1 ); break;
            case 5: renderFinishScreen( Window, 0 ); break;
            case 6: break;

            default: System.out.println( "Unknown state at Game.render(SpriteBatch)" ); break; }

        Window.end(); }

    public boolean isFinished ( ) {

        return State > 5; }

    public Coordinates getGridCoordinates ( int MouseX, int MouseY, int GridX, int GridY, int GridWidth, int GridHeight ) {

        int FieldCount = 10;
        Coordinates MyCoordinates = new Coordinates();

        if ( MouseX < GridX || MouseY < GridY ) {

            return null; }

        MyCoordinates.setX( FieldCount * ( MouseX - GridX ) / GridWidth );
        MyCoordinates.setY( FieldCount * ( MouseY - GridY ) / GridHeight );

        if ( MyCoordinates.getX() > 9 ) {

            return null; }

        if ( MyCoordinates.getY() > 9 ) {

            return null; }

        return MyCoordinates; }

    public void updateShipChoice ( Event MyEvent, int Index ) {

        if ( MyEvent.Type == Event.EventType.MouseKeyReleased ) {

            Coordinates MyCoordinates = getGridCoordinates( MyEvent.PositionX, MyEvent.PositionY, 100, 100, 600, 600 );

            if ( MyCoordinates != null ) {

                if ( ShipBegin == null ) {

                    ShipBegin = MyCoordinates;
                    ShipEnd = new Coordinates();

                    ShipEnd.setX( ShipBegin.getX() );
                    ShipEnd.setY( ShipBegin.getY() ); }

                else {

                    /// TODO PASS TO BOARD OR STH

                    ShipBegin = null;
                    ShipEnd = null; } } }

        else if ( MyEvent.Type == Event.EventType.MouseMoved ) {

            Coordinates MyCoordinates = getGridCoordinates( MyEvent.PositionX, MyEvent.PositionY, 100, 100, 600, 600 );

            if ( ShipBegin != null && ShipEnd != null ) {

                if ( MyCoordinates != null ) {

                    ShipEnd.setX( MyCoordinates.getX() );
                    ShipEnd.setY( MyCoordinates.getY() ); } } } }

    public void updateGameplay ( Event MyEvent, int Index ) {

        }

    public void updateFinishScreen ( Event MyEvent, int Index ) {

        }

    public void updateShipChoice ( float Time, int Index ) {

        // TODO CHECK IF ALL SHIPS HAVE BEEN PLACED

        }

    public void updateGameplay ( float Time, int Index ) {

        if ( Players[Index].getBoard().isAllSunk() ) {

            State = 4 + Index;

            return; }

        // TODO

        }

    public void updateFinishScreen ( float Time, int Index ) {

        }

    public void renderGrid ( SpriteBatch Window, int PositionX, int PositionY, int Width, int Height ) {

        Window.end();

        ShapeRenderer MyShapeRenderer = new ShapeRenderer();
        MyShapeRenderer.setProjectionMatrix( Window.getProjectionMatrix() );

        int FieldCount = 10;
        int LineThickness = 3;
        int FieldWidth = ( Width - LineThickness * FieldCount ) / FieldCount;
        int FieldHeight = ( Height - LineThickness * FieldCount ) / FieldCount;

        for ( int i = 0; i <= FieldCount; i++ ) {

            int Vy = PositionY + ( LineThickness + FieldHeight ) * i;
            int Hx = PositionX + ( LineThickness + FieldWidth ) * i;

            MyShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            MyShapeRenderer.setColor(Color.RED);
            MyShapeRenderer.rect( PositionX, Vy, Width, LineThickness );
            MyShapeRenderer.end();

            MyShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            MyShapeRenderer.setColor(Color.RED);
            MyShapeRenderer.rect( Hx, PositionY, LineThickness, Height );
            MyShapeRenderer.end(); }

        Window.begin(); }

    public void renderDot ( SpriteBatch Window, int PositionX, int PositionY, int GridX, int GridY, int GridWidth, int GridHeight ) {

        Window.end();

        ShapeRenderer MyShapeRenderer = new ShapeRenderer();
        MyShapeRenderer.setProjectionMatrix( Window.getProjectionMatrix() );

        int FieldCount = 10;
        int LineThickness = 3;
        int FieldWidth = ( GridWidth - LineThickness * FieldCount ) / FieldCount;
        int FieldHeight = ( GridHeight - LineThickness * FieldCount ) / FieldCount;
        int DotRadius = (int) ( 0.25f * Math.min( FieldWidth, FieldHeight ) );
        PositionY = Math.abs( 9 - PositionY );

        MyShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        MyShapeRenderer.setColor(Color.RED);
        MyShapeRenderer.circle( GridX + LineThickness + ( LineThickness + FieldWidth ) * PositionX + FieldWidth / 2,
                                GridY + LineThickness + ( LineThickness + FieldWidth ) * PositionY + FieldHeight / 2,
                                DotRadius );
        MyShapeRenderer.end();

        Window.begin(); }

    public void renderShipChoice ( SpriteBatch Window, int Index ) {

        renderGrid( Window, 100, 100, 600, 600 );

        if ( ShipBegin != null ) {

            renderDot( Window, ShipBegin.getX(), ShipBegin.getY(), 100, 100, 600, 600 );

            if ( ShipEnd.getY() == ShipBegin.getY() ) {

                int Direction = Math.min( Math.max( ShipBegin.getX() - ShipEnd.getX(), -1 ), 1 );
                int Iterator = ShipEnd.getX();

                while ( Iterator != ShipBegin.getX() ) {

                    renderDot( Window, Iterator, ShipBegin.getY(), 100, 100, 600, 600 );

                    Iterator += Direction; } }

            else if ( ShipEnd.getX() == ShipBegin.getX() ) {

                int Direction = Math.min( Math.max( ShipBegin.getY() - ShipEnd.getY(), -1 ), 1 );
                int Iterator = ShipEnd.getY();

                while ( Iterator != ShipBegin.getY() ) {

                    renderDot( Window, ShipBegin.getX(), Iterator, 100, 100, 600, 600 );

                    Iterator += Direction; } } }

        // TODO RENDER ALREADY PLACED SHIPS

        }

    public void renderGameplay ( SpriteBatch Window, int Index ) {

        }

    public void renderFinishScreen ( SpriteBatch Window, int Index ) {

        }

    }

