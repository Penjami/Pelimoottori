package fi.tamk.tikoot;

import fi.tamk.tikoot.Game.GameApplication;
import fi.tamk.tikoot.Game.Settings;

import java.util.ArrayList;

/**
 * Created by Penjami on 6.11.2017.
 */
public class TestGame extends GameApplication {

    private Sprite backGround = new Sprite("img.jpg");
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private GameObject monk = new GameObject("monk.png");
    private GameObject soda1 = new GameObject("soda.png");
    private GameObject soda2 = new GameObject("soda.png");
    private GameObject soda3 = new GameObject("soda.png");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void setSettings(Settings settings) {
        settings.setTitle("TestGame");
        settings.setWidth(600);
        settings.setHeight(360);
        gameObjects.add(monk);
        gameObjects.add(soda1);
        gameObjects.add(soda2);
        gameObjects.add(soda3);
        soda1.setPosition(300,200);
        soda2.setPosition(100,300);
        soda3.setPosition(300,300);


    }
    @Override
    protected void update(double time) {

        monk.setVelocity(0,0);
        if (inputHandler.getInput().contains("LEFT"))
            monk.addVelocity(-50,0);
        if (inputHandler.getInput().contains("RIGHT"))
            monk.addVelocity(50,0);
        if (inputHandler.getInput().contains("UP"))
            monk.addVelocity(0,-50);
        if (inputHandler.getInput().contains("DOWN"))
            monk.addVelocity(0,50);

        monk.update(time);

    }

    @Override
    protected void collisions() {
        if(monk.intersects(soda1)) {
            gameObjects.remove(soda1);
        }
        if(monk.intersects(soda2)) {
            gameObjects.remove(soda2);
        }
        if(monk.intersects(soda3)) {
            gameObjects.remove(soda3);
        }
    }

    @Override
    protected void draw() {
        graphicsContext.clearRect(0, 0, mainScene.getWidth(), mainScene.getHeight());
        backGround.render(graphicsContext);
        for(GameObject obj : gameObjects) {
            obj.render(graphicsContext);
        }
    }
}
