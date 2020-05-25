package com.hgelder.astar;

public class Node { 

    // denotes whether a node is already occupied
    enum NodeState {
        WALKABLE,
        UNWALKABLE
    }

    private int x; 
    private int y; 
    private int f;
    private int g;
    private int h; 
    private String contents;
    private Node parent; 
    private NodeState state;

    // keeps record of node coordinates, whether it contains an object (occupied) and if so, what the object is 
    public Node(int x, int y, NodeState state, String contents) {
        this.x = x;
        this.y = y;
        this.state = state;
        this.contents = contents;
    }

    // get/set objects occupying nodes
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }

    // get/set node coordinates
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    // get/set node f cost
    public int getF() {
        return f;
    }
    public void setF(int f) {
        this.f = f;
    }

    // get/set node g cost
    public int getG() {
        return g;
    }
    public void setG(int g) {
        this.g = g;
    }

    // get/set node h cost
    public int getH() {
        return h;
    }
    public void setH(int h) {
        this.h = h;
    }

    // get/set node parent
    public Node getParent() {
        return parent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }

    // get/set node occupancy 
    public NodeState getState() {
        return state;
    }
    public void setState(String state) {
        if (state.equals("W")) {
            this.state = NodeState.WALKABLE;
        } else if (state.equals("U")) {
            this.state = NodeState.UNWALKABLE;
        }
    }

}