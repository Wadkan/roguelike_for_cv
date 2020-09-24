package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Attack;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    protected Actor actualItem;
    private Actor player;
    private int damage;
    private boolean ifStepIntoTheDoor = false;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public Actor itemToPickUp;

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        String tileName = "";
        actualItem = null;
        player = cell.getActor();

        try {
            // check tile name: skeleton, sword, key...
            tileName = nextCell.getActor().getTileName();
            this.actualItem = nextCell.getActor();
        } catch (Exception ignored) {
        }

        // if tile is a wall, do not move
        String nextCellTitle = nextCell.getTileName();

        if (nextCellTitle.equals("sword") || nextCellTitle.equals("heart")) {
            itemToPickUp = nextCell.getActor();
            System.out.println("kuki " + itemToPickUp);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

        if (nextCellTitle.equals("floor")) {
            System.out.println("kaki " + itemToPickUp);
            cell.setActor(itemToPickUp);
            nextCell.setActor(this);
            cell = nextCell;
            itemToPickUp = null;
        }


        if (!nextCellTitle.equals("wall") && !nextCellTitle.equals("closedDoor") && !tileName.equals("skeleton")) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

        if (tileName.equals("skeleton") || tileName.equals("bat")) {   // if tile is a skeleton: FIGHT TODO: implement fight
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
        } else if (tileName.equals("sword") || tileName.equals("key")) {   // if tile is an item (sward, key...): CAN PUT IT TODO put an item
            System.out.println(tileName);
        }

        if (nextCellTitle.equals("openedDoor")) {
            ifStepIntoTheDoor = true;
            System.out.println("NEW MAP");
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

    public Item getItem() {
        if (actualItem instanceof Item) {
            return (Item) actualItem;
        } else
            return null;
    }

    public boolean getIfStepIntoTheDoor() {
        return this.ifStepIntoTheDoor;
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
