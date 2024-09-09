package columns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {

    private View view;
    private Figure figure;
    private int[][] field;

    @BeforeEach
    void setUp() {
        view = new View();
        figure = new Figure();
        field = new int[Model.Width + 1][Model.Depth + 1];
    }

    @Test
    void testUpdateField() {
        view.updateField(field);
        assertNotNull(view);
    }

    @Test
    void testUpdateFigure() {
        view.updateFigure(figure);
        assertNotNull(view);
    }

    @Test
    void testUpdateLevel() {
        view.updateLevel(2);
        assertNotNull(view);
    }

    @Test
    void testUpdateScore() {
        view.updateScore(100);
        assertNotNull(view);
    }

    @Test
    void testFinish() {
        view.finish();
        assertNotNull(view);
    }

}
