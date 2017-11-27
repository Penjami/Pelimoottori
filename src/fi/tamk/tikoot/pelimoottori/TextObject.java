package fi.tamk.tikoot.pelimoottori;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * This class is used to create text elements in game.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class TextObject extends Text {

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
     * @param fontSize The size of the font of this object.
     */
    public TextObject(String text, Color textColor, double x, double y, int fontSize, Group uiRoot) {
        setFont(Font.font(fontSize));
        setText(text);
        setTextColor(textColor);
        setTranslateX(x-getBoundsInLocal().getMaxX()/2);
        setTranslateY(y-getBoundsInLocal().getMaxY()/2);
        uiRoot.getChildren().add(this);
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
    public void setPosition(GraphicsContext gc)
    {

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
