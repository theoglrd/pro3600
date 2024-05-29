import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GridTest {

    @Test
    public void testRotateColumnUp() {
        Grid grid = new Grid(4, 3);
        grid.fillEmptySpaces();
        char[][] before = copyGrid(grid.getGrid());
        grid.rotateColumnUp(1);
        char[][] after = grid.getGrid();
        assertColumnRotatedUp(before, after, 1);
    }

    @Test
    public void testRotateColumnDown() {
        Grid grid = new Grid(4, 3);
        grid.fillEmptySpaces();
        char[][] before = copyGrid(grid.getGrid());
        grid.rotateColumnDown(1);
        char[][] after = grid.getGrid();
        assertColumnRotatedDown(before, after, 1);
    }

    @Test
    public void testRotateRowLeft() {
        Grid grid = new Grid(4, 3);
        grid.fillEmptySpaces();
        char[][] before = copyGrid(grid.getGrid());
        grid.rotateRowLeft(1);
        char[][] after = grid.getGrid();
        assertRowRotatedLeft(before, after, 1);
    }

    @Test
    public void testRotateRowRight() {
        Grid grid = new Grid(4, 3);
        grid.fillEmptySpaces();
        char[][] before = copyGrid(grid.getGrid());
        grid.rotateRowRight(1);
        char[][] after = grid.getGrid();
        assertRowRotatedRight(before, after, 1);
    }

    @Test
    public void testRandomRotate() {
        Grid grid = new Grid(4, 3);
        grid.fillEmptySpaces();
        char[][] before = copyGrid(grid.getGrid());
        grid.randomRotate();
        char[][] after = grid.getGrid();
        assertGridRotated(before, after);
    }

    private void assertColumnRotatedUp(char[][] before, char[][] after, int colIndex) {
        for (int i = 0; i < before.length - 1; i++) {
            assertEquals(before[i + 1][colIndex], after[i][colIndex]);
        }
        assertEquals(before[0][colIndex], after[before.length - 1][colIndex]);
    }

    private void assertColumnRotatedDown(char[][] before, char[][] after, int colIndex) {
        for (int i = before.length - 1; i > 0; i--) {
            assertEquals(before[i - 1][colIndex], after[i][colIndex]);
        }
        assertEquals(before[before.length - 1][colIndex], after[0][colIndex]);
    }

    private void assertRowRotatedLeft(char[][] before, char[][] after, int rowIndex) {
        for (int i = 0; i < before[rowIndex].length - 1; i++) {
            assertEquals(before[rowIndex][i + 1], after[rowIndex][i]);
        }
        assertEquals(before[rowIndex][0], after[rowIndex][before[rowIndex].length - 1]);
    }

    private void assertRowRotatedRight(char[][] before, char[][] after, int rowIndex) {
        for (int i = before[rowIndex].length - 1; i > 0; i--) {
            assertEquals(before[rowIndex][i - 1], after[rowIndex][i]);
        }
        assertEquals(before[rowIndex][before[rowIndex].length - 1], after[rowIndex][0]);
    }

    private void assertGridRotated(char[][] before, char[][] after) {
        // For simplicity, we'll just verify that the grid has changed after rotation
        // You could extend this to verify specific rotations if needed
        assert(!areGridsEqual(before, after));
    }

    private char[][] copyGrid(char[][] original) {
        char[][] copy = new char[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }

    private boolean areGridsEqual(char[][] grid1, char[][] grid2) {
        if (grid1.length != grid2.length) return false;
        for (int i = 0; i < grid1.length; i++) {
            if (grid1[i].length != grid2[i].length) return false;
            for (int j = 0; j < grid1[i].length; j++) {
                if (grid1[i][j] != grid2[i][j]) return false;
            }
        }
        return true;
    }
}
