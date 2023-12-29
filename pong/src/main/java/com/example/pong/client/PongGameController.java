package com.example.pong.client;

import java.util.ArrayList;
import java.util.List;

import com.example.pong.client.game_objects.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

public class PongGameController {
    public Parent mainMenu = null;
    public Parent parent = null;

    public List<GameObject> gameObjects = new ArrayList<>();
    public PongClient client = new PongClient("10.17.77.12", 1234, null);
    private int point1 = 0;
    private int point2 = 0;
    private boolean isGameOver = false;
    private boolean pressedUp = false;
    private boolean pressedDown = false;

    public void start(Stage primaryStage) {
        if (parent == null) {
            BorderPane root = new BorderPane();
            parent = root;
            mainMenu = primaryStage.getScene().getRoot();
            GridPane topMenu = new GridPane();
            root.setTop(topMenu);
            BorderPane.setAlignment(topMenu, Pos.CENTER);
            setBackgroundColor(topMenu, Color.DARKGREY);
            setupTopMenu(topMenu, primaryStage);
            SetupKeyListener(primaryStage);
            gameLoop(root);
        }
        primaryStage.setTitle("PONG");
        primaryStage.getScene().setRoot(parent);
        primaryStage.show();
    }


    public void SetupKeyListener(Stage primaryStage) {
        primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case K:
                        pressedUp = true;
                        break;
                    case M:
                        pressedDown = true;
                        break;
                    default:
                        break;
                }
            }
        });

        primaryStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case K:
                        pressedUp = false;
                        break;
                    case M:
                        pressedDown = false;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void setupTopMenu(GridPane topMenu, Stage primaryStage) {
        HBox buttonsBox = new HBox(20);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.prefWidthProperty().bind(topMenu.widthProperty());
        Button connectToServer = new Button("Connect");
        connectToServer.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 15));
        connectToServer.setStyle("-fx-text-fill: white; -fx-background-color: black;");
        connectToServer.setMinSize(90, 30);
        connectToServer.setOnAction(value -> {
            client.connectToServer();
        });
        Button returnToMenu = new Button("Return to menu");
        returnToMenu.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 15));
        returnToMenu.setStyle("-fx-text-fill: white; -fx-background-color: black;");
        returnToMenu.setMinSize(90, 30);
        returnToMenu.setOnAction(value -> {
            primaryStage.setTitle("Menu");
            primaryStage.getScene().setRoot(mainMenu);
            if (client.isConnected()) {
                client.disconnectFromServer();
            }
        });
        buttonsBox.getChildren().addAll(connectToServer, returnToMenu);
        topMenu.add(buttonsBox, 0, 0);
    }

    public void gameLoop(BorderPane root) {
        Pane wrapperPane = new Pane();
        root.setCenter(wrapperPane);
        BorderPane.setAlignment(wrapperPane, Pos.CENTER);
        setBackgroundColor(wrapperPane, Color.BLACK);

        Canvas gameCanvas = new Canvas();
        wrapperPane.getChildren().add(gameCanvas);

        gameCanvas.widthProperty().bind(wrapperPane.widthProperty());
        gameCanvas.heightProperty().bind(wrapperPane.heightProperty());

        Ball ball = new Ball(new Vector2D(), new Vector2D(10, 10), Color.WHITE);
        Paddle paddle1 = new Paddle(new Vector2D(), new Vector2D(10, 35), Color.WHITE);
        Paddle paddle2 = new Paddle(new Vector2D(), new Vector2D(10, 35), Color.WHITE);

        TextGameObject scoreOne = new TextGameObject(Integer.toString(point1),
                Font.font("impact", 40), new Vector2D(), new Vector2D(), Color.WHITE);
        TextGameObject scoreTwo = new TextGameObject(Integer.toString(point2),
                Font.font("impact", 40), new Vector2D(), new Vector2D(), Color.WHITE);

        TextGameObject winner = new TextGameObject("You win!",
                Font.font("impact", 80), new Vector2D(-30, 0), new Vector2D(), Color.WHITE);
        TextGameObject loser = new TextGameObject("You lose!",
                Font.font("impact", 80), new Vector2D(-35, 0), new Vector2D(), Color.WHITE);

        gameObjects.add(ball);
        gameObjects.add(paddle1);
        gameObjects.add(paddle2);
        gameObjects.add(scoreOne);
        gameObjects.add(scoreTwo);

        ball.setSpeed(new Vector2D(10, 20));
        GraphicsContext context = gameCanvas.getGraphicsContext2D();
        GameObject.setCanvasWidth(200);
        GameObject.setCanvasHeight(200);
        paddle1.setPosition(-(GameObject.getCanvasWidth() / 2) + 5, 0);
        paddle2.setPosition((GameObject.getCanvasWidth() / 2) - 10, 0);
        scoreOne.setPosition(-(GameObject.getCanvasWidth() / 2) + 50, GameObject.getCanvasHeight()/2);
        scoreTwo.setPosition((GameObject.getCanvasWidth() / 2) - 50, GameObject.getCanvasHeight()/2);

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                if (client.isConnected() && client.ready && !isGameOver) {
                    updateScene(gameCanvas, context);
                    highlightMyPaddle(paddle1, paddle2);
                    updateBallPosition(ball, paddle1, paddle2);
                    updatePaddlePosition(paddle1, paddle2);
                    addPoint(ball);
                    checkPoint(ball, winner, loser);
                    updatePoint(scoreOne, scoreTwo);
                    updatePaddlesPositions(paddle1, paddle2);
                    drawObject(context);
                }
            }
        }.start();
    }

    public void updateScene(Canvas gameCanvas, GraphicsContext context) {
        int windowWidth = (int) gameCanvas.getWidth();
        int windowHeight = (int) gameCanvas.getHeight();
        GameObject.setWindowWidth(windowWidth);
        GameObject.setWindowHeight(windowHeight);
        context.clearRect(0, 0, windowWidth, windowHeight);
    }

    public void highlightMyPaddle(Paddle paddle1, Paddle paddle2) {
        if (client.playerNumber == 1) {
            paddle1.setFillColor(Color.ORANGE);
        } else {
            paddle2.setFillColor(Color.ORANGE);
        }
    }

    public void updateBallPosition(GameObject ball, GameObject paddle1, GameObject paddle2) {
        ball.ballSpeed();
        if (ball.isCollision(paddle1)) {
            ball.setSpeedX(Math.abs(ball.getSpeed().x));
        } else if (ball.isCollision(paddle2)) {
            ball.setSpeedX(-(Math.abs(ball.getSpeed().x)));
        }
    }

    public void updatePaddlePosition(GameObject paddle1, GameObject paddle2) {
        if (client.playerNumber == 1) {
            if (pressedUp && (paddle1.getPositionY() >= -(GameObject.getCanvasHeight() / 2))) {
                paddle1.setPosition(new Vector2D(paddle1.getPositionX(), paddle1.getPositionY() - 2));
            }
            if (pressedDown && (paddle1.getPositionY() <= (GameObject.getCanvasHeight() / 2) - 40)) {
                paddle1.setPosition(new Vector2D(paddle1.getPositionX(), paddle1.getPositionY() + 2));
            }
        } else if (client.playerNumber == 2) {
            if (pressedUp && (paddle2.getPositionY() >= -(GameObject.getCanvasHeight() / 2))) {
                paddle2.setPosition(new Vector2D(paddle2.getPositionX(), paddle2.getPositionY() - 2));
            }
            if (pressedDown && (paddle2.getPositionY() <= (GameObject.getCanvasHeight() / 2) - 40)) {
                paddle2.setPosition(new Vector2D(paddle2.getPositionX(), paddle2.getPositionY() + 2));
            }
        }
    }

    public void addPoint(GameObject ball) {
        if (ball.position.x <= -(GameObject.getCanvasWidth() / 2)) {
            point2++;
            resetBall(ball);
        } else if (ball.position.x >= (GameObject.getCanvasWidth() / 2)) {
            point1++;
            resetBall(ball);
        }
    }

    public void checkPoint(GameObject ball, GameObject winner, GameObject loser) {
        if (point1 >= 10 && !isGameOver) {
            if (client.playerNumber == 1) {
                gameObjects.add(winner);
            } else {
                gameObjects.add(loser);
            }
            stopBall(ball);
            isGameOver = true;
            client.disconnectFromServer();
        } else if (point2 >= 10 && !isGameOver) {
            if (client.playerNumber == 1) {
                gameObjects.add(loser);
            } else {
                gameObjects.add(winner);
            }
            stopBall(ball);
            isGameOver = true;
            client.disconnectFromServer();
        }
    }

    public void updatePoint(TextGameObject scoreOne, TextGameObject scoreTwo) {
        scoreOne.setText(Integer.toString(point1));
        scoreTwo.setText(Integer.toString(point2));
    }

    public void drawObject(GraphicsContext context) {
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).drawObject(context);
        }
    }

    public void updatePaddlesPositions(GameObject paddle1, GameObject paddle2) {
        switch (client.playerNumber) {
            case 1:
                paddle1.sendPositionData(client);
                paddle2.setPositionY(client.readPosition());
                break;
            case 2:
                paddle2.sendPositionData(client);
                paddle1.setPositionY(client.readPosition());
                break;
            default:
                break;
        }
    }
    public void resetBall(GameObject ball) {
        ball.setPosition(new Vector2D());
        ball.setSpeed(new Vector2D(15, 20));
    }
    public void stopBall(GameObject ball) {
        ball.setPosition(new Vector2D());
        ball.setSpeed(new Vector2D());
    }
    public void setBackgroundColor(Pane node, Paint fill) {
        node.setBackground(new Background(new BackgroundFill(fill, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}


