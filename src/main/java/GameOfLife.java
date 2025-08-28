import java.util.Scanner;

public class GameOfLife {
	public static final int DEFAULT_SIZE = 5; // Default grid size
	public static final int LIVE_CELL = 1;
	public static final int DEAD_CELL = 0;
	private static int size;
	private static int[][] grid;
	private static int[][] nextGeneration;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Allow the user to enter grid size, default is 5 if input is empty
		System.out.print("Enter grid size (press Enter to use default - 5): ");
		String input = scanner.nextLine();
		size = input.isEmpty() ? DEFAULT_SIZE : Integer.parseInt(input);

		// Initialize the grid and nextGeneration based on user-defined size
		grid = new int[size][size];
		nextGeneration = new int[size][size];

		// Let the user define the initial grid pattern
		System.out.println("Enter the initial pattern for the " + size + "x" + size + " grid (0 for dead, 1 for live):");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print("Cell (" + i + "," + j + ") (0 for dead, 1 for live): ");
				int cellValue = scanner.nextInt();

				// Ensure valid input (only 0 or 1)
				while (cellValue != 0 && cellValue != 1) {
					System.out.print("Invalid input! Please enter 0 for dead or 1 for live: ");
					cellValue = scanner.nextInt();
				}
				grid[i][j] = cellValue;
			}
		}

		// Let the user choose the number of generations
		System.out.print("Enter the number of generations to simulate: ");
		int generations = scanner.nextInt();

		System.out.println("\nInitial Generation:");
		printGrid();

		// Run the game for the user-defined number of generations
		for (int i = 0; i < generations; i++) {
			nextGeneration();
			System.out.println("\nGeneration " + (i + 1) + ":");
			printGrid();
		}

		scanner.close();
	}

	/** Compute the next generation */
	public static void nextGeneration() {
		// Iterate over each cell in the grid and calculate the next generation
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int liveNeighbors = countLiveNeighbors(i, j);

				// Apply the rules of the Game of Life
				if (grid[i][j] == LIVE_CELL) { // Live cell
					if (liveNeighbors < 2 || liveNeighbors > 3) {
						nextGeneration[i][j] = DEAD_CELL; // Dies
					} else {
						nextGeneration[i][j] = LIVE_CELL; // Stays alive
					}
				} else { // Dead cell
					if (liveNeighbors == 3) {
						nextGeneration[i][j] = LIVE_CELL; // Becomes alive
					} else {
						nextGeneration[i][j] = DEAD_CELL; // Stays dead
					}
				}
			}
		}

		// After all cells are processed, update grid to the new generation
		for (int i = 0; i < size; i++) {
			System.arraycopy(nextGeneration[i], 0, grid[i], 0, size);
		}
	}

	/** Count live neighbors of a given cell */
	public static int countLiveNeighbors(int x, int y) {
		int count = 0;

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue; // Skip the cell itself

				int newX = x + i;
				int newY = y + j;

				// Check if neighbor is within bounds
				if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
					count += grid[newX][newY];
				}
			}
		}

		return count;
	}

	/** Print the grid */
	public static void printGrid() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(grid[i][j] == 1 ? "1 " : ". "); // 1 for live, . for dead
			}
			System.out.println();
		}
	}

	// Getters and setters
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public int[][] getNextGeneration() {
		return nextGeneration;
	}

	public void setNextGeneration(int[][] nextGeneration) {
		this.nextGeneration = nextGeneration;
	}
}
