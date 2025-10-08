package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Advanced;
import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;

public class GameAplcation {

    public static void main(String[] args) {

        MinesweeperGame minesweeperGame = new MinesweeperGame(new Advanced(), new ConsoleInputHandler(), new ConsoleOutputHandler());
        minesweeperGame.initialize();
        minesweeperGame.run();;
    }
}
