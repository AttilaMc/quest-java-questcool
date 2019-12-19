package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.item.Item;

import java.util.LinkedList;


public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private LinkedList<Item> inventory = new LinkedList<Item>();
    public int damage = 1;
    public int keyCount;



    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        int validMove = preventOccupiedCell(nextCell);

        int swordMove = pickSwordUp(nextCell);
        if (validMove == 1) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            //if(swordMove == 1){
            //this.damage += 2;
            // }
        }
    }

    public int preventOccupiedCell(Cell cell) {

        String cellType = cell.getType().toString();
        //if the cell.actor isnt null skeletons might wont attack, check later.
        if (cellType.equals("WALL") || cell.getActor() != null || cellType.equals("CLOSEDDOOR")) {
            if(cellType.equals("CLOSEDDOOR") && this.keyCount >= 1){
                cell.setType(CellType.OPENDOOR);
                return 1;
            } else {
                return 0;
            }
        }  else {
            return 1;
        }

    }



    public int pickSwordUp(Cell cell) {
        String cellType = cell.getType().toString();

        if (cellType.equals("SWORD")) {

            addToInventory(cell.getItem());
            System.out.println("inventory size: " + inventory.size());
            return 1;
        } else {
            return 0;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int changeAmount) {
        this.health = health + changeAmount;
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

    public LinkedList<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public int getDamage() {
        return damage;
    }

    public int getKeyCount(){
        return keyCount;
    }

    public void increasePlayerDamageBySword() {
        this.damage += 2;

    }

    public void increaseKeyCount(){
        this.keyCount++;
    }

    public void increaseHealth(){
        this.health += 5;
    }
}

