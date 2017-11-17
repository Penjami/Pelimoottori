package fi.tamk.tikoot;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by Penjami on 5.11.2017.
 */
public abstract class GameObject
{

    private double positionX;
    private double positionY;
    private double velocityY;
    private double velocityX;


    /**
     * Sets the position of the object'
     *
     * @param x Position in the x axis.
     * @param y Position in the y axis.
     */
    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    /**
     * Updates the gameobjects position based on velocity.
     *
     * @param time The time between frames.
     */
    public void update(double time)
    {
        setPosition(getPositionX() + velocityX * time, getPositionY() + velocityY * time );
    }

    /**
     * Get the y axis velocity of the object.
     *
     * @return Y axis velocity.
     */
    public double getVelocityY() {
        return velocityY;
    }

    /**
     * Sets the y axis velocity of the object.
     *
     * @param velocityY Y axis velocity.
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Get the x axis velocity of the object.
     *
     * @return X axis velocity.
     */
    public double getVelocityX() {
        return velocityX;
    }

    /**
     * Sets the x axis velocity of the object.
     *
     * @param velocityX X axis velocity.
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * Sets the velocity of both axis of the object.
     *
     * @param x X axis velocity.
     * @param y Y axis velocity.
     */
    public void setVelocity(double x, double y)
    {
        setVelocityX(x);
        setVelocityY(y);
    }

    /**
     * Adds to the current velocity of both axis of the object.
     *
     * @param x X axis velocity.
     * @param y Y axis velocity.
     */
    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }


    /**
     * @return The x position of the object.
     */
    public double getPositionX() {
        return positionX;
    }

    /**
     * @return The y position of the object.
     */
    public double getPositionY() {
        return positionY;
    }

}
