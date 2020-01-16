package com.codecool.quest.logic;

import com.codecool.quest.Tiles;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.Main;
import java.util.Timer;
import com.codecool.quest.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.TimerTask;
import java.util.ArrayList;


public class MonsterMoveThread extends Thread{
    Timer timer = new Timer();
    GameMap mapp;
    GraphicsContext context;
    Canvas canvas;

    public MonsterMoveThread(GameMap map, GraphicsContext context, Canvas canvas) {
            mapp = map;
            this.context = context;
            this.canvas = canvas;
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < mapp.getWidth(); x++) {
            for (int y = 0; y < mapp.getHeight(); y++) {
                Cell cell = mapp.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }

            }

            //healthLabel.setText(" " + mapp.getPlayer().getHealth());
            //DamageLabel.setText(" " + mapp.getPlayer().getDamage());
            //keyCount.setText(" " + mapp.getPlayer().getKeyCount());

        }

    }




        @Override
        public void run() {// l is link

            while(true) {

                for (int i = 0; i < mapp.getWidth(); i++) {
                    for (int j = 0; j < mapp.getHeight(); j++) {
                        try {
                            String tileName = mapp.cells[i][j].getActor().getTileName();
                            if (tileName.equals("skeleton") || tileName.equals("manBearPig") || tileName.equals("pudingMonster")) {
                                int[] vector = mapp.cells[i][j].getActor().generateRandomVector();
                                mapp.cells[i][j].getActor().move(vector[0], vector[1]);

                            }
                        } catch (NullPointerException e) {

                        }

                    }

                }
                refresh();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }




            }
        }



       // timer.schedule(new IntervalCodeRun(map), 0, 1000);












