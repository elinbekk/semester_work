package com.example.pong.client.game_objects;

import java.util.List;

import com.example.pong.client.game_objects.GameObject;
import com.example.pong.client.game_objects.HelpingVector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class TextGameObject extends GameObject {
    private String _text;
    private Font _font;

    private Paint _fillColor;
    private Paint _strokeColor;

    public TextGameObject() {
        super();
        setText("Default");
        SetFont(Font.font("Arial"));
    }

    public TextGameObject(String text, Font font, HelpingVector pos, HelpingVector size, Paint fill, Paint stroke) {
        super(pos, size);
        setText(text);
        SetFont(font);
        SetFillColor(fill);
        SetStrokeColor(stroke);
    }


    public void drawObject(GraphicsContext gc) {
        gc.setFill(_fillColor);
        gc.setStroke(_strokeColor);
        List<HelpingVector> posSize = ConvertToWorld(position, size);
        gc.setFont(_font);
        gc.fillText(_text, posSize.get(0).x, posSize.get(0).y);
    }

    protected void setText(String text) {
        _text = text;
    }

    protected void SetFont(Font font) {
        _font = font;
    }

    protected void SetFillColor(Paint fill) {
        _fillColor = fill;
    }

    protected void SetStrokeColor(Paint stroke) {
        _strokeColor = stroke;
    }
}
