package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Tree2 extends Item{
    public Tree2(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "tree2";
    }
}
