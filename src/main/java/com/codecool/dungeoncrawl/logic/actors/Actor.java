package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        String tileName = "";

        try {
            // check tile name: skeleton, sword, key...
            tileName = nextCell.getActor().getTileName();
//            System.out.println(tileName);
        } catch (Exception ignored) {
        }

        // if tile is a wall, do not move
        if (nextCell.getTileName() != "wall") {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

        if (tileName == "skeleton") {   // if tile is a skeleton: FIGHT TODO: implement fight
            System.out.println("SKELETON");
        } else if (tileName == "sword" || tileName == "key") {   // if tile is an item (sward, key...): CAN PUT IT TODO put an item
            System.out.println(tileName);
        }
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

}
