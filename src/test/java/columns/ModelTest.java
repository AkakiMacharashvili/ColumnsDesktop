package columns;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    private Model model;
    private Figure testFigure;

    @BeforeEach
    public void setUp() {
        model = new Model();
        model.initModel();
        testFigure = new Figure();
        model.Fig = testFigure;
    }

    @Test
    public void shouldInitializeModelCorrectly() {
        assertNotNull(model.newField);
        assertNotNull(model.oldField);
        assertTrue(model.firstMove);
        assertEquals(1, model.level);
        assertEquals(0, model.Score);
        assertEquals(0, model.removedCellsCounter);
    }

    @Test
    public void shouldDropFigureAndCalculateDropScore() {
        testFigure.x = 3;
        testFigure.y = 10;
        model.dropFigure(testFigure);

        assertEquals(13, testFigure.y);
        assertTrue(model.DropScore >= 0);
    }

    @Test
    public void shouldCheckIfFieldIsFullAndTriggerGameOver() {
        for (int i = 1; i <= Model.Width; i++) {
            model.newField[i][3] = 1; // Fill the 3rd row
        }
        model.checkIfFieldIsFull();

        assertTrue(model.finished);
    }

    @Test
    public void shouldNotTriggerGameOverWhenFieldNotFull() {
        model.checkIfFieldIsFull();

        // Game should continue
        assertFalse(model.finished);
    }

    @Test
    public void shouldPackFieldCorrectly() {
        model.oldField[3][14] = 1;
        model.oldField[3][13] = 2;
        model.oldField[3][12] = 3;

        model.packField();

        // Check that new field is packed correctly
        assertEquals(1, model.newField[3][15]);
        assertEquals(2, model.newField[3][14]);
        assertEquals(3, model.newField[3][13]);
        assertEquals(0, model.newField[3][12]);
    }

    @Test
    public void shouldPasteFigureCorrectly() {
        testFigure.x = 2;
        testFigure.y = 5;
        testFigure.setCells(new int[] {1, 2, 3});

        model.pasteFigure(testFigure);

        assertEquals(1, model.newField[2][5]);
        assertEquals(2, model.newField[2][6]);
        assertEquals(3, model.newField[2][7]);
    }

    @Test
    public void shouldAllowFigureToSlideDown() {
        model.Fig.y = 10;
        model.newField[model.Fig.x][model.Fig.y + 3] = 0;

        assertTrue(model.mayFigureSlideDown());
    }

    @Test
    public void shouldNotAllowFigureToSlideDownWhenBlocked() {
        model.Fig.y = 10;
        model.newField[model.Fig.x][model.Fig.y + 3] = 1;

        assertFalse(model.mayFigureSlideDown());
    }

    @Test
    public void shouldMoveFigureLeft() {
        testFigure.x = 3;
        model.newField[2][testFigure.y + 2] = 0;
        model.tryMoveLeft();

        assertEquals(2, testFigure.x);
    }

    @Test
    public void shouldMoveFigureRight() {
        testFigure.x = 3;
        model.newField[4][testFigure.y + 2] = 0;
        model.tryMoveRight();

        assertEquals(4, testFigure.x);
    }

    @Test
    public void shouldNotMoveFigureLeftWhenAtBorder() {
        testFigure.x = 1;
        model.tryMoveLeft();

        assertEquals(1, testFigure.x);
    }

    @Test
    public void shouldNotMoveFigureRightWhenAtBorder() {
        testFigure.x = Model.Width;
        model.tryMoveRight();

        assertEquals(Model.Width, testFigure.x);
    }

    @Test
    public void shouldRemoveSimilarNeighboringCells() {
        model.newField[2][2] = 1;
        model.newField[3][2] = 1;
        model.newField[4][2] = 1;

        model.removeSimilarNeighboringCells();

        assertEquals(0, model.newField[2][2]);
        assertEquals(0, model.newField[3][2]);
        assertEquals(0, model.newField[4][2]);
    }

    @Test
    public void shouldUpdateScoreWhenCellsAreRemoved() {
        int initialScore = model.Score;

        model.newField[2][2] = 1;
        model.newField[3][2] = 1;
        model.newField[4][2] = 1;

        model.removeSimilarNeighboringCells();

        assertTrue(model.Score > initialScore);
    }

    @Test
    public void shouldChangeLevelOnIncreaseLevel() {
        model.increaseLevel();
        assertEquals(2, model.level);
    }

    @Test
    public void shouldNotIncreaseLevelAboveMax() {
        model.level = Model.MaxLevel;
        model.increaseLevel();

        assertEquals(Model.MaxLevel, model.level);
    }

    @Test
    public void shouldPauseGameWhenPauseCalled() {
        model.pause();
        assertTrue(model.paused);

        model.pause();
        assertFalse(model.paused);
    }
}
