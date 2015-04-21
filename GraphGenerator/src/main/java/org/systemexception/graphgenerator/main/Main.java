/**
 *
 * @author leo
 * @date 20/04/2015 00:03
 *
 */
package org.systemexception.graphgenerator.main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.systemexception.graphgenerator.exception.CsvWriterException;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.exception.TreeException;
import org.systemexception.graphgenerator.model.Tree;
import org.systemexception.graphgenerator.pojo.CsvWriter;

public class Main {
	
	private static int treeLevels, childPerNode;
	private static String outputFileName;
	// Command line options
	private static Options options;
	private static CommandLineParser parser;
	private static CommandLine cmdLine;
	private static final String HELP_OPTION = "h", OUTPUT_FILENAME = "o", TREE_LEVELS = "l", CHILD_PER_NODE = "c";

	public static void main(String[] args) throws ParseException {

		// Validate launch options
		validateOptions(args);
		Tree tree = null;
		
		try {
			tree = new Tree(treeLevels, childPerNode);
		} catch (NodeException | EdgeException | TreeException ex) {
			System.out.println(ex.getMessage());
		}
		CsvWriter csvWriter = new CsvWriter(outputFileName);
		try {
			csvWriter.writeFile(tree);
		} catch (CsvWriterException ex) {
			System.out.println(ex.getMessage());
		}
		System.exit(0);
	}
	
	/**
	 * Validate all options
	 *
	 * @param args
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	private static void validateOptions(String[] args) throws ParseException, NumberFormatException {
		// Add CLI options
		options = new Options();
		options.addOption(HELP_OPTION, false, "print help");
		options.addOption(OUTPUT_FILENAME, true, "output file name");
		options.addOption(TREE_LEVELS, true, "tree levels");
		options.addOption(CHILD_PER_NODE, true, "childs per node");
		HelpFormatter helpFormatter = new HelpFormatter();
		// Parse CLI options
		parser = new GnuParser();
		cmdLine = parser.parse(options, args);
		// Verify mandatory options
		if (!cmdLine.hasOption(TREE_LEVELS)) {
			helpFormatter.printHelp(Main.class.getName(), options, true);
			exceptionHandler("Nodes amount is mandatory");
		} else {
			treeLevels = Integer.valueOf(cmdLine.getOptionValue(TREE_LEVELS));
			System.out.println("Tree levels: " + treeLevels);
		}
		if (!cmdLine.hasOption(CHILD_PER_NODE)) {
			helpFormatter.printHelp(Main.class.getName(), options, true);
			exceptionHandler("Nodes amount is mandatory");
		} else {
			childPerNode = Integer.valueOf(cmdLine.getOptionValue(CHILD_PER_NODE));
			System.out.println("Child per node: " + childPerNode);
		}
		if (!cmdLine.hasOption(OUTPUT_FILENAME)) {
			helpFormatter.printHelp(Main.class.getName(), options, true);
			exceptionHandler("Nodes amount is mandatory");
		} else {
			outputFileName = cmdLine.getOptionValue(OUTPUT_FILENAME);
			System.out.println("Filename: " + outputFileName);
		}
		
	}
	
	/**
	 * Print an exception and quit
	 *
	 * @param message
	 */
	public static void exceptionHandler(String message) {
		System.out.println(message);
		System.exit(1);
	}
}
