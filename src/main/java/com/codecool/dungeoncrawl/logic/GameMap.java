package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private int[] doorPosition;
    private ArrayList<Skeleton> skeletonList = new ArrayList<Skeleton>();

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDoorPosition(int x, int y) {
        this.doorPosition = new int[]{x, y};
    }

    public int[] getDoorPosition() {
        return this.doorPosition;
    }

    public void setSkeleton(Skeleton skeleton) {
        this.skeletonList.add(skeleton);
    }

    public ArrayList<Skeleton> getSkeletons() {
        return this.skeletonList;
    }

    public void removeSkeleton(Skeleton skeleton) {
        this.skeletonList.remove(skeleton);
    }
}
