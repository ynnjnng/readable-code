package cleancode.minesweeper.tobe;

public class BoardIndexConverter {
    
    private static final char BASE_CHAR_FOR_COL = 'a';

    public int getSelectedRowIdx(String cellInput, int rowSize) {
        int rowIdx = Integer.parseInt(cellInput.substring(1)) - 1;
        if (rowIdx < 0 || rowIdx >= rowSize) {
            throw new AppException("잘못된 입력값 입니다.");
        }
        return rowIdx;

    }

    public int getSelectedColIdx(String cellInput, int colSize) {
        int col = cellInput.charAt(0) - BASE_CHAR_FOR_COL;
        if (col < 0 || col >= colSize) {
            throw new AppException("잘못된 입력값 입니다.");
        }
        return col;
    }

    
}
