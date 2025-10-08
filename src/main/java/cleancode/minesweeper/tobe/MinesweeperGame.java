package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;

public class MinesweeperGame {

    // private static final int ING = 0;
    private static final int WIN = 1;
    private static final int LOSE = -1;
    
    private static final String ACTION_OPEN = "1";
    private static final String ACTION_FLAG = "2";

    private static final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

    private GameBoard board;

    public MinesweeperGame(GameLevel gameLevel) {
        this.board = new GameBoard(gameLevel);
    }

    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
    
    
    public void run() {
        
        consoleOutputHandler.showGameStartComments();
        board.initialize();

        while (true) {
            try {
                board.print();
 
                if (doesUserWin()) {
                    consoleOutputHandler.printGameWinningComments();
                    break;
                }
                if (doesUserLose()) {
                    consoleOutputHandler.printGameOverComments();
                    break;
                }
                
                String cellInput = getCellInput();
                int rowIdx = boardIndexConverter.getSelectedRowIdx(cellInput, this.board.getRowSize());
                int colIdx = boardIndexConverter.getSelectedColIdx(cellInput, this.board.getColSize());

                String action = getActionInput();
                doAction(board, action, rowIdx, colIdx);

            } catch (AppException e) {
                consoleOutputHandler.printExceptionMessage(e);
            } catch (Exception e) {
                consoleOutputHandler.printSimpleMessage("시스템 오류가 발생했습니다.");
                return;
            }
            
        }
    }

    private String getActionInput() {
        consoleOutputHandler.printCommentsForUserAction();
        String action = consoleInputHandler.getUserInput();
        if (!ACTION_OPEN.equals(action) && !ACTION_FLAG.equals(action)) {
            throw new AppException("잘못된 행위 입력값 입니다.");
        }
        return action;
    }

    private String getCellInput() {
        consoleOutputHandler.printCommentsForUserSelectingCell();
        return consoleInputHandler.getUserInput();
    }

    private boolean doesUserLose() {
        return getGameStatus() == LOSE;
    }

    private boolean doesUserWin() {
        return getGameStatus() == WIN;
    }

    private void doAction(GameBoard board, String action, int row, int col) {
        if (action.equals(ACTION_FLAG)) {
            board.doActionFlag(row, col);
        } else if (action.equals(ACTION_OPEN)) {
            board.doActionOpen(row, col);
        } else {
            consoleOutputHandler.printIncorrectNumberComments();
            return;
        }

        if (board.isFindLandMine()) {
            setGameSatatus(LOSE);
        }
        else if (board.isAllChecked()) {
            setGameSatatus(WIN);
        }
    }

    private int setGameSatatus(int status) {
        return gameStatus = status;
    }

    public int getGameStatus() {
        return gameStatus;
    }

}
