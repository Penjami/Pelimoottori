package fi.tamk.tikoot.pelimoottori;

import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

public class AnimationImageObject extends ImageObject {

    private int count;
    private int columns;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;
    private double duration;
    private double timeGonePast;

    /**
     * Constructor for animation image object.
     *
     * @param fileLocation Location of the sprite.
     * @param world
     */
    public AnimationImageObject(String fileLocation, World world,
                                double duration,
                                int count,   int columns,
                                int offsetX, int offsetY,
                                int width,   int height) {
        this.duration = duration;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setImage(fileLocation);
        getBody().addFixture(Geometry.createRectangle(getImage().getWidth()/Settings.SCALE/columns,
                getImage().getHeight()/Settings.SCALE/count),1,0.2,0.2);
        getBody().setMass(MassType.NORMAL);
        getBody().setAutoSleepingEnabled(false);
        world.addBody(getBody());
        affine = new Affine();

        //setCycleDuration(duration);
        //setInterpolator(Interpolator.LINEAR);
    }

    public void render(GraphicsContext gc, double time)
    {
        timeGonePast = timeGonePast + time;
        int index = (int)(((timeGonePast % (count * duration)) / duration));

        int x = (index % columns) * width  + offsetX;
        int y = (index / columns) * height + offsetY;

        Vector2 bodyCenter = getBody().getWorldCenter();
        System.out.println(index);

        gc.save();

        Rotate r = new Rotate(getBody().getTransform().getRotation() * 57.2958 + 180,
                bodyCenter.x * Settings.SCALE, bodyCenter.y * Settings.SCALE);
        affine.setToTransform(r);
        gc.transform(affine);

        gc.drawImage(getImage(),
                    x,
                    y,
                    getWidth()/columns,
                    getHeight(),
                    (bodyCenter.x * Settings.SCALE) - getWidth()/columns/2,
                    (bodyCenter.y * Settings.SCALE) - getHeight()/columns/2,
                    getWidth()/columns,
                    getHeight());
        gc.restore();
    }

}
