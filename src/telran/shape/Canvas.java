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

	@Override
	public int getHeight() {
		return direction.equals("row") ? super.getHeight() : (getHeightAllShapes() + margin * (shapes.length - 1));
	}

	@Override
	public String[] presentation(int offset) {
		for (Shape shape : shapes) {
			if (shape instanceof Canvas) {
				((Canvas) shape).setDirection(direction);
			}
		}
		return (direction == "row") ? buildShapesRow(shapes, offset) : buildShapesColumn(shapes, offset);
	}

	private String[] getPresintationShape(int index, int offset) {
		if (direction.equals("row")) {
			shapes[index].setHeight(getHeight());
		} else {
			shapes[index].setWidth(getWidth());
		}
		return shapes[index].presentation(offset);
	}

	private String[] buildShapesRow(Shape[] shapes, int offset) {
		String[] res = getPresintationShape(0, offset);
		for (int j = 1; j < shapes.length; j++) {
			String[] helper = getPresintationShape(j, margin);
			sumShapeInRow(res, helper);
		}
		return res;
	}

	private String[] buildShapesColumn(Shape[] shapes, int offset) {
		String[] res = new String[getHeightAllShapes() + (shapes.length - 1) * margin];
		Arrays.fill(res, "");

		int lenght = sumShapeInColumn(0, res, getPresintationShape(0, offset));

		for (int j = 1; j < shapes.length; j++) {
			lenght = sumShapeInColumn(lenght, res, getPresintationShape(j, offset));
		}
		return res;
	}

	private int sumShapeInColumn(int lenght, String[] res, String[] helper) {
		System.arraycopy(helper, 0, res, lenght, helper.length);
		return lenght + helper.length + margin;
	}

	private void sumShapeInRow(String[] res, String[] helper) {
		for (int i = 0; i < getHeight(); i++) {
			res[i] += helper[i];
		}
	}

	private int getHeightAllShapes() {
		int res = 0;
		for (Shape shape : shapes) {
			res += shape.getHeight();
		}
		return res;
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
