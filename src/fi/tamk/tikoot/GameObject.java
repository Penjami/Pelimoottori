package fi.tamk.tikoot;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by Penjami on 5.11.2017.
 */
public class GameObject
{

    private double positionX;
    private double positionY;
    private double velocityY;
    private double velocityX;


    public GameObject()
    {
        positionX = 0;
        positionY = 0;
    }


    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
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



    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

}
