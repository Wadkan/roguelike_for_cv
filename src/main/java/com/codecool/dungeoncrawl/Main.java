package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Item;
import com.codecool.dungeoncrawl.logic.actors.Key;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    GameMap map1 = MapLoader.loadMap("/map1.txt");
    GameMap map2 = MapLoader.loadMap("/map2.txt");
    GameMap map3 = MapLoader.loadMap("/map3.txt");
    GameMap winner = MapLoader.loadMap("/winner.txt");
    int level = 1;
    GameMap map = map1;

    int[] doorPoz;
    Inventory inventory = new Inventory();
    boolean ifMoved = false;
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();
    Text inventoryList = new Text();
    Button pickUpInventoryItem;
    Text totalDamage = new Text("" + map.getPlayer().getDamage());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Damage: "), 0, 1);
        ui.add(totalDamage, 1, 1);
        ui.add(new Label("Inventory items: "), 0, 2);
        ui.add(inventoryLabel, 1, 2);
        ui.add(inventoryList, 0, 3);

        BorderPane borderPane = new BorderPane();

        pickUpInventoryItem = new Button("Pick up item");
        pickUpInventoryItem.setOnAction(actionEvent -> {
            Item itemToPickUp = this.map.getPlayer().getCell().getActor().getItem();
            if (ifMoved) {
                ifMoved = false;
                inventory.addItemToInventory(itemToPickUp);
                if (itemToPickUp.getTileName().equals("sword")) {
                    int playerDamage = this.map.getPlayer().getDamage();
                    this.map.getPlayer().setDamage(playerDamage + 1);
                }
                inventoryList.setText(inventory.getItemsList());
                this.map.getPlayer().getCell().setType(CellType.FLOOR);     // remove the item after pick up
                if (inventory.getKeysNumber() > 0) {
                    doorPoz = map.getDoorPosition();
                    this.map.getCell(doorPoz[0], doorPoz[1]).setType(CellType.OPENED_DOOR);     // change closed door to opened
                }
                refresh();
            }
            borderPane.requestFocus();
        });
        ui.add(pickUpInventoryItem, 0, 10);

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();

        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        borderPane.requestFocus();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        ifMoved = true;
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
        }
    }

    private void refresh() {
        if (this.map.getPlayer().getIfStepIntoTheDoor()) {  // TODO load the new track
            System.out.println("STEP OUT");
            this.level++;
            System.out.println(this.level);
        }
        if (this.level == 1) {
            this.map = map1;
        } else if (this.level == 2) {
            this.map = map2;
        } else if (this.level == 3) {
            this.map = map3;
        } else if (this.level > 3) {
            System.out.println("WINNER");
            this.map = winner;
        }

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
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        inventoryLabel.setText("" + inventory.getItemsNumber());
        totalDamage.setText("" + map.getPlayer().getDamage());
    }
}
