package input;

import data.SalesRepresentative;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TXTInput extends Input {

    List<String> fileContents;

    public TXTInput(File receiptFileTXT) {
        this.inputFile = receiptFileTXT;
        inputFilePath = inputFile.getAbsolutePath();
    }

    protected void readFileContents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            fileContents = new ArrayList<String>();
            StringBuilder buffer = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    if (buffer.length() > 0) fileContents.add(buffer.toString());
                    buffer = new StringBuilder();
                } else {
                    if (buffer.length() == 0) buffer = new StringBuilder(line);
                    else buffer.append("\n").append(line);
                }
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog
                    (null, "Error with reading TXT file contents.");
        }
    }

    protected void createSalesRepresentative() {
        String name = parseField(fileContents.get(0).split("\n")[0]);
        String afm = parseField(fileContents.get(0).split("\n")[1]);
        salesRepresentative = new SalesRepresentative(name, afm);
    }

    protected void createReceipts() {
        for (String receipt : fileContents) {
            if (fileContents.indexOf(receipt) < 2) continue;

            String[] receiptFields = receipt.split("\n");
            receiptID = Integer.parseInt(parseField(receiptFields[0]));
            date = parseField(receiptFields[1]);
            kind = parseField(receiptFields[2]);
            sales = Double.parseDouble(parseField(receiptFields[3]));
            items = Integer.parseInt(parseField(receiptFields[4]));
            companyName = parseField(receiptFields[5]);
            companyCountry = parseField(receiptFields[6]);
            companyCity = parseField(receiptFields[7]);
            companyStreet = parseField(receiptFields[8]);
            companyStreetNumber = Integer.parseInt(parseField(receiptFields[9]));

            createReceipt();
        }
    }

    private String parseField(String str) {
        return str.split(":")[1].trim();
    }

}

