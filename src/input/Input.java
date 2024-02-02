package input;


import java.io.File;
import data.*;


public abstract class Input {
	
	protected SalesRepresentative salesRepresentative;
	protected File inputFile;
	protected String inputFilePath;
	protected String name;
	protected String afm;
	protected int receiptID;
	protected String date;
	protected String kind;
	protected double sales;
	protected int items;
	protected String companyName;
	protected String companyCountry;
	protected String companyCity;
	protected String companyStreet;
	protected int companyStreetNumber;

	public SalesRepresentative getSalesRepresentative() {
		return salesRepresentative;
	}

	// Common algorithm for parsing input files
	public void parseInputFile() {
		readFileContents();
		createSalesRepresentative();
		createReceipts();
	}

	protected abstract void readFileContents();

	protected abstract void createSalesRepresentative();

	protected abstract void createReceipts();

	// Refactor to use proper objects with constructor calls and not chain getters & setters calls
	protected void createReceipt() {
		Address address = new Address(
			companyCountry,
			companyCity,
			companyStreet,
			companyStreetNumber,
			0
		);

		Company company = new Company(
			companyName,
			address
		);

		Receipt receipt = new Receipt(
			receiptID,
			date,
			sales,
			items,
			company,
			kind
		);
		salesRepresentative.getReceipts().add(receipt);
	}

}
