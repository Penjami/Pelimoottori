package fi.tamk.tikoot.pelimoottori;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.*;

/**
 * This class is used to create game objects for the game.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class ImageObject extends GameObject{

    private Image image;
    private Affine affine;

    /**
     * Constructor for image object.
     *
     * @param fileLocation Location of the sprite.
     */
    public ImageObject(String fileLocation, World world) {
        setImage(fileLocation);
        getBody().addFixture(Geometry.createRectangle(getImage().getWidth()/STATIC.SCALE,
                getImage().getHeight()/STATIC.SCALE),1,0.2,0.2);
        getBody().setMass(MassType.NORMAL);
        getBody().setAutoSleepingEnabled(false);
        world.addBody(getBody());
        affine = new Affine();
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
        gc.save();
        Vector2 bodyCenter = getBody().getWorldCenter();
        Rotate r = new Rotate(getBody().getTransform().getRotation() * 57.2958,
                bodyCenter.x * STATIC.SCALE, bodyCenter.y * STATIC.SCALE);
        affine.setToTransform(r);
        gc.transform(affine);
        gc.drawImage( image, (bodyCenter.x * STATIC.SCALE) - getWidth()/2,
                (bodyCenter.y * STATIC.SCALE) - getHeight()/2);
        gc.restore();
    }
}