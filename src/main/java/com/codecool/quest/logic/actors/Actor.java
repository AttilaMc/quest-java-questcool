package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.item.Item;

import java.util.Random;

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

    public int[] generateRandomVector() {
        Random randX = new Random();
        Random randY = new Random();
        int[] moveVector = new int[2];
        int bigRandX = randX.nextInt(1000);
        int bigRandY = randY.nextInt(1000);
        if (bigRandX < 333) {
            moveVector[0] = -1;
        } else if (bigRandX >= 333 && bigRandX < 666) {
            moveVector[0] = 0;
        } else if (bigRandX > 666) {
            moveVector[0] = 1;
        }

        if (bigRandY < 333) {
            moveVector[1] = -1;
        } else if (bigRandY >= 333 && bigRandY < 666) {
            moveVector[1] = 0;
        } else if (bigRandY > 666) {
            moveVector[1] = 1;
        }
        return moveVector;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        int attackMove = checkIfMoveIsAttack(nextCell);
        if (attackMove == 1 && nextCell.getActor() != cell.getActor()) {
            fight(cell, nextCell);
        }
        int validMove = preventOccupiedCell(nextCell);

        if (validMove == 1) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public int preventOccupiedCell(Cell cell) {

        String cellType = cell.getType().toString();
        if (cellType.equals("WALL") || cell.getActor() != null || cellType.equals("CLOSEDDOOR") || cellType.equals("police")) {
            if (cellType.equals("CLOSEDDOOR") && this.keyCount >= 1) {
                cell.setType(CellType.OPENDOOR);
                return 1;
            } else {
                return 0;
            }
        } else {
            return 1;
        }

    }

    public int checkIfMoveIsAttack(Cell cell) {
        if (cell.getActor() != null) {
            return 1;
        }
        return 0;
    }

    public void fight(Cell cell, Cell nextCell) {
        actorAttack(cell, nextCell);
        actorAttack(nextCell, cell);
    }

    public void actorAttack(Cell cell, Cell nextCell) {
        int actorInActualCellDamage = cell.getActor().getDamage();
        nextCell.getActor().setHealth(-1 * actorInActualCellDamage);
        if (nextCell.getActor().health <= 0) {
            nextCell.setActor(null);
            nextCell.setType(CellType.FLOOR);

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

    public void setHealth(int modifyingValue) {
        health += modifyingValue;

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

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public int getDamage() {
        return damage;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public void increasePlayerDamageBySword() {
        this.damage += 2;

    }

    public void increaseKeyCount() {
        this.keyCount++;
    }

    public void increaseHealth() {
        this.health += 5;
    }
}

