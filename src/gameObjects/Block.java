// 206438186 Lior Muallem

package gameObjects;

import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import gameSetup.GameLevel;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a block object in a game that can be collided with.
 *
 * @author Lior Muallem
 * @version 1.0
 * @since 2023 -03-20
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Point upperLeft;
    private int width;
    private int height;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructs a new Block object with the specified upper-left corner, width, and height.
     *
     * @param upperLeft the upper-left corner of the block
     * @param width     the width of the block
     * @param height    the height of the block
     */
    public Block(Point upperLeft, int width, int height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        //Set default block color to black
        this.color = Color.BLACK;
        //Initialize listeners
        this.hitListeners =  new ArrayList<HitListener>();
    }


    /**
     * sets the color of the block.
     *
     * @param color the color to set the block to.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return the collision rectangle of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(upperLeft, width, height);
    }

    /**
     * Returns the new velocity after hitting the block.
     *
     * @param collisionPoint  the point where the hit occurred
     * @param currentVelocity the current velocity of the ball before the hit
     * @return the new velocity after hitting the block
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity;
        //Check if the collision line is vertical or horizontal
        if (this.getCollisionRectangle().hitOnSides(collisionPoint)) {
            //Hit in the side, change x direction
            newVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            //Hits from the below or above, change y direction
            newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface the surface to draw the block on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        //Draw the block itself
        surface.setColor(this.color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), this.width, this.height);
        //Draw the block borders
        surface.setColor(Color.lightGray);
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), this.width, this.height);
    }

    /**
     * This method is called once every frame, it does nothing yet.
     */
    @Override
    public void timePassed() {
        // This method does nothing yet
    }


    /**
     * Adds the block to a given game object both to the sprite and collidable lists.
     *
     * @param g the game to add the block to
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * This method notify that hit occurred.
     *
     * @param hitter the balls which hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }


    /**
     * This method removes the block to a given game.
     *
     * @param gameLevel the game to remove the block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
}
