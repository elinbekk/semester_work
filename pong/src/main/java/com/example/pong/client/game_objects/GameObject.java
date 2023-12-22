package com.example.pong.client.game_objects;

import java.util.ArrayList;
import java.util.List;

import com.example.pong.client.PongClient;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    protected static int WIDTH = 600;
    protected static int HEIGHT = 400;
    protected static int canvasWidth = 500;
    protected static int canvasHeight = 500;

    public HelpingVector position;
    protected HelpingVector size;
    protected HelpingVector speed = new HelpingVector();
    protected HelpingVector accelleration = new HelpingVector();

    protected GameObject() {
        setPosition(new HelpingVector());
        setSize(new HelpingVector(10, 10));
    }

    protected GameObject(HelpingVector position, HelpingVector size) {
        setPosition(position);
        setSize(size);
    }

    public void setSpeed(HelpingVector speed) {
        this.speed = speed;
    }

    public void setSpeedX(double x) {
        speed.x = x;
    }

    public HelpingVector getSpeed() {
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

    private boolean isPointCollision(HelpingVector point, HelpingVector pos, HelpingVector size) {
        if ((point.x < (pos.x + size.x / 2)) && (point.x > (pos.x - size.x / 2))) {
            if ((point.y < pos.y + size.y / 2) && (point.y > pos.y - size.y / 2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBallCollision(HelpingVector point1, double r1, HelpingVector point2, double r2) {
        if ((point2.sub(point1).getLength() < (r1 + r2))) {
            return false;
        }
        return false;
    }

    public boolean isCollision(GameObject paddle) {
        isWallCollision();
        HelpingVector topLeft = paddle.getPosition();
        HelpingVector topRight = new HelpingVector(paddle.getPositionX() + paddle.getSizeX(),
                paddle.getPositionY());
        HelpingVector bottomLeft = new HelpingVector(paddle.getPositionX(),
                paddle.getPositionY() + paddle.getSizeY());
        HelpingVector bottomRight = new HelpingVector(paddle.getPositionX() + paddle.getSizeX(),
                paddle.getPositionY() + paddle.getSizeY());

        double diameter = getSizeX();
        double r1W = paddle.getSizeX();
        double r1H = paddle.getSizeY();
        double r2W = paddle.getSizeX();
        double r2H = paddle.getSizeY();
        HelpingVector[] points = new HelpingVector[]{topLeft, topRight, bottomLeft, bottomRight};
        HelpingVector ballCenter = position.add(size.mult(0.5));
        HelpingVector paddleCenter = paddle.getPosition().add(paddle.getSize().mult(0.5));
        for (HelpingVector point : points) {
            if (isBallCollision(point, diameter / 2, ballCenter, diameter / 2)) {
                return true;
            }
        }
        if (isPointCollision(ballCenter, paddleCenter, new HelpingVector(r1W, r1H))) {
            return true;
        }
        if (isPointCollision(ballCenter, paddleCenter, new HelpingVector(r2W, r2H))) {
            return true;
        }
        return false;
    }


    public void drawObject(GraphicsContext gc) {
    }

    protected List<HelpingVector> convertToWorld(HelpingVector position, HelpingVector size) {
        List<HelpingVector> multiReturn = new ArrayList();
        multiReturn.add(new HelpingVector());
        multiReturn.add(new HelpingVector());
        double aspectRatio = (double) WIDTH / HEIGHT;
        double aspectWorldWidth = canvasWidth * aspectRatio;
        double percentPosX = (position.x + ((double) canvasWidth / 2)) / canvasWidth;
        double percentPosY = (position.y + ((double) canvasHeight / 2)) / canvasHeight;
        double windowPosX = percentPosX * (float) WIDTH;
        double windowPosY = percentPosY * (float) HEIGHT;
        multiReturn.get(0).set(windowPosX, windowPosY);
        double percentSizeX = (size.x) / aspectWorldWidth;
        double percentSizeY = (size.y) / canvasHeight;
        double windowSizeX = percentSizeX * (float) WIDTH;
        double windowSizeY = percentSizeY * (float) HEIGHT;
        multiReturn.get(1).set(windowSizeX, windowSizeY);

        return multiReturn;
    }

    public void sendPositionData(PongClient client) {
        if (client.isConnected()) {
            client.sendRequest("POSITION " + position.y);
            System.out.println("POSITION " + position.y);
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

    public void setPosition(HelpingVector position) {
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

    public void setSize(HelpingVector size) {
        this.size = size;
    }

    public static double getCanvasWidth() {
        return canvasWidth;
    }

    public static float getCanvasHeight() {
        return canvasHeight;
    }

    protected HelpingVector getPosition() {
        return position;
    }

    public double getPositionX() {
        return position.x;
    }

    public double getPositionY() {
        return position.y;
    }

    public HelpingVector getSize() {
        return size;
    }

    public double getSizeX() {
        return size.x;
    }

    public double getSizeY() {
        return size.y;
    }
}
