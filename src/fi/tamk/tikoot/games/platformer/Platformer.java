package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.core.GameApplication;
import fi.tamk.tikoot.pelimoottori.core.Settings;

public class Platformer extends GameApplication {
    private PlatformerScene platformerScene;

    public static void main(String[] args) { launch(args); }

    @Override
    protected void setSettings(Settings settings) {
        settings.setTitle("Platformer");
        settings.setWidth(600);
        settings.setHeight(360);
        gameScene = new MenuScene(settings, this);
    }
}
