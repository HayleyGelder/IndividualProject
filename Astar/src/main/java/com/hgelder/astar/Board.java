package com.hgelder.astar;

public class Board { 

    public final int maxX;
    public final int maxY;
    public Node[][] boardTiles;

    // keeps record of gameboard tiles
    public Board(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.boardTiles = new Node[maxX][maxY];
        
        // creates a gameboard consisting of nodes
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                boardTiles[x][y] = new Node(x, y, Node.NodeState.WALKABLE, "[ ]");
            }
        }
    }

    // returns the entire gameboard
    public Node[][] getTiles() {
        return boardTiles;
    }

    // get/set tile (node) occupancy
    public Node getTile(int x, int y) {
        return this.boardTiles[x][y];
    }
    public void setTile(int x, int y, String contents) {
        this.boardTiles[x][y].setContents(contents);
    }

    // print the gameboard
    public void print() {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                System.out.print(getTile(x, y).getContents());
            }
            System.out.println();
        }
    }
    
}