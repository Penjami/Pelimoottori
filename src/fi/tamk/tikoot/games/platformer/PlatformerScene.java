package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;

/**
 * Created by Penjami on 6.11.2017.
 */
public class PlatformerScene extends GameScene {

    private int player1Score = 0;
    private int player2Score = 0;
    private AnimationImageObject ball = new AnimationImageObject("ball-sheet.png",
            world, 0.100, 4, 4, 0, 0, 32, 32);
    private ImageObject player = new ImageObject("pongPad1.png", world);
    private TextObject player2ScoreText =
            new TextObject("P2 Points : " + player1Score, Color.ALICEBLUE,
                    getScene().getWidth()/2-100,30, 25, getUiRoot());
    private TextObject player1ScoreText =
            new TextObject("P1 Points : " + player2Score, Color.DARKRED,
                    getScene().getWidth()/2+100,30, 25, getUiRoot());
    private Music bgm = new Music("src/bgm.mp3");
    private Sound ballBounceWallSound = new Sound("src/ballHit.wav");
    private Sound ballBouncePadSound = new Sound("src/blop.mp3");
    private double gameSpeed = 2;

    private GameObject wallDown = new GameObject(MassType.INFINITE, getScene().getWidth()/2,
            -5, getScene().getWidth(), 10, world);

    public PlatformerScene(Settings settings, GameApplication app) {
        super(settings, app);
    }

    @Override
    protected void launchProperties() {
        world.setGravity(World.ZERO_GRAVITY);
        bgm.loop(true);

        ball.getBody().removeFixture(0);
        ball.getBody().addFixture(new Circle(ball.getWidth()/2/Settings.SCALE));
        ball.setVelocity(4,4);
        ball.getBody().setBullet(true);

        ball.setPosition(getScene().getWidth()/2-20,getScene().getHeight()/2);

        for(Body body : world.getBodies()) {
            body.getFixture(0).setRestitution(1);
            body.getFixture(0).setFriction(0);
        }
    }

    @Override
    protected void update(double time) {
        if(!bgm.isPlaying()) {
            bgm.play();
        }


        if (getInputHandler().getInput().contains("W")) {
        }
        if (getInputHandler().getInput().contains("A")) {
            player.addVelocity(0,-4);
        }
        if (getInputHandler().getInput().contains("S")) {
            player.setVelocity(0,4);
        }
        if (getInputHandler().getInput().contains("D")) {
            player.setVelocity(0,-4);
        }

    }

    @Override
    protected void collisions() {

    }

    @Override
    protected void draw(double time) {
        getGraphicsContext().clearRect(0, 0, getScene().getWidth(), getScene().getHeight());
        ball.render(getGraphicsContext(), time);
        player.render(getGraphicsContext());
        getGraphicsContext().fill();
    }

    @Override
    protected void removeObjects() {}

    /*
    private double randomBetweenNum(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }
    */
}
