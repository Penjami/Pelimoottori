package fi.tamk.tikoot;

import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

/**
 * Created by Penjami on 5.11.2017.
 */
public class GameObject extends Sprite {

    private double velocityY;
    private double velocityX;

    public GameObject() {}
    public GameObject(String fileLocation) {
        super(fileLocation);
    }

    public void update(double time)
    {
        setPosition(getPositionX() + velocityX * time, getPositionY() + velocityY * time );
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    public void setVelocity(double x, double y)
    {
        setVelocityX(x);
        setVelocityY(y);
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(getPositionX(),getPositionY(),getWidth(),getHeight());
    }

    public boolean intersects(GameObject g)
    {
        return g.getBoundary().intersects( this.getBoundary() );
    }

}
