package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.List;

public class Skeleton extends Actor {

    public Skeleton(Cell cell) {
        super(cell);
        setHealth(10);
        setDamage(2);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void move() {

    }
}
