package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Attack;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    protected Actor actualItem;
    private Actor player;
    private int damage;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        String tileName = "";
        actualItem = null;
        player = cell.getActor();

        try {
            // check tile name: skeleton, sword, key...
            tileName = nextCell.getActor().getTileName();
            this.actualItem = nextCell.getActor();
            System.out.println("act: " + actualItem);
            System.out.println(tileName);
        } catch (Exception ignored) {
        }

        // if tile is a wall, do not move
        if (nextCell.getTileName() != "wall" && tileName != "skeleton") {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

        if (tileName == "skeleton") {   // if tile is a skeleton: FIGHT TODO: implement fight
            System.out.println("SKELETON");
            Attack a = new Attack(player, actualItem);
            a.fight();
            if (a.getWinner() == player) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            } else if (a.getWinner() == actualItem) {
                cell.setActor(null);
                System.out.println("GAME OVER");
            }
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

    public Actor getItem() {
        if (actualItem instanceof Item) {
            return actualItem;
        } else
            return null;
    }

    public void decreaseHealthBy(int damage) {
        this.health = health - damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
