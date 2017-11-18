package fi.tamk.tikoot.games;

import fi.tamk.tikoot.pelimoottori.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Penjami on 6.11.2017.
 */
public class TestGame extends GameApplication {

    private Sprite backGround = new Sprite("img.jpg");
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private Sprite monk = new Sprite("monk.png");
    private Sprite soda1 = new Sprite("soda.png");
    private Sprite soda2 = new Sprite("soda.png");
    private Sprite soda3 = new Sprite("soda.png");
    private TextObject points = new TextObject("Points : 0", Color.ALICEBLUE, 0, 30);
    private Music bgm = new Music("src/bgm3.mp3");
    private Sound gotItemSound = new Sound("src/ballHit.wav");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void setSettings(Settings settings) {
        settings.setTitle("TestGame");
        settings.setWidth(600);
        settings.setHeight(360);
        sprites.add(monk);
        sprites.add(soda1);
        sprites.add(soda2);
        sprites.add(soda3);
        soda1.setPosition(300,200);
        soda2.setPosition(100,300);
        soda3.setPosition(300,300);
    }
    @Override
    protected void update(double time) {

        if(!bgm.isPlaying()) {
            bgm.play();
        }

        monk.setVelocity(0,0);
        if (inputHandler.getInput().contains("LEFT"))
            monk.addVelocity(-50,0);
        if (inputHandler.getInput().contains("RIGHT"))
            monk.addVelocity(50,0);
        if (inputHandler.getInput().contains("UP"))
            monk.addVelocity(0,-50);
        if (inputHandler.getInput().contains("DOWN"))
            monk.addVelocity(0,50);

        monk.update(time);

    }

    @Override
    protected void collisions() {
        if(soda1 != null && monk.intersects(soda1)) {
            gotItemSound.play();
            sprites.remove(soda1);
            soda1 = null;
        }
        if(soda2 != null && monk.intersects(soda2)) {
            gotItemSound.play();
            sprites.remove(soda2);
            soda2 = null;
        }
        if(soda3 != null && monk.intersects(soda3)) {
            gotItemSound.play();
            sprites.remove(soda3);
            soda3 = null;
        }
    }

    @Override
    protected void draw() {
        graphicsContext.clearRect(0, 0, mainScene.getWidth(), mainScene.getHeight());
        backGround.render(graphicsContext);
        for(Sprite obj : sprites) {
            obj.render(graphicsContext);
        }
        points.render(graphicsContext);
    }
}
