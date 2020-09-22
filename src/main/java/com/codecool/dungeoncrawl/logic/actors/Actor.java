package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    protected Actor actualItem;
    private boolean ifStepIntoTheDoor = false;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        String tileName = "";
        actualItem = null;

        try {
            // check tile name: skeleton, sword, key...
            this.actualItem = nextCell.getActor();
        } catch (Exception ignored) {
        }

        // if tile is a wall, do not move
        String nextCellTitle = nextCell.getTileName();
        if (!nextCellTitle.equals("wall") && !nextCellTitle.equals("closedDoor")) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        if (nextCellTitle.equals("openedDoor")) {
            ifStepIntoTheDoor = true;
            System.out.println("NEW MAP");
        }

//        if (tileName.equals("skeleton")) {   // if tile is a skeleton: FIGHT TODO: implement fight
//            System.out.println("SKELETON");
//        } else if (tileName == "sword" || tileName == "key") {   // if tile is an item (sward, key...): CAN PUT IT TODO put an item
//            cell.setType(CellType.FLOOR);
//        }
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public Item getItem() {
        if (actualItem instanceof Item) {
            return (Item) actualItem;
        } else
            return null;
    }

    public boolean getIfStepIntoTheDoor() {
        return this.ifStepIntoTheDoor;
    }

}
