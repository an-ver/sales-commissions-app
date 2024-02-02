package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import data.SalesRepresentative;


public class ReporterTXT extends Reporter {

    private BufferedWriter bufferedWriter;
    private String contents;

    public ReporterTXT(SalesRepresentative salesRepresentative) {
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
        return ".txt";
    }

    protected void createReportContents() {
        contents = "\nName: " + salesRepresentative.getName() + "\n" +
                "AFM: " + salesRepresentative.getAfm() + "\n\n\n" +
                "Total Sales: " + salesRepresentative.calculateTotalSales() + "\n" +
                "Trousers Sales: " + salesRepresentative.calculateSalesByKind("Trousers") + "\n" +
                "Skirts Sales: " + salesRepresentative.calculateSalesByKind("Skirts") + "\n" +
                "Shirts Sales: " + salesRepresentative.calculateSalesByKind("Shirts") + "\n" +
                "Coats Sales: " + salesRepresentative.calculateSalesByKind("Coats") + "\n" +
                "Commission: " + salesRepresentative.calculateCommission() + "\n";
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
