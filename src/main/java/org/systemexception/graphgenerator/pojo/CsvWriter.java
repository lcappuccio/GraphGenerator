/**
 * $Id$
 *
 * @author lcappuccio
 * @date 21/04/2015 14:44
 */
package org.systemexception.graphgenerator.pojo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.systemexception.graphgenerator.enums.CsvHeaders;
import org.systemexception.graphgenerator.model.Tree;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CsvWriter {

    private static final Logger logger = LoggerImpl.getFor(CsvWriter.class);
    private final CSVFormat csvFormat;
    private CSVPrinter csvFilePrinter;
    private final String fileName;
    private OutputStreamWriter outWriter;

    /**
     *
     * @param fileName the filename
     */
    public CsvWriter(String fileName) {
        String[] headerMapping = new String[]{CsvHeaders.NODE_ID.toString(), CsvHeaders.PARENT_ID.toString(),
                CsvHeaders.DESCRIPTION.toString(), CsvHeaders.TYPE.toString()};
        this.csvFormat = CSVFormat.RFC4180.withHeader(headerMapping).withSkipHeaderRecord(false).withRecordSeparator(System.lineSeparator());
        this.fileName = fileName;
    }

    /**
     * Writes a csv file with the tree
     *
     * @param tree the tree to write to the file
     */
    public void writeFile(Tree tree) {
        try {
            outWriter = new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8");
            csvFilePrinter = new CSVPrinter(outWriter, csvFormat);
            for (ArrayList<String> treeLevel : tree.getTreeLevelsString()) {
                csvFilePrinter.printRecord(treeLevel);
            }
            logger.info("CSV file was created successfully");
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            try {
                outWriter.flush();
                outWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                logger.error("IOException", e);
            }
        }
    }
}
