package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.core.GameApplication;
import fi.tamk.tikoot.pelimoottori.core.Settings;

/**
 * This is the main application class for the platformer game.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class Platformer extends GameApplication {
    private PlatformerScene platformerScene;

    /**
     * Launches the game.
     */
    public static void main(String[] args) { launch(args); }

    @Override
    protected void setSettings(Settings settings) {
        settings.setTitle("Platformer");
        settings.setWidth(800);
        settings.setHeight(480);
        gameScene = new MenuScene(settings, this);
    }
}
