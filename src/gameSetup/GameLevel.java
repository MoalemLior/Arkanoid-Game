// 206438186 Lior Muallem

package gameSetup;

import Interfaces.Animation;
import Interfaces.Collidable;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameObjects.Ball;
import gameObjects.BallRemover;
import gameObjects.Block;
import gameObjects.BlockRemover;
import gameObjects.Counter;
import gameObjects.LevelIndicator;
import gameObjects.Paddle;
import gameObjects.ScoreIndicator;
import gameObjects.ScoreTrackingListener;
import geometry.Point;

import java.awt.Color;

/**
 * The Game class represents the Arkanoid game.
 *
 * @author Lior Muallem
 * @version 1.0
 * @since 2023 -03-20
 */
public class GameLevel implements Animation {
    //The size of the borders in the game
    public static final int BORDER_SIZE = 25;
    public static final int SCORE_BORDER_HEIGHT = 20;
    //The height of the paddle
    public static final int PADDLE_HEIGHT = 20;
    //Points to be added if all the blocks in the level are done
    public static final int LEVEL_POINTS = 100;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private int width;
    private int height;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;

    /**
     * Constructs a new Game with a given width and height.
     *
     * @param width  The width of the game screen.
     * @param height The height of the game screen.
     * @param levelInformation the level to be played.
     * @param keyboardSensor the player keyboard.
     * @param score the game's score.
     * @param animationRunner the animation runner of the game.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter score, int width, int height) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.runner = animationRunner;
        this.score = score;
        this.keyboard = keyboardSensor;
        this.width = width;
        this.height = height;
        this.level = levelInformation;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game.
     *
     * @param s The sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a collidable object to the game.
     *
     * @param c The collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a sprite object to the game.
     *
     * @param s The sprite object to remove.
     */
    public void  removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Return the number of the blocks in the level.
     *
     * @return the number of the blocks in the level.
     */
    public int getBlocksNumber() {
        return blocksCounter.getValue();
    }

    /**
     * Return the number of the balls in the level.
     *
     * @return the number of the balls in the level.
     */
    public int getBallsNumber() {
        return ballsCounter.getValue();
    }

    /**
     * This method initializes a new game. It creates the Blocks, Balls and Paddle and adds them to the game.
     */
    public void initialize() {
        initializeCounters();
        createBorders();
        createNameScoreIndicator();
        createBalls();
        createBlocks();
        createPaddle();
    }

    /**
     * Initialize the game counters.
     */
    private void initializeCounters() {
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
    }

    /**
     * Creates the score and level name indicators and adds it to the game.
     */
    private void createNameScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, this.width, SCORE_BORDER_HEIGHT);
        scoreIndicator.addToGame(this);
        LevelIndicator levelIndicator = new LevelIndicator(this.level.levelName(), this.width, SCORE_BORDER_HEIGHT);
        levelIndicator.addToGame(this);
    }

    /**
     * Creates the borders of the game screen and adds them to the game.
     */
    private void createBorders() {
        //Create the blocks
        Block topBorder = new Block(new Point(0, SCORE_BORDER_HEIGHT), this.width, BORDER_SIZE);
        Block leftBorder = new Block(new Point(0, BORDER_SIZE + SCORE_BORDER_HEIGHT), BORDER_SIZE,
                this.height - BORDER_SIZE);
        Block rightBorder = new Block(new Point(this.width - BORDER_SIZE, BORDER_SIZE + SCORE_BORDER_HEIGHT),
                BORDER_SIZE, this.height - BORDER_SIZE);
        Block bottomBorder = new Block(new Point(BORDER_SIZE, this.height), this.width - 2 * BORDER_SIZE, BORDER_SIZE);

        //Set the borders color
        topBorder.setColor(Color.darkGray);
        leftBorder.setColor(Color.darkGray);
        rightBorder.setColor(Color.darkGray);
        bottomBorder.setColor(Color.darkGray);

        //Add the blocks to the game
        this.level.getBackground().addToGame(this);
        topBorder.addToGame(this);
        leftBorder.addToGame(this);
        rightBorder.addToGame(this);
        bottomBorder.addToGame(this);

        //Bottom border is special as it the "death-area", create new BallRemover and add it to the bottom border
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        bottomBorder.addHitListener(ballRemover);
    }

    /**
     * Creates the paddle and adds it to the game.
     */
    private void createPaddle() {
        double upperLeftX = (double) (this.width - this.level.paddleWidth()) / 2;
        double upperLeftY = this.height - PADDLE_HEIGHT - BORDER_SIZE;
        Point upperLeft = new Point(upperLeftX, upperLeftY);
        Paddle paddle = new Paddle(upperLeft, this.level.paddleWidth(), PADDLE_HEIGHT, this.keyboard);
        paddle.setScreenAttributes(BORDER_SIZE, this.width - BORDER_SIZE);
        paddle.setSpeed(this.level.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * Creates the blocks and adds them to the game.
     */
    private void createBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.blocksCounter.increase(this.level.numberOfBlocksToRemove());

        for (Block block : this.level.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * Creates the balls and adds them to the game.
     */
    private void createBalls() {
        //Increase the balls counter with the number of the balls
        this.ballsCounter.increase(this.level.numberOfBalls());

        //Create the balls
        for (int i = 1; i <= this.level.numberOfBalls(); i++) {
            Point ballCenter = new Point((double) this.width / 2, 500);
            Ball ball = new Ball(ballCenter, 5, Color.WHITE);
            ball.setBounds(0, 0, this.width, this.height);
            ball.setVelocity(this.level.initialBallVelocities().get(i - 1));
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
    }

    /**
     * Runs the game animation loop.
     */
    public void run() {
        // countdown before turn starts
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        //End the level if the blocks are over
        if (this.blocksCounter.getValue() == 0) {
            this.score.increase(LEVEL_POINTS);
            this.running = false;
        }
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
