package com.example.pong.client.game_objects;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextGameObject extends GameObject {
    private String text;
    private Font font;

    private Color fillColor;

    public TextGameObject() {
        super();
        setText("Default");
        SetFont(Font.font("Arial"));
    }

    public TextGameObject(String text, Font font, Vector2D pos, Vector2D size, Color fill) {
        super(pos, size);
        setText(text);
        SetFont(font);
        SetFillColor(fill);
    }

    public void drawObject(GraphicsContext gc) {
        gc.setFill(fillColor);
        List<Vector2D> posSize = convertToDrawing(position, size);
        gc.setFont(font);
        gc.fillText(text, posSize.get(0).x, posSize.get(0).y);
    }

    public void setText(String text) {
        this.text = text;
    }

    protected void SetFont(Font font) {
        this.font = font;
    }

    protected void SetFillColor(Color fill) {
        fillColor = fill;
    }

}
