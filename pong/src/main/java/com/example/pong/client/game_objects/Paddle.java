package com.example.pong.client.game_objects;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends GameObject {
    private Color fillColor;

    public Paddle() {
        super();
        setFillColor(Color.WHITE);

    }

    public Paddle(HelpingVector position, HelpingVector size, Color fill) {
        super(position, size);
        setFillColor(fill);
    }

    public void drawObject(GraphicsContext gc) {
        gc.setFill(fillColor);
        List<HelpingVector> posSize = convertToWorld(position, size);
        gc.fillRect(posSize.get(0).x, posSize.get(0).y, posSize.get(1).x, posSize.get(1).y);
    }

    public void setFillColor(Color fill)
    {
        fillColor = fill;
    }

}
