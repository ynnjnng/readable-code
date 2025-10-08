package cleancode.minesweeper.tobe.io;

import java.util.List;
import java.util.stream.IntStream;

import cleancode.minesweeper.tobe.GameBoard;

public class ConsoleOutputHandler implements OutputHandler{

    @Override
    public void showGameStartComments() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void showGameWinningComments() {
        System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");
    }    

    @Override
    public void showGameOverComments() {
        System.out.println("지뢰를 밟았습니다. GAME OVER!");
    }

    @Override
    public void showCommentsForUserSelectingCell() {
        System.out.println("선택할 좌표를 입력하세요. (예: a1)");
    }

    @Override
    public void showCommentsForUserAction() {
        System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
    }

    @Override
    public void showIncorrectNumberComments() {
        System.out.println("잘못된 번호를 선택하셨습니다.");
    }

    @Override
    public void showExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    @Override
    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showGameBoard(GameBoard board) {
        System.out.println("    " + generateColAlpabets(board.getColSize()));
        for (int row = 0; row < board.getRowSize(); row++) {
            System.out.printf("%2d  ", row + 1);
            for (int col = 0; col < board.getColSize(); col++) {
                System.out.print(board.getSign(row, col) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private String generateColAlpabets(int colSize) {
        List<String> alpabets = IntStream.range(0, colSize)
            .mapToObj(idx -> (char)('a'+idx))
            .map(String::valueOf)
            .toList();
        return String.join(" ", alpabets);
    }
}
