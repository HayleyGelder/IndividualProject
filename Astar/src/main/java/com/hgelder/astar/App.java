package com.hgelder.astar; 

import java.lang.reflect.Array; 
import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static Board gameBoard;
    public static Node currentNode; 
    public static Node startNode; 
    public static Node endNode; 
    public static Node[][] boardTiles; 

    private static Scanner userInput;
    private static int[] input = {0, 0};

    public static void main(String args[]) {
        newGame(5, 5);
        boardTiles = gameBoard.getTiles();

        placeObject(getObjectCell("Player"), "Player");
        placeObject(getObjectCell("Enemy"), "Enemy");
        placeObject(getObjectCell("Wall"), "Wall");
        placeObject(getObjectCell("Wall"), "Wall");
        placeObject(getObjectCell("Wall"), "Wall");

        AI ai = new AI(startNode, endNode, gameBoard, boardTiles);
        ai.getPath(startNode, endNode, boardTiles);
    }

    // begins a new game using a new instance of Board
    public static void newGame(int xSize, int ySize) {
        gameBoard = new Board(xSize, ySize);
        gameBoard.print();
    }

    // receives user input for where to place objects on the gameboard
    public static int[] getObjectCell(String object) {

        userInput = new Scanner(System.in);

        if (object.equals("Player")) {

            System.out.print("Enter row (1-5) in which to place player: ");
            input[0] = userInput.nextInt();

            while (input[0] < 1 || input[0] > 5) {
                System.out.println("Please enter a number between 1 and 5");
                input[0] = userInput.nextInt();
            }

            System.out.print("Enter column (1-5) in which to place player: ");
            input[1] = userInput.nextInt();

            while (input[1] < 1 || input[1] > 5) {
                System.out.println("Please enter a number between 1 and 5");
                input[1] = userInput.nextInt();
            }

            System.out.println("Player (P) cell chosen: " + Arrays.toString(input));

        } else if (object.equals("Enemy")) {

            System.out.print("Enter row (1-5) in which to place enemy: ");
            input[0] = userInput.nextInt();

            while (input[0] < 1 || input[0] > 5) {
                System.out.println("Please enter a number between 1 and 5");
                input[0] = userInput.nextInt();
            }

            System.out.print("Enter column (1-5) in which to place enemy: ");
            input[1] = userInput.nextInt();

            while (input[1] < 1 || input[1] > 5) {
                System.out.println("Please enter a number between 1 and 5");
                input[1] = userInput.nextInt();
            }

            System.out.println("Enemy (E) cell chosen: " + Arrays.toString(input));

        } else if (object.equals("Wall")) {

            System.out.print("Enter row (1-5) in which to place wall: ");
            input[0] = userInput.nextInt();

            while (input[0] < 1 || input[0] > 5) {
                System.out.println("Please enter a number between 1 and 5");
                input[0] = userInput.nextInt();
            }

            System.out.print("Enter column (1-5) in which to place wall: ");
            input[1] = userInput.nextInt();

            while (input[1] < 1 || input[1] > 5) {
                System.out.println("Please enter a number between 1 and 5");
                input[1] = userInput.nextInt();
            }

            System.out.println("Wall (X) cell chosen: " + Arrays.toString(input));

        }
        return input;
    }

    // utilises user input received above to place objects on the gameboard
    public static void placeObject(int[] cell, String object) {

        int x = (int)Array.get(cell, 0) - 1;
        int y = (int)Array.get(cell, 1) - 1;

        String tileContents = gameBoard.getTile(x, y).getContents();
        Node.NodeState state = gameBoard.getTile(x, y).getState();

        if (object.equals("Player")) {

            if (tileContents.equals("[ ]")) {
                endNode = gameBoard.getTile(x, y);
                gameBoard.setTile(x, y, "[P]");
                gameBoard.print();
            } else {
                System.out.println("Cell already occupied. Please choose an empty cell.");
                placeObject(getObjectCell("Player"), "Player");
            }

        } else if (object.equals("Enemy")) {

            if (tileContents.equals("[ ]")) {
                startNode = gameBoard.getTile(x, y);
                gameBoard.setTile(x, y, "[E]");
                gameBoard.print();
            } else {
                System.out.println("Cell already occupied. Please choose an empty cell.");
                placeObject(getObjectCell("Enemy"), "Enemy");
            }

        } else if (object.equals("Wall")) { 

            if (tileContents.equals("[ ]")) {
                state = Node.NodeState.UNWALKABLE;
                gameBoard.setTile(x, y, "[X]");
                gameBoard.print();
            } else {
                System.out.println("Cell already occupied. Please choose an empty cell.");
                placeObject(getObjectCell("Wall"), "Wall");
            }
        }
    }
    
}
