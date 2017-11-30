package fi.tamk.tikoot.games;

import fi.tamk.tikoot.pelimoottori.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.ArrayList;
/*
/**
 * Created by Penjami on 6.11.2017.
 *
public class TestGame extends GameApplication {

    private int score = 0;
    private ImageObject backGround = new ImageObject("img.jpg");
    private ArrayList<ImageObject> collectables = new ArrayList<>();
    private ArrayList<ImageObject> removeList = new ArrayList<>();
    private ImageObject monk = new ImageObject("monk.png");
    private UIText points = new UIText("Points : " + score, Color.ALICEBLUE, 0, 30, 20);
    private Music bgm = new Music("src/bgm3.mp3");
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
        monk.setPosition(20,40);
        int collisions = 1;
        for(int i = 0; i<10; i++) {
            collectables.add(new ImageObject("soda.png"));
        }
        for(ImageObject col : collectables) {
            double dir = randomBetweenNum(0,4);
            col.setPosition(randomBetweenNum(0,mainScene.getWidth() - col.getWidth()),
                    randomBetweenNum(0,mainScene.getHeight() - col.getHeight()));
            if(dir<=1) {
                col.setVelocity(randomBetweenNum(10,20),randomBetweenNum(-10,-20));
            } else if(dir>1 && dir<=2) {
                col.setVelocity(randomBetweenNum(-10,-20),randomBetweenNum(10,20));
            } else if(dir>2 && dir<=3) {
                col.setVelocity(randomBetweenNum(-10,-20),randomBetweenNum(-10,-20));
            } else if(dir>3 && dir<=4) {
                col.setVelocity(randomBetweenNum(10,20),randomBetweenNum(10,20));
            }
        }
        for (int k = 0; k < collisions; k++) {
            for(int i=0; i<collectables.size(); i++)
            {
                for(int j=i+1; j<collectables.size(); j++)
                {
                    if (collectables.get(i).intersects(collectables.get(j))
                            && collectables.get(i) != collectables.get(j)) {
                        collectables.get(i).setPosition(randomBetweenNum(0,mainScene.getWidth() - collectables.get(i).getWidth()),
                                randomBetweenNum(0,mainScene.getHeight() - collectables.get(i).getHeight()));
                        collisions++;
                    }
                }
            }
        }
    }

    @Override
    protected void update(double time) {

        if(!bgm.isPlaying()) {
            bgm.play();
        }

        monk.setVelocity(0,0);
        if (inputHandler.getInput().contains("LEFT"))
            monk.addVelocity(-50,0);
        if (inputHandler.getInput().contains("RIGHT"))
            monk.addVelocity(50,0);
        if (inputHandler.getInput().contains("UP"))
            monk.addVelocity(0,-50);
        if (inputHandler.getInput().contains("DOWN"))
            monk.addVelocity(0,50);

        for(ImageObject col : collectables) {
            col.update(time);
        }
        monk.update(time);

        if(collectables.isEmpty()) {
        }
    }

    @Override
    protected void collisions() {
        for(ImageObject col : collectables) {
            if(monk.intersects(col)) {
                addScore();
                gotItemSound.play();
                removeList.add(col);
            }
        }
        for(int i=0; i<collectables.size(); i++)
        {
            for(int j=i+1; j<collectables.size(); j++)
            {
                if (collectables.get(i).intersects(collectables.get(j))
                        && collectables.get(i) != collectables.get(j)) {
                    double vel1X = collectables.get(i).getVelocityX();
                    double vel1Y = collectables.get(i).getVelocityY();
                    double vel2X = collectables.get(j).getVelocityX();
                    double vel2Y = collectables.get(j).getVelocityY();

                    collectables.get(i).setVelocity(vel2X, vel2Y);
                    collectables.get(j).setVelocity(vel1X, vel1Y);
                }
            }
        }
        for(ImageObject col : collectables) {
            changeDirIfHitWall(col);
        }


    }

    @Override
    protected void draw() {
        graphicsContext.clearRect(0, 0, mainScene.getWidth(), mainScene.getHeight());
        backGround.render(graphicsContext);
        for(ImageObject col : collectables) {
            col.render(graphicsContext);
        }
        monk.render(graphicsContext);
        points.render(graphicsContext);
    }

    @Override
    protected void removeObjects() {
        collectables.removeAll(removeList);
    }

    private void changeDirIfHitWall(ImageObject sprite) {
        if(sprite.getPositionX() >= mainScene.getWidth() - sprite.getWidth()) {
            sprite.setVelocityX(sprite.getVelocityX() * -1);
        }
        if(sprite.getPositionX() <= 0) {
            sprite.setVelocityX(sprite.getVelocityX() * -1);
        }
        if(sprite.getPositionY() >= mainScene.getHeight() - sprite.getHeight()) {
            sprite.setVelocityY(sprite.getVelocityY() * -1);
        }
        if(sprite.getPositionY() <= 0) {
            sprite.setVelocityY(sprite.getVelocityY() * -1);
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
*/