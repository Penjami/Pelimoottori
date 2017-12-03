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
    private Affine affine;
    private Body body = new Body();
    private ObjectType type;

    GameObject(World world) {
        body.setAutoSleepingEnabled(false);
        world.addBody(body);
        affine = new Affine();
    }

    public GameObject(MassType type, double width, double height, World world) {
        body.addFixture(Geometry.createRectangle(width/ Settings.SCALE,height/Settings.SCALE));
        body.setMass(type);
        body.setAutoSleepingEnabled(false);
        world.addBody(body);
        affine = new Affine();
    }

    public GameObject(MassType type, String imageLocation, World world) {
        setImage(imageLocation);
        getBody().addFixture(Geometry.createRectangle(getImage().getWidth()/Settings.SCALE,
                getImage().getHeight()/Settings.SCALE),1,0.2,0.2);
        getBody().setMass(type);
        getBody().setAutoSleepingEnabled(false);
        world.addBody(getBody());
        affine = new Affine();
    }

    /**
     * Used to check if this object intersects with a certain other object.
     *
     * @param g the other sprite object.
     * @return Returns the boolean that informs if the objects in question collide with each other.
     */
    public boolean intersects(GameObject g)
    {
        return true;
    }

    /**
     * Used to get the width of this sprite object.
     *
     * @return the width of this sprite object.
     */
    public double getWidth() {
        AABB aabb = getBody().createAABB();
        return aabb.getWidth();
    }

    /**
     * Used to get the height of this sprite object.
     *
     * @return the height of this sprite object.
     */
    public double getHeight() {
        AABB aabb = getBody().createAABB();
        return aabb.getHeight();
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
        return ((body.getTransform().getTranslationY() - getHeight()/2) * Settings.SCALE );
    }

    /**
     * @return The y coordinate of the center of the object in the world.
     */
    public double getPositionY() {
        return ((body.getTransform().getTranslationY() - getWidth()/2) * Settings.SCALE);
    }

    /**
     * @return The physcis body of this object.
     */
    public Body getBody() {
        return body;
    }

    /**
     * Sets the image objects image.
     *
     * @param i The image.
     */
    public void setImage(Image i)
    {
        image = i;
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

    public ObjectType getType() {
        return type;
    }

    public void setType(ObjectType type) {
        this.type = type;
    }

    public void render(GraphicsContext gc, double time, Animation animation)
    {
        int[] xyz = animation.getFrameLocation(time);
        Vector2 bodyCenter = getBody().getWorldCenter();
        Image sprite = animation.getSpriteSheet(time);

        gc.save();

        Rotate r = new Rotate(getBody().getTransform().getRotation() * 57.2958 + 180,
                bodyCenter.x * Settings.SCALE, bodyCenter.y * Settings.SCALE);
        affine.setToTransform(r);
        gc.transform(affine);

        gc.drawImage(sprite,
                xyz[0],
                xyz[1],
                sprite.getWidth()/xyz[2],
                sprite.getHeight(),
                (bodyCenter.x * Settings.SCALE) - sprite.getWidth()/xyz[2]/2,
                (bodyCenter.y * Settings.SCALE) - sprite.getHeight()/2,
                sprite.getWidth()/xyz[2],
                sprite.getHeight());
        gc.restore();
    }

    public void render(GraphicsContext gc)
    {
        gc.save();
        Vector2 bodyCenter = getBody().getWorldCenter();
        Rotate r = new Rotate(getBody().getTransform().getRotation() * 57.2958,
                bodyCenter.x * Settings.SCALE, bodyCenter.y * Settings.SCALE);
        affine.setToTransform(r);
        gc.transform(affine);
        gc.drawImage( image, (bodyCenter.x * Settings.SCALE) - getWidth()/2,
                (bodyCenter.y * Settings.SCALE) - getHeight()/2);
        gc.restore();
    }
}
