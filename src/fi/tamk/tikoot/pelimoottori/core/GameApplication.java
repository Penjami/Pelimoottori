package fi.tamk.tikoot.pelimoottori.core;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the main-class of the program. Everything starts from here.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public abstract class GameApplication extends Application {

    protected Stage primaryStage;
    protected GameScene gameScene;
    protected GameLoop gameLoop;
    /**
     * Creates and initializes the stage and settings.
     *
     * @param primaryStage Stage in which the games content is drawn.
     */
    private void initialize(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Settings localSettings = new Settings();
        setSettings(localSettings);
        primaryStage.setTitle(localSettings.getTitle());
        primaryStage.setResizable(false);
        primaryStage.setScene(gameScene.getScene());
        primaryStage.show();
        gameScene.launchProperties();
    }

    /**
     * Used to change the scene.
     *
     * @param scene The scene that is drawn on the screen.
     */
    public void changeScene(GameScene scene) {
        gameScene = scene;
        primaryStage.setScene(scene.getScene());
        gameLoop.setGameScene(scene);
        scene.launchProperties();
        gameScene.setInputHandler(new InputHandler(gameScene.getScene()));
    }

    /**
     * Does everything needed to start the game.
     *
     * @param primaryStage Stage in which the games content is drawn.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize(primaryStage);

        final Long[] lastNanoTime = {System.nanoTime()};

        gameScene.setInputHandler(new InputHandler(gameScene.getScene()));

        //Create game gameLoop
        gameLoop = new GameLoop(gameScene, lastNanoTime[0]);
        gameLoop.start();
    }

    /**
     * Setting the necessary settings.
     *
     * @param settings object containing all the settings
     */
    abstract protected void setSettings(Settings settings);

}
