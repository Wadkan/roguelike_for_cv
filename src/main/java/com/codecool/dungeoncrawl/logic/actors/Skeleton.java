package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.List;
import java.util.Random;

public class Skeleton extends Actor {
    private Cell cell;

    public Skeleton(Cell cell) {
        super(cell);
        setHealth(10);
        setDamage(2);
        this.cell = cell;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public void randomMove() {
        Random r = new Random();
        int dx = 0;
        int dy = 0;
        int direction = r.nextInt(6);
        switch (direction) {
            case 0:
                dx = 1;
                break;
            case 1:
                dx = -1;
                break;
            case 2:
                dy = -1;
                break;
            case 3:
                dy = 1;
                break;
            case 4:
            case 5:
                break;
        }
        Cell nextCell = cell.getNeighbor(dx, dy);
        
        String tileName = "";

        try {
            tileName = nextCell.getActor().getTileName();
        } catch (Exception ignored) {
        }

        String nextCellTitle = nextCell.getTileName();

        if (!nextCellTitle.equals("wall") && !nextCellTitle.equals("closedDoor") && !tileName.equals("bat")) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
}
