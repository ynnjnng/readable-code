package cleancode.minesweeper.tobe.cell;

public class CountCell extends Cell{

    private final int nearbyLandMineCount;

    public CountCell(int nearbyLandMineCount) {
        this.nearbyLandMineCount = nearbyLandMineCount;
    }

    @Override
    public boolean hasNearbyLandMineCount() {
        return nearbyLandMineCount > 0;
    }

    @Override
    public boolean isLandMineCell() {
        return false;
    }

    @Override
    public String getSign() {
        if (super.isOpened) {
            return String.valueOf(nearbyLandMineCount);
        } else if (super.isFlagged) {
            return SIGN_FLAG;
        } else {
            return SIGN_CLOSED_CELL;
        }
    }

}
