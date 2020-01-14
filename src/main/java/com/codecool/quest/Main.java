package com.codecool.quest;

import com.codecool.quest.logic.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class Main extends Application {
    private GameMap map = MapLoader.loadMap("/map.txt");

    private Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);


    private GraphicsContext context = canvas.getGraphicsContext2D();
    private Label healthLabel = new Label();
    private Label DamageLabel = new Label();
    private Label keyCount = new Label();
    private Label inventory = new Label();
    private Button buttonPickup = new Button("Pick-up");
    Timer timer = new Timer();


    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(new Label("Damage: "), 0, 1);
        ui.add(new Label("Key Count"), 0, 2);
        ui.add(new Label("Inventory"), 0, 3);

        ui.add(healthLabel, 1, 0);
        ui.add(DamageLabel, 1, 1);
        ui.add(keyCount, 2, 2);
        ui.add(inventory, 2, 3);
        ui.add(buttonPickup, 3, 4);

        BorderPane borderPane = new BorderPane();



        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        refresh();


        scene.setOnKeyPressed(this::onKeyPressed);


        buttonPickup.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    for (int x = 0; x < map.getWidth(); x++) {
                        for (int y = 0; y < map.getHeight(); y++) {
                            Cell cell = map.getCell(x, y);
                            if (cell.getTileName().equals("sword") && cell.getActor() != null) {
                                cell.getActor().increasePlayerDamageBySword();
                                cell.setType(CellType.FLOOR);
                            } else if (cell.getTileName().equals("key") && cell.getActor() != null) {
                                cell.getActor().increaseKeyCount();
                                cell.setType(CellType.FLOOR);
                                refresh();
                            } else if (cell.getTileName().equals("health") && cell.getActor() != null) {
                                cell.getActor().increaseHealth();
                                cell.setType(CellType.FLOOR);
                                refresh();
                            } else if (cell.getTileName().equals("next") && cell.getActor() != null){
                                map = MapLoader.loadMap("/map2.txt");
                                context = canvas.getGraphicsContext2D();
                                borderPane.setCenter(canvas);

                                System.out.println("hsdf");
                            }
                        }
                    }


                });

        //Scene 2
        /*
        for(int x = 0; x < map.getWidth(); x++){
            for (int y = 0; y < map.getHeight(); y++){
                Cell cell = map.getCell(x, y);
                if(cell.getTileName().equals("next") && cell.getActor() != null){
                    //primaryStage.setScene(scene);
                    //refresh(map2);
                    System.out.println("heyasdf");
                }
            }
        }
        */



        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();

    }

    public void setStage(String level) {
        map = MapLoader.loadMap(level);
        context = canvas.getGraphicsContext2D();
    }


    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case S:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case A:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case D:
                map.getPlayer().move(1, 0);
                refresh();
                break;
            case F:
                MonsterMoveThread monsterMoveThread = new MonsterMoveThread(map, context, canvas);
                monsterMoveThread.start();
                System.out.println("Main method executed by main thread");
                System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
                //timer.schedule(new IntervalCodeRun(map), 0, 1000);
                refresh();
                break;



        }
    }


    public void refresh() {

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }

            }

            healthLabel.setText(" " + map.getPlayer().getHealth());
            DamageLabel.setText(" " + map.getPlayer().getDamage());
            keyCount.setText(" " + map.getPlayer().getKeyCount());

        }

    }

    public void refresh(GameMap map) {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }

            }

            healthLabel.setText(" " + map.getPlayer().getHealth());
            DamageLabel.setText(" " + map.getPlayer().getDamage());
            keyCount.setText(" " + map.getPlayer().getKeyCount());

        }

    }


    public void SkeletonsMove(){

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
        refresh();
    }
}






