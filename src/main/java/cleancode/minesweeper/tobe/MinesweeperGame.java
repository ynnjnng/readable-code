package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

public class MinesweeperGame implements GameInitializable, GameRunnable{

    // private static final int ING = 0;
    private static final int WIN = 1;
    private static final int LOSE = -1;
    
    private static final String ACTION_OPEN = "1";
    private static final String ACTION_FLAG = "2";

    private static final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

    private GameBoard board;
    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    InputHandler inputHandler;
    OutputHandler outputHandler;

    public MinesweeperGame(GameLevel gameLevel, InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.board = new GameBoard(gameLevel);
    }
    
    @Override
    public void initialize() {
        board.initialize();
    }

    @Override
    public void run() {
        
        outputHandler.showGameStartComments();
        
        while (true) {
            try {
                outputHandler.showGameBoard(board);
 
                if (doesUserWin()) {
                    outputHandler.showGameWinningComments();
                    break;
                }
                if (doesUserLose()) {
                    outputHandler.showGameOverComments();
                    break;
                }
                
                String cellInput = getCellInput();
                int rowIdx = boardIndexConverter.getSelectedRowIdx(cellInput, this.board.getRowSize());
                int colIdx = boardIndexConverter.getSelectedColIdx(cellInput, this.board.getColSize());

                String action = getActionInput();
                doAction(board, action, rowIdx, colIdx);

            } catch (AppException e) {
                outputHandler.showExceptionMessage(e);
            } catch (Exception e) {
                outputHandler.showSimpleMessage("시스템 오류가 발생했습니다.");
                return;
            }
            
        }
    }

    private String getActionInput() {
        outputHandler.showCommentsForUserAction();
        String action = inputHandler.getUserInput();
        if (!ACTION_OPEN.equals(action) && !ACTION_FLAG.equals(action)) {
            throw new AppException("잘못된 행위 입력값 입니다.");
        }
        return action;
    }

    private String getCellInput() {
        outputHandler.showCommentsForUserSelectingCell();
        return inputHandler.getUserInput();
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
            outputHandler.showIncorrectNumberComments();
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
