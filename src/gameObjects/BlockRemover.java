// 206438186 Lior Muallem

package gameObjects;

import Interfaces.HitListener;
import gameSetup.GameLevel;

/**
 * A BlockRemover class that implements the HitListener interface.
 * It is responsible for removing blocks from the game and keeping track of the remaining blocks count.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover with the given game and remaining blocks counter.
     *
     * @param gameLevel the game instance
     * @param removedBlocks the counter representing the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Handles the hit event when a block is hit by a ball.
     *
     * @param beingHit the block being hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        //Update the counter
        this.remainingBlocks.decrease(1);
    }
}