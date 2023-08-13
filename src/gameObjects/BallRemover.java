// 206438186 Lior Muallem

package gameObjects;

import Interfaces.HitListener;
import gameSetup.GameLevel;

/**
 * A BallRemover class that implements the HitListener interface.
 * It is responsible for removing balls from the game when they hit a block.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructs a BallRemover with the given game and remaining balls counter.
     *
     * @param gameLevel the game instance
     * @param remainingBalls the counter representing the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Handles the hit event when a ball hits a block.
     * Removes the ball from the game and decreases the remaining balls count.
     *
     * @param beingHit the block being hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
    }
}
