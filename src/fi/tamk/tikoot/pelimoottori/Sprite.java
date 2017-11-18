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

    public Sprite() {}
    public Sprite(String fileLocation) {
        setImage(fileLocation);
    }

    void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, getPositionX(),getPositionY() );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(getPositionX(),getPositionY(),getWidth(),getHeight());
    }

    public boolean intersects(Sprite g)
    {
        return g.getBoundary().intersects( this.getBoundary() );
    }


    public Image getImage() {
        return image;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

}
