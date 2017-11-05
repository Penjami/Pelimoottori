package fi.tamk.tikoot.Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Penjami on 5.11.2017.
 */
public class GameApplication extends Application {
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

    Scene mainScene;

    private void initialize(Stage primaryStage)
    {
        Group root = new Group();
        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );
        mainScene = new Scene(root);
        primaryStage.setTitle("Game Tutorial");
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    private void update()
    {

    }

    private void draw()
    {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize(primaryStage);

        final Long[] lastNanoTime = {new Long(System.nanoTime())};

        InputHandler inputHandler = new InputHandler(mainScene);


        //Create game loop
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime[0]) / 1000000000.0;
                lastNanoTime[0] = currentNanoTime;

                // game logic
                update();

                // collision detection


                // render
                draw();
            }
        }.start();
    }
}
