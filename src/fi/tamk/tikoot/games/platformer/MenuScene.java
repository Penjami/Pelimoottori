package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.core.GameApplication;
import fi.tamk.tikoot.pelimoottori.core.GameScene;
import fi.tamk.tikoot.pelimoottori.core.Settings;
import fi.tamk.tikoot.pelimoottori.ui.UIButton;
import fi.tamk.tikoot.pelimoottori.ui.UIText;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

/**
 * This class is the menu scene of the Platformer Game.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class MenuScene extends GameScene {


    UIText logo = new UIText("Platformer", Color.BLACK, getScene().getWidth()/2,
            getScene().getHeight()*1/4,50 , getUiRoot());
    UIText instructions1 = new UIText("Controls", Color.BLACK, getScene().getWidth()/2,
            getScene().getHeight()*4/7,30 , getUiRoot());
    UIText instructions2 = new UIText("a = go right", Color.BLACK, getScene().getWidth()/2,
            getScene().getHeight()*12/18,20 , getUiRoot());
    UIText instructions3 = new UIText("d = go left", Color.BLACK, getScene().getWidth()/2,
            getScene().getHeight()*13/18,20 , getUiRoot());
    UIText instructions4 = new UIText("space = jump", Color.BLACK, getScene().getWidth()/2,
            getScene().getHeight()*14/18,20 , getUiRoot());
    UIButton play = new UIButton("PLAY", getUiRoot(), getScene().getWidth()/2, getScene().getHeight()*3/7);

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
        play.setOnAction(event -> {
            System.out.println(2);
            changeGameScene(new PlatformerScene(getSettings(), getApp()));
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
