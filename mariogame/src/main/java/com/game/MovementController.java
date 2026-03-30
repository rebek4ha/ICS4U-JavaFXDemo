package com.game;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.HashSet;
import java.util.Set;

public class MovementController {

    @FXML private Pane gamePane;
    @FXML private ImageView player;

    @FXML private Rectangle wall1;
    @FXML private Rectangle wall2;
    @FXML private Rectangle wall3;
    @FXML private Rectangle wall4;
    @FXML private Rectangle wall5;

    @FXML private Text collisionText;

    private final double playerSpeed = 4;

    private Set<String> keysPressed = new HashSet<>();

    @FXML
    public void initialize() {
        
        gamePane.setOnMouseClicked(e -> gamePane.requestFocus());
        gamePane.setFocusTraversable(true);
        gamePane.requestFocus();

        gamePane.setOnKeyPressed(this::keyPressed);
        gamePane.setOnKeyReleased(this::keyReleased);

        // JavaFX game loop
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

        gameLoop.start();
    }

    private void keyPressed(KeyEvent event) {
        keysPressed.add(event.getCode().toString());
    }

    private void keyReleased(KeyEvent event) {
        keysPressed.remove(event.getCode().toString());
    }

    private void update() {
        double dx = 0;
        double dy = 0;

        if (keysPressed.contains("W") || keysPressed.contains("UP")) {
            dy -= playerSpeed;
        }
        if (keysPressed.contains("S") || keysPressed.contains("DOWN")) {
            dy += playerSpeed;
        }
        if (keysPressed.contains("A") || keysPressed.contains("LEFT")) {
            dx -= playerSpeed;
        }
        if (keysPressed.contains("D") || keysPressed.contains("RIGHT")) {
            dx += playerSpeed;
        }

        movePlayer(dx, dy);
    }

    private void movePlayer(double dx, double dy) {

    player.setTranslateX(player.getTranslateX() + dx);
    player.setTranslateY(player.getTranslateY() + dy);

    checkCollision();
}

    private void checkCollision() {
        if (player.getBoundsInParent().intersects(wall1.getBoundsInParent()) ||
            player.getBoundsInParent().intersects(wall2.getBoundsInParent()) ||
            player.getBoundsInParent().intersects(wall3.getBoundsInParent()) ||
            player.getBoundsInParent().intersects(wall4.getBoundsInParent()) ||
            player.getBoundsInParent().intersects(wall5.getBoundsInParent())) {

            collisionText.setText("Collision detected!");
        } else {
            collisionText.setText("No collision");
        }
    }
}