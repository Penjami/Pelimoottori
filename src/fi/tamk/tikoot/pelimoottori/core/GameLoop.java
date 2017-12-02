package fi.tamk.tikoot.pelimoottori.core;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private GameScene gameScene;
    private Long lastNanoTime;

    public GameLoop(GameScene gameScene, Long lastNanoTime) {
        this.gameScene = gameScene;
        this.lastNanoTime = lastNanoTime;
    }

    @Override
    public void handle(long currentNanoTime) {
        // calculate time since last update.
        double delta = (currentNanoTime - lastNanoTime) / 1000000000.0;
        gameScene.loop(delta);
        lastNanoTime = currentNanoTime;
    }

    public void setGameScene(GameScene scene) {
        this.gameScene = scene;
    }
}
