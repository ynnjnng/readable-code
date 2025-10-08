package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Advanced;
import cleancode.minesweeper.tobe.gamelevel.Beginner;

public class GameAplcation {

    public static void main(String[] args) {

        MinesweeperGame minesweeperGame = new MinesweeperGame(new Advanced());
        minesweeperGame.initialize();
        minesweeperGame.run();;
    }
}
