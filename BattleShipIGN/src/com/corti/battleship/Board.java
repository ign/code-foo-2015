package com.corti.battleship;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Parent {
	
    private VBox rows = new VBox();
    private boolean enemy = false;
    public int ships = 5;
    private String death;

    public Board(boolean newEnemy, EventHandler<? super MouseEvent> handler) {
        enemy = newEnemy;
        for (int y = 0; y < 10; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 10; x++) {
                Cell cell = new Cell(x, y, this);
                cell.setOnMouseClicked(handler);
                row.getChildren().add(cell);
            }

            rows.getChildren().add(row);
        }

        getChildren().add(rows);
    }

    public boolean placeShip(Ship ship, int x, int y) {
        if (canPlaceShip(ship, x, y)) {
            int length = ship.type;

            if (ship.vertical) {
                for (int index = y; index < y + length; index++) {
                    Cell cell = getCell(x, index);
                    cell.ship = ship;
                    if (!enemy) {
                        cell.setFill(Color.BURLYWOOD);
                        cell.setStroke(Color.GAINSBORO);
                    }
                }
            }
            else {
                for (int index = x; index < x + length; index++) {
                    Cell cell = getCell(index, y);
                    cell.ship = ship;
                    if (!enemy) {
                        cell.setFill(Color.BURLYWOOD);
                        cell.setStroke(Color.GAINSBORO);
                    }
                }
            }

            return true;
        }

        return false;
    }

    public Cell getCell(int x, int y) 
    {
        return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

    private Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[] {
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1)
        };

        List<Cell> neighbors = new ArrayList<Cell>();

        for (Point2D p : points) {
            if (isValidPoint(p)) {
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }
        }

        return neighbors.toArray(new Cell[0]);
    }

    private boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.type;

        if (ship.vertical) {
            for (int index = y; index < y + length; index++) {
                if (!isValidPoint(x, index))
                    return false;

                Cell cell = getCell(x, index);
                if (cell.ship != null)
                    return false;

                for (Cell neighbor : getNeighbors(x, index)) {
                    if (!isValidPoint(x, index))
                        return false;

                    if (neighbor.ship != null)
                        return false;
                }
            }
        }
        else {
            for (int index = x; index < x + length; index++) {
                if (!isValidPoint(index, y))
                    return false;

                Cell cell = getCell(index, y);
                if (cell.ship != null)
                    return false;

                for (Cell neighbor : getNeighbors(index, y)) {
                    if (!isValidPoint(index, y))
                        return false;

                    if (neighbor.ship != null)
                        return false;
                }
            }
        }

        return true;
    }

    private boolean isValidPoint(Point2D point) {
        return isValidPoint(point.getX(), point.getY());
    }

    private boolean isValidPoint(double x, double y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    public class Cell extends Rectangle {
        public int x, y;
        public Ship ship = null;
        public boolean wasShot = false;
        public boolean wasShotSalvo = false;

        private Board board;

        public Cell(int x, int y, Board board) {
            super(30, 30);
            this.x = x;
            this.y = y;
            this.board = board;
            setFill(Color.BLUE);
            setStroke(Color.WHITE);
        }

        public boolean shoot() {
            wasShot = true;
            setFill(Color.BLACK);

            if (ship != null) {
                ship.hit();
                setFill(Color.RED);
                if (!ship.isAlive()) { 
                	death = ship.getName();
                    board.ships--;
                }
            }

            return false;
        }
        public boolean shootSalvo() {
            wasShotSalvo = true;
            setFill(Color.BLACK); 
            int count =0;
            if(count < board.ships){
            if (ship != null) {
                ship.hit();
                setFill(Color.RED);
                if (!ship.isAlive()) { 
                	death = ship.getName();
                    board.ships--;
                }
            }
            return true;
            }
            return false;
        }
        public String getShipName()
        {
        	return death;
        }
    }
}