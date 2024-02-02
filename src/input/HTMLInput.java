package input;

import data.SalesRepresentative;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTMLInput extends Input {
    private static final String h1Open = "<h1>";
    private static final String h1Close = "</h1>";
    private static final String h3Open = "<h3>";
    private static final String h3Close = "</h3>";
    private static final String tdOpen = "<td>";
    private static final String tdClose = "</td>";

    List<String> fileContentsByLine;

    public HTMLInput(File receiptFileHTML) {
        this.inputFile = receiptFileHTML;
        inputFilePath = inputFile.getAbsolutePath();
    }

    protected void readFileContents() {
        fileContentsByLine = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContentsByLine.add(line);
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog
                    (null, "Error with reading HTML file contents.");
        }
    }

    protected void createSalesRepresentative() {
        String name = parseName();
        String afm = parseAfm();
        salesRepresentative = new SalesRepresentative(name, afm);
    }

    private String parseName() {
        return fileContentsByLine.get(9)
            .replace(h1Open, "")
            .replace(h1Close, "")
            .split(":")[1]
            .trim();
    }

    private String parseAfm() {
        return fileContentsByLine.get(10)
                .replace(h3Open, "")
                .replace(h3Close, "")
                .split(":")[1]
                .trim();
    }

    protected void createReceipts() {
        for (int i=25; i < fileContentsByLine.size(); i += 12) {
            if (i + 12 >= fileContentsByLine.size()) break;

            receiptID = Integer.parseInt(parseField(fileContentsByLine.get(i+1)));
            date = parseField(fileContentsByLine.get(i+2));
            kind = parseField(fileContentsByLine.get(i+3));
            sales = Double.parseDouble(parseField(fileContentsByLine.get(i+4)));
            items = Integer.parseInt(parseField(fileContentsByLine.get(i+5)));
            companyName = parseField(fileContentsByLine.get(i+6));
            companyCountry = parseField(fileContentsByLine.get(i+7));
            companyCity = parseField(fileContentsByLine.get(i+8));
            companyStreet = parseField(fileContentsByLine.get(i+9));
            companyStreetNumber = Integer.parseInt(parseField(fileContentsByLine.get(i+10)));

            createReceipt();
        }
    }

    private String parseField(String line) {
        return line.replace(tdOpen, "")
                .replace(tdClose, "")
                .trim();
    }

    // Add print list debug method
    private void printFileContentsByLine() {
        for (String line : fileContentsByLine) {
            System.out.println(line);
        }
    }

}
