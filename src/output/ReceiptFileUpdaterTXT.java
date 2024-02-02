package output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import data.Receipt;

public class ReceiptFileUpdaterTXT extends ReceiptFileUpdater {

	// Change the hard coded fields and call them dynamically with getters.
	protected void writeReceiptToFile() {
		try {
			String contents = "\nReceipt ID: " + receipt.getReceiptID() + "\n" +
					"Date: " + receipt.getDate() + "\n" +
					"Kind: " + receipt.getKind() + "\n" +
					"Sales: " + receipt.getSales() + "\n" +
					"Items: " + receipt.getItems() + "\n" +
					"Company: " + receipt.getCompany().getName() + "\n" +
					"Country: " + receipt.getCompany().getAddress().getCountry() + "\n" +
					"City: " + receipt.getCompany().getAddress().getCity() + "\n" +
					"Street: " + receipt.getCompany().getAddress().getStreet() + "\n" +
					"Number: " + receipt.getCompany().getAddress().getNumber() + "\n";

			FileWriter fileWriter = new FileWriter(fileToAppend,true);
			fileWriter.write(contents);
			fileWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
