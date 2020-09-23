package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Tree3 extends Item{
    public Tree3(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "tree3";
    }
}
