package cleancode.minesweeper.tobe.cell;

public abstract class Cell {
    protected static final String SIGN_FLAG = "⚑";
    protected static final String SIGN_CLOSED_CELL  = "□";
    protected static final String SIGN_OPEND_CELL = "■";
    
    protected boolean isOpened = false;
    protected boolean isFlagged = false;

    public boolean isChecked() {
        return (isOpened | isFlagged);
    }

    public void open() {
        this.isOpened = true;
    }
    
    public void flag() {
        this.isFlagged = true;
    }
    
    public abstract boolean hasNearbyLandMineCount();

    public abstract boolean isLandMineCell();
    
    public abstract String getSign();

}
