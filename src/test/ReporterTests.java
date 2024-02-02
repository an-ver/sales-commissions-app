package test;

import data.SalesRepresentative;
import input.TXTInput;
import org.junit.Before;
import org.junit.Test;
import output.ReporterHTML;
import output.ReporterTXT;
import output.ReporterXML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ReporterTests {

    private final static String txtTestFile = "src/test/resources/test-case-1-TXT.txt";
    private final static String expectedReportFilePath = "src/test/resources/expected-report";
    private final static String actualReportFilePath = "src/test/resources/test-generated-report";


    private ReporterTXT reporterTXT;
    private ReporterXML reporterXML;
    private ReporterHTML reporterHTML;

    @Before
    public void init() {
        TXTInput txtInput = new TXTInput(new File(txtTestFile));
        txtInput.parseInputFile();
        SalesRepresentative salesRepresentative = txtInput.getSalesRepresentative();
        reporterTXT = new ReporterTXT(salesRepresentative);
        reporterXML = new ReporterXML(salesRepresentative);
        reporterHTML = new ReporterHTML(salesRepresentative);

    }

    @Test
    public void testTxtReport() {
        reporterTXT.createReport(actualReportFilePath);
        String expectedReportContents = readFileContents(expectedReportFilePath + ".txt");
        String actualReportContents = readFileContents(actualReportFilePath + ".txt");
        assertEquals(expectedReportContents, actualReportContents);
    }

    @Test
    public void testXmlReport() {
        reporterXML.createReport(actualReportFilePath);
        String expectedReportContents = readFileContents(expectedReportFilePath + ".xml");
        String actualReportContents = readFileContents(actualReportFilePath + ".xml");
        assertEquals(expectedReportContents, actualReportContents);
    }

    @Test
    public void testHtmlReport() {
        reporterHTML.createReport(actualReportFilePath);
        String expectedReportContents = readFileContents(expectedReportFilePath + ".html");
        String actualReportContents = readFileContents(actualReportFilePath + ".html");
        assertEquals(expectedReportContents, actualReportContents);
    }

    private String readFileContents(String filePath) {
        StringBuilder fileContents = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContents.append(line);
            }
        }
        catch (IOException e) {
            System.out.println("Error with reading file contents.");
        }
        return fileContents.toString().trim().replace("    ", "");
    }

}
