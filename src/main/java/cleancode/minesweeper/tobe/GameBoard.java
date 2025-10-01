package cleancode.minesweeper.tobe;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {
    
   

    private final Cell[][] board;
    // private final Integer[][] landMineCounts;
    // private final boolean[][] landMines;

    private final int rowSize, colSize, landMineCount;
    
    
    private boolean isFindLandMine = false;

    public GameBoard(int rowSize, int colSize, int landMineCount) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.landMineCount = landMineCount;
        this.board = new Cell[this.rowSize][this.colSize];
        // this.landMineCounts = new Integer[this.rowSize][this.colSize];
        // this.landMines = new boolean[this.rowSize][this.colSize];
    }

    public void initialize() {
        
        // 폭탄 먼저 설치
        for (int i = 0; i < landMineCount; i++) {
            int col = new Random().nextInt(colSize);
            int row = new Random().nextInt(rowSize);
            //this.landMines[row][col] = true;
            this.board[row][col] = Cell.ofLandMine();
        }
        
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (isLandMineCell(row, col)) {
                    continue;
                }
                this.board[row][col] = Cell.ofNearbyLandMineCount(this.getNearbyLandMineCount(row, col));
            }
        }
    }

    private int getNearbyLandMineCount(int row, int col) {
        int count = 0;
        if (isLandMineCell(row - 1, col -1)) {
            count++;
        }
        if (isLandMineCell(row - 1, col)) {
            count++;
        }
        if (isLandMineCell(row - 1, col + 1)) {
            count++;
        }
        if (isLandMineCell(row, col - 1)) {
            count++;
        }
        if (isLandMineCell(row, col + 1)) {
            count++;
        }
        if (isLandMineCell(row + 1, col - 1)) {
            count++;
        }
        if (isLandMineCell(row + 1, col)) {
            count++;
        }
        if (isLandMineCell(row + 1, col + 1)) {
            count++;
        }
        return count;
    }

    public void print() {
        System.out.println("   a b c d e f g h i j");
        for (int row = 0; row < rowSize; row++) {
            System.out.printf("%d  ", row + 1);
            for (int col = 0; col < colSize; col++) {
                System.out.print(this.board[row][col].getSign() + " ");
            }
            System.out.println();
        }
    }

    public void printLandMine() {
        System.out.println("   a b c d e f g h i j");
        for (int row = 0; row < rowSize; row++) {
            System.out.printf("%d  ", row + 1);
            for (int col = 0; col < colSize; col++) {
                System.out.print((this.board[row][col].isLandMineCell() ? "1" : "0") + " ");
            }
            System.out.println();
        }
    }

    private boolean isLandMineCell(int row, int col) {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) {
            return false;
        }
        if (this.board[row][col] == null) {
            return false;
        }
        return this.board[row][col].isLandMineCell();
    }

    
    public boolean isAllChecked() {
        // for (int row = 0; row < this.rowSize; row++) {
        //     for (int col = 0; col < this.colSize; col++) {
        //         if (this.board[row][col].equals(SIGN_CLOSED_CELL)) {
        //             return false;
        //         }
        //     }
        // }
        // return true;
        
        return Arrays.stream(this.board).flatMap(Arrays::stream).allMatch(cell -> cell.isChecked());
    }


    public void doActionFlag(int row, int col) {
        // board[row][col] = SIGN_FLAG;
        board[row][col].flag();
    }

    public void doActionOpen(int row, int col) {
        if (this.board[row][col].isLandMineCell()) {
            // board[row][col] = SIGN_LAND_MINE;
            this.board[row][col].open();
            this.isFindLandMine = true;
        } else {
            // 첫번쨰 오픈은 지뢰셀을 
            open(row, col);
        }
    }

    private void open(int row, int col) {
        // row, col 범위 체크
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) {
            return;
        }
        // 이미 열린 셀이거나 깃발이 꽂힌 셀은 무시
        // -> 이미 체크한 셀은 무시
        if (this.board[row][col].isChecked()) {
            return;
        }
        // 지뢰 셀은 열지 않음
        if (this.board[row][col].isLandMineCell()) {
            return;
        }

        // 오픈!!
        this.board[row][col].open();

        // 숫자 셀이면
        if (this.board[row][col].hasNearbyLandMineCount()) {
            return;
        }
        // 8방향 재귀 오픈
        open(row - 1, col - 1);
        open(row - 1, col);
        open(row - 1, col + 1);
        open(row, col - 1);
        open(row, col + 1);
        open(row + 1, col - 1);
        open(row + 1, col);
        open(row + 1, col + 1);
    }

    public boolean isFindLandMine() {
        return this.isFindLandMine;
    }
}
