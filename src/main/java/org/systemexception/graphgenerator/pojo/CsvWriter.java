/**
 * $Id$
 *
 * @author lcappuccio
 * @date 21/04/2015 14:44
 */
package org.systemexception.graphgenerator.pojo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.systemexception.graphgenerator.enums.CsvHeaders;
import org.systemexception.graphgenerator.model.tree.Tree;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CsvWriter {

	private static final Logger logger = LogManager.getLogger(CsvWriter.class);
	private final CSVFormat csvFormat;
	private final String fileName;

	/**
	 * @param fileName the filename
	 */
	public CsvWriter(String fileName) {
		String[] headerMapping = new String[]{CsvHeaders.PARENT_ID.toString(), CsvHeaders.NODE_ID.toString(),
				CsvHeaders.DESCRIPTION.toString(), CsvHeaders.TYPE.toString()};
		this.csvFormat = CSVFormat.RFC4180.withHeader(headerMapping).withSkipHeaderRecord(false).withRecordSeparator
				(System.lineSeparator());
		this.fileName = fileName;
	}

	/**
	 * Writes a csv file with the tree
	 *
	 * @param tree the tree to write to the file
	 */
	public void writeTreeFile(Tree tree) {

		CSVPrinter csvFilePrinter = null;
		OutputStreamWriter outWriter = null;

		try {
			outWriter = new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8");
			csvFilePrinter = new CSVPrinter(outWriter, csvFormat);
			for (ArrayList<String> treeLevel : tree.getTreeLevelsString()) {
				csvFilePrinter.printRecord(treeLevel);
			}
			logger.info("CSV file was created successfully");
		} catch (IOException exception) {
			logger.error("IOException", exception);
		} finally {
			try {
				outWriter.flush();
				outWriter.close();
				csvFilePrinter.close();
			} catch (IOException exception) {
				logger.error("IOException", exception);
			}
		}
	}
}
