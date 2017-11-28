package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.GameApplication;
import fi.tamk.tikoot.pelimoottori.Settings;

public class Platformer extends GameApplication {
    private PlatformerScene platformerScene;

    public static void main(String[] args) { launch(args); }

    @Override
    protected void setSettings(Settings settings) {
        settings.setTitle("TestGame");
        settings.setWidth(600);
        settings.setHeight(360);
        gameScene = new MenuScene(settings, this);
    }
}
