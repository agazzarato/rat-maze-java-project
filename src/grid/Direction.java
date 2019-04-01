package grid;

public enum Direction {
	up, down, left, right;
	Direction reverse() {
		if(this == up) {
			return down;
		} else if(this == down) {
			return up;
		} else if(this == left) {
			return right;
		} else {
			return left;
		}
	}
	
	public char toChar() {
		if(this == up) {
			return '^';
		} else if(this == down) {
			return 'v';
		} else if(this == left) {
			return '<';
		} else if(this == right){
			return '>';
		} else {
			return 'x';
		}
	}
}
