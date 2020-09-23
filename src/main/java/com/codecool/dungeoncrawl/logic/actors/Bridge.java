package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bridge extends Item {
    public Bridge(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "bridge";
    }
}
