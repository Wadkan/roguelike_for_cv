package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Tree1 extends Item{
    public Tree1(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "tree1";
    }
}
