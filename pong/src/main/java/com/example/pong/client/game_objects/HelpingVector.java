package com.example.pong.client.game_objects;

public class HelpingVector {
     public double x = 0;
    double y = 0;

    public HelpingVector() {
        x = 0;
        y = 0;
    }
    public HelpingVector(double x1, double y2) {
        x = x1;
        y = y2;
    }

    public HelpingVector(HelpingVector copy) {
        x = copy.x;
        y = copy.y;
    }

    public double getLength() {
        return Math.sqrt((x * x) + (y * y));
    }

    public HelpingVector add(HelpingVector v2) {
        HelpingVector temp = new HelpingVector(v2);
        temp.x += x;
        temp.y += y;
        return temp;
    }

    public HelpingVector sub(HelpingVector v2) {
        HelpingVector temp = new HelpingVector(v2);
        temp.x -= x;
        temp.y -= y;
        return temp;
    }

    public HelpingVector mult(double scalar) {
        HelpingVector temp = new HelpingVector(this);
        temp.x *= scalar;
        temp.y *= scalar;
        return temp;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
