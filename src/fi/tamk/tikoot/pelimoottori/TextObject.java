package fi.tamk.tikoot.pelimoottori;

import fi.tamk.tikoot.pelimoottori.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * Created by Penjami on 13.11.2017.
 */
public class TextObject extends GameObject {

    private String text;
    private Color textColor;
    private int fontSize;

    /**
     * Constructor for text object.
     *
     * @param text The text that is being shown.
     * @param textColor The color of the text.
     * @param x Position x of the text object.
     * @param y Position y of the text object.
     */
    public TextObject(String text, Color textColor, double x, double y, int fontSize) {
        this.text = text;
        this.textColor = textColor;
        this.fontSize = fontSize;
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
     * Used to render this text object.
     *
     * @param gc GraphicsContext object that is used to draw text objects.
     */
    public void render(GraphicsContext gc)
    {
        double tempSize = gc.getFont().getSize();
        gc.setFont(Font.font(fontSize));
        gc.setFill(textColor);
        gc.strokeText( text, getPositionX(),getPositionY() );
        gc.setFont(Font.font(tempSize));
    }

    /**
     * Used to get the size of the font.
     *
     * @return The size of the font.
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * Used to set the size of the font.
     *
     * @param fontSize The size of the font.
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

}
