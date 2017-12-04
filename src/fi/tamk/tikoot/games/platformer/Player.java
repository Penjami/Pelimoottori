package fi.tamk.tikoot.games.platformer;

import fi.tamk.tikoot.pelimoottori.object.Animation;
import fi.tamk.tikoot.pelimoottori.object.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dyn4j.dynamics.*;
import org.dyn4j.dynamics.contact.*;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;

public class Player extends GameObject {

    private boolean[] isGrounded = {false};
    public Animation walk = new Animation(new Image("player-walk-sheet.png"), 0.1,
            4,34,44);
    public Image jump = new Image("player-jump.png");

    public Player(MassType type, double width, double height, World world) {
        super(type, width, height, world);
        getBody().addFixture(new Rectangle(getWidth()-0.3,getHeight()+0.1));
        //getBody().getFixture(1).setSensor(true);

        world.addListener(
                new ContactAdapter() {
                    @Override
                    public boolean begin(ContactPoint point) {
                        if(point.getFixture1().equals(getBody().getFixture(1))
                                || point.getFixture2().equals(getBody().getFixture(1))) {
                            System.out.println("je");
                            isGrounded[0] = true;
                        }
                        return true;
                    }


                    @Override
                    public void end(ContactPoint point) {
                        if(point.getFixture1().equals(getBody().getFixture(1))
                                || point.getFixture2().equals(getBody().getFixture(1))) {
                            System.out.println("sa");
                            isGrounded[0] = false;
                        }
                    }
                }
        );
    }

    public boolean isGrounded() {
        return isGrounded[0];
    }

    public void draw(GraphicsContext gc, double time) {
        if(isGrounded()) {
            render(gc, time, walk);
        } else {
            render(gc, jump);
        }

    }
}
