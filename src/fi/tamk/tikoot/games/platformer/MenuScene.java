package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.core.GameApplication;
import fi.tamk.tikoot.pelimoottori.core.GameScene;
import fi.tamk.tikoot.pelimoottori.core.Settings;
import fi.tamk.tikoot.pelimoottori.ui.UIButton;
import fi.tamk.tikoot.pelimoottori.ui.UIText;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

public class MenuScene extends GameScene {


    UIText logo = new UIText("Platformer", Color.BLACK, getScene().getWidth()/2,
            getScene().getHeight()*1/4,50 , getUiRoot());
    UIButton play = new UIButton("PLAY", getUiRoot(), getScene().getWidth()/2, getScene().getHeight()/2);

    public MenuScene(Settings settings, GameApplication app) {
        super(settings, app);
    }

    @Override
    protected void launchProperties() {
        play.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(2);
                changeGameScene(new PlatformerScene(getSettings(), getApp()));
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
