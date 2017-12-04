package fi.tamk.tikoot.pelimoottori.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.dyn4j.geometry.Vector2;

import java.util.ArrayList;
import java.util.Arrays;



/**
 * This is the Animation class that is used to create sprite based animations.
 *
 * You can create sprite based animations from one image or multiple.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class Animation {
    private double duration;
    private int count;
    private int width;
    private int height;
    private double timeGonePast;
    private ArrayList<Image> sprites;
    private boolean multipleImages;

    /**
     * Constructor for the animation class, that creates an animation from one spritesheet.
     *
     * @param spriteSheet One image that contains multiple sprites horizontally.
     * @param count The amount of sprites horizontally on the image.
     * @param duration The amount of time in seconds between the sprites.
     * @param height The height of one sprite in the image.
     * @param width The width of one sprite in the image
     */
    public Animation(Image spriteSheet,double duration,
                     int count, int width, int height) {
        this.duration = duration;
        this.count = count;
        this.width = width;
        this.height = height;
        multipleImages = false;
        sprites = ConvertToImages(spriteSheet);
    }

    /**
     * Constructor for the animation class, that creates an animation from multiple images.
     *
     * @param sprites multiple images that contain the sprite animation.
     * @param duration The amount of time in seconds between the sprites.
     */
    public Animation(ArrayList<Image> sprites, double duration) {
        this.duration = duration;
        this.sprites = sprites;
        multipleImages = true;
    }

    /**
     * Constructor for the animation class, that creates an animation from multiple images.
     *
     * @param sprites multiple images that contain the sprite animation.
     * @param duration The amount of time in seconds between the sprites.
     */
    public Animation(double duration, Image... sprites) {
        this.sprites = new ArrayList<>();
        this.duration = duration;
        this.sprites.addAll(Arrays.asList(sprites));
    }

    /**
     * Gets the next frame in the image.
     *
     * @param time The time between frames in milliseconds.
     */
    public Image getNextFrame(double time) {
        timeGonePast = timeGonePast + time;
        int index = (int) (((timeGonePast % (sprites.size() * duration)) / duration));
        return sprites.get(index);
    }

    /**
     * Converts one image into multiple sprites.
     *
     * @param spriteSheet One image that contains multiple sprites.
     * @return Arraylist that contains the sprite from the source image.
     */
    private ArrayList<Image> ConvertToImages(Image spriteSheet) {
        ArrayList<Image> images = new ArrayList<>();
        ImageView imageView = new ImageView(spriteSheet);

        for(int i = 0; i < count; i++) {
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            parameters.setViewport(new Rectangle2D(i*width, 0,  width, height));

            WritableImage wi = new WritableImage( width, height);
            imageView.snapshot(parameters, wi);
            images.add((Image) wi);
        }

        return images;
    }
}
