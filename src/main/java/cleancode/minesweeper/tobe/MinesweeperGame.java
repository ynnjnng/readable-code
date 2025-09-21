package cleancode.minesweeper.tobe;

import java.util.Random;
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

    public static final String SIGN_FLAG = "⚑";
    public static final String SIGN_CLOSED_CELL  = "□";
    public static final String SIGN_OPEND_CELL = "■";
    public static final String SIGN_LAND_MINE  = "☼";


    private static final String[][] BOARD = new String[ROW_COUNT][COL_COUNT];
    private static final Integer[][] LAND_MINE_COUNTS = new Integer[ROW_COUNT][COL_COUNT];
    private static final boolean[][] LAND_MINES = new boolean[ROW_COUNT][COL_COUNT];
    
    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showGameStartComments();
        initializeGame();
        while (true) {
            
            drawBoard();

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

            String action = getAction();
            
            doAction(action, col, row);
        }
    }

    private static String getAction() {
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

    private static void doAction(String action, int col, int row) {
        if (action.equals(ACTION_FLAG)) {
            BOARD[row][col] = SIGN_FLAG;
        } else if (action.equals(ACTION_OPEN)) {
            if (isLandMineCell(col, row)) {
                BOARD[row][col] = SIGN_LAND_MINE;
                setGameSatatus(LOSE);
                return;
            } else {
                open(row, col);
            }
        } else {
            System.out.println("잘못된 번호를 선택하셨습니다.");
            return;
        }

        if (isAllOpend()) {
            setGameSatatus(WIN);
        }
    }

    private static int setGameSatatus(int status) {
        return gameStatus = status;
    }

    public static int getGameStatus() {
        return gameStatus;
    }



    

    private static boolean isLandMineCell(int col, int row) {
        return LAND_MINES[row][col];
    }

    private static boolean isAllOpend() {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                if (BOARD[row][col].equals(SIGN_CLOSED_CELL)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int getRow(char r) {
        return Character.getNumericValue(r) - 1;
    }

    private static int getCol(char c) {
        int col;
        switch (c) {
            case 'a':
                col = 0;
                break;
            case 'b':
                col = 1;
                break;
            case 'c':
                col = 2;
                break;
            case 'd':
                col = 3;
                break;
            case 'e':
                col = 4;
                break;
            case 'f':
                col = 5;
                break;
            case 'g':
                col = 6;
                break;
            case 'h':
                col = 7;
                break;
            case 'i':
                col = 8;
                break;
            case 'j':
                col = 9;
                break;
            default:
                col = -1;
                break;
        }
        return col;
    }

    private static void drawBoard() {
        System.out.println("   a b c d e f g h i j");
        for (int row = 0; row < ROW_COUNT; row++) {
            System.out.printf("%d  ", row + 1);
            for (int col = 0; col < COL_COUNT; col++) {
                System.out.print(BOARD[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void initializeGame() {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                BOARD[row][col] = SIGN_CLOSED_CELL;
            }
        }

        for (int i = 0; i < LAND_MINE_COUNT; i++) {
            int col = new Random().nextInt(COL_COUNT);
            int row = new Random().nextInt(ROW_COUNT);
            LAND_MINES[row][col] = true;
        }
        
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                int count = 0;
                if (!isLandMineCell(col, row)) {
                    if (row - 1 >= 0 && col - 1 >= 0 && LAND_MINES[row - 1][col - 1]) {
                        count++;
                    }
                    if (row - 1 >= 0 && LAND_MINES[row - 1][col]) {
                        count++;
                    }
                    if (row - 1 >= 0 && col + 1 < 10 && LAND_MINES[row - 1][col + 1]) {
                        count++;
                    }
                    if (col - 1 >= 0 && LAND_MINES[row][col - 1]) {
                        count++;
                    }
                    if (col + 1 < 10 && LAND_MINES[row][col + 1]) {
                        count++;
                    }
                    if (row + 1 < 8 && col - 1 >= 0 && LAND_MINES[row + 1][col - 1]) {
                        count++;
                    }
                    if (row + 1 < 8 && LAND_MINES[row + 1][col]) {
                        count++;
                    }
                    if (row + 1 < 8 && col + 1 < 10 && LAND_MINES[row + 1][col + 1]) {
                        count++;
                    }
                    LAND_MINE_COUNTS[row][col] = count;
                    continue;
                }
                LAND_MINE_COUNTS[row][col] = 0;
            }
        }
    }

    private static void open(int row, int col) {
        if (row < 0 || row >= ROW_COUNT || col < 0 || col >= COL_COUNT) {
            return;
        }
        if (!BOARD[row][col].equals(SIGN_CLOSED_CELL)) {
            return;
        }
        if (isLandMineCell(col, row)) {
            return;
        }
        if (LAND_MINE_COUNTS[row][col] != 0) {
            BOARD[row][col] = String.valueOf(LAND_MINE_COUNTS[row][col]);
            return;
        } else {
            BOARD[row][col] = SIGN_OPEND_CELL;
        }
        open(row - 1, col - 1);
        open(row - 1, col);
        open(row - 1, col + 1);
        open(row, col - 1);
        open(row, col + 1);
        open(row + 1, col - 1);
        open(row + 1, col);
        open(row + 1, col + 1);
    }

}
