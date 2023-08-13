// 206438186 Lior Muallem

package gameSetup;

import Interfaces.LevelInformation;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import gameObjects.Counter;

import java.util.List;

/**
 * The GameFlow class manages the flow of the game, including running levels and displaying the end screen.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private int width;
    private int height;
    private Counter score;

    /**
     * Constructs a new GameFlow instance with the specified animation runner, keyboard sensor,
     * width, and height of the game.
     *
     * @param ar the animation runner responsible for running the game animations
     * @param ks  the keyboard sensor used for player input
     * @param width the width of the game window
     * @param height the height of the game window
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int width, int height) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.width = width;
        this.height = height;
    }

    /**
     * Runs the specified levels in the game.
     *
     * @param levels the list of levels to be played
     */
    public void runLevels(List<LevelInformation> levels) {
        this.score = new Counter();
        boolean playerWon = true;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score,
                    this.width, this.height);

            level.initialize();

            //If balls or blocks are over end game
            while (level.getBlocksNumber() > 0 && level.getBallsNumber() > 0) {
                level.run();
            }

            //If balls are over then the player loss.
            if (level.getBallsNumber() ==  0) {
                playerWon = false;
                break;
            }
        }

        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(keyboardSensor, playerWon, this.score.getValue())));
    }
}
