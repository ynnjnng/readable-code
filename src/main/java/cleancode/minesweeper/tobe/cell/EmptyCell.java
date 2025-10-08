package cleancode.minesweeper.tobe.cell;

public class EmptyCell extends Cell{

    @Override
    public boolean hasNearbyLandMineCount() {
        return false;
    }

    @Override
    public boolean isLandMineCell() {
        return false;
    }

    @Override
    public String getSign() {
        if (super.isOpened) {
            return SIGN_OPEND_CELL;
        } else if (super.isFlagged) {
            return SIGN_FLAG;
        } else {
            return SIGN_CLOSED_CELL;
        }
    }

}
