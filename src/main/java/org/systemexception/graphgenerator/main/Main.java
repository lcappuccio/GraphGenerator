/**
 * @author leo
 * @date 20/04/2015 00:03
 */
package org.systemexception.graphgenerator.main;

import org.apache.commons.cli.*;
import org.systemexception.graphgenerator.exception.CsvWriterException;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.exception.TreeException;
import org.systemexception.graphgenerator.model.Tree;
import org.systemexception.graphgenerator.pojo.CsvWriter;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

public class Main {

	private static final Logger logger = LoggerImpl.getFor(Main.class);
	private static int treeLevels, childPerNode;
	private static String outputFileName;
	private static final String HELP_OPTION = "h", OUTPUT_FILENAME = "o", TREE_LEVELS = "l", CHILD_PER_NODE = "c";

	public static void main(String[] args) throws ParseException, NodeException, EdgeException, TreeException, CsvWriterException {

		// Validate launch options
		validateOptions(args);
		// Create tree and csv util
		Tree tree = new Tree(treeLevels, childPerNode);
		CsvWriter csvWriter = new CsvWriter(outputFileName);
		csvWriter.writeFile(tree);
		tree.emptyTree();
		System.exit(0);
	}

	/**
	 * Validate all options
	 *
	 * @param args the arguments
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	private static void validateOptions(String[] args) throws ParseException, NumberFormatException {
		// Add CLI options
		Options options = new Options();
		options.addOption(HELP_OPTION, false, "print help");
		options.addOption(OUTPUT_FILENAME, true, "output file name");
		options.addOption(TREE_LEVELS, true, "tree levels");
		options.addOption(CHILD_PER_NODE, true, "childs per node");
		HelpFormatter helpFormatter = new HelpFormatter();
		// Parse CLI options
		CommandLineParser parser = new GnuParser();
		CommandLine cmdLine = parser.parse(options, args);
		// Verify mandatory options
		if (!cmdLine.hasOption(TREE_LEVELS)) {
			helpFormatter.printHelp(Main.class.getName(), options, true);
			exceptionHandler("Tree height/levels is mandatory");
		} else {
			treeLevels = Integer.parseInt(cmdLine.getOptionValue(TREE_LEVELS));
			logger.info("Tree levels: " + treeLevels);
		}
		if (!cmdLine.hasOption(CHILD_PER_NODE)) {
			helpFormatter.printHelp(Main.class.getName(), options, true);
			exceptionHandler("Child per nodes amount is mandatory");
		} else {
			childPerNode = Integer.parseInt(cmdLine.getOptionValue(CHILD_PER_NODE));
			logger.info("Child per node: " + childPerNode);
			if (Integer.parseInt(cmdLine.getOptionValue(CHILD_PER_NODE)) >= 10) {
				exceptionHandler("No more than 10 childs per node supported");
			}
		}
		if (!cmdLine.hasOption(OUTPUT_FILENAME)) {
			helpFormatter.printHelp(Main.class.getName(), options, true);
			exceptionHandler("Output filename is mandatory");
		} else {
			outputFileName = cmdLine.getOptionValue(OUTPUT_FILENAME);
			logger.info("Filename: " + outputFileName);
		}

	}

	/**
	 * Print an exception and quit
	 *
	 * @param message the message to be thrown
	 */
	private static void exceptionHandler(String message) {
		logger.error(message, new RuntimeException());
		throw new RuntimeException(message);
	}
}
