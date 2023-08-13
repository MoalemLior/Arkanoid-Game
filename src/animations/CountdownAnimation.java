// 206438186 Lior Muallem

package animations;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import gameSetup.SpriteCollection;

import java.awt.Color;

/**
 * The CountdownAnimation class displays a countdown animation on top of a given game screen.
 * It implements the Animation interface.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;
    private boolean firstFrame;
    private long startTime;

    /**
     * Constructs a CountdownAnimation with the specified duration, count from value, and game screen.
     *
     * @param numOfSeconds the duration of the countdown animation in seconds
     * @param countFrom the number to count down from
     * @param gameScreen the game screen on which to display the countdown animation
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
        this.stop = false;
        this.firstFrame = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.firstFrame) {
            this.startTime = System.currentTimeMillis();
            this.firstFrame = false;
        }

        this.gameScreen.drawAllOn(d);

        long currentTime = System.currentTimeMillis();
        double digitTime = this.numOfSeconds / (double) this.countFrom;
        int currentNumber = calculateCurrentNumber(currentTime, digitTime);

        d.setColor(Color.GREEN);
        d.drawText(d.getWidth() / 2, d.getHeight() * 2 / 3, String.valueOf(currentNumber), 55);

        double elapsedTime = currentTime - this.startTime;
        double totalDuration = this.numOfSeconds * 1000.0;

        if (elapsedTime > totalDuration) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Calculates the current countdown number based on the current time and digit time.
     *
     * @param currentTime the current time in milliseconds
     * @param digitTime   the duration in seconds for each countdown digit
     * @return the current countdown number
     */
    private int calculateCurrentNumber(long currentTime, double digitTime) {
        return (int) ((1 + this.countFrom) - (double) (currentTime - this.startTime) / (digitTime * 1000.0));
    }
}