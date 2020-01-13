package com.codecool.quest.logic;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.Cell;

import java.util.ArrayList;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

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

    public void getSkeletons() {
        ArrayList<Skeleton> Skeletons = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                try {
                    String tileName = cells[i][j].getActor().getTileName();
                    if (tileName.equals("skeleton")) {
                        int[] vector = cells[i][j].getActor().generateRandomVector();
                        cells[i][j].getActor().move(vector[0], vector[1]);
                    }
                }
                catch (NullPointerException e){

                }


                // System.out.println(Integer.toString(i));
                //

            }
        }
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
