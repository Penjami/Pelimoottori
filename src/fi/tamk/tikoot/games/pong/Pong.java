package fi.tamk.tikoot.games.pong;

import fi.tamk.tikoot.pelimoottori.*;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Penjami on 6.11.2017.
 */
public class Pong extends GameApplication {

    private int score = 0;
    private SpriteObject pongPadPlayer1 = new SpriteObject("pongPad1.png");
    private SpriteObject pongPadPlayer2 = new SpriteObject("pongPad2.png");
    private SpriteObject ball = new SpriteObject("ball.png");
    private TextObject points = new TextObject("Points : " + score, Color.ALICEBLUE,0,0);
    private Music bgm = new Music("src/bgm.mp3");
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
        pongPadPlayer1.setPosition(20,mainScene.getHeight()/2 - pongPadPlayer1.getHeight()/2);
        pongPadPlayer1.setPosition(mainScene.getWidth() - pongPadPlayer2.getWidth(),
                mainScene.getHeight()/2 - pongPadPlayer2.getHeight()/2);
        points.setPosition( mainScene.getWidth()/2, 30);

        ball.setPosition(mainScene.getWidth()/2, mainScene.getHeight()/2);
        ball.setVelocity(randomBetweenNum(50,100),randomBetweenNum(-50,-100));
    }

    @Override
    protected void update(double time) {

        if(!bgm.isPlaying()) {
            bgm.play();
        }

        pongPadPlayer2.setVelocity(0,0);
        pongPadPlayer1.setVelocity(0,0);
        if (inputHandler.getInput().contains("W"))
            pongPadPlayer1.addVelocity(0,-100);
        if (inputHandler.getInput().contains("S"))
            pongPadPlayer1.addVelocity(0,100);
        if (inputHandler.getInput().contains("UP"))
            pongPadPlayer2.addVelocity(0,-100);
        if (inputHandler.getInput().contains("DOWN"))
            pongPadPlayer2.addVelocity(0,100);

        ball.update(time);
        pongPadPlayer1.update(time);
        pongPadPlayer2.update(time);

    }

    @Override
    protected void collisions() {

        changeDirIfHitWall(ball);

    }

    @Override
    protected void draw() {
        graphicsContext.clearRect(0, 0, mainScene.getWidth(), mainScene.getHeight());
        ball.render(graphicsContext);
        pongPadPlayer1.render(graphicsContext);
        pongPadPlayer2.render(graphicsContext);
        points.render(graphicsContext);
    }

    @Override
    protected void removeObjects() {}

    private void changeDirIfHitWall(SpriteObject sprite) {
        if(sprite.getPositionX() >= mainScene.getWidth() - sprite.getWidth()) {
            sprite.setVelocityX(sprite.getVelocityX() * -1);
            gotItemSound.play();
        }
        if(sprite.getPositionX() <= 0) {
            sprite.setVelocityX(sprite.getVelocityX() * -1);
            gotItemSound.play();
        }
        if(sprite.getPositionY() >= mainScene.getHeight() - sprite.getHeight()) {
            sprite.setVelocityY(sprite.getVelocityY() * -1);
            gotItemSound.play();
        }
        if(sprite.getPositionY() <= 0) {
            sprite.setVelocityY(sprite.getVelocityY() * -1);
            gotItemSound.play();
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
