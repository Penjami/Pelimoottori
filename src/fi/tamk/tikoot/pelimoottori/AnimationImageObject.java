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
import sun.plugin.services.WPlatformService;

public class AnimationImageObject extends ImageObject {

    Animation animation;


    /**
     * Constructor for animation image object.
     *
     * @param animation Location of the sprite.
     * @param world
     */
    public AnimationImageObject(Animation animation, World world, int width, int height) {

        this.animation = animation;

        getBody().addFixture(Geometry.createRectangle((double) width/Settings.SCALE,
                (double) height/Settings.SCALE),1,0.2,0.2);
        getBody().setMass(MassType.NORMAL);
        getBody().setAutoSleepingEnabled(false);
        world.addBody(getBody());
        affine = new Affine();
    }

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
                                int width, int height) {

        animation = new Animation(new Image(fileLocation),
                duration, count, columns, offsetX, offsetY, width, height);

        getBody().addFixture(Geometry.createRectangle((double) width/Settings.SCALE,
                (double) height/Settings.SCALE),1,0.2,0.2);
        getBody().setMass(MassType.NORMAL);
        getBody().setAutoSleepingEnabled(false);
        world.addBody(getBody());
        affine = new Affine();
    }

    public void render(GraphicsContext gc, double time)
    {
        int[] xyz = animation.getFrameLocation(time);

        Vector2 bodyCenter = getBody().getWorldCenter();

        gc.save();

        Rotate r = new Rotate(getBody().getTransform().getRotation() * 57.2958 + 180,
                bodyCenter.x * Settings.SCALE, bodyCenter.y * Settings.SCALE);
        affine.setToTransform(r);
        gc.transform(affine);

        gc.drawImage(animation.getSpriteSheet(),
                xyz[0],
                xyz[1],
                animation.getSpriteSheet().getWidth()/xyz[2],
                animation.getSpriteSheet().getHeight(),
                (bodyCenter.x * Settings.SCALE) - animation.getSpriteSheet().getWidth()/xyz[2]/2,
                (bodyCenter.y * Settings.SCALE) - animation.getSpriteSheet().getHeight()/2,
                animation.getSpriteSheet().getWidth()/xyz[2],
                animation.getSpriteSheet().getHeight());
        gc.restore();
    }

    public void render(GraphicsContext gc, double time, Animation animation)
    {
        int[] xyz = animation.getFrameLocation(time);

        Vector2 bodyCenter = getBody().getWorldCenter();

        gc.save();

        Rotate r = new Rotate(getBody().getTransform().getRotation() * 57.2958 + 180,
                bodyCenter.x * Settings.SCALE, bodyCenter.y * Settings.SCALE);
        affine.setToTransform(r);
        gc.transform(affine);

        gc.drawImage(animation.getSpriteSheet(),
                xyz[0],
                xyz[1],
                animation.getSpriteSheet().getWidth()/xyz[2],
                animation.getSpriteSheet().getWidth(),
                (bodyCenter.x * Settings.SCALE) - animation.getSpriteSheet().getWidth()/xyz[2]/2,
                (bodyCenter.y * Settings.SCALE) - animation.getSpriteSheet().getHeight()/2,
                animation.getSpriteSheet().getWidth(),
                animation.getSpriteSheet().getHeight());
        gc.restore();
    }

}