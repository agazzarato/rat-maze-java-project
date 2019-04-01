import java.util.Scanner;
import java.util.Stack;

import graph.GraphTraverser;
import grid.Grid;
import grid.GridObject;
import grid.Tile;

public class Main {
	static Scanner kb = new Scanner(System.in);
	static Grid grid;
	static GridObject rat = new GridObject('R');
	static GridObject food = new GridObject('F');
	
	public static void main(String[] args) throws Exception {
		GridSetup();

		Grid shortestGrid = (Grid) grid.duplicate();
		Grid longestGrid = (Grid) grid.duplicate();
		
		System.out.println(grid);
		
		GraphTraverser<Tile> traverser = new GraphTraverser<>(grid.toGraph());

		try {
			Stack<Tile> shortestPath = traverser.dataPath(rat.getTile(), food.getTile(), t -> t.isEmpty() || t.containsOne(rat, food));
			System.out.println("Minimum number of moves is: " + (shortestPath.size() - 1));
			for(int i = 0; i < shortestPath.size(); i++) {
				Tile t = shortestPath.get(i);
				char dirChar = 'x';
				try {
					dirChar = t.directionOf(shortestPath.get(i+1)).toChar();
				} catch (Exception ex) {}
				shortestGrid.place(new GridObject(dirChar), t.getColumn(), t.getRow());
			}
			System.out.println(shortestGrid);
			
			Stack<Tile> longestPath = traverser.longDataPath(rat.getTile(), food.getTile(), t -> t.isEmpty() || t.containsOne(rat, food));
			System.out.println("Maximum number of moves is: " + (longestPath.size() - 1));
			for(int i = 0; i < longestPath.size(); i++) {
				Tile t = longestPath.get(i);
				char dirChar = 'x';
				try {
					dirChar = t.directionOf(longestPath.get(i+1)).toChar();
				} catch (Exception ex) {}
				longestGrid.place(new GridObject(dirChar), t.getColumn(), t.getRow());
			}
			System.out.println(longestGrid);
			
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			System.out.println("No valid path found.");
		}
		
		kb.close();
	}
	
	static void GridSetup() {
		String input;
		int rows = 0;
		int cols = 0;
		
		System.out.println("How many rows in the grid?");
		while(rows == 0) {
			input = kb.next();
			try {
				int r = Integer.parseInt(input);
				if(r > 0) {
					rows = r;
				} else {
					System.out.println("Please enter a number greater than 0!");
				}
			} catch (NumberFormatException ex) {
				System.out.println("Please enter a number!");
			}
		}

		System.out.println("How many columns in the grid?");
		while(cols == 0) {
			input = kb.next();
			try {
				int c = Integer.parseInt(input);
				if(c > 0) {
					cols = c;
				} else {
					System.out.println("Please enter a number greater than 0!");
				}
			} catch (NumberFormatException ex) {
				System.out.println("Please enter a number!");
			}
		}
		
		grid = new Grid(cols, rows);
		
		System.out.println("Input the layout of the maze by typing 0s and 1s, separated by spaces.");
		System.out.println("0s are walls; 1s are open spaces.");
		int rowCounter = 0;
		while(rowCounter < rows) {
			input = kb.nextLine();
			String[] split = input.split(" ");
			if(split.length == cols) {
				try {
					for(int i = 0; i < split.length; i++) {
						String s = split[i].trim();
						split[i] = s;
						if(!s.equals("0") && !s.equals("1")) {
							throw new NumberFormatException("ERROR: " + s + " is not a valid input.");
						}
					}
					for(int i = 0; i < cols; i++) {
						char tileChar = split[i].charAt(0);
						if(tileChar != '1') {
							grid.place(new GridObject(tileChar), i, rowCounter);
						} else {
							grid.place(null, i, rowCounter);
						}
					}
					rowCounter++;
				} catch (NumberFormatException ex) {
					System.out.println(ex.getMessage());
				}
			} else {
				System.out.println("Your grid has " + cols + " columns; please enter " + cols + " items separated by spaces.");
			}
		}
		
		System.out.println("Choose a column (from 1 to " + cols + ") in the top row for the rat to start in.");
		int ratCol = 0;
		while(ratCol == 0) {
			input = kb.nextLine();
			try {
				int parsed = Integer.parseInt(input);
				if(parsed > 0 && parsed <= cols) {
					if(grid.tileEmpty(parsed - 1, 0)) {
						ratCol = parsed;
					} else {
						System.out.println("That tile is occupied. The rat cannot be placed there.");
					}
				} else {
					System.out.println("Please input a number between 1 and " + cols);
				}
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			}
		}

		grid.place(rat, ratCol - 1, 0);
		
		System.out.println("Choose a column (from 1 to " + cols + ") in the bottom row for the food to start in.");
		int foodCol = 0;
		while(foodCol == 0) {
			input = kb.nextLine();
			try {
				int parsed = Integer.parseInt(input);
				if(parsed > 0 && parsed <= cols) {
					if(grid.tileEmpty(parsed - 1, rows - 1)) {
						foodCol = parsed;
					} else {
						System.out.println("That tile is occupied. The food cannot be placed there.");
					}
				} else {
					System.out.println("Please input a number between 1 and " + cols);
				}
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			}
		}

		grid.place(food, foodCol - 1, rows - 1);
	}	
}