// 206438186 Lior Muallem

package gameObjects;

import Interfaces.Sprite;
import biuoop.DrawSurface;
import gameSetup.GameLevel;

import java.awt.Color;

/**
 * The LevelIndicator class is responsible for displaying the name of the current level on the game screen.
 * It implements the Sprite interface to be drawn on the game surface.
 */
public class LevelIndicator implements Sprite {
    //A const represents the font size of the text
    public static final int FONT_SIZE = 14;
    private String levelName;
    private int width;
    private int height;

    /**
     * Constructs a LevelIndicator with the specified level name, width, and height.
     *
     * @param levelName the name of the current level
     * @param width the width of the game screen
     * @param height the height of the game screen
     */
    public LevelIndicator(String levelName, int width, int height) {
        this.levelName = levelName;
        this.width = width;
        this.height = height;
    }
    @Override
    public void drawOn(DrawSurface d) {
        String text = "Level Name: " + this.levelName;
        d.setColor(Color.BLACK);
        d.drawText((this.width / 3) * 2, this.height - 4, text, FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
