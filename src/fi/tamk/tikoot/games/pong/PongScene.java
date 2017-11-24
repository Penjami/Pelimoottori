package fi.tamk.tikoot.games.pong;

import fi.tamk.tikoot.pelimoottori.*;
import javafx.scene.paint.Color;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import java.util.Random;

/**
 * Created by Penjami on 6.11.2017.
 */
public class PongScene extends GameScene {

    private int player1Score = 0;
    private int player2Score = 0;
    private ImageObject pongPadPlayer1 = new ImageObject("pongPad1.png", world);
    private ImageObject pongPadPlayer2 = new ImageObject("pongPad2.png", world);
    private ImageObject ball = new ImageObject("ball.png", world);
    private TextObject player1ScoreText =
            new TextObject("P2 Points : " + player1Score, Color.ALICEBLUE,
                    getScene().getWidth()/2-250,30, 25, getUiRoot());
    private TextObject player2ScoreText =
            new TextObject("P1 Points : " + player2Score, Color.DARKRED,
                    getScene().getWidth()/2+100,30, 25, getUiRoot());
    private Music bgm = new Music("src/bgm.mp3");
    private Sound ballBounceWallSound = new Sound("src/ballHit.wav");
    private Sound ballBouncePadSound = new Sound("src/blop.mp3");
    private double gameSpeed = 2;

    private GameObject wallUp = new GameObject(MassType.INFINITE, 0,
            getScene().getHeight(),10, 1, world);
    private GameObject wallDown = new GameObject(MassType.INFINITE, 0,
            0,1000, 10, world);
    private GameObject wallRigth = new GameObject(MassType.INFINITE, getScene().getWidth()/2,
            getScene().getHeight()/2,1, getScene().getHeight(), world);
    private GameObject wallLeft = new GameObject(MassType.INFINITE, -getScene().getWidth()/2,
            getScene().getHeight()/2,1, getScene().getHeight(), world);

    public PongScene(Settings settings) {
        super(settings);
    }

    @Override
    protected void launchProperties() {
        bgm.loop(true);
        wallDown.body.isStatic();
        //pongPadPlayer1.setPosition(-200,getScene().getHeight()/2 - pongPadPlayer1.getHeight()/2);
        /*
        pongPadPlayer2.setPosition(getScene().getWidth() - pongPadPlayer2.getWidth(),
                getScene().getHeight()/2 - pongPadPlayer2.getHeight()/2);

        ball.setPosition(getScene().getWidth()/2, getScene().getHeight()/2);
        randomBallVel();
        */
    }

    @Override
    protected void update(double time) {

        if(!bgm.isPlaying()) {
            bgm.play();
        }

        if (getInputHandler().getInput().contains("W")) {
            pongPadPlayer1.addVelocity(0,100 * gameSpeed);
        }
        if (getInputHandler().getInput().contains("S")){
            pongPadPlayer1.addVelocity(0,-100 * gameSpeed);
        }
        if (getInputHandler().getInput().contains("UP")){
            pongPadPlayer2.addVelocity(0,100 * gameSpeed);
        }
        if (getInputHandler().getInput().contains("DOWN")) {
            pongPadPlayer2.addVelocity(0,-100 * gameSpeed);
        }

    }

    @Override
    protected void collisions() {
        /*
        if(pongPadPlayer1.intersects(ball) || pongPadPlayer2.intersects(ball) ) {
            ball.setVelocity(-ball.getVelocityX(), ball.getVelocityY());
            ballBouncePadSound.play();
        }
        //changeDirIfHitWall(ball);
        */

    }

    @Override
    protected void draw() {
        getGraphicsContext().clearRect(0, 0, getScene().getWidth(), getScene().getHeight());
        ball.render(getGraphicsContext());
        pongPadPlayer1.render(getGraphicsContext());
        pongPadPlayer2.render(getGraphicsContext());
    }

    @Override
    protected void removeObjects() {}

    /*
    private void changeDirIfHitWall(ImageObject sprite) {
        if(sprite.getPositionX() >= getScene().getWidth() - sprite.getWidth()) {
            player1Score++;
            player1ScoreText.setText("P1 Points : " + player1Score);
            ball.setPosition(getScene().getWidth()/2, getScene().getHeight()/2);
            randomBallVel();
            ballBounceWallSound.play();
        }
        if(sprite.getPositionX() <= 0) {
            player2Score++;
            player2ScoreText.setText("P2 Points : " + player2Score);
            ball.setPosition(getScene().getWidth()/2, getScene().getHeight()/2);
            randomBallVel();
            ballBounceWallSound.play();
        }
        if(sprite.getPositionY() >= getScene().getHeight() - sprite.getHeight()) {
            sprite.setVelocity(sprite.getVelocityX(),sprite.getVelocityY() * -1);
            ballBounceWallSound.play();
        }
        if(sprite.getPositionY() <= 0) {
            sprite.setVelocity(sprite.getVelocityX(), sprite.getVelocityY() * -1);
            ballBounceWallSound.play();
        }
    }

    private double randomBetweenNum(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }
    */
}
