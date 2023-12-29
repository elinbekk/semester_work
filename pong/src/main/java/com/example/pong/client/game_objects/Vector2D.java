package com.example.pong.client.game_objects;

public class Vector2D {
    public double x = 0;
    public double y = 0;

    public Vector2D() {
        x = 0;
        y = 0;
    }
    public Vector2D(double x1, double y2) {
        x = x1;
        y = y2;
    }

    public Vector2D(Vector2D copy) {
        x = copy.x;
        y = copy.y;
    }

    public double getLength() {
        return Math.sqrt((x * x) + (y * y));
    }

    public Vector2D add(Vector2D v2) {
        Vector2D temp = new Vector2D(v2);
        temp.x += x;
        temp.y += y;
        return temp;
    }

    public Vector2D sub(Vector2D v2) {
        Vector2D temp = new Vector2D(v2);
        temp.x -= x;
        temp.y -= y;
        return temp;
    }

    public Vector2D mult(double scalar) {
        Vector2D temp = new Vector2D(this);
        temp.x *= scalar;
        temp.y *= scalar;
        return temp;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
