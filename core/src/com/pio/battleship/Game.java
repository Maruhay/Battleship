package com.pio.battleship;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Game {

    private int State;
    private Player [] Players;
    private int MaxShipLength;
    private int [][] ShipCount;
    private int [] MaxShipCount;
    private Coordinates ShipBegin;
    private Coordinates ShipEnd;

    Game ( ) {

        State = 0;
        Players = new Player [2];
        Players[0] = new Player ( Color.GREEN );
        Players[1] = new Player ( Color.BLUE );
        MaxShipLength = 4;
        ShipCount = new int [2][];
        ShipCount[0] = new int [ MaxShipLength + 1 ];
        ShipCount[1] = new int [ MaxShipLength + 1 ];
        ShipCount[0][MaxShipLength] = 0;
        ShipCount[1][MaxShipLength] = 0;
        MaxShipCount = new int [MaxShipLength];
        ShipBegin = null;
        ShipEnd = null;


        for ( int i = 0; i < 2; i++ ) {

            for ( int j = 0; j < MaxShipLength; j++ ) {

                ShipCount[i][j] = 0;
                MaxShipCount[j] = 0; } }

        MaxShipCount[0] = 1;
        MaxShipCount[1] = 1;
        MaxShipCount[2] = 1;
        MaxShipCount[3] = 1; }

    public void process ( Event MyEvent ) {

        switch ( State ) {

            case 0: updateShipChoice( MyEvent, 0 ); break;
            case 1: updateShipChoice( MyEvent, 1 ); break;
            case 2: updateGameplay( MyEvent, 1 ); break;
            case 3: updateGameplay( MyEvent, 0 ); break;
            case 4: updateFinishScreen( MyEvent, 0 ); break;
            case 5: updateFinishScreen( MyEvent, 1 ); break;
            case 6: break;

            default: System.out.println( "Unknown state at Game.process(Event)" ); break; } }

    public void process ( float Time ) {

        switch ( State ) {

            case 0: updateShipChoice( Time, 0 ); break;
            case 1: updateShipChoice( Time, 1 ); break;
            case 2: updateGameplay( Time, 1 ); break;
            case 3: updateGameplay( Time, 0 ); break;
            case 4: updateFinishScreen( Time, 0 ); break;
            case 5: updateFinishScreen( Time, 1 ); break;
            case 6: break;

            default: System.out.println( "Unknown state at Game.process(Time)" ); break; } }

    public void render ( SpriteBatch Window ) {

        Window.begin();

        switch ( State ) {

            case 0: renderShipChoice( Window, 0 ); break;
            case 1: renderShipChoice( Window, 1 ); break;
            case 2: renderGameplay( Window, 1 ); break;
            case 3: renderGameplay( Window, 0 ); break;
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

                    boolean allowed = true;
                    int length = 1;

                    Coordinates coordinates = new Coordinates();
                    coordinates.setX( ShipBegin.getX() );
                    coordinates.setY( ShipBegin.getY() );

                    if ( Players[Index].getBoard().pick( coordinates, true ) == 1 ) {

                        if ( ShipEnd.getY() == ShipBegin.getY() ) {

                            int Direction = Math.min( Math.max( ShipBegin.getX() - ShipEnd.getX(), -1 ), 1 );
                            int it = ShipEnd.getX();

                            while ( it != ShipBegin.getX() ) {

                                coordinates.setX( it );
                                coordinates.setY( ShipBegin.getY() );

                                if ( Players[Index].getBoard().pick( coordinates, true ) != 1 ) {

                                    allowed = false;

                                    break; }

                                length++;
                                it += Direction; } }

                        else if ( ShipEnd.getX() == ShipBegin.getX() ) {

                            int Direction = Math.min( Math.max( ShipBegin.getY() - ShipEnd.getY(), -1 ), 1 );
                            int it = ShipEnd.getY();

                            while ( it != ShipBegin.getY() ) {

                                coordinates.setX( ShipBegin.getX() );
                                coordinates.setY( it );

                                if ( Players[Index].getBoard().pick( coordinates, true ) != 1 ) {

                                    allowed = false;

                                    break; }

                                length++;
                                it += Direction; } }

                        else {

                            allowed = false; } }

                    else {

                        allowed = false; }

                    if ( length > MaxShipLength ) {

                        allowed = false; }

                    else if ( ShipCount[Index][ length - 1 ] == MaxShipCount[ length - 1 ] ) {

                        allowed = false; }

                    if ( allowed ) {

                        Board board = Players[Index].getBoard();

                        if ( ShipEnd.getY() == ShipBegin.getY() ) {

                            int Direction = Math.min( Math.max( ShipBegin.getX() - ShipEnd.getX(), -1 ), 1 );

                            for ( int i = 0; i < length; i++ ) {

                                coordinates.setX( ShipEnd.getX() + Direction * i );
                                coordinates.setY( ShipEnd.getY() );
                                board.pick( coordinates ); } }

                        else if ( ShipEnd.getX() == ShipBegin.getX() ) {

                            int Direction = Math.min( Math.max( ShipBegin.getY() - ShipEnd.getY(), -1 ), 1 );

                            for ( int i = 0; i < length; i++ ) {

                                coordinates.setX( ShipEnd.getX() );
                                coordinates.setY( ShipEnd.getY() + Direction * i );
                                board.pick( coordinates ); } }

                        Ship ship;
                        ShipFactory factory;

                        switch ( length ) {

                            case 1: factory = ShipProducer.getFactory( ShipProducer.FactoryType.SmallBoatFactory ); break;
                            case 2: factory = ShipProducer.getFactory( ShipProducer.FactoryType.CorvetteFactory ); break;
                            case 3: factory = ShipProducer.getFactory( ShipProducer.FactoryType.DestroyerFactory ); break;
                            case 4: factory = ShipProducer.getFactory( ShipProducer.FactoryType.AircraftCarrierFactory ); break;

                            default: factory = null; break; }

                        ship = factory.getShip( ShipBegin, ShipEnd, Players[Index].getColor() );
                        Players[Index].getBoard().addShip( ship );
                        ShipCount[Index][ length - 1 ]++;
                        ShipCount[Index][MaxShipLength]++; }

                    ShipBegin = null;
                    ShipEnd = null; } } }

        else if ( MyEvent.Type == Event.EventType.MouseMoved ) {

            Coordinates MyCoordinates = getGridCoordinates( MyEvent.PositionX, MyEvent.PositionY, 100, 100, 600, 600 );

            if ( ShipBegin != null && ShipEnd != null ) {

                if ( MyCoordinates != null ) {

                    ShipEnd.setX( MyCoordinates.getX() );
                    ShipEnd.setY( MyCoordinates.getY() ); } } } }

    public void updateGameplay ( Event MyEvent, int Index ) {

        // TODO THIS
        // TODO DECREASE ShipCount[Index][MaxShipLength] ON HIT AND SUNK (3)

        }

    public void updateFinishScreen ( Event MyEvent, int Index ) {

        // TODO THIS
        // TODO CLOSE ON ANY KEY (State=6)

        }

    public void updateShipChoice ( float Time, int Index ) {

        boolean AllShipsPlaced = true;

        for ( int i = 0; i < MaxShipLength; i++ ) {
            if ( ShipCount[Index][i] != MaxShipCount[i] ) {
                AllShipsPlaced = false;


                break; } }

        if ( AllShipsPlaced ) {

            Players[Index].getBoard().clear();
            State++;

            return; } }

    public void updateGameplay ( float Time, int Index ) {

        int EnemyIndex = ( Index == 0 ) ? 1 : 0;

        if ( Players[EnemyIndex].getBoard().areAllShipsSunk() ) {

            State = 4 + Index;

            return; } }

    public void updateFinishScreen ( float Time, int Index ) {

        // TODO I GUESS NOTHING ?

        }

    public void renderGrid ( SpriteBatch Window, int PositionX, int PositionY, int Width, int Height, Color color ) {

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
            MyShapeRenderer.setColor(color);
            MyShapeRenderer.rect( PositionX, Vy, Width + LineThickness, LineThickness );
            MyShapeRenderer.end();

            MyShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            MyShapeRenderer.setColor(color);
            MyShapeRenderer.rect( Hx, PositionY, LineThickness, Height + LineThickness );
            MyShapeRenderer.end(); }

        Window.begin(); }

    public void renderDot ( SpriteBatch Window, int PositionX, int PositionY, Color color, int GridX, int GridY, int GridWidth, int GridHeight ) {

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
        MyShapeRenderer.setColor(color);
        MyShapeRenderer.circle( GridX + LineThickness + ( LineThickness + FieldWidth ) * PositionX + FieldWidth / 2,
                                GridY + LineThickness + ( LineThickness + FieldWidth ) * PositionY + FieldHeight / 2,
                                DotRadius );
        MyShapeRenderer.end();

        Window.begin(); }





    public void renderShipChoice ( SpriteBatch Window, int Index ) {

        renderGrid( Window, 100, 100, 600, 600, Players[Index].getColor() );



        Board board = Players[Index].getBoard();

        for ( int x = 0; x < 10; x ++ ) {

            for ( int y = 0; y < 10; y++ ) {

                if( board.grid[x][y] > 0 ) {

                    renderDot( Window, x, y, Players[Index].getColor(), 100, 100, 600, 600 );

                } } }

        if ( ShipBegin != null ) {

            renderDot( Window, ShipBegin.getX(), ShipBegin.getY(), Color.WHITE, 100, 100, 600, 600 );

            if ( ShipEnd.getY() == ShipBegin.getY() ) {

                int Direction = Math.min( Math.max( ShipBegin.getX() - ShipEnd.getX(), -1 ), 1 );
                int it = ShipEnd.getX();

                while ( it != ShipBegin.getX() ) {

                    renderDot( Window, it, ShipBegin.getY(), Color.WHITE, 100, 100, 600, 600 );

                    it += Direction; } }

            else if ( ShipEnd.getX() == ShipBegin.getX() ) {

                int Direction = Math.min( Math.max( ShipBegin.getY() - ShipEnd.getY(), -1 ), 1 );
                int it = ShipEnd.getY();

                while ( it != ShipBegin.getY() ) {

                    renderDot( Window, ShipBegin.getX(), it, Color.WHITE, 100, 100, 600, 600 );

                    it += Direction; } } }

        // TODO RENDER HOW MANY MORE SHIPS HAVE TO BE PLACED
        BitmapFont font = new BitmapFont();
        font.draw(Window, "One deck ships left  " + (MaxShipCount[0] - ShipCount[Index][0]), 20, 90);
        font.draw(Window, "Two deck ships left  " + (MaxShipCount[1] - ShipCount[Index][1]), 20, 70);
        font.draw(Window, "Three deck ships left  " + (MaxShipCount[2] - ShipCount[Index][2]), 20, 50);
        font.draw(Window, "Four deck ships left  " + (MaxShipCount[3] - ShipCount[Index][3]), 20, 30);

        }

    public void renderGameplay ( SpriteBatch Window, int Index ) {

        int EnemyIndex = ( Index == 0 ) ? 1 : 0;
        Board board = Players[EnemyIndex].getBoard();

        // TODO RENDER INFORMATION IF HIT / HIT AND SUNK (INDEX INDEPENDENT)

        renderGrid( Window, 100, 100, 600, 600, Players[EnemyIndex].getColor() );

        for ( int x = 0; x < 10; x ++ ) {

            for ( int y = 0; y < 10; y++ ) {

                if ( board.grid[x][y] > 0 ) {

                    Color color = Players[Index].getColor();

                    if ( board.grid[x][y] > 1 ) {

                        color = Color.RED; }

                    renderDot( Window, x, y, color, 100, 100, 600, 600 );
                    } } }

        // TODO RENDER HOW MANY MORE SHIPS HAVE TO BE SUNK (LAST INDEX OF SHIP COUNT)


        }

    public void renderFinishScreen ( SpriteBatch Window, int Index ) {

        // TODO

        }

    }

