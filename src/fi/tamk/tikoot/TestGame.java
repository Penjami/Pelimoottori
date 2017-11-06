package fi.tamk.tikoot;

import fi.tamk.tikoot.Game.GameApplication;
import fi.tamk.tikoot.Game.Settings;
import javafx.scene.paint.Paint;

/**
 * Created by Penjami on 6.11.2017.
 */
public class TestGame extends GameApplication {

    Sprite backGround = new Sprite("img.jpg");
    GameObject rock = new GameObject("rock.png");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void setSettings(Settings settings) {
        settings.setTitle("TestGame");
        settings.setWidth(600);
        settings.setHeight(360);
    }
    @Override
    protected void update(double time) {

        rock.setVelocity(0,0);
        if (inputHandler.getInput().contains("LEFT"))
            rock.addVelocity(-50,0);
        if (inputHandler.getInput().contains("RIGHT"))
            rock.addVelocity(50,0);
        if (inputHandler.getInput().contains("UP"))
            rock.addVelocity(0,-50);
        if (inputHandler.getInput().contains("DOWN"))
            rock.addVelocity(0,50);

        rock.update(time);

    }
    @Override
    protected void draw() {
        graphicsContext.clearRect(0, 0, mainScene.getWidth(), mainScene.getHeight());
        backGround.render(graphicsContext);
        rock.render(graphicsContext);
    }
}
