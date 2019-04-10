package com.pio.battleship;

public class Board {
    private int width;
    private int height;
    private boolean[][] grid = new boolean[width][height];
    private ShipFactory[] ships ;

    public int pick(Cordinate cordinate){
        return 1;
    }
    public void addShip(ShipFactory shipFactory,boolean shadow){

    }
    public boolean isAllSunk(){
        return true;
    }
}
