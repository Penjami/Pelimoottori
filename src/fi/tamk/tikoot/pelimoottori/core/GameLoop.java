package fi.tamk.tikoot.pelimoottori.core;

import javafx.animation.AnimationTimer;

/**
 * This is the gameloop. update and draw gameobjects every frame.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class GameLoop extends AnimationTimer {
    private GameScene gameScene;
    private Long lastNanoTime;

    /**
     * This is the constructor for gameloop.
     *
     * @param gameScene The gameScene that contains all the information required to draw and update the game.
     * @param lastNanoTime The lastNanoTime is the amount of time in milliseconds from the start of 1970.
     */
    public GameLoop(GameScene gameScene, Long lastNanoTime) {
        this.gameScene = gameScene;
        this.lastNanoTime = lastNanoTime;
    }

    /**
     * This is the constructor for gameloop.
     *
     * @param currentNanoTime The currentNanoTime is the amount of time in milliseconds from the last frame to this frame.
     */
    @Override
    public void handle(long currentNanoTime) {
        // calculate time since last update.
        double delta = (currentNanoTime - lastNanoTime) / 1000000000.0;
        gameScene.loop(delta);
        lastNanoTime = currentNanoTime;
    }

    /**
     * This is the constructor for gameloop.
     *
     * @param gameScene The gameScene that contains all the information required to draw and update the game.
     */
    public void setGameScene(GameScene gameScene) {
        this.gameScene = gameScene;
    }
}
