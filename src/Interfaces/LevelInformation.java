// 206438186 Lior Muallem

package Interfaces;

import gameObjects.Block;
import gameObjects.Velocity;

import java.util.List;

/**
 * The LevelInformation interface defines the information required for a specific level in the game.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Returns a list of initial ball velocities.
     *
     * @return the list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * Returns a list of blocks that make up the level.
     *
     * @return the list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed to clear the level.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}