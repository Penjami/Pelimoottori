package fi.tamk.tikoot.pelimoottori;

import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;

public class AnimationImageObject extends ImageObject {

    private int count;
    private int columns;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;
    private int lastIndex;

    /**
     * Constructor for animation image object.
     *
     * @param fileLocation Location of the sprite.
     * @param world
     */
    public AnimationImageObject(String fileLocation, World world,
                                Duration duration,
                                int count,   int columns,
                                int offsetX, int offsetY,
                                int width,   int height) {
        super(fileLocation, world);
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;

        //setCycleDuration(duration);
        //setInterpolator(Interpolator.LINEAR);
    }

    @Override
    public void render(GraphicsContext gc)
    {
        int x = 0;
        int y = 0;
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            x = (index % columns) * width  + offsetX;
            y = (index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }

        gc.save();
        Vector2 bodyCenter = getBody().getWorldCenter();
        Rotate r = new Rotate(getBody().getTransform().getRotation() * 57.2958,
                bodyCenter.x * Settings.SCALE, bodyCenter.y * Settings.SCALE);
        affine.setToTransform(r);
        gc.transform(affine);

        gc.drawImage(getImage(),
                    x,
                    y,
                    getWidth(),
                    getWidth(),
                    getPositionX(),
                    getVelocityY(),
                    getWidth(), getHeight());
        gc.drawImage( getImage(), (bodyCenter.x * Settings.SCALE) - getWidth()/2,
                (bodyCenter.y * Settings.SCALE) - getHeight()/2);
        gc.restore();
    }

}
