package grid;

public class GridObject {
	private final char asChar;
	private Tile tile = null;
	
	public GridObject(char asChar) {
		this.asChar = asChar;
	}
	
	//Mutators
	public void move(Direction dir) {
		Tile nextTile = tile.getTile(dir);
		if(nextTile != null) {
			if(nextTile.isEmpty()) {
				tile.setContents(null);
				nextTile.setContents(this);
			}
		}
	}
	void setTile(Tile tile) {
		this.tile = tile;
	}
	
	//Accessors
	public boolean adjacentTo(GridObject other) {
		return tile.adjacentTo(other.getTile());
	}
	public GridObject duplicate() {
		return new GridObject(asChar);
	}
	public char getAsChar() {
		return asChar;
	}
	public String getCoords() {
		if(this.tile != null) {
			return this.tile.getCoords();
		}
		return "(?, ?)";
	}
	public int getColumn() {
		return tile.getColumn();
	}
	public int getRow() {
		return tile.getRow();
	}
	public Tile getTile() {
		return tile;
	}
}
