package fi.tamk.tikoot;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * Created by Penjami on 13.11.2017.
 */
public class TextObject extends GameObject {

    private String text;
    private Color textColor;

    public TextObject(String text, Color textColor, double x, double y) {
        this.text = text;
        this.textColor = textColor;
        setPosition(x,y);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }


    public void render(GraphicsContext gc)
    {
        gc.setFill(textColor);
        gc.strokeText( text, getPositionX(),getPositionY() );
    }
}
