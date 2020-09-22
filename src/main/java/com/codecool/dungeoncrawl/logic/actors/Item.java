package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Item extends Actor {
    public Item(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return null;
    }

}
