package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.Main;


import java.util.ArrayList;

public class MonsterMoveThread extends Thread {
    public MonsterMoveThread() {
    }

    public void run(GameMap map, Cell[][] cells) {
        ArrayList<Skeleton> Skeletons = new ArrayList<>();

        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                try {
                    String tileName = cells[i][j].getActor().getTileName();
                    if (tileName.equals("skeleton")) {
                        int[] vector = cells[i][j].getActor().generateRandomVector();
                        cells[i][j].getActor().move(vector[0], vector[1]);
                    }
                } catch (NullPointerException e) {
                }
            }
        }
    }
}

