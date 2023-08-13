// 206438186 Lior Muallem

package animations;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The PauseScreen class represents a pause screen animation. It implements the Animation interface.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;

    /**
     * Constructs a PauseScreen with the specified KeyboardSensor.
     *
     * @param k the KeyboardSensor used to detect key presses
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 5, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}