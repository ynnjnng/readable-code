package cleancode.minesweeper.tobe.cell;

public class LandMineCell extends Cell{

    private static final String SIGN_LAND_MINE  = "☼";

    @Override
    public boolean hasNearbyLandMineCount() {
        return false;
    }

    @Override
    public boolean isLandMineCell() {
        return true;
    }

    @Override
    public String getSign() {
        if (super.isOpened) {
            return SIGN_LAND_MINE;
        } else if (super.isFlagged) {
            return SIGN_FLAG;
        } else {
            return SIGN_CLOSED_CELL;
        }
    }

}
