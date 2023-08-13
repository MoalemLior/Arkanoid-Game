// 206438186 Lior Muallem

package levels;

import Interfaces.LevelInformation;
import Interfaces.Sprite;
import gameObjects.Background;
import gameObjects.Block;
import gameObjects.Velocity;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Green3 class represents a third level configuration implementing the LevelInformation interface.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(50, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-50, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Background(800, 600, Color.DARK_GRAY, 3);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        int blockHeight = 25;
        int blockWidth = 50;

        int lineBlocks = 10;
        double x;
        double y = (double) 600 / 3;
        for (int i = 0; i < 5; i++) {
            x = 800 - blockWidth - 25;
            for (int j = 0; j < lineBlocks; j++) {
                Block block = new Block(new Point(x, y), blockWidth, blockHeight);
                block.setColor(colors[i]);
                blocks.add(block);
                x = x - blockWidth;
            }
            lineBlocks--;
            y = y + blockHeight;
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
