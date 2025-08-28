import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameOfLifeTest {
	private GameOfLife game;

	@BeforeEach
	void setUp() {
		game = new GameOfLife();
		game.setSize(5);
		game.setGrid(new int[][]{
				{0, 1, 0, 0, 0},
				{0, 1, 0, 0, 0},
				{0, 1, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0}
		});
		game.setNextGeneration(new int[game.getSize()][game.getSize()]);
	}

	@Test
	void testCountLiveNeighbors() {
		// Test counting neighbors for the cell at (1,1) in the initial grid
		int liveNeighbors = game.countLiveNeighbors(1, 1);
		assertEquals(2, liveNeighbors, "Cell (1,1) should have 2 live neighbors.");
	}

	@Test
	void testEdgeCaseCellWithLessThanTwoNeighbors() {
		// Test a live cell that should die due to having fewer than two live neighbors
		game.setGrid(new int[][]{
				{0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0}
		});

		game.nextGeneration();
		int[][] expectedGrid = {
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0}
		};
		assertArrayEquals(expectedGrid, game.getGrid(), "The live cell should die after having fewer than 2 live neighbors.");
	}
}
