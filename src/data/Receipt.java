package data;

public class Receipt {
	protected int receiptId;
	protected String date;
	protected double sales;
	protected int items;
	protected Company company;
	protected String kind;

	// Add constructor to initialize fields
    public Receipt(int receiptId, String date, double sales, int items, Company company, String kind) {
        this.receiptId = receiptId;
        this.date = date;
        this.sales = sales;
        this.items = items;
        this.company = company;
        this.kind = kind;
    }
    
	public Company getCompany(){
		
		return company;
	}

	public void setCompany(Company company) {
        this.company = company;
    }
	
	public String getKind() {
		return kind;
		
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public int getItems() {
		return this.items;
	}

	public void setItems(int items) {
		this.items = items;
	}
	
	public int getReceiptID() {
		return receiptId;
	}

	public void setReceiptID(int id) {
		this.receiptId = id;
		
	}

	public String getDate() {
		return date;			
	}	
	
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
