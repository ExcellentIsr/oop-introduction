package telran.shape;

import java.util.Arrays;

public class Canvas extends Shape {
	private Shape[] shapes;
	private String direction = "row";
	private int margin = 5;

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
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
		Shape[] copyShapes = shapes.clone();
		String[] res;
		if (direction == "row") {
			changeHeight(getHeight(), copyShapes);
			res = new String[getHeight()];
		} else {
			changeWidth(getWidth(), copyShapes);
			res = new String[getHeightAllShapes(copyShapes) + (copyShapes.length - 1)];
		}
		Arrays.fill(res, getOffset(offset));

		if (direction == "row") {
			buildShapesRow(res, copyShapes, offset);
		} else {
			buildShapesColumn(res, copyShapes, offset);
		}
		return res;
	}

	private void buildShapesColumn(String[] res, Shape[] shapes, int offset) {
		int j = 0;
		for (Shape shape : shapes) {
			String[] helper = shape.presentation(0);
			int shapeLength = helper.length;

			for (int i = 0; i < shapeLength; i++, j++) {
				res[j] += helper[i];
			}
			if (shape != shapes[shapes.length - 1]) {
				res[j++] = getStringMargin();
			}
		}

	}

	private void buildShapesRow(String[] res, Shape[] shapes, int offset) {
		for (Shape shape : shapes) {
			String[] helper = shape.presentation(0);
			for (int i = 0; i < getHeight(); i++) {
				res[i] += (shape.equals(shapes[shapes.length - 1])) ? helper[i] : helper[i] + getStringMargin();
			}
		}
	}

	private int getHeightAllShapes(Shape[] shapes) {
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
