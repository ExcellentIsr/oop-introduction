package telran.shapes.test;

import org.junit.jupiter.api.Test;
import telran.shape.*;

class ShapeTest {

	@Test
	void shapesTest() {

		Shape[] shapes = { new SquareLeftTriangle(5), new SquareRightTriangle(8) };
		Canvas canvas = new Canvas(5, 7, shapes);

		Shape[] shapes1 = { new Rectangle(5, 15), new Square(7), canvas };
		Canvas canvas1 = new Canvas(10, 10, shapes1);
		canvas1.setMargin(2);
		displayStrings(canvas1.presentation(10));
		canvas1.setDirection("column");
		displayStrings(canvas1.presentation(10));
		canvas1.setDirection("row");
		displayStrings(canvas1.presentation(10));
	}

	private void displayStrings(String strings[]) {
		for (String str : strings) {
			System.out.println(str);
		}
	}

}
