import java.util.Scanner;

public class GameOfLife {
	public static final int DEFAULT_SIZE = 5; // Default grid size
	public static final int LIVE_CELL = 1;
	public static final int DEAD_CELL = 0;
	private static int SIZE;
	private static int[][] grid;
	private static int[][] nextGeneration;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Allow the user to enter grid size, default is 5 if input is empty
		System.out.print("Enter grid size (press Enter to use default - 5): ");
		String input = scanner.nextLine();
		SIZE = input.isEmpty() ? DEFAULT_SIZE : Integer.parseInt(input);

		// Initialize the grid and nextGeneration based on user-defined size
		grid = new int[SIZE][SIZE];
		nextGeneration = new int[SIZE][SIZE];

		System.out.println("Enter the initial pattern for the " + SIZE + "x" + SIZE + " grid (0 for dead, 1 for live):");
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
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
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
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

		// Update grid to the new generation
		for (int i = 0; i < SIZE; i++) {
			System.arraycopy(nextGeneration[i], 0, grid[i], 0, SIZE);
		}
	}

	/** Count live neighbors of a given cell */
	public static int countLiveNeighbors(int x, int y) {
		int count = 0;

		// Check all 8 neighboring cells
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue; // Skip the cell itself

				int newX = x + i;
				int newY = y + j;

				// Check if neighbor is within bounds
				if (newX >= 0 && newX < SIZE && newY >= 0 && newY < SIZE) {
					count += grid[newX][newY];
				}
			}
		}

		return count;
	}

	/** Print the grid */
	public static void printGrid() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(grid[i][j] == 1 ? "1 " : ". "); // 1 for live, . for dead
			}
			System.out.println();
		}
	}
}
