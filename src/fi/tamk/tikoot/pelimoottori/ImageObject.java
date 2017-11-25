package fi.tamk.tikoot.pelimoottori;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;

/**
 * This class is used to create game objects for the game.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class ImageObject extends GameObject{

    private Image image;

    /**
     * Constructor for image object.
     *
     * @param fileLocation Location of the sprite.
     */
    public ImageObject(String fileLocation, World world) {
        setImage(fileLocation);
        getBody().addFixture(Geometry.createRectangle(getImage().getWidth()/STATIC.SCALE,
                getImage().getWidth()/STATIC.SCALE),1,0.2,0.2);
        getBody().setMass(MassType.NORMAL);
        getBody().translate(1,3);
        getBody().setAutoSleepingEnabled(false);
        world.addBody(getBody());
    }

    /**
     * Sets the image objects image.
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
     * Sets the image objects image based on the filename.
     *
     * @param filename The filename of the image.
     */
    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    /**
     * Used to get the image of this image object.
     *
     * @return The image of this sprite object.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Used to render the image object.
     *
     * @param gc GraphicsContext object that is used to draw sprites.
     */
    public void render(GraphicsContext gc)
    {
        Transform t = getBody().getTransform();
        gc.drawImage( image, t.getTranslationX() * STATIC.SCALE,t.getTranslationY() * STATIC.SCALE);
    }
}