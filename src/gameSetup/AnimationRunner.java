// 206438186 Lior Muallem

package gameSetup;

import biuoop.GUI;
import biuoop.Sleeper;
import Interfaces.Animation;
import biuoop.DrawSurface;

/**
 * The AnimationRunner class is responsible for running animations on the GUI.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructs a new AnimationRunner with the specified frames per second (fps) and GUI.
     *
     * @param framesPerSecond the desired frames per second for the animation
     * @param gui the GUI on which to run the animation
     */
    public AnimationRunner(int framesPerSecond, GUI gui) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * Runs the specified animation on the GUI.
     *
     * @param animation the animation to run
     */
    public void run(Animation animation) {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}