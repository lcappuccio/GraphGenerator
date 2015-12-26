package org.systemexception.graphgenerator.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.exception.TreeException;
import org.systemexception.graphgenerator.model.KaryTree;
import org.systemexception.graphgenerator.pojo.CsvWriter;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * @author leo
 * @date 26/12/15 14:29
 */
public class CsvWriterTest {

	private KaryTree testTree;
	private final String TEST_CSV = "target" + File.separator + "test.csv";
	private final CsvWriter sut = new CsvWriter(TEST_CSV);

	@Before
	public void setUp() throws NodeException, EdgeException, TreeException {
		testTree = new KaryTree(1,3);
		File testCsv = new File(TEST_CSV);
		testCsv.delete();
	}

	@After
	public void tearDown() {
		File testCsv = new File(TEST_CSV);
		testCsv.delete();
	}

	@Test
	public void test_csv_exists() {
		sut.writeTreeFile(testTree);
		File testCsv = new File(TEST_CSV);
		assertTrue(testCsv.exists());
	}

}