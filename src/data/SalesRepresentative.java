package data;


import java.util.ArrayList;

import output.ReceiptFileUpdater;
import output.ReceiptFileUpdaterTXT;
import output.ReceiptFileUpdaterXML;

public class SalesRepresentative {
	private String name;
	private String afm;
	private ArrayList <Receipt> receipts;
	private ReceiptFileUpdater receiptFileUpdater;
	
	int MIN_SALES = 6000;
	int AVG_SALES = 10000;
	int MAX_SALES = 40000;
	
	public SalesRepresentative(String name, String afm) {
		this.name = name;
		this.afm = afm;
		receipts = new ArrayList<Receipt>();
	}

	public ArrayList<Receipt> getReceipts() {
		return receipts;

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAfm() {
		return afm;
	}
	public void setAfm(String afm) {
		this.afm = afm;
	}

	public ReceiptFileUpdater getReceiptFileUpdater() {
		return receiptFileUpdater;
	}

	public void setFileType(String fileType) {
		if(fileType.equals("TXT")){
			receiptFileUpdater = new ReceiptFileUpdaterTXT();
		}
		else{
			receiptFileUpdater = new ReceiptFileUpdaterXML();
		}
	}

	// Use calculateTotal to avoid duplicate code for calculating total sales and total items
	public double calculateTotal(String type) {
	    double sum = 0;
	    for (Receipt receipt : receipts) {
	        if (type.equals("sales")) sum += receipt.getSales();
			else if (type.equals("items")) sum += receipt.getItems();
	    }
	    return sum;
	}

	// Adding the latest receipt to the file seems like a responsibility of the SalesRepresentative model
	// since it has both the newly added in receipt (in receipts list) and the receiptFileUpdater
	// No point in having this logic DetailsSelectionWindow.
	public void addLatestReceiptToFile() {
		int size = receipts.size();
		Receipt latestReceipt = receipts.get(size-1);
		receiptFileUpdater.appendReceiptToFile(latestReceipt);
	}

	public double calculateTotalSales() {
		return calculateTotal("sales");
	}

	public int calculateTotalItems() {
		return (int) calculateTotal("items");
	}

	public float calculateSalesByKind(String kind) {
        float sum = 0;
        for (Receipt receipt : receipts) {
            if (kind.equals(receipt.getKind())) {
                sum += receipt.getItems();
            }
        }
        return sum;
    }
	
	public double calculateCommission(){	
		double commission = 0;
		if(checkSalesBetweenMinAndAvg()){
			commission = 0.1*(calculateTotalSales()-MIN_SALES);
		}
		else if(checkSalesBetweenAvgAndMax()){
			commission = (((calculateTotalSales() - AVG_SALES) * 0.15) + (AVG_SALES*0.1));			
		}
		else if(checkSalesGreaterThanMax()) {
			commission = AVG_SALES*0.1 + 30000*0.15 + (calculateTotalSales() - MAX_SALES)*0.2;			
		}
		return commission;
	}
	
	private boolean checkSalesBetweenMinAndAvg() {
		return this.calculateTotalSales() > MIN_SALES && this.calculateTotalSales() <= AVG_SALES;
	}
	
	private boolean checkSalesBetweenAvgAndMax() {
		return this.calculateTotalSales() > AVG_SALES && this.calculateTotalSales() <= MAX_SALES;
	}

	private boolean checkSalesGreaterThanMax() {
		return this.calculateTotalSales() > MAX_SALES;
	}
	 



}
