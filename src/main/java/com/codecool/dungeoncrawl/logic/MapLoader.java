package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case 'c':
                            cell.setType(CellType.CLOSED_DOOR);
                            map.setDoorPosition(x, y);      // save the door position for change
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'k':
                            cell.setType(CellType.KEY);
                            new Key(cell);
                            break;
                        case 'w':
                            cell.setType(CellType.SWORD);
                            new Sword(cell);
                            break;
                        case 'W':
                            cell.setType(CellType.WALL);
                            new Water(cell);
                            break;
                        case 'b':
                            cell.setType(CellType.BRIDGE);
                            new Bridge(cell);
                            break;
                        case '1':
                            cell.setType(CellType.WALL);
                            new Tree1(cell);
                            break;
                        case '2':
                            cell.setType(CellType.WALL);
                            new Tree2(cell);
                            break;
                        case '3':
                            cell.setType(CellType.WALL);
                            new Tree3(cell);
                            break;
                        case '$':
                            cell.setType(CellType.STAIRS);
                            new Stairs(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
