package fi.tamk.tikoot.Game;

import fi.tamk.tikoot.GameObject;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    protected ArrayList<GameObject> gameObjects = new ArrayList<>();

    private void initialize(Stage primaryStage) {
        Settings localSettings = new Settings();
        setSettings(localSettings);

        Group root = new Group();
        Canvas gameCanvas = new Canvas(localSettings.getWidth() + 20,localSettings.getHeight() + 20);
        graphicsContext = gameCanvas.getGraphicsContext2D();
        root.getChildren().add(gameCanvas);
        mainScene = new Scene(root,localSettings.getWidth(),localSettings.getHeight());
        primaryStage.setTitle(localSettings.getTitle());
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

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

    abstract protected void setSettings(Settings settings);
    abstract protected void update(double time);
    abstract protected void collisions();
    abstract protected void draw();
}
