package fi.tamk.tikoot.games.pong;

import fi.tamk.tikoot.pelimoottori.audio.Music;
import fi.tamk.tikoot.pelimoottori.audio.Sound;
import fi.tamk.tikoot.pelimoottori.core.GameApplication;
import fi.tamk.tikoot.pelimoottori.core.GameScene;
import fi.tamk.tikoot.pelimoottori.core.Settings;
import fi.tamk.tikoot.pelimoottori.object.GameObject;
import fi.tamk.tikoot.pelimoottori.object.GameObjectCreator;
import fi.tamk.tikoot.pelimoottori.ui.UIText;
import javafx.scene.paint.Color;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.MassType;

/**
 * Created by Penjami on 6.11.2017.
 */
public class PongScene extends GameScene {

    private GameObjectCreator creator = new GameObjectCreator();
    private int player1Score = 0;
    private int player2Score = 0;
    private UIText player2ScoreText =
            new UIText("P2 Points : " + player1Score, Color.ALICEBLUE,
                    getScene().getWidth()/2-100,30, 25, getUiRoot());
    private UIText player1ScoreText =
            new UIText("P1 Points : " + player2Score, Color.DARKRED,
                    getScene().getWidth()/2+100,30, 25, getUiRoot());
    private Music bgm = new Music("src/bgm.mp3");
    private Sound ballBounceWallSound = new Sound("src/ballHit.wav");
    private Sound ballBouncePadSound = new Sound("src/blop.mp3");
    private double gameSpeed = 2;

    private GameObject pongPadPlayer1 = creator.createRectangleSpriteObject
            ("pongPad1.png", world,20,getScene().getHeight()/2,MassType.INFINITE);
    private GameObject pongPadPlayer2 = creator.createRectangleSpriteObject
            ("pongPad2.png", world,getScene().getWidth()-20,getScene().getHeight()/2, MassType.INFINITE);
    private GameObject ball = creator.createCircleSpriteObject
            ("ball.png",world,getScene().getWidth()/2-20, getScene().getHeight()/2,MassType.NORMAL);
    private GameObject wallUp = creator.createRectangleObject
            (world,getScene().getWidth(),10,getScene().getWidth()/2,getScene().getHeight() - 5, MassType.INFINITE);
    private GameObject wallDown = creator.createRectangleObject
            (world,getScene().getWidth(),10,getScene().getWidth()/2,-5, MassType.INFINITE);
    private GameObject wallRight = creator.createRectangleObject
            (world,10,getScene().getHeight(),getScene().getWidth(),getScene().getHeight()/2, MassType.INFINITE);
    private GameObject wallLeft = creator.createRectangleObject
            (world,10,getScene().getHeight(), 0, getScene().getHeight()/2, MassType.INFINITE);

    public PongScene(Settings settings, GameApplication app) {
        super(settings, app);
    }

    @Override
    protected void launchProperties() {
        world.setGravity(World.ZERO_GRAVITY);
        bgm.loop(true);

        ball.setVelocity(4,4);
        ball.getBody().setBullet(true);

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

        pongPadPlayer1.setVelocity(0,0);
        pongPadPlayer2.setVelocity(0,0);
        if (getInputHandler().getInput().contains("W") &&
                pongPadPlayer1.getPositionY() < getScene().getHeight() - pongPadPlayer1.getHeight()/2) {
            pongPadPlayer1.addVelocity(0,4);
        }
        if (getInputHandler().getInput().contains("S") && pongPadPlayer1.getPositionY() > pongPadPlayer1.getHeight()/2) {
            pongPadPlayer1.addVelocity(0,-4);
        }
        if (getInputHandler().getInput().contains("UP") &&
                pongPadPlayer2.getPositionY() < getScene().getHeight() - pongPadPlayer2.getHeight()/2) {
            pongPadPlayer2.setVelocity(0,4);
        }
        if (getInputHandler().getInput().contains("DOWN") && pongPadPlayer2.getPositionY() > pongPadPlayer2.getHeight()/2) {
            pongPadPlayer2.setVelocity(0,-4);
        }

        ball.getBody().getTransform().setRotation(0);
    }

    @Override
    protected void collisions() {
        if(ball.getBody().isInContact(wallLeft.getBody())) {
            ball.setPosition(getScene().getWidth()/2-20,getScene().getHeight()/2);
            ball.setVelocity(4,4);
            player1Score++;
            player1ScoreText.setText("P1 Points : " + player1Score);
        }
        if( ball.getBody().isInContact(wallRight.getBody())) {
            ball.setPosition(getScene().getWidth()/2-20,getScene().getHeight()/2);
            ball.setVelocity(-4,4);
            player2Score++;
            player2ScoreText.setText("P2 Points : " + player2Score);
        }
    }

    @Override
    protected void draw(double time) {
        getGraphicsContext().clearRect(0, 0, getScene().getWidth(), getScene().getHeight());
        ball.render(getGraphicsContext());
        pongPadPlayer1.render(getGraphicsContext());
        pongPadPlayer2.render(getGraphicsContext());
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
