package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Item;
import com.codecool.dungeoncrawl.logic.actors.Key;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
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
    // initial level
    int level = 1;
    boolean ifFirstTrack = true;

    GameMap map1 = MapLoader.loadMap("/map1.txt");
    GameMap map2 = MapLoader.loadMap("/map2.txt");
    GameMap map3 = MapLoader.loadMap("/map3.txt");
    GameMap winner = MapLoader.loadMap("/mapWinner.txt");

    // initial map
    GameMap map = map1;

    // for map moving
    /**
     * rangeY and rangeX that show the screen
     */
    int rangeX = 18;
    int rangeY = 18;

    int playerX = this.map.getPlayer().getX();
    int playerY = this.map.getPlayer().getY();

    int xShift;
    int yShift;

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
            this.map.getPlayer().getCell().getActor().itemToPickUp = null;
            if (ifMoved) {
                ifMoved = false;
                inventory.addItemToInventory(itemToPickUp);
                if (itemToPickUp.getTileName().equals("sword")) {
                    int playerDamage = this.map.getPlayer().getDamage();
                    this.map.getPlayer().setDamage(playerDamage + 1);
                } else if (itemToPickUp.getTileName().equals("heart")) {
                    int playerHealth = this.map.getPlayer().getHealth();
                    this.map.getPlayer().setHealth(playerHealth + 2);
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
        // moving the map
        playerX = this.map.getPlayer().getX();
        playerY = this.map.getPlayer().getY();

        ifMoved = true;
        try {
            map.getSkeletons().forEach(skeleton -> {
                if (skeleton.getHealth() <= 0) {
                    map.removeSkeleton(skeleton);
                }
            });
            map.getSkeletons().forEach(Skeleton::randomMove);
        } catch (Exception ignored) {}
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);

                if (rangeY / 2 < playerY + 1 && playerY < map.getHeight() - rangeY / 2) {
                    yShift = playerY - rangeY / 2;
                }

                refresh();
                break;

            case DOWN:
                map.getPlayer().move(0, 1);

                if (rangeY / 2 < playerY && playerY < map.getHeight() - rangeY / 2 + 1) {
                    yShift = playerY - rangeY / 2;
                }

                refresh();
                break;

            case LEFT:
                map.getPlayer().move(-1, 0);

                if (rangeX / 2 < playerX + 1 && playerX < map.getWidth() - rangeX / 2) {
                    xShift = playerX - rangeX / 2;
                }

                refresh();
                break;

            case RIGHT:
                map.getPlayer().move(1, 0);

                if (rangeX / 2 < playerX && playerX < map.getWidth() - rangeX / 2 + 1) {
                    xShift = playerX - rangeX / 2;
                }

                refresh();
                break;
        }
    }

    private void getFirstMapPoz() {
        ifFirstTrack = false;
        System.out.println("track set");

        if (playerX - rangeX / 2 < 1) {
            xShift = 0;
        } else if (playerX - rangeX / 2 >= this.map.getWidth()) {
        } else {
            xShift = map.getWidth() - rangeX;
        }

        if (playerY - rangeY / 2 < 1) {
            yShift = 0;
        } else if (playerY - rangeY / 2 >= this.map.getHeight()) {
        } else {
            yShift = map.getHeight() - rangeY;
        }
    }

    private void refresh() {
        if (this.map.getPlayer().getIfStepIntoTheDoor()) {
            System.out.println("STEP OUT");
            this.level++;
            ifFirstTrack = true;
            /** get player coordinates, only when first step into a new map */
        }
        if (ifFirstTrack) {
            this.getFirstMapPoz();
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

        canvas.setWidth(rangeX * Tiles.TILE_WIDTH);
        canvas.setHeight(rangeY * Tiles.TILE_WIDTH);

//        this.context = canvas.getGraphicsContext2D();

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        /** move the map with showFromY and showFromX. They calculated in onKeyPressed method above */

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x - xShift, y - yShift);
                } else {
                    Tiles.drawTile(context, cell, x - xShift, y - yShift);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        inventoryLabel.setText("" + inventory.getItemsNumber());
        totalDamage.setText("" + map.getPlayer().getDamage());
    }
}
