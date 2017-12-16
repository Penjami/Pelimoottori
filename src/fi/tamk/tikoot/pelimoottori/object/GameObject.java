package fi.tamk.tikoot.pelimoottori.object;

import fi.tamk.tikoot.pelimoottori.core.Settings;
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
public class GameObject{

    private Image image;
    private Affine affine = new Affine();
    private Body body = new Body();
    private ObjectType type;

    /**
     * Default constructor for the GameObject class.
     *
     * @param world The physics world of this scene.
     */
    public GameObject(World world) {
        body.setAutoSleepingEnabled(false);
        world.addBody(body);
    }

    /**
     * Used to check if this object intersects with a certain other object.
     *
     * @param g the other sprite object.
     * @return Returns the boolean that informs if the objects in question collide with each other.
     */
    public boolean intersects(GameObject g)
    {
        return getBody().isInContact(g.getBody());
    }

    /**
     * Used to get the width of this sprite object.
     *
     * @return the width of this sprite object.
     */
    public double getWidth() {
        AABB aabb = getBody().createAABB();
        return aabb.getWidth() * Settings.SCALE;
    }

    /**
     * Used to get the height of this sprite object.
     *
     * @return the height of this sprite object.
     */
    public double getHeight() {
        AABB aabb = getBody().createAABB();
        return aabb.getHeight() * Settings.SCALE;
    }

    /**
     * Sets the position of the object.
     *
     * @param x Position in the x axis.
     * @param y Position in the y axis.
     */
    public void setPosition(double x, double y) {
        Transform transform = new Transform();
        transform.translate(x/Settings.SCALE,y/Settings.SCALE);
        body.setTransform(transform);
    }

    /**
     * Get the y axis velocity of the object.
     *
     * @return Y axis velocity.
     */
    public double getVelocityY() {
        return body.getLinearVelocity().y;
    }


    /**
     * Get the x axis velocity of the object.
     *
     * @return X axis velocity.
     */
    public double getVelocityX() {
        return body.getLinearVelocity().x;
    }

    /**
     * Sets the velocity of both axis of the object.
     *
     * @param x X axis velocity.
     * @param y Y axis velocity.
     */
    public void setVelocity(double x, double y) {
        body.setLinearVelocity(x,y);
    }

    /**
     * Adds to the current velocity of both axis of the object.
     *
     * @param x X axis velocity.
     * @param y Y axis velocity.
     */
    public void addVelocity(double x, double y) {
        body.setLinearVelocity(getVelocityX()+x,getVelocityY()+y);
    }

    /**
     * @return The x coordinate of the center of the object in the world.
     */
    public double getPositionX() {
        return ((body.getTransform().getTranslationX()) * Settings.SCALE );
    }

    /**
     * @return The y coordinate of the center of the object in the world.
     */
    public double getPositionY() {
        return ((body.getTransform().getTranslationY()) * Settings.SCALE);
    }

    /**
     * @return The physcis body of this GameObject.
     */
    public Body getBody() {
        return body;
    }

    /**
     * Sets the GameObject image.
     *
     * @param i The image.
     */
    public void setImage(Image i)
    {
        image = i;
    }

    /**
     * Sets the GameObject image based on the filename.
     *
     * @param filename The filename of the image.
     */
    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    /**
     * Used to get the image of this GameObject.
     *
     * @return The image of this sprite object.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Used to get the type of this GameObject.
     *
     * @return The type of this GameObject.
     */
    public ObjectType getType() {
        return type;
    }

    /**
     * Used to set the type of this GameObject.
     *
     * @param type The type of this GameObject.
     */
    public void setType(ObjectType type) {
        this.type = type;
    }

    /**
     * Used to render this GameObject using an animation.
     *
     * @param gc The GraphicContext that is used to render the GameObject onto the scene.
     * @param animation The animation that is used to render this GameObject.
     * @param time The time between frames in milliseconds.
     */
    public void render(GraphicsContext gc, double time, Animation animation)
    {
        Image image = animation.getNextFrame(time);

        gc.save();
        Vector2 bodyCenter = getBody().getWorldCenter();
        Rotate r = new Rotate(getBody().getTransform().getRotation() * 57.2958 + 180,
                bodyCenter.x * Settings.SCALE, bodyCenter.y * Settings.SCALE);
        affine.setToTransform(r);
        gc.transform(affine);
        gc.drawImage( image, (bodyCenter.x * Settings.SCALE) + image.getWidth()/2,
                (bodyCenter.y * Settings.SCALE) - image.getHeight()/2, -image.getWidth(), image.getHeight());
        gc.restore();
    }

    /**
     * Used to render this GameObject using an image.
     *
     * @param gc The GraphicContext that is used to render the GameObject onto the scene.
     * @param image The image that is used to render this GameObject.
     */
    public void render(GraphicsContext gc, Image image)
    {
        gc.save();
        Vector2 bodyCenter = getBody().getWorldCenter();
        Rotate r = new Rotate(getBody().getTransform().getRotation() * 57.2958 + 180,
                bodyCenter.x * Settings.SCALE, bodyCenter.y * Settings.SCALE);
        affine.setToTransform(r);
        gc.transform(affine);
        gc.drawImage( image, (bodyCenter.x * Settings.SCALE) + image.getWidth()/2,
                (bodyCenter.y * Settings.SCALE) - image.getHeight()/2, -image.getWidth(), image.getHeight());
        gc.restore();
    }

    /**
     * Used to render this GameObject using the image from this GameObject.
     *
     * @param gc The GraphicContext that is used to render the GameObject onto the scene.
     */
    public void render(GraphicsContext gc)
    {
        gc.save();
        Vector2 bodyCenter = getBody().getWorldCenter();
        Rotate r = new Rotate(getBody().getTransform().getRotation() * 57.2958 + 180,
                bodyCenter.x * Settings.SCALE, bodyCenter.y * Settings.SCALE);
        affine.setToTransform(r);
        gc.transform(affine);
        gc.drawImage( this.image, (bodyCenter.x * Settings.SCALE) + this.image.getWidth()/2,
                (bodyCenter.y * Settings.SCALE) - this.image.getHeight()/2,
                    -this.image.getWidth(), this.image.getHeight());
        gc.restore();
    }
}
