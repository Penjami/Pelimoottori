package fi.tamk.tikoot.games.pong;

import fi.tamk.tikoot.pelimoottori.core.GameApplication;
import fi.tamk.tikoot.pelimoottori.core.GameScene;
import fi.tamk.tikoot.pelimoottori.core.Settings;
import fi.tamk.tikoot.pelimoottori.ui.UIButton;
import fi.tamk.tikoot.pelimoottori.ui.UIText;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

/**
 *  This class is the menu scene of the Pong Game.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */

public class MenuScene extends GameScene {


    UIText logo = new UIText("Pong", Color.BLACK, getScene().getWidth()/2,
            getScene().getHeight()*1/4,50 , getUiRoot());
    UIText instructions1 = new UIText("P1 controls", Color.BLACK, getScene().getWidth()*1/6,
            getScene().getHeight()*1/2,30 , getUiRoot());
    UIText instructions2 = new UIText("w = go up", Color.BLACK, getScene().getWidth()*1/6,
            getScene().getHeight()*11/18,20 , getUiRoot());
    UIText instructions3 = new UIText("s = go down", Color.BLACK, getScene().getWidth()*1/6,
            getScene().getHeight()*12/18,20 , getUiRoot());
    UIText instructions4 = new UIText("P2 controls", Color.BLACK, getScene().getWidth()*5/6,
            getScene().getHeight()*1/2,30 , getUiRoot());
    UIText instructions5 = new UIText("Arrow up = go up", Color.BLACK, getScene().getWidth()*5/6,
            getScene().getHeight()*11/18,20 , getUiRoot());
    UIText instructions6 = new UIText("Arrow down = go down", Color.BLACK, getScene().getWidth()*5/6,
            getScene().getHeight()*12/18,20 , getUiRoot());
    UIButton play = new UIButton("PLAY", getUiRoot(), getScene().getWidth()/2, getScene().getHeight()/2);

    /**
     * This is the constructor of the MenuScene class.
     *
     * @param app The app is used to manage the gameScenes.
     * @param settings The settings contains the parameters that are needed to set the gameScene.
     */
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

}
