// 206438186 Lior Muallem

package gameObjects;

import Interfaces.Sprite;
import biuoop.DrawSurface;
import gameSetup.GameLevel;

import java.awt.Color;
import java.util.Random;

/**
 * The Background class represents the background of a game level.
 * It implements the Sprite interface to be drawn on the game surface.
 */
public class Background implements Sprite {
    private Color color;
    private int width;
    private int height;
    private int level;

    /**
     * Constructs a Background with the specified width, height, color, and level.
     *
     * @param width the width of the background
     * @param height the height of the background
     * @param color the color of the background
     * @param level the level of the background
     */
    public Background(int width, int height, Color color, int level) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.level = level;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, this.width, this.height);
        drawDecoration(d);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Draws the decoration elements on the specified DrawSurface based on the current level.
     *
     * @param d the DrawSurface on which to draw the decoration
     */
    private void drawDecoration(DrawSurface d) {
        switch (this.level) {
            case 1:
                levelOneDecoration(d);
                break;
            case 2:
                levelTwoDecoration(d);
                break;
            case 3:
                levelThreeDecoration(d);
                break;
            default:
                break;
        }
    }

    /**
     * Draws the decoration elements for level one on the specified DrawSurface.
     *
     * @param d the DrawSurface on which to draw the decoration
     */
    private void levelOneDecoration(DrawSurface d)  {
        // Draw sky
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, width, height);

        // Draw stars
        d.setColor(Color.WHITE);
        int starSize = 2;

        // Draw stars in specific positions
        drawStar(d, 100, 100, starSize);
        drawStar(d, 150, 200, starSize);
        drawStar(d, 250, 150, starSize);
        drawStar(d, 300, 300, starSize);
        drawStar(d, 400, 100, starSize);
        drawStar(d, 500, 250, starSize);
        drawStar(d, 600, 150, starSize);
        drawStar(d, 700, 300, starSize);
        drawStar(d, 800, 100, starSize);
        drawStar(d, 900, 200, starSize);

        // Draw moon
        d.setColor(Color.GRAY);
        d.fillCircle(700, 100, 50);
    }

    /**
     * Draws the decoration elements for level two on the specified DrawSurface.
     *
     * @param d the DrawSurface on which to draw the decoration
     */
    private void levelTwoDecoration(DrawSurface d) {
        // Draw the sky
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, width, height);

        // Draw sun
        int sunRadius = 50;
        int sunX = width - sunRadius - 50;
        int sunY = sunRadius + 50;
        d.setColor(Color.YELLOW);
        d.fillCircle(sunX, sunY, sunRadius);

        // Draw sun rays
        int rayLength = 100;
        int numRays = 12;
        double angleIncrement = 2 * Math.PI / numRays;

        for (int i = 0; i < numRays; i++) {
            double angle = i * angleIncrement;

            int rayEndX = (int) (sunX + rayLength * Math.cos(angle));
            int rayEndY = (int) (sunY + rayLength * Math.sin(angle));

            d.setColor(Color.YELLOW);
            d.drawLine(sunX, sunY, rayEndX, rayEndY);
        }

        // Pre-determined cloud positions
        int[] cloudXPositions = {100, 250, 400, 550, 700};
        int[] cloudYPositions = {100, 200, 150, 250, 180};

        int cloudWidth = 100;
        int cloudHeight = 50;

        for (int i = 0; i < cloudXPositions.length; i++) {
            int cloudX = cloudXPositions[i];
            int cloudY = cloudYPositions[i];

            d.setColor(Color.WHITE);

            // Draw cloud body
            d.fillOval(cloudX, cloudY, cloudWidth, cloudHeight);
            d.fillOval(cloudX + cloudWidth / 4, cloudY - cloudHeight / 2, cloudWidth / 2, cloudHeight);
            d.fillOval(cloudX + cloudWidth / 2, cloudY - cloudHeight / 4, cloudWidth / 2, cloudHeight);
            d.fillOval(cloudX + cloudWidth * 3 / 4, cloudY, cloudWidth / 2, cloudHeight);
        }
    }

    /**
     * Draws the decoration elements for level three on the specified DrawSurface.
     *
     * @param d the DrawSurface on which to draw the decoration
     */
    private void levelThreeDecoration(DrawSurface d) {
        for (int i = 30; i < 770;  i += 15) {
            for (int j = 50; j < 80; j += 12) {
                d.setColor(generateRandomColor());
                d.fillCircle(i, j, 2);
            }
        }
    }

    /**
     * Generates a random color.
     *
     * @return A random color
     */
    private Color generateRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    /**
     * Draws a star at the specified position with the specified size on the specified DrawSurface.
     *
     * @param d     the DrawSurface on which to draw the star
     * @param x     the x-coordinate of the star
     * @param y     the y-coordinate of the star
     * @param size  the size of the star
     */
    private void drawStar(DrawSurface d, int x, int y, int size) {
        int innerRadius = size / 2;
        int numPoints = 10;
        double angleIncrement = Math.PI / 5.0;

        for (int i = 0; i < numPoints; i++) {
            double angle = i * angleIncrement;
            int xOuter = x + (int) (size * Math.cos(angle));
            int yOuter = y + (int) (size * Math.sin(angle));
            d.drawLine(x, y, xOuter, yOuter);

            angle += angleIncrement / 2.0;
            int xInner = x + (int) (innerRadius * Math.cos(angle));
            int yInner = y + (int) (innerRadius * Math.sin(angle));
            d.drawLine(xOuter, yOuter, xInner, yInner);
        }
    }
}
