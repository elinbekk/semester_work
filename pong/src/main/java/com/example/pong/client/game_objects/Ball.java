package com.example.pong.client.game_objects;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Ball extends GameObject {
    private Color fillColor;

    public Ball() {
        super();
        SetFillColor(Color.WHITE);
    }

    public Ball(HelpingVector position, HelpingVector size, Color fill) {
        super(position, size);
        SetFillColor(fill);
    }

    public void drawObject(GraphicsContext gc) {
        gc.setFill(fillColor);
        List<HelpingVector> posSize = convertToWorld(position, size);
        gc.fillOval(posSize.get(0).x, posSize.get(0).y, posSize.get(1).x, posSize.get(1).y);
    }

    protected void SetFillColor(Color fill) {
        fillColor = fill;
    }

    protected Color GetFillColor() {
        return fillColor;
    }

}
