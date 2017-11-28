package fi.tamk.tikoot.games.pong;

import fi.tamk.tikoot.pelimoottori.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.paint.Color;

import java.beans.EventHandler;

public class MenuScene extends GameScene {


    TextObject logo = new TextObject("Platformer", Color.BLACK, getScene().getWidth()/2,
            getScene().getHeight()*1/4,50 , getUiRoot());
    ButtonObject play = new ButtonObject("PLAY", getUiRoot(), getScene().getWidth()/2, getScene().getHeight()/2);

    public MenuScene(Settings settings, GameApplication app) {
        super(settings, app);
    }

    @Override
    protected void launchProperties() {
        play.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(2);
                changeGameScene(new PongScene(getSettings(), getApp()));
            }
        });
    }

    @Override
    protected void update(double time) {

    }

    @Override
    protected void collisions() {

    }

    @Override
    protected void draw(double time) {

    }

    @Override
    protected void removeObjects() {

    }
}
