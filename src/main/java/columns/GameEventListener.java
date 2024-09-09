package columns;

public interface GameEventListener {
    void trySlideDown();
    void tryMoveRight();
    void tryMoveLeft();
    void dropFigure(Figure f);
    void rotateUp();
    void rotateDown();
    void pause();
    void increaseLevel();
    void decreaseLevel();
}
