package fi.tamk.tikoot.games;

import fi.tamk.tikoot.pelimoottori.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Penjami on 6.11.2017.
 */
public class TestGame extends GameApplication {

    private int score = 0;
    private SpriteObject backGround = new SpriteObject("img.jpg");
    private ArrayList<SpriteObject> collectables = new ArrayList<>();
    private ArrayList<SpriteObject> removeList = new ArrayList<>();
    private SpriteObject monk = new SpriteObject("monk.png");
    private TextObject points = new TextObject("Points : " + score, Color.ALICEBLUE, 0, 30);
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
    }

    @Override
    protected void launchProperties() {
        monk.setPosition(20,40);

        for(int i = 0; i<10; i++) {
            collectables.add(new SpriteObject("soda.png"));
        }
        for(SpriteObject col : collectables) {
            col.setPosition(randomBetweenNum(0,mainScene.getWidth() - col.getWidth()),
                    randomBetweenNum(0,mainScene.getHeight() - col.getHeight()));
            col.setVelocity(randomBetweenNum(-20,20),randomBetweenNum(-20,20));
        }
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

        for(SpriteObject col : collectables) {
            changeDirIfHitWall(col);
        }
        for(SpriteObject col : collectables) {
            col.update(time);
        }
        monk.update(time);

        if(collectables.isEmpty()) {
        }
    }

    @Override
    protected void collisions() {
        for(SpriteObject col : collectables) {
            if(monk.intersects(col)) {
                addScore();
                gotItemSound.play();
                removeList.add(col);
            }
        }
    }

    @Override
    protected void draw() {
        graphicsContext.clearRect(0, 0, mainScene.getWidth(), mainScene.getHeight());
        backGround.render(graphicsContext);
        for(SpriteObject col : collectables) {
            col.render(graphicsContext);
        }
        monk.render(graphicsContext);
        points.render(graphicsContext);
    }

    @Override
    protected void removeObjects() {
        for(SpriteObject col : removeList) {
            collectables.remove(col);
        }
    }

    private void changeDirIfHitWall(SpriteObject sprite) {
        if(sprite.getPositionX() >= mainScene.getWidth() - sprite.getWidth()) {
            sprite.setVelocityX(sprite.getVelocityX() * -1);
        }
        if(sprite.getPositionX() <= 0) {
            sprite.setVelocityX(sprite.getVelocityX() * -1);
        }
        if(sprite.getPositionY() >= mainScene.getHeight() - sprite.getHeight()) {
            sprite.setVelocityY(sprite.getVelocityY() * -1);
        }
        if(sprite.getPositionY() <= 0) {
            sprite.setVelocityY(sprite.getVelocityY() * -1);
        }
    }

    private double randomBetweenNum(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    private void addScore() {
        score++;
        points.setText("Points : " + score);
    }
}
