package columns;

public class Controller implements ModelListener, GameEventListener{

    Model model;
    View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        model.addListener(this);
    }

    @Override
    public void foundNeighboursAt(int i, int j, int k, int l, int i2, int j2) {
        // Optional: Add visual highlighting or other feedback
    }

    @Override
    public void fieldUpdated(int[][] newField) {
        view.updateField(newField);
    }

    @Override
    public void scoreHasChanged(int score) {
        view.updateScore(score);
    }

    @Override
    public void levelHasChanged(int level) {
        view.updateLevel(level);
    }

    @Override
    public void figureMovedFrom(int oldX, int oldY) {
        //do nothing for now
    }

    @Override
    public void figureUpdated(Figure fig) {
        view.updateFigure(fig);
    }

    @Override
    public void gameOver() {
       view.finish();
    }
//<---------------------------------GameEventListener------------------------------------>
    @Override
    public void trySlideDown() {
        model.trySlideDown();
    }

    @Override
    public void tryMoveRight() {
        model.tryMoveRight();
    }

    @Override
    public void tryMoveLeft() {
        model.tryMoveLeft();
    }

    @Override
    public void dropFigure(Figure f) {
        model.dropFigure(f);
    }

    @Override
    public void rotateUp() {
        model.rotateUp();
    }

    @Override
    public void rotateDown() {
        model.rotateDown();
    }

    @Override
    public void pause() {
        model.pause();
    }

    @Override
    public void increaseLevel() {
        model.increaseLevel();
    }

    @Override
    public void decreaseLevel() {
        model.decreaseLevel();
    }
}
