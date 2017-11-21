package fi.tamk.tikoot.pelimoottori;

import fi.tamk.tikoot.pelimoottori.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * Created by Penjami on 13.11.2017.
 */
public class TextObject extends GameObject {

    private String text;
    private Color textColor;

    /**
     * Constructor for text object.
     *
     * @param text The text that is being shown.
     * @param textColor The color of the text.
     * @param x Position x of the text object.
     * @param y Position y of the text object.
     */
    public TextObject(String text, Color textColor, double x, double y) {
        this.text = text;
        this.textColor = textColor;
        setPosition(x,y);
    }

    /**
     * Constructor for text object.
     *
     * @param text The text that is being shown.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Used to get the text to of this object.
     *
     * @return The text that is being shown.
     */
    public String getText() {
        return text;
    }

    /**
     * Used to get the text color of this object.
     *
     * @return The text color.
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * Used to set the text color of this object.
     *
     * @param textColor The text color.
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     * Used to get the width of this object.
     *
     * @return Width of this line of text.
     */
    public double getWidth() {
        return 2;
    }

    /**
     * Used to render this text object.
     *
     * @param gc GraphicsContext object that is used to draw text objects.
     */
    public void render(GraphicsContext gc)
    {
        gc.setFill(textColor);
        gc.strokeText( text, getPositionX(),getPositionY() );
    }
}
