package fi.tamk.tikoot.pelimoottori;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class is used to create game objects for the game.
 *
 * @author Penjami Rantakangas
 * @version "%I%, %G%"
 * @since 1.8
 */
public class SpriteObject extends GameObject {

    private Image image;
    private double width;
    private double height;
    private SpriteObject lastCollision;

    /**
     * Constructor for sprite object.
     */
    public SpriteObject() {}

    /**
     * Constructor for sprite object.
     *
     * @param fileLocation Location of the sprite.
     */
    public SpriteObject(String fileLocation) {
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
     * @return Returns a Rectangle2D object that has this objects boundaries.
     */
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(getPositionX(),getPositionY(),getWidth(),getHeight());
    }

    /**
     * Used to check if this object intersects with a certain other sprite object.
     *
     * @param g the other sprite object.
     * @return Returns the boolean that informs if the objects in question collide with each other.
     */
    public boolean intersects(SpriteObject g)
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
