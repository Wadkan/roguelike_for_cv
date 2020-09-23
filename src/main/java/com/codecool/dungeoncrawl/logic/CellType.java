package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    KEY("key"),
    SWORD("sword"),
    CLOSED_DOOR("closedDoor"),
    OPENED_DOOR("openedDoor"),
    WATER("water"),
    BRIDGE("bridge"),
    TREE1("tree1"),
    TREE2("tree2"),
    TREE3("tree3"),
    STAIRS("stairs");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
