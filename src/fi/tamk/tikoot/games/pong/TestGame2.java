package fi.tamk.tikoot.games.pong;

import fi.tamk.tikoot.pelimoottori.*;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Penjami on 6.11.2017.
 */
public class TestGame2 extends GameApplication {

    private int score = 0;
    private SpriteObject backGround = new SpriteObject("img.jpg");
    private ArrayList<SpriteObject> collectables = new ArrayList<>();
    private ArrayList<SpriteObject> removeList = new ArrayList<>();
    private SpriteObject pongPadPlayer1 = new SpriteObject("pongPad1.png");
    private SpriteObject pongPadPlayer2 = new SpriteObject("pongPad2.png");
    private TextObject points = new TextObject("Points : " + score, Color.ALICEBLUE,0,0);
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
        pongPadPlayer1.setPosition(20,mainScene.getHeight()/2 - pongPadPlayer1.getHeight()/2);
        pongPadPlayer1.setPosition(mainScene.getWidth() - pongPadPlayer2.getWidth(),
                mainScene.getHeight()/2 - pongPadPlayer2.getHeight()/2);
        points.setPosition( mainScene.getWidth()/2, 30);
        int collisions = 1;
        for(int i = 0; i<1; i++) {
            collectables.add(new SpriteObject("soda.png"));
        }
        for(SpriteObject col : collectables) {
            double dir = randomBetweenNum(0,4);
            col.setPosition(randomBetweenNum(0,mainScene.getWidth() - col.getWidth()),
                    randomBetweenNum(0,mainScene.getHeight() - col.getHeight()));
            int max = 100;
            int min = 50;
            if(dir<=1) {
                col.setVelocity(randomBetweenNum(min,max),randomBetweenNum(-min,-max));
            } else if(dir>1 && dir<=2) {
                col.setVelocity(randomBetweenNum(-min,-max),randomBetweenNum(min,max));
            } else if(dir>2 && dir<=3) {
                col.setVelocity(randomBetweenNum(-min,-max),randomBetweenNum(-min,-max));
            } else if(dir>3 && dir<=4) {
                col.setVelocity(randomBetweenNum(min,max),randomBetweenNum(min,max));
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

        pongPadPlayer2.setVelocity(0,0);
        pongPadPlayer1.setVelocity(0,0);
        if (inputHandler.getInput().contains("W"))
            pongPadPlayer1.addVelocity(0,-100);
        if (inputHandler.getInput().contains("S"))
            pongPadPlayer1.addVelocity(0,100);
        if (inputHandler.getInput().contains("UP"))
            pongPadPlayer2.addVelocity(0,-100);
        if (inputHandler.getInput().contains("DOWN"))
            pongPadPlayer2.addVelocity(0,100);

        for(SpriteObject col : collectables) {
            col.update(time);
        }
        pongPadPlayer1.update(time);
        pongPadPlayer2.update(time);

        if(collectables.isEmpty()) {
        }
    }

    @Override
    protected void collisions() {
        for(SpriteObject col : collectables) {
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
        for(SpriteObject col : collectables) {
            changeDirIfHitWall(col);
        }


    }

    @Override
    protected void draw() {
        graphicsContext.clearRect(0, 0, mainScene.getWidth(), mainScene.getHeight());
        backGround.render(graphicsContext);
        for(SpriteObject col : collectables) {
            col.render(graphicsContext);
        }
        pongPadPlayer1.render(graphicsContext);
        pongPadPlayer2.render(graphicsContext);
        points.render(graphicsContext);
    }

    @Override
    protected void removeObjects() {
        collectables.removeAll(removeList);
    }

    private void changeDirIfHitWall(SpriteObject sprite) {
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
