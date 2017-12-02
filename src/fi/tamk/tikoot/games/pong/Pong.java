package fi.tamk.tikoot.games.pong;

import fi.tamk.tikoot.pelimoottori.core.GameApplication;
import fi.tamk.tikoot.pelimoottori.core.Settings;

public class Pong extends GameApplication {
    private PongScene pongScene;

    public static void main(String[] args) { launch(args); }

    @Override
    protected void setSettings(Settings settings) {
        settings.setTitle("Pong");
        settings.setWidth(600);
        settings.setHeight(360);
        gameScene = new MenuScene(settings, this);
    }
}
