package com.hgelder.astar;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.List;

public class AI {

    public Node startNode;
    public Node endNode;
    public Board gameBoard;
    public Node[][] boardTiles;

    public List<Node> open = new ArrayList<>();
    public List<Node> closed = new ArrayList<>();

    public AI(Node startNode, Node endNode, Board gameBoard, Node[][] boardTiles) {
        this.startNode = startNode;
        this.endNode = endNode; 
        this.gameBoard = gameBoard;
        this.boardTiles = boardTiles;
    }

    // add/remove nodes from open/closed lists
    public void addToList (String list, Node node) {
        if (list.equals("Open")) {
            open.add(node);
        } else if (list.equals("Closed")) {
            closed.add(node);
        }
    }
    public void removeFromList (String list, Node node) {
        if (list.equals("Open")) {
            open.remove(node);
        } else if (list.equals("Closed")) {
            closed.remove(node);
        }
    }

    // fetches neighbouring nodes of given node
    public List<Node> getNeighbours(Node currentNode, Node[][] boardTiles) {

        int x = currentNode.getX();
        int y = currentNode.getY();

        int[] coords = { x-1, y, x+1, y, x, y-1, x, y+1 };

        List<Node> neighbours = new ArrayList<>();

        for (int i = 0; i < coords.length; i++) {
            int x1 = coords[i];
            int y1 = coords[++i];

            if (x1 >= 0 && x1 < boardTiles.length 
             && y1 >= 0 && y1 < boardTiles[0].length
             && boardTiles[x1][y1].getState() == Node.NodeState.WALKABLE) {
                 neighbours.add(boardTiles[x1][y1]);
             }
        }
        return neighbours;
    }

    // calculates and prints the path
    public List<Node> getPath(Node startNode, Node endNode, Node[][] boardTiles) {

        List<Node> path = new ArrayList<>();
        List<Node> neighbours = new ArrayList<>();
        boolean endReached = false;
        Node currentNode = startNode;

        for (int x = 0; x < boardTiles.length; x++) {
            for (int y = 0; y < boardTiles.length; y++) {
                boardTiles[x][y].setH(Math.abs(endNode.getX() - x) + Math.abs(endNode.getY() - y));
            }
        }

        while (!endReached) {
            neighbours = getNeighbours(currentNode, boardTiles);
            List<Integer> fCosts = new ArrayList<>();

            for (int i = 0; i < neighbours.size(); i++) {
                if (neighbours.get(i).getState() != Node.NodeState.UNWALKABLE) {

                    addToList("Open", neighbours.get(i));
                    neighbours.get(i).setParent(currentNode);
                    neighbours.get(i).setG(neighbours.get(i).getG() + 10);

                    int fCost = neighbours.get(i).getG() + neighbours.get(i).getH();
                    neighbours.get(i).setF(fCost);

                    fCosts.add(fCost);
                }
            }

            Collections.sort(fCosts);
            int smallestFCost = fCosts.get(0);

            for (int j = 0; j < open.size(); j++) {
                if (open.get(j).getF() == smallestFCost) {
                    path.add(currentNode);
                    addToList("Closed", currentNode);
                    currentNode = open.get(j);
                }
            }

            if (currentNode == endNode) {
                endReached = true;
            }
        }
        path.add(currentNode);

        System.out.println("Completed path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.println("row: " + (path.get(i).getX()+1) + ", col: " + (path.get(i).getY()+1));
        }

        return path;
    }
}