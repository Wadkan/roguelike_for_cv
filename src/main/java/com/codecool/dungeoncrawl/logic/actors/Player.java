package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    private String name;    // todo

    public Player(Cell cell) {
        super(cell);
        setDamage(5);
        setHealth(10);
    }

    public Player(Cell cell, String name) {    // todo
        super(cell);
        this.name = name;
    }

    public String getTileName() {
        return "player";
    }

    public String getName() {
        return name;
    }    // todo

    public void setName(String name) {
        this.name = name;
    }    // todo
}
