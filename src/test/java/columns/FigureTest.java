package columns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FigureTest {

    private Figure figure;

    @BeforeEach
    void setUp() {
        figure = new Figure();
    }

    @Test
    void testRotateArraysElementsLeft() {
        int[] cells = {1, 2, 3};
        Figure.rotateArraysElementsLeft(cells);
        assertArrayEquals(new int[]{2, 3, 1}, cells);
    }

    @Test
    void testRotateArraysElementsRight() {
        int[] cells = {1, 2, 3};
        Figure.rotateArraysElementsRight(cells);
        assertArrayEquals(new int[]{3, 1, 2}, cells);
    }

    @Test
    void testRotateFigureCellsLeft() {
        figure.setCells(new int[]{4, 5, 6});
        figure.rotateFigureCellsLeft();
        assertArrayEquals(new int[]{5, 6, 4}, figure.getCells());
    }

    @Test
    void testRotateFigureCellsRight() {
        figure.setCells(new int[]{7, 8, 9});
        figure.rotateFigureCellsRight();
        assertArrayEquals(new int[]{9, 7, 8}, figure.getCells());
    }

    @Test
    void testGetCells() {
        int[] expected = figure.getCells();
        assertEquals(3, expected.length);
    }

    @Test
    void testSetCells() {
        int[] newCells = {1, 1, 1};
        figure.setCells(newCells);
        assertArrayEquals(newCells, figure.getCells());
    }
}
