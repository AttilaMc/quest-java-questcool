package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.items.Sword;
import java.util.*;


import java.util.ArrayList;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    //0 = damage, 1 = health,
    private  Map< String,Integer> stats = new HashMap<String,Integer>();
    public int damage = 1;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        int validMove = preventOccupiedCell(nextCell);
        int swordMove = pickSwordUp(nextCell);
            if (validMove == 1){
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                //if(swordMove == 1){
                    //this.damage += 2;
               // }
            }


    }


    public int preventOccupiedCell(Cell cell){
        String cellType = cell.getType().toString();
        //if the cell.actor isnt null skeletons might wont attack, check later.
        if (cellType.equals("WALL") | cell.getActor() != null){
            return 0;
        }
        else{
            return 1;
        }
    }

    public int pickSwordUp(Cell cell){
        String cellType = cell.getType().toString();

        if(cellType.equals("SWORD")){
            return 1;
        } else {
            return 0;
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

    public int getDamage(){
        return damage;
    }
    public void increasePlayerDamageBySword(){
        this.damage += 2;
    }
}
