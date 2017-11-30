package fi.tamk.tikoot.pelimoottori;

import javafx.scene.image.Image;
import org.dyn4j.geometry.Vector2;

import java.util.ArrayList;
import java.util.Arrays;

public class Animation {
    private double duration;
    private int count;
    private int columns;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;
    private double timeGonePast;
    private ArrayList<Image> sprites;
    private Image spriteSheet;

    public Animation(Image spriteSheet,double duration,
                     int count,   int columns,
                     int offsetX, int offsetY,
                     int width, int height) {
        this.spriteSheet = spriteSheet;
        this.duration = duration;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
    }

    public Animation(ArrayList<Image> sprites, double duration) {
        this.duration = duration;
        this.sprites = sprites;
    }

    public Animation(double duration, Image... sprites) {
        this.sprites = new ArrayList<>();
        this.duration = duration;
        this.sprites.addAll(Arrays.asList(sprites));
    }

    public int[] getFrameLocation(double time) {
        int[] xyc = new int[3];

        timeGonePast = timeGonePast + time;
        int index = (int) (((timeGonePast % (count * duration)) / duration));
        int x = (index % columns) * width + offsetX;
        int y = (index / columns) * height + offsetY;

        xyc[0] = x;
        xyc[1] = y;
        xyc[2] = columns;
        return xyc;
    }

    public Image getFrame(double time) {
        timeGonePast = timeGonePast + time;
        int index = (int) (((timeGonePast % (sprites.size() * duration)) / duration));
        return sprites.get(index);
    }

    public Image getSpriteSheet() {
        return spriteSheet;
    }
}
