package output;

import data.SalesRepresentative;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReporterHTML extends Reporter {

    private BufferedWriter bufferedWriter;
    private String contents;

    private final static String htmlOpening = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Receipt Information</title>\n" +
            "</head>\n" +
            "<body>\n";

    private static final String htmlEnding = "</body>\n" + "</html>";


    public ReporterHTML(SalesRepresentative salesRepresentative) {
        super(salesRepresentative);
    }

    protected void initialize() {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File(absoluteFilePath)));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,"Error with creating TXT report.");
        }
    }

    protected String getFileExtension() {
        return ".html";
    }

    protected void createReportContents() {
        contents = htmlOpening +
                "\n<h1>Name: " + salesRepresentative.getName() + "</h1>\n" +
                "<h3>AFM: " + salesRepresentative.getAfm() + "</h3>\n\n\n" +
                "<table border=\"1\">\n" +
                "        <tr>\n" +
                "            <th>Total Sales</th>\n" +
                "            <th>Total Items</th>\n" +
                "            <th>Trousers Sales</th>\n" +
                "            <th>Skirts Sales</th>\n" +
                "            <th>Shirts Sales</th>\n" +
                "            <th>Coats Sales</th>\n" +
                "            <th>Commission</th>\n" +
                "        </tr>" +
                "        <tr>\n" +
                "            <td>" + salesRepresentative.calculateTotalSales() + "</td>\n" +
                "            <td>" + salesRepresentative.calculateTotalItems() +"</td>\n" +
                "            <td>" + salesRepresentative.calculateSalesByKind("Trousers") +"</td>\n" +
                "            <td>" + salesRepresentative.calculateSalesByKind("Skirts") +"</td>\n" +
                "            <td>" + salesRepresentative.calculateSalesByKind("Shirts") +"</td>\n" +
                "            <td>" + salesRepresentative.calculateSalesByKind("Coats") +"</td>\n" +
                "            <td>" + salesRepresentative.calculateCommission() +"</td>\n" +
                "        </tr>" +
                "</table>" +
                htmlEnding;
    }

    protected void writeReportContentsToFile() {
        try {
            bufferedWriter.write(contents);
            bufferedWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,"Error with creating TXT report.");
        }
    }
}
