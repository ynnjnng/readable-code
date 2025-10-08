package cleancode.minesweeper.tobe;

import java.util.Scanner;

import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;

public class MinesweeperGame {

    public static final int ING = 0;
    public static final int WIN = 1;
    public static final int LOSE = -1;

    public static final String ACTION_OPEN = "1";
    public static final String ACTION_FLAG = "2";

    public static final int ROW_COUNT = 8;
    public static final int COL_COUNT = 10;
    public static final int LAND_MINE_COUNT = 10;

    GameBoard board = new GameBoard(ROW_COUNT, COL_COUNT, LAND_MINE_COUNT);

    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
    
    
    public void run() {
        
        consoleOutputHandler.showGameStartComments();

        
        board.initialize();
        board.printLandMine();
        

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
                int col = getCol(cellInput.charAt(0));
                int row = getRow(cellInput.charAt(1));

                String action = getActionInput();
                doAction(board, action, row, col);

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

    private int getRow(char r) {
        int rowIdx = Character.getNumericValue(r) - 1;
        if (rowIdx < 0 || rowIdx >= ROW_COUNT) {
            throw new AppException("잘못된 입력값 입니다.");
        }
        return rowIdx;
    }

    private int getCol(char c) {
        int col = c - 'a';
        if (col < 0 || col >= COL_COUNT) {
            throw new AppException("잘못된 입력값 입니다.");
        }
        return col;
    }
}
