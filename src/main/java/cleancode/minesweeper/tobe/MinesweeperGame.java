package cleancode.minesweeper.tobe;

import java.util.Scanner;

public class MinesweeperGame {

    public static final int ING = 0;
    public static final int WIN = 1;
    public static final int LOSE = -1;

    public static final String ACTION_OPEN = "1";
    public static final String ACTION_FLAG = "2";

    public static final int ROW_COUNT = 8;
    public static final int COL_COUNT = 10;
    public static final int LAND_MINE_COUNT = 10;

    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        
        showGameStartComments();

        GameBoard board = new GameBoard(ROW_COUNT, COL_COUNT, LAND_MINE_COUNT);
        board.initialize();
        board.printLandMine();

        while (true) {
            
            board.print();
 
            if (doesUserWin()) {
                System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");
                break;
            }
            if (doesUserLose()) {
                System.out.println("지뢰를 밟았습니다. GAME OVER!");
                break;
            }
            System.out.println();
        
            String cellInput = getCellInput();
            int col = getCol(cellInput.charAt(0));
            int row = getRow(cellInput.charAt(1));

            String action = getActionInput();
            doAction(board, action, row, col);
        }
    }

    private static String getActionInput() {
        System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
        String action = scanner.nextLine();
        return action;
    }

    private static String getCellInput() {
        System.out.println("선택할 좌표를 입력하세요. (예: a1)");
        String input = scanner.nextLine();
        return input;
    }

    private static boolean doesUserLose() {
        return getGameStatus() == LOSE;
    }

    private static boolean doesUserWin() {
        return getGameStatus() == WIN;
    }

    private static void showGameStartComments() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private static void doAction(GameBoard board, String action, int row, int col) {
        if (action.equals(ACTION_FLAG)) {
            board.doActionFlag(row, col);
        } else if (action.equals(ACTION_OPEN)) {
            board.doActionOpen(row, col);
        } else {
            System.out.println("잘못된 번호를 선택하셨습니다.");
            return;
        }

        if (board.isLandMineCell(row, col) && !board.isCellFlag(row, col)) {
            setGameSatatus(LOSE);
        }
        else if (board.isAllOpend()) {
            setGameSatatus(WIN);
        }
    }

    private static int setGameSatatus(int status) {
        return gameStatus = status;
    }

    public static int getGameStatus() {
        return gameStatus;
    }

    private static int getRow(char r) {
        return Character.getNumericValue(r) - 1;
    }

    private static int getCol(char c) {
        int col = c - 'a';
        if (col < 0 || col > 9) {
            col = -1;
        }
        return col;
    }
}
