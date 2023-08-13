// 206438186 Lior Muallem

package Interfaces;

import biuoop.DrawSurface;

/**
 * The Animation interface represents an animation that can be displayed on a DrawSurface.
 */
public interface Animation {
    /**
     * Performs one frame of the animation, drawing it on the provided DrawSurface.
     *
     * @param d the `DrawSurface` on which to draw the animation frame
     */
    void doOneFrame(DrawSurface d);

    /**
     * Determines whether the animation should stop or continue to the next frame.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
