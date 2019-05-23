package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;
import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {

    @Test
    public void aircraftCarrierPick ( ) {

        Coordinates [] myCoordinatesA = new Coordinates[4];
        myCoordinatesA[0] = new Coordinates();
        myCoordinatesA[1] = new Coordinates();
        myCoordinatesA[2] = new Coordinates();
        myCoordinatesA[3] = new Coordinates();

        myCoordinatesA[0].setX(4);
        myCoordinatesA[0].setY(8);
        myCoordinatesA[1].setX(5);
        myCoordinatesA[1].setY(8);
        myCoordinatesA[2].setX(6);
        myCoordinatesA[2].setY(8);
        myCoordinatesA[3].setX(7);
        myCoordinatesA[3].setY(8);

        AircraftCarrier myAircraftCarrier = new AircraftCarrier(myCoordinatesA,Color.RED);
        Coordinates myCoordinatesB = new Coordinates();

        myCoordinatesB.setX(7);
        myCoordinatesB.setY(3);
        assertEquals(myAircraftCarrier.pick(myCoordinatesB,false),1);

        myCoordinatesB.setX(6);
        myCoordinatesB.setY(8);
        assertEquals(myAircraftCarrier.pick(myCoordinatesB,false),2);

        myCoordinatesB.setX(4);
        myCoordinatesB.setY(8);
        assertEquals(myAircraftCarrier.pick(myCoordinatesB,false),2);

        myCoordinatesB.setX(7);
        myCoordinatesB.setY(8);
        assertEquals(myAircraftCarrier.pick(myCoordinatesB,false),2);

        myCoordinatesB.setX(5);
        myCoordinatesB.setY(8);
        assertEquals(myAircraftCarrier.pick(myCoordinatesB,true),2);

        myCoordinatesB.setX(5);
        myCoordinatesB.setY(8);
        assertEquals(myAircraftCarrier.pick(myCoordinatesB,false),3);

        }

    @Test
    public void boardAreAllShipsSunk ( ) {

        Board myBoard = new Board(10,10);

        Coordinates myCoordinatesA = new Coordinates();
        myCoordinatesA.setX(6);
        myCoordinatesA.setY(4);
        myBoard.addShip(new SmallBoat(myCoordinatesA,Color.RED));

        Coordinates myCoordinatesB = new Coordinates();
        myCoordinatesB.setX(3);
        myCoordinatesB.setY(7);
        myBoard.addShip(new SmallBoat(myCoordinatesB,Color.RED));
        assertFalse(myBoard.areAllShipsSunk());

        Coordinates myCoordinatesC = new Coordinates();
        myCoordinatesC.setX(6);
        myCoordinatesC.setY(4);
        myBoard.pick(myCoordinatesC);
        assertFalse(myBoard.areAllShipsSunk());

        myCoordinatesC.setX(3);
        myCoordinatesC.setY(7);
        myBoard.pick(myCoordinatesC);
        assertTrue(myBoard.areAllShipsSunk());

        }

    @Test
    public void boardClear ( ) {

        Board myBoard = new Board(10,10);

        assertEquals(myBoard.grid[0][0],0);
        assertEquals(myBoard.grid[5][5],0);
        assertEquals(myBoard.grid[9][9],0);

        myBoard.grid[0][0] = 1;
        myBoard.grid[5][5] = 2;
        myBoard.grid[9][9] = 3;

        assertEquals(myBoard.grid[0][0],1);
        assertEquals(myBoard.grid[5][5],2);
        assertEquals(myBoard.grid[9][9],3);

        myBoard.clear();

        assertEquals(myBoard.grid[0][0],0);
        assertEquals(myBoard.grid[5][5],0);
        assertEquals(myBoard.grid[9][9],0);

        }

    @Test
    public void corvetteIsSunk ( ) {

        Coordinates [] myCoordinatesA = new Coordinates[2];
        myCoordinatesA[0] = new Coordinates();
        myCoordinatesA[1] = new Coordinates();
        myCoordinatesA[0].setX(2);
        myCoordinatesA[0].setY(7);
        myCoordinatesA[1].setX(9);
        myCoordinatesA[1].setY(4);

        Corvette myCorvette = new Corvette(myCoordinatesA,Color.RED);
        assertFalse(myCorvette.isSunk());

        Coordinates myCoordinatesB = new Coordinates();
        myCoordinatesB.setX(5);
        myCoordinatesB.setY(3);
        myCorvette.pick(myCoordinatesB,false);
        assertFalse(myCorvette.isSunk());

        myCoordinatesB.setX(9);
        myCoordinatesB.setY(4);
        myCorvette.pick(myCoordinatesB,false);
        assertFalse(myCorvette.isSunk());

        myCoordinatesB.setX(2);
        myCoordinatesB.setY(7);
        myCorvette.pick(myCoordinatesB,false);
        assertTrue(myCorvette.isSunk());

        }

    @Test
    public void destroyerFactoryGetShip ( ) {

        DestroyerFactory myDestroyerFactory = new DestroyerFactory();
        Coordinates myCoordinatesA = new Coordinates();
        Coordinates myCoordinatesB = new Coordinates();

        myCoordinatesA.setX(3);
        myCoordinatesA.setY(7);
        myCoordinatesB.setX(5);
        myCoordinatesB.setY(7);

        Ship myShip = myDestroyerFactory.getShip(myCoordinatesA,myCoordinatesB,Color.RED);
        assertNotNull(myShip);

        Coordinates myCoordinatesC = new Coordinates();
        myCoordinatesC.setY(7);

        myCoordinatesC.setX(3);
        myShip.pick(myCoordinatesC,false);
        assertFalse(myShip.isSunk());

        myCoordinatesC.setX(4);
        myShip.pick(myCoordinatesC,false);
        assertFalse(myShip.isSunk());

        myCoordinatesC.setX(5);
        myShip.pick(myCoordinatesC,false);
        assertTrue(myShip.isSunk());

        }

    @Test
    public void eventListenerIsEventAvailable ( ) {

        EventListener myEventListener = new EventListener();
        assertFalse(myEventListener.isEventAvailable());

        myEventListener.keyDown(0);
        assertTrue(myEventListener.isEventAvailable());

        myEventListener.keyUp(0);
        assertTrue(myEventListener.isEventAvailable());

        myEventListener.getEvent();
        myEventListener.getEvent();
        assertFalse(myEventListener.isEventAvailable());

        }

    @Test
    public void eventListenerGetEvent ( ) {

        EventListener myEventListener = new EventListener();

        myEventListener.keyDown(15);
        myEventListener.touchDown(20,25,0,3);

        Event myEventA = myEventListener.getEvent();
        Event myEventB = myEventListener.getEvent();

        assertSame(myEventA.Type,Event.EventType.KeyPressed);
        assertEquals(myEventA.Code,15);
        assertSame(myEventB.Type,Event.EventType.MouseKeyPressed);
        assertEquals(myEventB.Code,3);
        assertEquals(myEventB.PositionX,20);
        assertEquals(myEventB.PositionY,25);

        }

    @Test
    public void gameGetGridCoordinates ( ) {

        Game myGame = new Game();
    // comment
        Coordinates myCoordinatesA = myGame.getGridCoordinates(50,150,100,100,600,600);
        Coordinates myCoordinatesB = myGame.getGridCoordinates(150,50,100,100,600,600);
        Coordinates myCoordinatesC = myGame.getGridCoordinates(750,150,100,100,600,600);
        Coordinates myCoordinatesD = myGame.getGridCoordinates(150,750,100,100,600,600);

        assertNull(myCoordinatesA);
        assertNull(myCoordinatesB);
        assertNull(myCoordinatesC);
        assertNull(myCoordinatesD);

        Coordinates myCoordinatesE = myGame.getGridCoordinates(437,283,100,100,600,600);
        Coordinates myCoordinatesF = myGame.getGridCoordinates(172,684,100,100,600,600);

        assertEquals(myCoordinatesE.getX(),5);
        assertEquals(myCoordinatesE.getY(),3);
        assertEquals(myCoordinatesF.getX(),1);
        assertEquals(myCoordinatesF.getY(),9);

        }

    @Test
    public void playerGetColor ( ) {

        Player myRedPlayer = new Player(Color.RED);
        Player myGreenPlayer = new Player(Color.GREEN);
        Player myBluePlayer = new Player(Color.BLUE);

        assertSame(myRedPlayer.getColor(), Color.RED);
        assertSame(myGreenPlayer.getColor(), Color.GREEN);
        assertSame(myBluePlayer.getColor(), Color.BLUE);

        }

    @Test
    public void playerGetBoard ( ) {
        //comment
        Player myPlayer = new Player(Color.RED);
        assertNotNull(myPlayer.getBoard());

        }

    }
