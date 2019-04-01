package grid;

import java.util.HashMap;

import graph.Graph;

public class Grid {
	private HashMap<Integer, HashMap<Integer, Tile>> tiles = new HashMap<Integer, HashMap<Integer, Tile>>();
	private Integer columns;
	private Integer rows;
	
	public Grid(int columns, int rows) {
		for(int i = 0; i < columns; i++) {
			HashMap<Integer, Tile> column = new HashMap<Integer, Tile>();
			tiles.put(i, column);
			
			for(int j = 0; j < rows; j++) {
				Tile newTile = new Tile(i, j);
				column.put(j, newTile);

				try {
					newTile.connect(Direction.up, column.get(j - 1));
				} catch (NullPointerException ex) {}
				try {
					newTile.connect(Direction.down, column.get(j + 1));
				} catch (NullPointerException ex) {}
				try {
					newTile.connect(Direction.left, tiles.get(i - 1).get(j));
				} catch (NullPointerException ex) {}
				try {
					newTile.connect(Direction.right, tiles.get(i + 1).get(j));
				} catch (NullPointerException ex) {}
			}
		}
		this.columns = columns;
		this.rows = rows;
	}

	//Mutators
	public void place(GridObject contents, int x, int y) {
		Tile atPos = tileAtPos(x, y);
		if(atPos != null) {
			atPos.setContents(contents);
		}
	}
	
	//Accessors
	public boolean tileEmpty(int x, int y) {
		try {	
			return tileAtPos(x, y).isEmpty();
		} catch (NullPointerException ex) {
			return false;
		}
	}
	Tile tileAtPos(int x, int y) {
		try {
			return tiles.get(x).get(y);
		} catch (NullPointerException ex) {
			return null;
		}
	}
	
	public Graph<Tile> addTileToGraph(Tile tile, Graph<Tile> graph) {
		graph.add(tile);
		for(Direction dir : Direction.values()) {
			Tile dirTile = tile.getTile(dir);
			if(dirTile != null) {
				graph.add(dirTile);
				graph.connect(tile, dirTile);
			}
		}
		return graph;
	}
	public Graph<Tile> toGraph() {
		Graph<Tile> asGraph = new Graph<Tile>();
		
		for(Integer column : this.tiles.keySet()) {
			for(Integer row : this.tiles.get(column).keySet()) {
				Tile vertexTile = this.tileAtPos(column, row);
				asGraph = addTileToGraph(vertexTile, asGraph);
			}
		}
		
		return asGraph;
	}
	
	public Grid duplicate() {
		Grid newGrid = new Grid(columns, rows);
		
		for(int c = 0; c < columns; c++) {
			for(int r = 0; r < rows; r++) {
				Tile currentTile = tiles.get(c).get(r);
				if(currentTile.getContents() != null) {
					newGrid.place(currentTile.getContents().duplicate(), c, r);
				}
			}
		}
		
		return newGrid;
	}
	@Override
	public String toString() {
		String returnString = "";
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
				returnString += this.tiles.get(j).get(i).toGridString();
			}
			returnString += "\n";
		}
		return returnString;
	}
	
}
