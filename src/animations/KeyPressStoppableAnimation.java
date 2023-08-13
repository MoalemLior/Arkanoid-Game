// 206438186 Lior Muallem

package animations;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The KeyPressStoppableAnimation class represents an animation that can be stopped by a key press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private Animation animation;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructs a KeyPressStoppableAnimation with the specified KeyboardSensor, key, and animation.
     *
     * @param sensor the KeyboardSensor used to detect key presses
     * @param key the key that can stop the animation when pressed
     * @param animation the animation to be stopped by the key press
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.animation = animation;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}