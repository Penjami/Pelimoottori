package fi.tamk.tikoot.games;

import fi.tamk.tikoot.pelimoottori.*;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Penjami on 6.11.2017.
 */
public class TestGame extends GameApplication {

    private int score = 0;
    private Sprite backGround = new Sprite("img.jpg");
    private ArrayList<Sprite> collectables = new ArrayList<>();
    private Sprite monk = new Sprite("monk.png");
    private Sprite soda1 = new Sprite("soda.png");
    private Sprite soda2 = new Sprite("soda.png");
    private Sprite soda3 = new Sprite("soda.png");
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
        collectables.add(soda1);
        collectables.add(soda2);
        collectables.add(soda3);
    }

    @Override
    protected void launchProperties() {
        monk.setPosition(20,40);
        soda1.setPosition(randomBetweenNum(0,mainScene.getWidth() - soda1.getWidth()),
                randomBetweenNum(0,mainScene.getHeight() - soda1.getHeight()));
        soda2.setPosition(randomBetweenNum(0,mainScene.getWidth() - soda2.getWidth()),
                randomBetweenNum(0,mainScene.getHeight() - soda2.getHeight()));
        soda3.setPosition(randomBetweenNum(0,mainScene.getWidth() - soda3.getWidth()),
                randomBetweenNum(0,mainScene.getHeight() - soda3.getHeight()));
        soda1.setVelocity(randomBetweenNum(10,20),randomBetweenNum(-10,-20));
        soda2.setVelocity(randomBetweenNum(-10,-20),randomBetweenNum(10,20));
        soda3.setVelocity(randomBetweenNum(10,20),randomBetweenNum(-10,20));
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

        for(Sprite obj : collectables) {
            changeDirIfHitWall(obj);
        }
        for(Sprite obj : collectables) {
            obj.update(time);
        }
        monk.update(time);
    }

    @Override
    protected void collisions() {
        if(soda1 != null && monk.intersects(soda1)) {
            addScore();
            gotItemSound.play();
            collectables.remove(soda1);
            soda1 = null;
        }
        if(soda2 != null && monk.intersects(soda2)) {
            addScore();
            gotItemSound.play();
            collectables.remove(soda2);
            soda2 = null;
        }
        if(soda3 != null && monk.intersects(soda3)) {
            addScore();
            gotItemSound.play();
            collectables.remove(soda3);
            soda3 = null;
        }
    }

    @Override
    protected void draw() {
        graphicsContext.clearRect(0, 0, mainScene.getWidth(), mainScene.getHeight());
        backGround.render(graphicsContext);
        for(Sprite obj : collectables) {
            obj.render(graphicsContext);
        }
        monk.render(graphicsContext);
        points.render(graphicsContext);
    }

    private void changeDirIfHitWall(Sprite sprite) {
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
