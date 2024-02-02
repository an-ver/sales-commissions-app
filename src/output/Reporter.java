package output;

import data.SalesRepresentative;

public abstract class Reporter {

	protected String absoluteFilePath;
	protected SalesRepresentative salesRepresentative;

	public Reporter(SalesRepresentative salesRepresentative){
		this.salesRepresentative = salesRepresentative;
	}

	// This is the main algorithm for creating a report file.
	public void createReport(String selectedFileAbsolutePath) {
		determineOutputFile(selectedFileAbsolutePath);
		initialize();
		createReportContents();
		writeReportContentsToFile();
	}

	private void determineOutputFile(String selectedFileAbsolutePath) {
		absoluteFilePath = selectedFileAbsolutePath + getFileExtension();
	}

	protected abstract void initialize();

	protected abstract String getFileExtension();

	protected abstract void createReportContents();

	protected abstract void writeReportContentsToFile();


}
