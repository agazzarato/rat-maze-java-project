package grid;

public class Tile {
	private int row;
	private int col;
	private GridObject contents = null;
	private Tile above = null, below = null, left = null, right = null;

	Tile(int col, int row) {
		this(col, row, null);
	}
	Tile(int col, int row, GridObject contents) {
		this.row = row;
		this.col = col;
		this.setContents(contents);
	}

	//Mutators
	void connect(Direction dir, Tile tile) {
		set(dir, tile);
		tile.set(dir.reverse(), this);
	}
	
	void setContents(GridObject contents) {
		this.contents = contents;
		if(this.contents != null) {
			this.contents.setTile(this);
		}
	}
	void swapContents(Tile other) {
		GridObject myContents = this.getContents();
		this.setContents(other.getContents());
		other.setContents(myContents);
	}
	
	void set(Direction dir, Tile tile) {
		Tile current = getTile(dir);
		if(current != null) {
			current.set(dir.reverse(), null);
		}
		
		if(dir == Direction.up) {
			setAbove(tile);
		} else if(dir == Direction.down) {
			setBelow(tile);
		} else if(dir == Direction.left) {
			setLeft(tile);
		} else {
			setRight(tile);
		}
	}
	void setAbove(Tile above) {
		this.above = above;
	}
	void setBelow(Tile below) {
		this.below = below;
	}
	void setLeft(Tile left) {
		this.left = left;
	}
	void setRight(Tile right) {
		this.right = right;
	}
	
	//Accessors
	boolean adjacentTo(Tile other) {
		return directionOf(other) != null;
	}
	public boolean containsOne(GridObject...gridObjects) {
		for(GridObject gridObject : gridObjects) {
			if(this.contents.equals(gridObject)) {
				return true;
			}
		}
		return false;
	}
	public Direction directionOf(Tile other) {
		for(Direction dir : Direction.values()) {
			try {
				if(getTile(dir).equals(other)) {
					return dir;
				}
			} catch (NullPointerException ex) {}
		}
		return null;
	}
	Tile duplicate() {
		return new Tile(col, row, contents.duplicate());
	}
	GridObject getContents() {
		return contents;
	}
	public int getColumn() {
		return col;
	}
	public int getRow() {
		return row;
	}
	Tile getTile(Direction dir) {
		if(dir == Direction.up) {
			return above();
		} else if(dir == Direction.down) {
			return below();
		} else if(dir == Direction.left) {
			return left();
		} else {
			return right();
		}
	}
	Tile above() {
		return above;
	}
	Tile below() {
		return below;
	}
	Tile left() {
		return left;
	}
	Tile right() {
		return right;
	}
	public boolean isEmpty() {
		return contents == null;
	}
	String getCoords() {
		return "(" + col + ", " + row + ")";
	}
	@Override
	public String toString() {
		return this.getCoords();
	}
	String toGridString() {
		return (contents == null ? " " : contents.getAsChar()) + (getTile(Direction.right) == null ? " " : "|");
	}
}
