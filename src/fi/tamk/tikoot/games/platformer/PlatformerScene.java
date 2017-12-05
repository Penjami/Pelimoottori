package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.audio.Music;
import fi.tamk.tikoot.pelimoottori.core.GameApplication;
import fi.tamk.tikoot.pelimoottori.core.GameScene;
import fi.tamk.tikoot.pelimoottori.core.Settings;
import fi.tamk.tikoot.pelimoottori.object.Animation;
import fi.tamk.tikoot.pelimoottori.object.GameObject;
import fi.tamk.tikoot.pelimoottori.object.GameObjectCreator;
import javafx.scene.image.Image;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Ray;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Penjami on 6.11.2017.
 */
public class PlatformerScene extends GameScene {

    private double createPlatformTime = 3.1;
    private double timePassed = 0;
    private Image groundSprite = new Image("ground.png");
    private GameObjectCreator creator = new GameObjectCreator();
    private Player player = new Player(MassType.NORMAL, 34, 44, world);
    private ArrayList<GameObject> groundObjects = new ArrayList<>();
    private Animation groundAnimation = new Animation(new Image("belt-sheet.png"), 0.1,
            3,600,32);
    private Music bgm = new Music("src/bgm.mp3");

    private GameObject ground = creator.createRectangleObject(world, getScene().getWidth(),
            32, getScene().getWidth()/2,16, MassType.INFINITE);

    public PlatformerScene(Settings settings, GameApplication app) {
        super(settings, app);
    }

    @Override
    protected void launchProperties() {
        //world.setGravity(World.ZERO_GRAVITY);
        bgm.loop(true);
        player.setPosition(getScene().getWidth()/2-50,getScene().getHeight()/2+50);
    }

    @Override
    protected void update(double time) {
        timePassed += time;


        player.setVelocity(0,player.getVelocityY());
        if (getInputHandler().getInput().contains("SPACE") && player.isGrounded() && !player.hasJumped) {
            player.getBody().applyImpulse(new Vector2(0,0.5));
            player.hasJumped = true;
        }

        if (getInputHandler().getInput().contains("A")) {
            player.setVelocity(-2,player.getVelocityY());
        }
        if (getInputHandler().getInput().contains("D")) {
            player.setVelocity(1,player.getVelocityY());
        }

        if(timePassed > createPlatformTime) {
            createPlatform();
            timePassed = 0;
        }
        player.checkIfGrounded();
        player.getBody().getTransform().setRotation(0);
    }

    @Override
    protected void collisions() {

    }

    @Override
    protected void draw(double time) {
        getGraphicsContext().clearRect(0, 0, getScene().getWidth(), getScene().getHeight());
        ground.render(getGraphicsContext(), time, groundAnimation);
        player.draw(getGraphicsContext(),time);

        for(GameObject groundObject : groundObjects) {
            groundObject.render(getGraphicsContext(), groundSprite);
        }
    }

    @Override
    protected void removeObjects() {}


    private double randomBetweenNum(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    private void createPlatform() {
        int size = (int)randomBetweenNum(1,10);
        GameObject ground1 = creator.createRectangleObject(world, 32,
                32, getScene().getWidth(), randomBetweenNum(0, getScene().getHeight() / 2), MassType.INFINITE);
        ground1.getBody().getFixture(0).setFriction(0);
        ground1.setVelocity(-2, 0);
        groundObjects.add(ground1);

        for(int i = 1; i<size; i++) {
            GameObject lastGround = groundObjects.get(groundObjects.size()-1);
            GameObject ground = creator.createRectangleObject(world, 32,
                    32, lastGround.getPositionX()+32,lastGround.getPositionY(), MassType.INFINITE);
            ground.getBody().getFixture(0).setFriction(0);
            ground.setVelocity(-2, 0);
            groundObjects.add(ground);
            /*
            WeldJoint joint = new WeldJoint(lastGround.getBody(), ground.getBody(),
                    new Vector2(lastGround.getPositionX()+16,lastGround.getPositionY()));
                    */
        }
    }

}
