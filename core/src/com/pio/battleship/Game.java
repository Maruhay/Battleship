package com.pio.battleship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Game {

    private int state;
    private Player[] players;
    private int maxShipLength;
    private int[][] shipCount;
    private int[] maxShipCount;
    private Coordinates shipBegin;
    private Coordinates shipEnd;

    Game() {

        state = 0;
        players = new Player[2];
        players[0] = new Player(Color.GREEN);
        players[1] = new Player(Color.BLUE);
        maxShipLength = 4;
        shipCount = new int[2][];
        shipCount[0] = new int[maxShipLength + 1];
        shipCount[1] = new int[maxShipLength + 1];
        shipCount[0][maxShipLength] = 0;
        shipCount[1][maxShipLength] = 0;
        maxShipCount = new int[maxShipLength];
        shipBegin = null;
        shipEnd = null;


        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < maxShipLength; j++) {

                shipCount[i][j] = 0;
                maxShipCount[j] = 0;
            }
        }

        maxShipCount[0] = 1;
        maxShipCount[1] = 1;
        maxShipCount[2] = 1;
        maxShipCount[3] = 1;
    }

    public void process(Event myEvent) {

        switch (state) {

            case 0:
                updateShipChoice(myEvent, 0);
                break;
            case 1:
                updateShipChoice(myEvent, 1);
                break;
            case 2:
                updateGameplay(myEvent, 1);
                break;
            case 3:
                updateGameplay(myEvent, 0);
                break;
            case 4:
                updateFinishScreen(myEvent, 1);
                break;
            case 5:
                updateFinishScreen(myEvent, 0);
                break;
            case 6:
                break;

            default:
                System.out.println("Unknown state at Game.process(Event)");
                break;
        }
    }

    public void process(float time) {

        switch (state) {

            case 0:
                updateShipChoice(time, 0);
                break;
            case 1:
                updateShipChoice(time, 1);
                break;
            case 2:
                updateGameplay(time, 1);
                break;
            case 3:
                updateGameplay(time, 0);
                break;
            case 4:
                updateFinishScreen(time, 1);
                break;
            case 5:
                updateFinishScreen(time, 0);
                break;
            case 6:
                break;

            default:
                System.out.println("Unknown state at Game.process(Time)");
                break;
        }
    }

    public void render(SpriteBatch window) {

        window.begin();

        switch (state) {

            case 0:
                renderShipChoice(window, 0);
                break;
            case 1:
                renderShipChoice(window, 1);
                break;
            case 2:
                renderGameplay(window, 1);
                break;
            case 3:
                renderGameplay(window, 0);
                break;
            case 4:
                renderFinishScreen(window, 1);
                break;
            case 5:
                renderFinishScreen(window, 0);
                break;
            case 6:
                break;

            default:
                System.out.println("Unknown state at Game.render(SpriteBatch)");
                break;
        }

        window.end();
    }

    public boolean isFinished() {

        return state > 5;
    }

    public Coordinates getGridCoordinates(int mouseX, int mouseY, int gridX, int gridY, int gridWidth, int gridHeight) {

        int fieldCount = 10;
        Coordinates myCoordinates = new Coordinates();

        if (mouseX < gridX || mouseY < gridY) {

            return null;
        }

        myCoordinates.setX(fieldCount * (mouseX - gridX) / gridWidth);
        myCoordinates.setY(fieldCount * (mouseY - gridY) / gridHeight);

        if (myCoordinates.getX() > 9) {

            return null;
        }

        if (myCoordinates.getY() > 9) {

            return null;
        }

        return myCoordinates;
    }

    public void updateShipChoice(Event myEvent, int index) {

        if (myEvent.Type == Event.EventType.MouseKeyReleased) {

            Coordinates myCoordinates = getGridCoordinates(myEvent.PositionX, myEvent.PositionY, 100, 100, 600, 600);

            if (myCoordinates != null) {

                if (shipBegin == null) {

                    shipBegin = myCoordinates;
                    shipEnd = new Coordinates();

                    shipEnd.setX(shipBegin.getX());
                    shipEnd.setY(shipBegin.getY());
                } else {

                    boolean allowed = true;
                    int length = 1;

                    Coordinates coordinates = new Coordinates();
                    coordinates.setX(shipBegin.getX());
                    coordinates.setY(shipBegin.getY());

                    if (players[index].getBoard().pick(coordinates, true) == 1) {

                        if (shipEnd.getY() == shipBegin.getY()) {

                            int direction = Math.min(Math.max(shipBegin.getX() - shipEnd.getX(), -1), 1);
                            int it = shipEnd.getX();

                            while (it != shipBegin.getX()) {

                                coordinates.setX(it);
                                coordinates.setY(shipBegin.getY());

                                if (players[index].getBoard().pick(coordinates, true) != 1) {

                                    allowed = false;

                                    break;
                                }

                                length++;
                                it += direction;
                            }
                        } else if (shipEnd.getX() == shipBegin.getX()) {

                            int direction = Math.min(Math.max(shipBegin.getY() - shipEnd.getY(), -1), 1);
                            int it = shipEnd.getY();

                            while (it != shipBegin.getY()) {

                                coordinates.setX(shipBegin.getX());
                                coordinates.setY(it);

                                if (players[index].getBoard().pick(coordinates, true) != 1) {

                                    allowed = false;

                                    break;
                                }

                                length++;
                                it += direction;
                            }
                        } else {

                            allowed = false;
                        }
                    } else {

                        allowed = false;
                    }

                    if (length > maxShipLength) {

                        allowed = false;
                    } else if (shipCount[index][length - 1] == maxShipCount[length - 1]) {

                        allowed = false;
                    }

                    if (allowed) {

                        Board board = players[index].getBoard();

                        if (shipEnd.getY() == shipBegin.getY()) {

                            int direction = Math.min(Math.max(shipBegin.getX() - shipEnd.getX(), -1), 1);

                            for (int i = 0; i < length; i++) {

                                coordinates.setX(shipEnd.getX() + direction * i);
                                coordinates.setY(shipEnd.getY());
                                board.pick(coordinates);
                            }
                        } else if (shipEnd.getX() == shipBegin.getX()) {

                            int direction = Math.min(Math.max(shipBegin.getY() - shipEnd.getY(), -1), 1);

                            for (int i = 0; i < length; i++) {

                                coordinates.setX(shipEnd.getX());
                                coordinates.setY(shipEnd.getY() + direction * i);
                                board.pick(coordinates);
                            }
                        }

                        Ship ship;
                        ShipFactory factory;

                        switch (length) {

                            case 1:
                                factory = ShipProducer.getFactory(ShipProducer.FactoryType.SmallBoatFactory);
                                break;
                            case 2:
                                factory = ShipProducer.getFactory(ShipProducer.FactoryType.CorvetteFactory);
                                break;
                            case 3:
                                factory = ShipProducer.getFactory(ShipProducer.FactoryType.DestroyerFactory);
                                break;
                            case 4:
                                factory = ShipProducer.getFactory(ShipProducer.FactoryType.AircraftCarrierFactory);
                                break;

                            default:
                                factory = null;
                                break;
                        }

                        ship = factory.getShip(shipBegin, shipEnd, players[index].getColor());
                        players[index].getBoard().addShip(ship);
                        shipCount[index][length - 1]++;
                        shipCount[index][maxShipLength]++;
                    }

                    shipBegin = null;
                    shipEnd = null;
                }
            }
        } else if (myEvent.Type == Event.EventType.MouseMoved) {

            Coordinates myCoordinates = getGridCoordinates(myEvent.PositionX, myEvent.PositionY, 100, 100, 600, 600);

            if (shipBegin != null && shipEnd != null) {

                if (myCoordinates != null) {

                    shipEnd.setX(myCoordinates.getX());
                    shipEnd.setY(myCoordinates.getY());
                }
            }
        }
    }

    public void updateGameplay(Event myEvent, int index) {

        if (myEvent.Type == Event.EventType.MouseKeyReleased) {

            Coordinates myCoordinates = getGridCoordinates(myEvent.PositionX, myEvent.PositionY, 100, 100, 600, 600);

            if (myCoordinates != null) {

                int enemyIndex = (index == 0) ? 1 : 0;
                int result = players[enemyIndex].getBoard().pick( myCoordinates );

                if ( result > 0 ) {

                    if ( state == 2 ) {

                        state = 3; }

                    else {

                        state = 2; }

                    if ( result == 3 ) {

                        shipCount[enemyIndex][maxShipLength]--; } }

                } }

    }

    public void updateFinishScreen(Event myEvent, int index) {

        // EMPTY

        }

    public void updateShipChoice(float time, int index) {

        boolean allShipsPlaced = true;

        for (int i = 0; i < maxShipLength; i++) {
            if (shipCount[index][i] != maxShipCount[i]) {
                allShipsPlaced = false;


                break;
            }
        }

        if (allShipsPlaced) {

            players[index].getBoard().clear();
            state++;

            return;
        }
    }

    public void updateGameplay(float time, int index) {

        if (players[index].getBoard().areAllShipsSunk()) {

            state = 4 + index;

            return;
        }
    }

    public void updateFinishScreen(float time, int index) {

        // EMPTY
    }

    public void renderGrid(SpriteBatch window, int positionX, int positionY, int width, int height, Color color) {

        window.end();

        ShapeRenderer myShapeRenderer = new ShapeRenderer();
        myShapeRenderer.setProjectionMatrix(window.getProjectionMatrix());

        int fieldCount = 10;
        int lineThickness = 3;
        int fieldWidth = (width - lineThickness * fieldCount) / fieldCount;
        int fieldHeight = (height - lineThickness * fieldCount) / fieldCount;

        for (int i = 0; i <= fieldCount; i++) {

            int vy = positionY + (lineThickness + fieldHeight) * i;
            int hx = positionX + (lineThickness + fieldWidth) * i;

            myShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            myShapeRenderer.setColor(color);
            myShapeRenderer.rect(positionX, vy, width + lineThickness, lineThickness);
            myShapeRenderer.end();

            myShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            myShapeRenderer.setColor(color);
            myShapeRenderer.rect(hx, positionY, lineThickness, height + lineThickness);
            myShapeRenderer.end();
        }

        window.begin();
    }

    public void renderDot(SpriteBatch window, int positionX, int positionY, Color color, int gridX, int gridY, int gridWidth, int gridHeight) {

        window.end();

        ShapeRenderer myShapeRenderer = new ShapeRenderer();
        myShapeRenderer.setProjectionMatrix(window.getProjectionMatrix());

        int fieldCount = 10;
        int lineThickness = 3;
        int fieldWidth = (gridWidth - lineThickness * fieldCount) / fieldCount;
        int fieldHeight = (gridHeight - lineThickness * fieldCount) / fieldCount; ;
        int dotRadius = (int) (0.25f * Math.min(fieldWidth, fieldHeight));
        positionY = Math.abs(9 - positionY);

        myShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        myShapeRenderer.setColor(color);
        myShapeRenderer.circle(gridX + lineThickness + (lineThickness + fieldWidth) * positionX + fieldWidth / 2,
                gridY + lineThickness + (lineThickness + fieldWidth) * positionY + fieldHeight / 2,
                dotRadius);
        myShapeRenderer.end();

        window.begin();
    }


    public void renderShipChoice(SpriteBatch window, int index) {

        renderGrid(window, 100, 100, 600, 600, players[index].getColor());


        Board board = players[index].getBoard();

        for (int x = 0; x < 10; x++) {

            for (int y = 0; y < 10; y++) {

                if (board.grid[x][y] > 0) {

                    renderDot(window, x, y, players[index].getColor(), 100, 100, 600, 600);

                }
            }
        }

        if (shipBegin != null) {

            renderDot(window, shipBegin.getX(), shipBegin.getY(), Color.WHITE, 100, 100, 600, 600);

            if (shipEnd.getY() == shipBegin.getY()) {

                int direction = Math.min(Math.max(shipBegin.getX() - shipEnd.getX(), -1), 1);
                int it = shipEnd.getX();

                while (it != shipBegin.getX()) {

                    renderDot(window, it, shipBegin.getY(), Color.WHITE, 100, 100, 600, 600);

                    it += direction;
                }
            } else if (shipEnd.getX() == shipBegin.getX()) {

                int direction = Math.min(Math.max(shipBegin.getY() - shipEnd.getY(), -1), 1);
                int it = shipEnd.getY();

                while (it != shipBegin.getY()) {

                    renderDot(window, shipBegin.getX(), it, Color.WHITE, 100, 100, 600, 600);

                    it += direction;
                }
            }
        }

        BitmapFont font = new BitmapFont();
        int shipsLeft = 0;
        if(maxShipCount[0] - shipCount[index][0] != 0)
            font.draw(window, "One deck ships left  " + (maxShipCount[0] - shipCount[index][0]), 20, 90);
        else shipsLeft += 20;
        if(maxShipCount[1] - shipCount[index][1] != 0)
            font.draw(window, "Two deck ships left  " + (maxShipCount[1] - shipCount[index][1]), 20, 70 + shipsLeft);
        else shipsLeft += 20;
        if(maxShipCount[2] - shipCount[index][2] != 0)
            font.draw(window, "Three deck ships left  " + (maxShipCount[2] - shipCount[index][2]), 20, 50 + shipsLeft);
        else shipsLeft += 20;
        if(maxShipCount[3] - shipCount[index][3] != 0)
            font.draw(window, "Four deck ships left  " + (maxShipCount[3] - shipCount[index][3]), 20, 30 + shipsLeft);
        else shipsLeft += 20;
    }

    public void renderGameplay(SpriteBatch window, int index) {

        int enemyIndex = (index == 0) ? 1 : 0;
        Board board = players[enemyIndex].getBoard();

        renderGrid(window, 100, 100, 600, 600, players[enemyIndex].getColor());

        for (int x = 0; x < 10; x++) {

            for (int y = 0; y < 10; y++) {

                if (board.grid[x][y] > 0) {

                    Color color = players[index].getColor();

                    if (board.grid[x][y] > 1) {

                        color = Color.RED;
                    }

                    renderDot(window, x, y, color, 100, 100, 600, 600);
                }
            }
        }

    }

    public void renderFinishScreen(SpriteBatch window, int index) {

        BitmapFont font = new BitmapFont();
        font.getData().setScale(2, 2);

        if (index == 0)
            font.draw(window, "Green wins!", 340, 400);
        else
            font.draw(window, "Blue wins!", 340, 400);


    }

}

