package gui;

/**
 * Enum class for Target's vertical move modes
 */
public enum VerticalMoveModes {

    DOWN(1),
    STOP(0),
    UP(-1);

    private final int moveConstant;

    public int getMoveConstant() {
        return moveConstant;
    }

    VerticalMoveModes(int moveConstant) {
        this.moveConstant = moveConstant;
    }
}
