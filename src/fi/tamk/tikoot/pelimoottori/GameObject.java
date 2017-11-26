package fi.tamk.tikoot.pelimoottori;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.Force;
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
public class GameObject{

    protected double width;
    protected double height;
    private Body body = new Body();

    GameObject() {
    }

    public GameObject(MassType type, double x, double y, double width, double height, World world) {
        body.addFixture(Geometry.createRectangle(width/STATIC.SCALE,height/STATIC.SCALE));
        this.width = width;
        this.height = height;
        body.setMass(type);
        body.translate(x/STATIC.SCALE,y/STATIC.SCALE);
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
        return true;
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

    /**
     * Sets the position of the object.
     *
     * @param x Position in the x axis.
     * @param y Position in the y axis.
     */
    public void setPosition(double x, double y) {
        Transform transform = new Transform();
        transform.translate(x/STATIC.SCALE,y/STATIC.SCALE);
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
     * @return The x position of the object.
     */
    public double getPositionX() {
        return body.getTransform().getTranslationX();
    }

    /**
     * @return The y position of the object.
     */
    public double getPositionY() {
        return body.getTransform().getTranslationY();
    }

    /**
     * @return The physcis body of this object.
     */
    public Body getBody() {
        return body;
    }
}
