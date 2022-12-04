package telran.shapes.test;

import org.junit.jupiter.api.Test;
import telran.shape.*;

class ShapeTest {

	@Test
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(15, 4);
		displayStrings(rectangle.presentation(20));
		
		Rectangle square = new Square(5);
		displayStrings(square.presentation(20));
		
		Rectangle squareLeftTriangle = new SquareLeftTriangle(10);
		squareLeftTriangle.setWidth(12);
		displayStrings(squareLeftTriangle.presentation(20));

		Rectangle squareRightTriangle = new SquareRightTriangle(10);
		squareRightTriangle.setWidth(8);
		displayStrings(squareRightTriangle.presentation(20));

		Rectangle.setSymbol("#");
		displayStrings(squareLeftTriangle.presentation(20));
		displayStrings(squareRightTriangle.presentation(20));
	}

	private void displayStrings(String strings[]) {
		for (String str : strings) {
			System.out.println(str);
		}
	}

}
