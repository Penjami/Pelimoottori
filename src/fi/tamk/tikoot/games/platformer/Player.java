package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.core.Settings;
import fi.tamk.tikoot.pelimoottori.object.Animation;
import fi.tamk.tikoot.pelimoottori.object.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dyn4j.collision.Fixture;
import org.dyn4j.dynamics.*;
import org.dyn4j.dynamics.contact.*;
import org.dyn4j.geometry.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This class is used to extend GameObject with methods.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class Player extends GameObject {

    private World world;
    private boolean isGrounded = false;
    public boolean hasJumped = false;
    public Animation walk = new Animation(new Image("player-walk-sheet.png"), 0.1,
            4,34,44);
    public Image jump = new Image("player-jump.png");

    /**
     * Constructor for a Player object.
     *
     * @param type The mass type of the player.
     * @param width The width of the player in pixels.
     * @param height The height of the player in pixels.
     * @param world The physics world of the game.
     */
    public Player(MassType type, double width, double height, World world) {
        super(world);
        this.world = world;
        getBody().addFixture(new Capsule(width/Settings.SCALE,height/Settings.SCALE), 1,0,0);
        getBody().setMass(type);
        getBody().setAutoSleepingEnabled(false);
        getBody().setAngularDamping(1000000000);
    }

    /**
     * @return boolean that tells if the player is on the ground.
     */
    public boolean isGrounded() {
        return isGrounded;
    }

    /**
     * Sends two rays from the bottom of the player to see if the player is on the ground.
     */
    public void checkIfGrounded() {
        List<RaycastResult> results = new ArrayList<>();
        if(world.raycast(getBody().getWorldCenter(),
                new Vector2(getBody().getWorldCenter().x + getWidth()*1/3/Settings.SCALE,
                        getBody().getWorldCenter().y-getHeight()/2/Settings.SCALE-0.1),
                true,true,true, results) ||
                world.raycast(getBody().getWorldCenter(),
                        new Vector2(getBody().getWorldCenter().x - getWidth()*1/3/Settings.SCALE,
                                getBody().getWorldCenter().y-getHeight()/2/Settings.SCALE-0.1),
                        true,true,true, results)) {
            hasJumped = false;
            isGrounded = true;
        } else {
            isGrounded = false;
            hasJumped = true;
        }
    }

    /**
     * Used to render this Player depending on the current state of the player.
     *
     * @param gc The GraphicContext that is used to render the GameObject onto the scene.
     * @param time The time between frames in milliseconds.
     */
    public void draw(GraphicsContext gc, double time) {
        if(isGrounded()) {
            render(gc, time, walk);
        } else {
            render(gc, jump);
        }

    }
}
