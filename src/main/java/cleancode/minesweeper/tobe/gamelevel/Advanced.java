package cleancode.minesweeper.tobe.gamelevel;

public class Advanced implements GameLevel {
    
    @Override
    public int getRowSize() {
        return 15;
    }
    
    @Override
    public int getColSize() {
        return 20;
    }
    
    @Override
    public int getLandMineCount() {
        return 30;
    }

}
