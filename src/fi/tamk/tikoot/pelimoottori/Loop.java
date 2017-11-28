package fi.tamk.tikoot.pelimoottori;

import javafx.animation.AnimationTimer;

public class Loop extends AnimationTimer {
    private GameScene gameScene;
    private Long lastNanoTime;

    public Loop(GameScene gameScene, Long lastNanoTime) {
        this.gameScene = gameScene;
        this.lastNanoTime = lastNanoTime;
    }

    @Override
    public void handle(long currentNanoTime) {
        // calculate time since last update.
        float delta = 1f / (1000.0f / ((currentNanoTime-lastNanoTime) / 1000000));
        gameScene.loop(delta);
        lastNanoTime = currentNanoTime;
    }

    public void setGameScene(GameScene scene) {
        this.gameScene = scene;
    }
}
