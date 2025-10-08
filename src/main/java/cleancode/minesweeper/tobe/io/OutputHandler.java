package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.GameBoard;

public interface OutputHandler {
    
    public void showGameStartComments();

    public void showGameWinningComments();

    public void showGameOverComments();

    public void showCommentsForUserSelectingCell();

    public void showCommentsForUserAction();

    public void showIncorrectNumberComments();

    public void showExceptionMessage(Exception e);

    public void showSimpleMessage(String message);

    public void showGameBoard(GameBoard board);

}
