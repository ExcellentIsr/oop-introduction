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
		res[0] = isLeftDiagonal ? getOffset(offset) + symbol : getOffset(offset) + getOffset(getWidth() - 1) + symbol;
		for (int i = 1; i < lastLine; i++) {
			res[i] = getMiddleLine(offset, i);
		}
		res[lastLine] = line;
		return res;
	}

	private String getMiddleLine(int offset, int i) {
		String leftOffset = getOffset(offset) + symbol + getOffset(i - 1) + symbol;
		String rightOffset = getOffset(offset) + getOffset(getWidth() - i - 1) + symbol + getOffset(i - 1) + symbol;
		return this.isLeftDiagonal ? leftOffset : rightOffset;
	}
}
