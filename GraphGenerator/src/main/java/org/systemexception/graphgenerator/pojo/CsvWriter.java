/**
 * $Id$
 *
 * @author lcappuccio
 * @date 21/04/2015 14:44
 *
 */
package org.systemexception.graphgenerator.pojo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.systemexception.graphgenerator.enums.CsvHeaders;
import org.systemexception.graphgenerator.exception.CsvWriterException;
import org.systemexception.graphgenerator.model.Node;
import org.systemexception.graphgenerator.model.Tree;

public class CsvWriter {

	private final CSVFormat csvFormat;
	private CSVPrinter csvFilePrinter;
	private final String[] headerMapping = new String[]{CsvHeaders.PARENT_ID.toString(), CsvHeaders.NODE_ID.toString(),
		CsvHeaders.DESCRIPTION.toString(), CsvHeaders.TYPE.toString()};
	private final String fileName;
	private OutputStreamWriter outWriter;

	/**
	 *
	 * @param fileName
	 */
	public CsvWriter(String fileName) {
		this.csvFormat = CSVFormat.RFC4180.withHeader(headerMapping).withSkipHeaderRecord(false).withRecordSeparator(System.lineSeparator());
		this.fileName = fileName;
	}

	/**
	 * Writes a csv file with the tree
	 *
	 * @param tree
	 * @throws org.systemexception.graphgenerator.exception.CsvWriterException
	 */
	public void writeFile(Tree tree) throws CsvWriterException {
		try {
			outWriter = new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8");
			csvFilePrinter = new CSVPrinter(outWriter, csvFormat);
			for (Node treeNode : tree.getNodes()) {
				List treeNodeRecord = new ArrayList();
				treeNodeRecord.add(treeNode.getNodeId());
				treeNodeRecord.add(treeNode.getNodeDescr());
				csvFilePrinter.printRecord(treeNodeRecord);
			}
			System.out.println("CSV file was created successfully");
		} catch (IOException e) {
			throw new CsvWriterException("Error while fileWriter/csvPrinter: " + e.getMessage());
		} finally {
			try {
				outWriter.flush();
				outWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				throw new CsvWriterException("Error while flushing/closing fileWriter/csvPrinter: " + e.getMessage());
			}
		}
	}
}
