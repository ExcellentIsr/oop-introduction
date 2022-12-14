package telran.shape;

import java.util.Arrays;

public class Canvas extends Shape {
	private Shape[] copyShapes;

	private Shape[] shapes;
	private String direction = "row";
	private int margin = 5;

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
		this.copyShapes = shapes;
	}

	private void changeHeight(int height, Shape[] shapes) {
		for (Shape shape : shapes) {
			shape.setHeight(height);
		}
	}

	private void changeWidth(int width, Shape[] shapes) {
		for (Shape shape : shapes) {
			shape.setWidth(width);
		}
	}

	@Override
	public String[] presentation(int offset) {
		String[] res;

		copyShapes = Arrays.copyOf(shapes, shapes.length);
		int shapeHeight = getHeight();

		if (direction == "row") {
			changeHeight(getHeight(), copyShapes);
			res = new String[shapeHeight];
		} else {
			changeWidth(getWidth(), copyShapes);
			res = new String[getHeightAllSHapes(copyShapes) + (copyShapes.length - 1)];
		}
		Arrays.fill(res, getOffset(offset));

		if (direction == "row") {
			for (Shape shape : copyShapes) {
				String[] helper = shape.presentation(offset);
				for (int i = 0; i < shapeHeight; i++) {
					res[i] += (shape == copyShapes[copyShapes.length - 1]) ? helper[i] : helper[i] + getStringMargin();
				}
			}
		} else {
			int j = 0;
			for (Shape shape : copyShapes) {
				String[] helper = shape.presentation(offset);
				int shapeLength = helper.length;

				for (int i = 0; i < shapeLength; i++, j++) {
					res[j] += helper[i];
				}
				if (shape != copyShapes[copyShapes.length - 1]) {
					res[j++] = getStringMargin();
				}
			}
		}
		return res;
	}

	private int getHeightAllSHapes(Shape[] shapes) {
		int res = 0;
		for (Shape shape : shapes) {
			res += shape.getHeight();
		}
		return res;
	}

	private String getStringMargin() {
		return direction == "row" ? getOffset(margin) : "\n".repeat(margin - 1);
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction.matches("row|column") ? direction : "row";
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = (margin < 1) ? 1 : margin;
	}
}
