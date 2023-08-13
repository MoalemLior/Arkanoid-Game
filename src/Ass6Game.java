// 206438186 Lior Muallem

import Interfaces.LevelInformation;
import biuoop.GUI;
import gameSetup.AnimationRunner;
import gameSetup.GameFlow;
import levels.DirectHit;
import levels.Green3;
import levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

/**
 * The `Ass6Game` class represents the main class of the game.
 * It creates a new `Game` object with a specified width and height, initializes the game, and runs it.
 */
public class Ass6Game {
    /**
     * The main method of the game, creates a new game.Game object with a specified width and height,
     * initializes the game and runs it.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid Game", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(60, gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor(), 800, 600);
        List<LevelInformation> levels = createLevels(args);
        gameFlow.runLevels(levels);
        gui.close();
    }

    /**
     * Creates a list of `LevelInformation` objects based on the command line arguments.
     * If no arguments are provided, it adds the default levels.
     *
     * @param args the command line arguments
     * @return the list of LevelInformation objects
     */
    private static List<LevelInformation> createLevels(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        for (String levelNum : args) {
            switch (levelNum) {
                case "1":
                    levels.add(new DirectHit());
                    break;
                case "2":
                    levels.add(new WideEasy());
                    break;
                case "3":
                    levels.add(new Green3());
                    break;
                default:
                    break;
            }
        }

        if (levels.size() == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
        }

        return levels;
    }
}
