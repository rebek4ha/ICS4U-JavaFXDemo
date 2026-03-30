package com.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    var url = App.class.getResource("/com/game/MovementController.fxml");

    System.out.println("URL = " + url);

    if (url == null) {
        throw new RuntimeException("FXML file NOT FOUND on classpath");
    }

    FXMLLoader loader = new FXMLLoader(url);

    Scene scene = new Scene(loader.load(), 800, 600);
    stage.setScene(scene);
    stage.show();
}
    
    public static void main(String[] args) {
        launch();
    }
}