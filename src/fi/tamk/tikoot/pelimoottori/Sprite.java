package fi.tamk.tikoot.pelimoottori;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by Penjami on 5.11.2017.
 */
public class Sprite extends GameObject {

    private Image image;
    private double width;
    private double height;

    /**
     * Constructor for sprite object.
     */
    public Sprite() {}

    /**
     * Constructor for sprite object.
     *
     * @param fileLocation Location of the sprite.
     */
    public Sprite(String fileLocation) {
        setImage(fileLocation);
    }

    /**
     * Sets the sprite objects image.
     *
     * @param i The image.
     */
    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    /**
     * Sets the sprite objects image based on the filename.
     *
     * @param filename The filename of the image.
     */
    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    /**
     * Used to render the sprite object.
     *
     * @param gc GraphicsContext object that is used to draw sprites.
     */
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, getPositionX(),getPositionY() );
    }

    /**
     * Used to get the boundaries of this object.
     */
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(getPositionX(),getPositionY(),getWidth(),getHeight());
    }

    /**
     * Used to check if this object intersects with a certain other sprite object.
     *
     * @param g the other sprite object.
     */
    public boolean intersects(Sprite g)
    {
        return g.getBoundary().intersects( this.getBoundary() );
    }

    /**
     * Used to get the image of this sprite object.
     *
     * @return The image of this sprite object.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Used to get the width of this sprite object.
     *
     * @return the width of this sprite object.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Used to get the height of this sprite object.
     *
     * @return the height of this sprite object.
     */
    public double getHeight() {
        return height;
    }

}
