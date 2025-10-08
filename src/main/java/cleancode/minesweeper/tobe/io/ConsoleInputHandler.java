package cleancode.minesweeper.tobe.io;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    
    private static Scanner SCANNER = new Scanner(System.in);

    @Override
    public String getUserInput() {
        return SCANNER.nextLine();
    }


}
