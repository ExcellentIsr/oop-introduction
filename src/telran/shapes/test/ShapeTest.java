package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import telran.shape.*;

class ShapeTest {

	@Test
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(8, 5);
		displayStrings(rectangle.presentation(20));

		Rectangle squareLeftTriangle = new SquareLeftTriangle(10);
		squareLeftTriangle.setWidth(5);
		assertEquals(5, squareLeftTriangle.getWidth());
		assertEquals(5, squareLeftTriangle.getHeight());
		displayStrings(squareLeftTriangle.presentation(20));

		Rectangle squareRightTriangle = new SquareRightTriangle(10);
		squareRightTriangle.setWidth(5);
		assertEquals(5, squareRightTriangle.getWidth());
		assertEquals(5, squareRightTriangle.getHeight());
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
