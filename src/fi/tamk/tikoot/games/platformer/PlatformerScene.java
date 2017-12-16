package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.core.GameApplication;
import fi.tamk.tikoot.pelimoottori.core.GameScene;
import fi.tamk.tikoot.pelimoottori.core.Settings;
import fi.tamk.tikoot.pelimoottori.object.GameObject;
import fi.tamk.tikoot.pelimoottori.object.GameObjectCreator;
import fi.tamk.tikoot.pelimoottori.tile.TileMap;
import fi.tamk.tikoot.pelimoottori.tile.TileImage;
import javafx.scene.image.Image;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This is the main scene class for the platformer game.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class PlatformerScene extends GameScene {

    private double createPlatformTime = 2.5;
    private double timePassed = 0;
    private Image groundSprite = new Image("ground.png");
    private GameObjectCreator creator = new GameObjectCreator();
    private Player player = new Player(MassType.NORMAL, 34, 44, world);
    private GameObject killBox = creator.createRectangleObject(
            world,32, getScene().getHeight(),-getScene().getWidth()/2,
            getScene().getHeight()/2, MassType.INFINITE);
    private GameObject deathBox = creator.createRectangleObject(
            world,getScene().getWidth() * 2, 32,getScene().getWidth()/2,
            -getScene().getHeight()/2, MassType.INFINITE);
    private ArrayList<GameObject> groundObjects = new ArrayList<>();
    private ArrayList<GameObject> removeObjects = new ArrayList<>();
    private TileMap tileMap1;
    private TileMap tileMap2;
    private TileImage tileImage;

    public PlatformerScene(Settings settings, GameApplication app) {
        super(settings, app);
    }

    @Override
    protected void launchProperties() {
        player.setPosition(getScene().getWidth()*1/5,getScene().getHeight()/2+50);
        createPlatform(getScene().getWidth()*1/4, getScene().getHeight()*1/3);
        createPlatform(getScene().getWidth()*9/10, getScene().getHeight()*1/5);
        killBox.getBody().getFixture(0).setSensor(true);
        tileImage = new TileImage("tileSheet.png", 32, 17,8);
        tileMap1 = new TileMap("src/platformerMap1.txt", tileImage,0,0);
        tileMap2 = new TileMap("src/platformerMap2.txt", tileImage,864,0);
    }

    @Override
    protected void update(double time) {
        timePassed += time;


        player.setVelocity(0,player.getVelocityY());
        if (getInputHandler().getInput().contains("SPACE") && player.isGrounded() && !player.hasJumped) {
            player.getBody().applyImpulse(new Vector2(0,0.6));
            player.hasJumped = true;
        }

        if (getInputHandler().getInput().contains("A")) {
            player.setVelocity(-2,player.getVelocityY());
        }
        if (getInputHandler().getInput().contains("D")) {
            player.setVelocity(1,player.getVelocityY());
        }

        if(timePassed > createPlatformTime) {
            createPlatform(getScene().getWidth(), randomBetweenNum(getScene().getHeight()*1/5, getScene().getHeight()*2/5));
            timePassed = 0;
        }

        tileMap1.setPosition(tileMap1.getMapX() - 0.2, tileMap1.getMapY());
        tileMap2.setPosition(tileMap2.getMapX() - 0.2, tileMap2.getMapY());

        if(tileMap1.getMapX()<-832) {
            tileMap1.setPosition(864, tileMap1.getMapY());
        }
        if(tileMap2.getMapX()<-832) {
            tileMap2.setPosition(864, tileMap2.getMapY());
        }

        player.checkIfGrounded();
        player.getBody().getTransform().setRotation(0);
        removeRemovedObjects();
    }

    @Override
    protected void collisions() {
        if(deathBox.intersects(player)){
            changeGameScene(new MenuScene(getSettings(), getApp()));
        }
        Iterator<GameObject> itr = groundObjects.iterator();
        while(itr.hasNext()){
            GameObject groundObject = itr.next();
            if(killBox.intersects(groundObject)) {
                removeObjects.add(groundObject);
                itr.remove();
            }
        }
    }

    @Override
    protected void draw(double time) {
        getGraphicsContext().clearRect(0, 0, getScene().getWidth(), getScene().getHeight());
        tileMap1.draw(getGraphicsContext());
        tileMap2.draw(getGraphicsContext());
        player.draw(getGraphicsContext(),time);
        for(GameObject groundObject : groundObjects) {
            groundObject.render(getGraphicsContext(), groundSprite);
        }
    }

    /**
     * @param min The minimum value
     * @param max The maximum value.
     * @return Random between the min and max.
     */
    private double randomBetweenNum(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    /**
     *  Creates between 5*64 and 10*64 pixels long platform at the x and y location.
     *
     * @param x The horizontal location of where the first piece of the platform is placed.
     * @param y The vertical location of where the first piece of the platform is placed.
     */
    private void createPlatform(double x, double y) {
        int size = (int)randomBetweenNum(2,10);
        GameObject ground1 = creator.createRectangleObject(world, 32,
                32, x, y, MassType.INFINITE);
        ground1.getBody().getFixture(0).setFriction(0);
        ground1.setVelocity(-3, 0);
        groundObjects.add(ground1);

        for(int i = 1; i<size; i++) {
            GameObject lastGround = groundObjects.get(groundObjects.size()-1);
            GameObject ground = creator.createRectangleObject(world, 32,
                    32, lastGround.getPositionX()+32,lastGround.getPositionY(), MassType.INFINITE);
            ground.getBody().getFixture(0).setFriction(0);
            ground.setVelocity(-3, 0);
            groundObjects.add(ground);
            /*
            WeldJoint joint = new WeldJoint(lastGround.getBody(), ground.getBody(),
                    new Vector2(lastGround.getPositionX()+16,lastGround.getPositionY()));
                    */
        }
    }

    /**
     * Remove the bodies in the removedObject list.
     */
    private void removeRemovedObjects() {
        for(GameObject removedObject : removeObjects) {
            world.removeBody(removedObject.getBody());
        }
    }

}
