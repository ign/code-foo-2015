package com.corti.battleship;

import java.util.Optional;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.corti.battleship.Board.Cell;

public class Game extends Application {
	private String lost;
	
    private boolean running = false;
    private Board enemyBoard, playerBoard;
    private int shipsToPlace = 5;

    private boolean enemyTurn = false;
    private Random random = new Random();
    private String[] ships={"Aircraft carrier","Battleship","Submarine","Destroyer","Patrol boat"};
    private int gameType = 0;
   
   
    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(700, 800);
        int type = shipsToPlace;
        if(gameType == 1){
        	enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            root.setRight(new Text("Lost Ships " + lost));
            Cell cell = (Cell) event.getSource();
            if (cell.wasShot){
                lost += "\n"+cell.getShipName();
                return;
            }
            
            enemyTurn = !cell.shoot();
            
            if (enemyBoard.ships == 0) {
              	Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Winner");
            	alert.setHeaderText(null);
            	alert.setContentText("Winner winner chicken dinner!");

            	alert.showAndWait(); 
                System.exit(0);
            }

            if (enemyTurn){
                enemyMove();
            }
        });
        
        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource(); 
            if (playerBoard.placeShip(new Ship(shipsToPlace + 1, ships[type-1] , event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                } 
                	
            }
        });
        }else if(gameType ==2){
        	enemyBoard = new Board(true, event -> {
                if (!running)
                    return;

                root.setRight(new Text("Lost Ships " + lost));
                Cell cell = (Cell) event.getSource();
                if (cell.wasShotSalvo){
                	lost += "\n"+cell.getShipName();
                    return;
                } 
                enemyTurn = !cell.shoot();
                if (enemyBoard.ships == 0) {
                  	Alert alert = new Alert(AlertType.INFORMATION);
                	alert.setTitle("Winner");
                	alert.setHeaderText(null);
                	alert.setContentText("Winner winner chicken dinner!");

                	alert.showAndWait(); 
                    System.exit(0);
                }

                if (enemyTurn){
                    enemyMove();
                }
            });
            
            playerBoard = new Board(false, event -> {
                if (running)
                    return;

                Cell cell = (Cell) event.getSource(); 
                if (playerBoard.placeShip(new Ship(shipsToPlace + 1, ships[type-1] , event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                    if (--shipsToPlace == 0) {
                        startGame();
                    } 
                    	
                }
            });
        }
        VBox vbox = new VBox(50, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);

        return root;
    }

    private void enemyMove() {
        while (enemyTurn) {
        	
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) { 
            	Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Losser");
            	alert.setHeaderText(null);
            	alert.setContentText("You have lost!");

            	alert.showAndWait();
            	main(null);
                //System.exit(0);
            }
        }
    }

    private void startGame() {
        // place enemy ships
    	
        int type = 5;

        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type + 1, ships[type - 1], Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        running = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Game Type");
    	alert.setHeaderText("Select your game type");
    	alert.setContentText("Choose your option.");

    	ButtonType buttonTypeOne = new ButtonType("Standard");
    	ButtonType buttonTypeTwo = new ButtonType("Salvo");
    	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

    	alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == buttonTypeOne){
    	    gameType = 1;
    	} else if (result.get() == buttonTypeTwo) {
    	    gameType = 2;
	} else {
		 gameType = 1;
    	}
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship IGN");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}