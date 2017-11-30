package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.*;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

/**
 * Created by Penjami on 6.11.2017.
 */
public class PlatformerScene extends GameScene {

    private AnimationImageObject player = new AnimationImageObject("ball-sheet.png",
            world, 0.100, 4, 4, 0, 0, 64, 64);
    private Music bgm = new Music("src/bgm.mp3");

    private GameObject wallDown = new GameObject(MassType.INFINITE, getScene().getWidth()/2,
            -5, getScene().getWidth(), 10, world);

    public PlatformerScene(Settings settings, GameApplication app) {
        super(settings, app);
    }

    @Override
    protected void launchProperties() {
        //world.setGravity(World.ZERO_GRAVITY);
        bgm.loop(true);

        player.getBody().removeFixture(0);
        player.getBody().addFixture(new Circle(64/2/Settings.SCALE));
        player.getBody().setBullet(true);

        player.setPosition(getScene().getWidth()/2+50,getScene().getHeight()/2+50);
    }

    @Override
    protected void update(double time) {

        player.setVelocity(0,player.getVelocityY());
        if (getInputHandler().getInput().contains("W") && player.isGrounded) {
            player.getBody().applyForce(new Vector2(0,100));
        }
        if (getInputHandler().getInput().contains("A")) {
            player.addVelocity(-1,0);
        }
        if (getInputHandler().getInput().contains("D")) {
            player.setVelocity(1,0);
        }
    }

    @Override
    protected void collisions() {

    }

    @Override
    protected void draw(double time) {
        getGraphicsContext().clearRect(0, 0, getScene().getWidth(), getScene().getHeight());
        player.render(getGraphicsContext(), time);

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
