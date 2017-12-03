package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.audio.Music;
import fi.tamk.tikoot.pelimoottori.core.GameApplication;
import fi.tamk.tikoot.pelimoottori.core.GameScene;
import fi.tamk.tikoot.pelimoottori.core.Settings;
import fi.tamk.tikoot.pelimoottori.object.Animation;
import fi.tamk.tikoot.pelimoottori.object.GameObject;
import fi.tamk.tikoot.pelimoottori.object.GameObjectCreator;
import javafx.scene.image.Image;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import java.util.ArrayList;

/**
 * Created by Penjami on 6.11.2017.
 */
public class PlatformerScene extends GameScene {

    GameObjectCreator creator = new GameObjectCreator();
    private GameObject player = creator.createRectangleObject(world,64,64 ,64,64,MassType.NORMAL);
    private Animation animation = new Animation(new Image("ball-sheet.png"), 0.1,
            4,4,0,0,64,64);
    private Music bgm = new Music("src/bgm.mp3");

    private GameObject wallDown = new GameObject(MassType.INFINITE, getScene().getWidth(),
            30, world);

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
        wallDown.setPosition(getScene().getWidth()/2,-5);
    }

    @Override
    protected void update(double time) {

        player.setVelocity(0,player.getVelocityY());
        if (getInputHandler().getInput().contains("W")) {
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
        player.render(getGraphicsContext(), time, animation);

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
