package com.example.pong.client;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.stage.Stage;


public class PongApplication extends Application {
    PongGameController game;
    GridPane mainMenu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        setBackgroundColor(root, Color.BLACK);
        mainMenu = new GridPane();
        root.setCenter(mainMenu);
        setupMenuButtons(mainMenu, primaryStage);
        primaryStage.setTitle("PONG");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    public void setupMenuButtons(GridPane menu, Stage primaryStage) {
        menu.setAlignment(Pos.CENTER);
        menu.setVgap(30);
        Button playButton = new Button("Play");
        playButton.setFont(Font.font("Impact", FontWeight.LIGHT, FontPosture.REGULAR, 45));
        playButton.setStyle("-fx-text-fill: white; -fx-background-color: black;");
        playButton.setMaxSize(700, 550);
        playButton.setMinSize(150, 80);
        menu.add(playButton, 0, 0);
        GridPane.setHalignment(playButton, HPos.CENTER);

        playButton.setOnAction(value -> {
            game = new PongGameController();
            game.start(primaryStage);
        });

        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.font("Impact", FontWeight.LIGHT, FontPosture.REGULAR, 45));
        exitButton.setStyle("-fx-text-fill: white; -fx-background-color: black;");
        exitButton.setMaxSize(800, 650);
        exitButton.setMinSize(150, 80);
        menu.add(exitButton, 0, 2);
        GridPane.setHalignment(exitButton, HPos.CENTER);

        exitButton.setOnAction(value -> {
            Runtime.getRuntime().exit(0);
        });
    }

    public void setBackgroundColor(Pane node, Paint fill) {
        node.setBackground(new Background(new BackgroundFill(fill, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}