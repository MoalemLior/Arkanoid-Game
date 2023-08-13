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
 * The WideEasy class represents the second level configuration implementing the LevelInformation interface.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 1; i <= this.numberOfBalls() / 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(-10 * i, 5));
        }
        for (int i = 1; i <= this.numberOfBalls() / 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(10 * i, 5));
        }

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background(800, 600, Color.white, 2);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int yUpperLeft = 250;
        int blockHeight = 25;
        int blockWidth = 50;

        Block b1 = new Block(new Point(25, yUpperLeft), blockWidth, blockHeight);
        b1.setColor(Color.RED);
        blocks.add(b1);
        Block b2 = new Block(new Point(75, yUpperLeft), blockWidth, blockHeight);
        b2.setColor(Color.RED);
        blocks.add(b2);
        Block b3 = new Block(new Point(125, yUpperLeft), blockWidth, blockHeight);
        b3.setColor(Color.ORANGE);
        blocks.add(b3);
        Block b4 = new Block(new Point(175, yUpperLeft), blockWidth, blockHeight);
        b4.setColor(Color.ORANGE);
        blocks.add(b4);
        Block b5 = new Block(new Point(225, yUpperLeft), blockWidth, blockHeight);
        b5.setColor(Color.YELLOW);
        blocks.add(b5);
        Block b6 = new Block(new Point(275, yUpperLeft), blockWidth, blockHeight);
        b6.setColor(Color.YELLOW);
        blocks.add(b6);
        Block b7 = new Block(new Point(325, yUpperLeft), blockWidth, blockHeight);
        b7.setColor(Color.GREEN);
        blocks.add(b7);
        Block b8 = new Block(new Point(375, yUpperLeft), blockWidth, blockHeight);
        b8.setColor(Color.GREEN);
        blocks.add(b8);
        Block b9 = new Block(new Point(425, yUpperLeft), blockWidth, blockHeight);
        b9.setColor(Color.GREEN);
        blocks.add(b9);
        Block b10 = new Block(new Point(475, yUpperLeft), blockWidth, blockHeight);
        b10.setColor(Color.BLUE);
        blocks.add(b10);
        Block b11 = new Block(new Point(525, yUpperLeft), blockWidth, blockHeight);
        b11.setColor(Color.BLUE);
        blocks.add(b11);
        Block b12 = new Block(new Point(575, yUpperLeft), blockWidth, blockHeight);
        b12.setColor(Color.PINK);
        blocks.add(b12);
        Block b13 = new Block(new Point(625, yUpperLeft), blockWidth, blockHeight);
        b13.setColor(Color.PINK);
        blocks.add(b13);
        Block b14 = new Block(new Point(675, yUpperLeft), blockWidth, blockHeight);
        b14.setColor(Color.CYAN);
        blocks.add(b14);
        Block b15 = new Block(new Point(725, yUpperLeft), blockWidth, blockHeight);
        b15.setColor(Color.CYAN);
        blocks.add(b15);

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
