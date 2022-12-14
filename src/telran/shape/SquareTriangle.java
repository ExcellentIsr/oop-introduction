package telran.shape;

public class SquareTriangle extends Square {
	private boolean isLeftDiagonal;

	protected SquareTriangle(int width, boolean isLeftDiagonal) {
		super(width);
		this.isLeftDiagonal = isLeftDiagonal;
	}

	public String[] presentation(int offset) {
		String res[] = new String[getWidth()];
		String line = getLine(offset);
		int lastLine = getHeight() - 1;
		res[0] = isLeftDiagonal ? symbol + getOffset(getWidth() - 1) : getOffset(getWidth() - 1) + symbol;
		for (int i = 1; i < lastLine; i++) {
			res[i] = isLeftDiagonal ? getLeftMiddleLine(offset, i) : getRightMiddleLine(offset, i);
		}
		res[lastLine] = line;
		return res;
	}

	private String getLeftMiddleLine(int offset, int i) {
		return symbol + getOffset(i - 1) + symbol + getOffset(getWidth() - i - 1);
	}

	private String getRightMiddleLine(int offset, int i) {
		return getOffset(getWidth() - i - 1) + symbol + getOffset(i - 1) + symbol;
	}

	public boolean getIsLeftDiagonal() {
		return this.isLeftDiagonal;
	}
}
