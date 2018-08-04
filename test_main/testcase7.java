package test_main;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class testcase7 {

	@Test
	public void testMakeNewText() {
		DWGraph graph = new DWGraph();
		graph.readFile(new File("src/source/text/testcase.txt"));
		String result = String.join(" ", graph.makeNewText("c e l"));
		assertEquals("c d e l", result);
	}
}
