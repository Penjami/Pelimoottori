package fi.tamk.tikoot.games.pong;

import fi.tamk.tikoot.pelimoottori.*;
import javafx.scene.paint.Color;
import java.util.Random;

/**
 * Created by Penjami on 6.11.2017.
 */
public class Pong extends GameApplication {

    private int player1Score = 0;
    private int player2Score = 0;
    private SpriteObject pongPadPlayer1 = new SpriteObject("pongPad1.png");
    private SpriteObject pongPadPlayer2 = new SpriteObject("pongPad2.png");
    private SpriteObject ball = new SpriteObject("ball.png");
    private TextObject player1ScoreText =
            new TextObject("P2 Points : " + player1Score, Color.ALICEBLUE,0,0, 25);
    private TextObject player2ScoreText =
            new TextObject("P1 Points : " + player2Score, Color.ALICEBLUE,0,0, 25);
    private Music bgm = new Music("src/bgm.mp3");
    private Sound ballBounceSound = new Sound("src/ballHit.wav");

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
        player1ScoreText.setPosition( mainScene.getWidth()/2-250, 30);
        player2ScoreText.setPosition( mainScene.getWidth()/2+100, 30);

        ball.setPosition(mainScene.getWidth()/2, mainScene.getHeight()/2);
        randomBallVel();

    }

    @Override
    protected void update(double time) {

        if(!bgm.isPlaying()) {
            bgm.play();
        }

        pongPadPlayer2.setVelocity(0,0);
        pongPadPlayer1.setVelocity(0,0);
        if (inputHandler.getInput().contains("W") && pongPadPlayer1.getPositionY()>0)
            pongPadPlayer1.addVelocity(0,-100);
        if (inputHandler.getInput().contains("S")
                && pongPadPlayer1.getPositionY()<mainScene.getHeight()-pongPadPlayer1.getHeight())
            pongPadPlayer1.addVelocity(0,100);
        if (inputHandler.getInput().contains("UP") && pongPadPlayer2.getPositionY()>0)
            pongPadPlayer2.addVelocity(0,-100);
        if (inputHandler.getInput().contains("DOWN")
                && pongPadPlayer1.getPositionY()<mainScene.getHeight()-pongPadPlayer1.getHeight() )
            pongPadPlayer2.addVelocity(0,100);

        ball.update(time);
        pongPadPlayer1.update(time);
        pongPadPlayer2.update(time);

    }

    @Override
    protected void collisions() {
        if(pongPadPlayer1.intersects(ball) || pongPadPlayer2.intersects(ball) ) {
            ball.setVelocityX(-ball.getVelocityX());
        }
        changeDirIfHitWall(ball);

    }

    @Override
    protected void draw() {
        graphicsContext.clearRect(0, 0, mainScene.getWidth(), mainScene.getHeight());
        ball.render(graphicsContext);
        pongPadPlayer1.render(graphicsContext);
        pongPadPlayer2.render(graphicsContext);
        player1ScoreText.render(graphicsContext);
        player2ScoreText.render(graphicsContext);
    }

    @Override
    protected void removeObjects() {}

    private void changeDirIfHitWall(SpriteObject sprite) {
        if(sprite.getPositionX() >= mainScene.getWidth() - sprite.getWidth()) {
            player1Score++;
            player1ScoreText.setText("P2 Points : " + player1Score);
            ball.setPosition(mainScene.getWidth()/2, mainScene.getHeight()/2);
            randomBallVel();
            ballBounceSound.play();
        }
        if(sprite.getPositionX() <= 0) {
            player2Score++;
            player2ScoreText.setText("P2 Points : " + player2Score);
            ball.setPosition(mainScene.getWidth()/2, mainScene.getHeight()/2);
            randomBallVel();
            ballBounceSound.play();
        }
        if(sprite.getPositionY() >= mainScene.getHeight() - sprite.getHeight()) {
            sprite.setVelocityY(sprite.getVelocityY() * -1);
            ballBounceSound.play();
        }
        if(sprite.getPositionY() <= 0) {
            sprite.setVelocityY(sprite.getVelocityY() * -1);
            ballBounceSound.play();
        }
    }

    private double randomBetweenNum(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    private void randomBallVel() {
        double dir = randomBetweenNum(0,4);
        if(dir<=1) {
            ball.setVelocity(100,-100);
        } else if(dir>1 && dir<=2) {
            ball.setVelocity(-100,100);
        } else if(dir>2 && dir<=3) {
            ball.setVelocity(-100,-100);
        } else if(dir>3 && dir<=4) {
            ball.setVelocity(100,100);
        }
    }

}
