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

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        int validMove = preventOccupiedCell(nextCell);
        if (validMove == 1) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

    }

    public int preventOccupiedCell(Cell cell) {
        String cellType = cell.getType().toString();
        //if the cell.actor isnt null skeletons might wont attack, check later.
        if (cellType.equals("WALL") | cell.getActor() != null) {
            return 0;
        } else {
            return 1;
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
}
