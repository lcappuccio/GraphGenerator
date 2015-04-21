/**
 *
 * @author leo
 * @date 20/04/2015 00:03
 *
 */
package org.systemexception.graphgenerator.main;

import org.systemexception.graphgenerator.exception.CsvWriterException;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.exception.TreeException;
import org.systemexception.graphgenerator.model.Tree;
import org.systemexception.graphgenerator.pojo.CsvWriter;

public class Main {

	public static void main(String[] args) {

		Tree tree = null;
		
		try {
			tree = new Tree(1,10);
		} catch (NodeException | EdgeException | TreeException ex) {
			System.out.println(ex.getMessage());
		}
		
		CsvWriter csvWriter = new CsvWriter("target/output.csv");
		try {
			csvWriter.writeFile(tree);
		} catch (CsvWriterException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
