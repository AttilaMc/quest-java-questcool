package com.codecool.quest.logic;


import java.util.Timer;
import java.util.TimerTask;

public class IntervalCodeRun extends TimerTask {

    private GameMap map;
    int loop = 5;

    public IntervalCodeRun(GameMap map){
        this.map = map;
    }





    @Override
    public void run() {

        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                try {
                    String tileName = map.cells[i][j].getActor().getTileName();
                    if (tileName.equals("skeleton")) {
                        int[] vector = map.cells[i][j].getActor().generateRandomVector();
                        map.cells[i][j].getActor().move(vector[0], vector[1]);

                    }
                } catch (NullPointerException e) {

                }
            }




        }
    }}
