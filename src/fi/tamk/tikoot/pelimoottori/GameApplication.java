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
 * Created by Penjami on 5.11.2017.
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
    protected ArrayList<Sprite> sprites = new ArrayList<>();


    /**
     * Creates and initializes the stage and settings.
     *
     * @param primaryStage Stage in which the games content is drawn.
     */
    private void initialize(Stage primaryStage) {
        Settings localSettings = new Settings();
        setSettings(localSettings);

        Group root = new Group();
        Canvas gameCanvas = new Canvas(localSettings.getWidth() + 20,localSettings.getHeight() + 20);
        graphicsContext = gameCanvas.getGraphicsContext2D();
        graphicsContext.setFont(Font.font(40));
        root.getChildren().add(gameCanvas);
        mainScene = new Scene(root,localSettings.getWidth(),localSettings.getHeight());
        primaryStage.setTitle(localSettings.getTitle());
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        launchProperties();
    }

    /**
     * Does everything needed to launchProperties the game.
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

                // game logic
                update(elapsedTime);

                // collision detection
                collisions();

                // render
                draw();

            }
        }.start();
    }

    /**
     * Setting the necessary settings.
     *
     * @param settings object containing all the settings
     */
    abstract protected void setSettings(Settings settings);
    /**
     * Do logic in this method
     *
     * @param time
     */
    abstract protected void update(double time);
    /**
     * Check collision in this method
     */
    abstract protected void collisions();
    /**
     * Draw all sprites in this method
     */
    abstract protected void draw();
    /**
     * Used to set all objects to start values.
     */
    abstract protected void launchProperties();
}
