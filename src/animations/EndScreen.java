// 206438186 Lior Muallem

package animations;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The EndScreen class represents the animation displayed at the end of the game.
 * It implements the Animation interface.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean playerWon;
    private int finalScore;

    /**
     * Constructs an EndScreen animation with the specified KeyboardSensor, playerWon status, and final score.
     *
     * @param keyboard the KeyboardSensor used to detect key presses
     * @param playerWon true if the player won the game, false otherwise
     * @param finalScore the final score achieved by the player
     */
    public EndScreen(KeyboardSensor keyboard, boolean playerWon, int finalScore) {
        this.keyboard = keyboard;
        this.playerWon = playerWon;
        this.finalScore =  finalScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String text;
        if (this.playerWon) {
            text  = "You Win! Your score is " + this.finalScore;
        } else {
            text = "Game Over. Your score is " + this.finalScore;
        }

        d.drawText(d.getWidth() / 4, d.getHeight() / 2, text, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
