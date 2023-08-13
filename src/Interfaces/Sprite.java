// 206438186 Lior Muallem

package Interfaces;
import biuoop.DrawSurface;
import gameSetup.GameLevel;

/**
 * The Sprite interface represents an object that can be drawn on a DrawSurface and can change its state over time.
 *
 * @author Lior Muallem
 * @version 1.0
 * @since 2023 -03-20
 */
public interface Sprite {

    /**
     * Draws the sprite on the given DrawSurface object.
     *
     * @param d the DrawSurface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add the Sprite object to the game.
     *
     * @param g the game to add the sprite to
     */
    void addToGame(GameLevel g);
}