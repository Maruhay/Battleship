package com.pio.battleship;

public class Board {
    private int width;
    private int height;
    private boolean[][] grid = new boolean[width][height];
    private ShipFactory[] ships ;

    public int pick(Coordinates coordinates){
        return 1;
    }
    public void addShip(ShipFactory shipFactory,boolean shadow){
        int row = ship.getRow();//n
        int col = ship.getCol();//n
        int dir = ship.getDirection();//n
        
        
        if (dir == 0) 
        {
            for (int i = col; i < col+length; i++)
            {
               
                grid[row][i].setShip(true);
             
            }
        }
        else if (dir == 1) // Vertical
        {
            for (int i = row; i < row+length; i++)
            {
                
                grid[i][col].setShip(true);
              
            }
        }
    }
    public boolean isAllSunk(){
        return true;
    }
}
