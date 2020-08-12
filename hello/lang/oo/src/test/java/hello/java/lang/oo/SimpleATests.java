package hello.java.lang.oo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hello.java.lang.oo.overridea.*;

public class SimpleATests {

	@Test
	public void contextLoads() {

	}

	@Test
	public void introduceYourself() {
		IShape aShape = new Square();
		assertEquals("SimpleATests - introduceYourself: ",
				aShape.introduceYourself());
	}
}
