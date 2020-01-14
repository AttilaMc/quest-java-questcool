package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.Police;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.item.Heart;
import com.codecool.quest.logic.item.Sword;
import com.codecool.quest.logic.item.Key;



import java.io.InputStream;
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
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case 'S':
                            cell.setType(CellType.SWORD);
                            new Sword(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'K':
                            cell.setType(CellType.KEY);
                            new Key(cell);
                            break;
                        case 'O':
                            cell.setType(CellType.OPENDOOR);
                            break;
                        case 'D':
                            cell.setType(CellType.CLOSEDDOOR);
                            break;
                        case 'H':
                            cell.setType(CellType.HEALTH);
                            new Heart(cell);
                            break;
                        case 'P':
                            cell.setType(CellType.POLICE);
                            new Police(cell);
                            break;
                        case 'U':
                            cell.setType(CellType.NEXT);
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
