package com.example.pong.client.game_objects;

import java.util.ArrayList;
import java.util.List;

import com.example.pong.client.PongClient;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    protected static int WIDTH = 600;
    protected static int HEIGHT = 400;
    protected static int canvasWidth;
    protected static int canvasHeight;

    public Vector2D position;
    protected Vector2D size;
    protected Vector2D speed = new Vector2D();
    protected Vector2D accelleration = new Vector2D();

    protected GameObject(Vector2D position, Vector2D size) {
        setPosition(position);
        setSize(size);
    }

    public void setSpeed(Vector2D speed) {
        this.speed = speed;
    }

    public void setSpeedX(double x) {
        speed.x = x;
    }

    public Vector2D getSpeed() {
        return speed;
    }

    public void ballSpeed() {
        double dt = 0.05;
        position = position.add(speed.mult(dt)).add
                (accelleration.mult(dt * dt).mult(0.5));

    }

    private void isWallCollision() {
        if (position.y > getCanvasHeight() / 2 - getSizeY()) {
            speed.y = Math.abs(speed.y) * (-1);
        } else if (position.y < -(getCanvasHeight()) / 2) {
            speed.y = Math.abs(speed.y);
        }
    }

    private boolean isPointCollision(Vector2D point, Vector2D pos, Vector2D size) {
        if ((point.x < (pos.x + size.x / 2)) && (point.x > (pos.x - size.x / 2))) {
            return (point.y < pos.y + size.y / 2) && (point.y > pos.y - size.y / 2);
        }
        return false;
    }

    private boolean isBallCollision(Vector2D point1, double r1, Vector2D point2, double r2) {
        if ((point2.sub(point1).getLength() < (r1 + r2))) {
            return false;
        }
        return false;
    }

    public boolean isCollision(GameObject paddle) {
        isWallCollision();
        Vector2D topLeft = paddle.getPosition();
        Vector2D topRight = new Vector2D(paddle.getPositionX() + paddle.getSizeX(),
                paddle.getPositionY());
        Vector2D bottomLeft = new Vector2D(paddle.getPositionX(),
                paddle.getPositionY() + paddle.getSizeY());
        Vector2D bottomRight = new Vector2D(paddle.getPositionX() + paddle.getSizeX(),
                paddle.getPositionY() + paddle.getSizeY());

        double diameter = getSizeX();
        double r1W = paddle.getSizeX();
        double r1H = paddle.getSizeY();
        double r2W = paddle.getSizeX();
        double r2H = paddle.getSizeY();
        Vector2D[] paddlePoints = new Vector2D[]{topLeft, topRight, bottomLeft, bottomRight};
        Vector2D ballCenter = position.add(size.mult(0.5));
        Vector2D paddleCenter = paddle.getPosition().add(paddle.getSize().mult(0.5));
        for (Vector2D point : paddlePoints) {
            if (isBallCollision(point, diameter / 2, ballCenter, diameter / 2)) {
                return true;
            }
        }
        if (isPointCollision(ballCenter, paddleCenter, new Vector2D(r1W, r1H))) {
            return true;
        }
        if (isPointCollision(ballCenter, paddleCenter, new Vector2D(r2W, r2H))) {
            return true;
        }
        return false;
    }


    public void drawObject(GraphicsContext gc) {
    }

    protected List<Vector2D> convertToDrawing(Vector2D position, Vector2D size) {
        List<Vector2D> coord = new ArrayList();
        coord.add(new Vector2D());
        coord.add(new Vector2D());
        double aspectRatio = (double) WIDTH / HEIGHT;
        double acpectDrawingRatio = canvasWidth * aspectRatio;
        double percentPosX = (position.x + ((double) canvasWidth / 2)) / canvasWidth;
        double percentPosY = (position.y + ((double) canvasHeight / 2)) / canvasHeight;
        double windowPosX = percentPosX * (double) WIDTH;
        double windowPosY = percentPosY * (double) HEIGHT;
        coord.get(0).set(windowPosX, windowPosY);
        double percentSizeX = (size.x) / acpectDrawingRatio;
        double percentSizeY = (size.y) / canvasHeight;
        double windowSizeX = percentSizeX * (double) WIDTH;
        double windowSizeY = percentSizeY * (double) HEIGHT;
        coord.get(1).set(windowSizeX, windowSizeY);

        return coord;
    }

    public void sendPositionData(PongClient client) {
        if (client.isConnected()) {
            client.sendRequest("POSITION " + position.y);
            //System.out.println("POSITION " + position.y);
        }
    }

    public static void setCanvasWidth(int width) {
        canvasWidth = width;
    }

    public static void setCanvasHeight(int height) {
        canvasHeight = height;
    }

    public static void setWindowWidth(int width) {
        WIDTH = width;
    }

    public static void setWindowHeight(int height) {
        HEIGHT = height;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setPosition(double x, double y) {
        position.x = x;
        position.y = y;
    }

    protected void SetPositionX(double x) {
        position.x = x;
    }

    public void setPositionY(double y) {
        position.y = y;
    }

    public void setSize(Vector2D size) {
        this.size = size;
    }

    public static double getCanvasWidth() {
        return canvasWidth;
    }

    public static float getCanvasHeight() {
        return canvasHeight;
    }

    protected Vector2D getPosition() {
        return position;
    }

    public double getPositionX() {
        return position.x;
    }

    public double getPositionY() {
        return position.y;
    }

    public Vector2D getSize() {
        return size;
    }

    public double getSizeX() {
        return size.x;
    }

    public double getSizeY() {
        return size.y;
    }
}
