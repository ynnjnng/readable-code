package cleancode.minesweeper.tobe;

public class Cell {
    public static final String SIGN_FLAG = "⚑";
    public static final String SIGN_CLOSED_CELL  = "□";
    public static final String SIGN_OPEND_CELL = "■";
    public static final String SIGN_LAND_MINE  = "☼";

    private boolean isOpened;
    private boolean isFlagged;
    private final boolean isLandMine;
    private final int nearbyLandMineCount;
    
    public Cell(boolean isOpened, boolean isFlagged, boolean isLandMine, int nearbyLandMineCount) {
        this.isOpened = isOpened;
        this.isFlagged = isFlagged;
        this.isLandMine = isLandMine;
        this.nearbyLandMineCount = nearbyLandMineCount;
    }

    public static Cell ofNearbyLandMineCount(int nearbyLandMineCount) {
        return new Cell(false, false, false, nearbyLandMineCount);
    }


    public static Cell ofLandMine() {
        return new Cell(false, false, true, 0);
    }


    public boolean isLandMineCell() {
        return this.isLandMine;
    }

    public boolean isChecked() {
        return (isOpened | isFlagged);
    }

    public String getSign() {
        if (this.isOpened) {
            if (this.isLandMine) {
                return SIGN_LAND_MINE;
            }
            else if (this.hasNearbyLandMineCount()) {
                return String.valueOf(this.nearbyLandMineCount);
            }
            else {
                return SIGN_OPEND_CELL;
            }
        } else if (this.isFlagged) {
            return SIGN_FLAG;
        } else {
            return SIGN_CLOSED_CELL;
        }
    }

    public void open() {
        this.isOpened = true;
    }
    
    public void flag() {
        this.isFlagged = true;
    }

    public boolean hasNearbyLandMineCount() {
        return this.nearbyLandMineCount > 0 ? true : false;
    }
             
    

     
    

}
