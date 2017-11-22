package fi.tamk.tikoot.pelimoottori;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * This is the main-class of the program. Everything starts from here.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public abstract class GameApplication extends Application {
    /*
        while(isRunning)
    {
        Input->readInput();
        isRunning = GameLogic->doLogic();
        Camera->update();
        World->update();
        GUI->update();
        AI->update();
        Audio->play();
        Render->draw();
    }
     */
    protected Scene mainScene;
    protected GraphicsContext graphicsContext;
    protected InputHandler inputHandler;
    private Stage primaryStage;
    private GameScene gameScene;

    /**
     * Creates and initializes the stage and settings.
     *
     * @param primaryStage Stage in which the games content is drawn.
     */
    private void initialize(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Settings localSettings = new Settings();
        setSettings(localSettings);

        Group root = new Group();
        Canvas gameCanvas = new Canvas(localSettings.getWidth() + 20,localSettings.getHeight() + 20);
        graphicsContext = gameCanvas.getGraphicsContext2D();
        graphicsContext.setFont(Font.font(30));
        root.getChildren().add(gameCanvas);
        mainScene = new Scene(root,localSettings.getWidth(),localSettings.getHeight());
        primaryStage.setTitle(localSettings.getTitle());
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        launchProperties();
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

        inputHandler = new InputHandler(mainScene);

        //Create game loop
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime[0]) / 1000000000.0;
                lastNanoTime[0] = currentNanoTime;
                gameScene.update(elapsedTime);

            }
        }.start();
    }

    /**
     * Setting the necessary settings.
     *
     * @param settings object containing all the settings
     */
    abstract protected void setSettings(Settings settings);
}
