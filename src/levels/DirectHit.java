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
 * The Direct class represents the first level configuration implementing the LevelInformation interface.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0.0, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 20;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Background(800, 600, Color.BLACK, 1);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(new Point(380, 205), 40, 40);
        block.setColor(Color.RED);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
