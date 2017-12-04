package fi.tamk.tikoot.pelimoottori.core;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import org.dyn4j.dynamics.World;

/**
 * This is the scene class of the gameEngine.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
abstract public class GameScene {
    private GraphicsContext graphicsContext;
    private Canvas gameCanvas;
    private Group uiRoot;
    private Scene scene;
    private InputHandler inputHandler;
    private Settings settings;
    private GameApplication app;
    public World world = new World();

    /**
     * This is the constructor of the GameScene class.
     *
     * @param app The app is used to manage the gameScenes.
     * @param settings The settings contains the parameters that are needed to set the gameScene.
     */
    public GameScene(Settings settings, GameApplication app) {
        this.app = app;
        this.settings = settings;
        Pane root = new Pane();
        uiRoot = new Group();
        Scale s = new Scale(1, -1);
        Translate t = new Translate(0,-settings.getHeight());
        gameCanvas = new Canvas(settings.getWidth(),settings.getHeight());
        gameCanvas.setWidth(settings.getWidth());
        gameCanvas.setHeight(settings.getHeight());
        setGraphicsContext(gameCanvas.getGraphicsContext2D());
        getGraphicsContext().setFont(Font.font(30));
        gameCanvas.getTransforms().addAll(s,t);
        root.getChildren().addAll(gameCanvas, uiRoot);
        setScene(new Scene(root,settings.getWidth(),settings.getHeight()));
    }

    /**
     * This is the loop of the GameScene class.
     *
     * @param time The time between frames in milliseconds.
     */
    public void loop(double time) {
        update(time);
        collisions();
        draw(time);
        world.update(time);
    }

    /**
     * @return GraphicsContext that is used to draw images and shapes.
     */
    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    /**
     * replaces the current graphicsContext.
     *
     * @param graphicsContext The GraphicsContext that is used to draw images and shapes.
     */
    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    /**
     * @return The javafx scene of this GameScene.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * replaces the current javafx scene.
     *
     * @param scene The javafx scene.
     */
    private void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * A method that invokes changeScene method in the GameApplication.
     *
     * @param gameScene The GameScene that is being changed.
     */
    public void changeGameScene(GameScene gameScene) {
        app.changeScene(gameScene);
    }

    /**
     * @return The InputHandler that has all the inputs of the player.
     */
    public InputHandler getInputHandler() {
        return inputHandler;
    }

    /**
     * replaces the current inputHandler.
     *
     * @param inputHandler The InputHandler that has all the inputs of the player.
     */
    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }


    /**
     * Used to set all objects to start values.
     */
    abstract protected void launchProperties();
    /**
     * Do logic in this method
     *
     * @param time the time between frames.
     */
    abstract protected void update(double time);
    /**
     * Check collision in this method
     */
    abstract protected void collisions();
    /**
     * Draw all sprites in this method
     */
    abstract protected void draw(double time);
    /**
     * Draw all sprites in this method
     */
    abstract protected void removeObjects();

    /**
     * @return Ui root used for ui elements.
     */
    public Group getUiRoot() {
        return uiRoot;
    }

    /**
     * @return Get the settings of this GameScene.
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * @return Get the GameApplication of this GameScene.
     */
    public GameApplication getApp() {
        return app;
    }
}
