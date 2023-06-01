package gui;

public enum HorizontalMoveModes {

    RIGHT(1),
    STOP(0),
    LEFT(-1);

    private final int moveConstant;

    public int getMoveConstant() {
        return moveConstant;
    }

    HorizontalMoveModes(int moveConstant) {
        this.moveConstant = moveConstant;
    }
}
