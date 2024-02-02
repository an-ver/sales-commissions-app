package output;

import java.io.File;

import data.Receipt;

//The fields have been replaced with a receipt object.
public abstract class ReceiptFileUpdater {
    protected File fileToAppend;
    protected Receipt receipt; // Primitive fields replaced by Receipt object

    public void setFileToAppend(File fileToAppend) {
        this.fileToAppend = fileToAppend;
    }

    // Setters for primitive fields replaced by setter for receipt
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    // This is the main algorithm for appending a receipt to a file.
    public void appendReceiptToFile(Receipt receipt) {
        setReceipt(receipt); // Setting the receipt as a field is common for all algorithms.
        writeReceiptToFile();
    }

    // Writing to file is different for TXT and XML.
    protected abstract void writeReceiptToFile();

}

