package telran.shapes.test;

import org.junit.jupiter.api.Test;
import telran.shape.*;

class ShapeTest {

	@Test
	void shapesTest() {
		Shape rectangle = new Rectangle(15, 20);
		Shape square = new Square(12);
		Shape squareLeftTriangle = new SquareLeftTriangle(15);
		Shape squareRightTriangle = new SquareRightTriangle(20);
		
		Shape[] shapes = {rectangle, square, squareLeftTriangle, squareRightTriangle};
		Canvas canvas = new Canvas(5, 10, shapes);
		canvas.setMargin(3);
		displayStrings(canvas.presentation(10));
		canvas.setDirection("column");
		displayStrings(canvas.presentation(10));
		canvas.setDirection("row");
		displayStrings(canvas.presentation(10));
	}


	private void displayStrings(String strings[]) {
		for (String str : strings) {
			System.out.println(str);
		}
	}

}
