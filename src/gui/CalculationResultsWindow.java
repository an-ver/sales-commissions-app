package gui;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

import output.ReporterHTML;
import output.ReporterTXT;
import data.SalesRepresentative;
import output.ReporterXML;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class CalculationResultsWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel resultWindowPanel = new JPanel();
	private JTextField totalSalesTextField;
	private JTextField totalItemsTextField;
	private JTextField trouserSalesTextField;
	private JTextField shirtSalesTextField;
	private JTextField coatSalesTextField;
	private JTextField skirtSalesTextField;
	private JTextField commissionTextField;
	private DetailsSelectionWindow detailsSelectionWindow;
	private SalesRepresentative salesRepresentative;
	private boolean isTotalSalesSelected;
	private boolean isTotalItemsSelected;
	private boolean isShirtSalesSelected;
	private boolean isSkirtsSalesSelected;
	private boolean isTrousersSalesSelected;
	private boolean isCoatsSalesSelected;
	private boolean isCommissionSelected;
	private String selectedFileAbsolutePath;

	public CalculationResultsWindow(
			final DetailsSelectionWindow detailsSelectionWindow,
			SalesRepresentative salesRepresentative,
			boolean isTotalSalesSelected,
			boolean isTotalItemsSelected,
			boolean isShirtSalesSelected,
			boolean isSkirtsSalesSelected,
			boolean isTrousersSalesSelected,
			boolean isCoatsSalesSelected,
			boolean isCommissionSelected) {
		this.setTitle(CalculationResultsWindow.class.getSimpleName());

		this.detailsSelectionWindow = detailsSelectionWindow;
		this.salesRepresentative = salesRepresentative;
		this.isTotalSalesSelected = isTotalSalesSelected;
		this.isTotalItemsSelected = isTotalItemsSelected;
		this.isShirtSalesSelected = isShirtSalesSelected;
		this.isSkirtsSalesSelected = isSkirtsSalesSelected;
		this.isTrousersSalesSelected = isTrousersSalesSelected;
		this.isCoatsSalesSelected = isCoatsSalesSelected;
		this.isCommissionSelected = isCommissionSelected;
		
		initialise();
	}	
	private void initialise(){
		setBounds(100, 100, 686, 456);
		getContentPane().setLayout(new BorderLayout());
		resultWindowPanel.setBackground(SystemColor.controlHighlight);
		resultWindowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(resultWindowPanel, BorderLayout.CENTER);
		resultWindowPanel.setLayout(null);
		{
			JButton txtReportButton = new JButton("\u0395\u03BE\u03B1\u03B3\u03C9\u03B3\u03AE \u03C3\u03B5 TXT");
			txtReportButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					outputTXTButtonPressed();
				}
			});
			txtReportButton.setBackground(UIManager.getColor("Button.light"));
			txtReportButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			txtReportButton.setBounds(436, 30, 163, 60);
			resultWindowPanel.add(txtReportButton);
		}
		{
			JButton xmlReportButton = new JButton("\u0395\u03BE\u03B1\u03B3\u03C9\u03B3\u03AE \u03C3\u03B5 XML");
			xmlReportButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					outputXMLButtonPressed();
				}
			});
			xmlReportButton.setBackground(UIManager.getColor("Button.light"));
			xmlReportButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			xmlReportButton.setBounds(436, 130, 163, 60);
			resultWindowPanel.add(xmlReportButton);
		}
		// Added htmlReportButton
		{
			JButton htmlReportButton = new JButton("\u0395\u03BE\u03B1\u03B3\u03C9\u03B3\u03AE \u03C3\u03B5 HTML");
			htmlReportButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					outputHTMLButtonPressed();
				}
			});
			htmlReportButton.setBackground(UIManager.getColor("Button.light"));
			htmlReportButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			htmlReportButton.setBounds(436, 220, 163, 60);
			resultWindowPanel.add(htmlReportButton);
		}
		{
			JLabel lblNewLabel = new JLabel("\u03A3\u03C5\u03BD\u03BF\u03BB\u03B9\u03BA\u03AE \u03B1\u03BE\u03AF\u03B1");
			lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lblNewLabel.setBounds(60, 41, 84, 43);
			resultWindowPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u03A3\u03CD\u03BD\u03BF\u03BB\u03BF \u03C0\u03C9\u03BB\u03AE\u03C3\u03B5\u03C9\u03BD");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(60, 95, 124, 14);
			resultWindowPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2 \u03C0\u03B1\u03BD\u03C4\u03B5\u03BB\u03BF\u03BD\u03B9\u03CE\u03BD");
			lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lblNewLabel_2.setBounds(60, 138, 138, 14);
			resultWindowPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2 \u039C\u03C0\u03BB\u03BF\u03C5\u03B6\u03CE\u03BD");
			lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lblNewLabel_3.setBounds(60, 178, 124, 14);
			resultWindowPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2 \u03C0\u03B1\u03BB\u03C4\u03CE\u03BD");
			lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lblNewLabel_4.setBounds(60, 220, 124, 14);
			resultWindowPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2 \u03C6\u03BF\u03C5\u03C3\u03C4\u03CE\u03BD");
			lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lblNewLabel_5.setBounds(60, 255, 124, 14);
			resultWindowPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("\u03A0\u03C1\u03BF\u03BC\u03AE\u03B8\u03B5\u03B9\u03B1");
			lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lblNewLabel_6.setBounds(60, 301, 101, 14);
			resultWindowPanel.add(lblNewLabel_6);
		}
		
		totalSalesTextField = new JTextField();
		totalSalesTextField.setEditable(false);
		totalSalesTextField.setBounds(208, 53, 86, 20);
		resultWindowPanel.add(totalSalesTextField);
		totalSalesTextField.setColumns(10);
		
		totalItemsTextField = new JTextField();
		totalItemsTextField.setEditable(false);
		totalItemsTextField.setBounds(208, 93, 86, 20);
		resultWindowPanel.add(totalItemsTextField);
		totalItemsTextField.setColumns(10);
		
		trouserSalesTextField = new JTextField();
		trouserSalesTextField.setEditable(false);
		trouserSalesTextField.setBounds(208, 136, 86, 20);
		resultWindowPanel.add(trouserSalesTextField);
		trouserSalesTextField.setColumns(10);
		
		shirtSalesTextField = new JTextField();
		shirtSalesTextField.setEditable(false);
		shirtSalesTextField.setBounds(208, 176, 86, 20);
		resultWindowPanel.add(shirtSalesTextField);
		shirtSalesTextField.setColumns(10);
		
		coatSalesTextField = new JTextField();
		coatSalesTextField.setEditable(false);
		coatSalesTextField.setBounds(208, 218, 86, 20);
		resultWindowPanel.add(coatSalesTextField);
		coatSalesTextField.setColumns(10);
		
		skirtSalesTextField = new JTextField();
		skirtSalesTextField.setEditable(false);
		skirtSalesTextField.setBounds(208, 253, 86, 20);
		resultWindowPanel.add(skirtSalesTextField);
		skirtSalesTextField.setColumns(10);
		
		commissionTextField = new JTextField();
		commissionTextField.setEditable(false);
		commissionTextField.setBounds(208, 299, 86, 20);
		resultWindowPanel.add(commissionTextField);
		commissionTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u039F\u039A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				okButtonPressed(evt);
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(246, 360, 101, 33);
		resultWindowPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelButtonPressed(evt);
				
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(357, 360, 101, 33);
		resultWindowPanel.add(btnNewButton_1);
		updateResults();
		
	}
	
	private void updateResults() {
		updateTotalSales();
		updateTotalItems();
		updateShirtSales();
		updateSkirtSales();
		updateCoatsSales();
		updateTrousersSales();
		updateCommission();
	}

	private void updateTotalSales()  {
		if (isTotalSalesSelected) {
			double totalSales = salesRepresentative.calculateTotalSales();
			totalSalesTextField.setText(Double.toString(totalSales));
		}
		else totalSalesTextField.setEnabled(false);
	}

	private void updateTotalItems() {
		if (isTotalItemsSelected) {
			int totalItems = salesRepresentative.calculateTotalItems();
			totalItemsTextField.setText(Integer.toString(totalItems));
		}
		else totalItemsTextField.setEnabled(false);
	}

	private void updateShirtSales() {
		if (isShirtSalesSelected) {
			float shirtsSales = salesRepresentative.calculateSalesByKind("Shirts");
			shirtSalesTextField.setText(Float.toString(shirtsSales));
		}
		else shirtSalesTextField.setEnabled(false);
	}

	private void updateSkirtSales() {
		if (isSkirtsSalesSelected) {
			float skirtsSales = salesRepresentative.calculateSalesByKind("Skirts");
			skirtSalesTextField.setText(Float.toString(skirtsSales));
		}
		else skirtSalesTextField.setEnabled(false);
	}

	private void updateCoatsSales() {
		if (isCoatsSalesSelected) {
			float coatsSales = salesRepresentative.calculateSalesByKind("Coats");
			coatSalesTextField.setText(Float.toString(coatsSales));
		}
		else coatSalesTextField.setEnabled(false);
	}

	private void updateTrousersSales() {
		if (isTrousersSalesSelected) {
			float trousersSales = salesRepresentative.calculateSalesByKind("Trousers");
			trouserSalesTextField.setText(Float.toString(trousersSales));
		}
		else trouserSalesTextField.setEnabled(false);
	}

	private void updateCommission() {
		if (isCommissionSelected) {
			double commission = salesRepresentative.calculateCommission();
			commissionTextField.setText(Double.toString(commission));
		}
		else commissionTextField.setEnabled(false);
	}

	// Modified to allow the user to select file for saving the report
	private void outputTXTButtonPressed() {
		clearSelectedFileAbsolutePath();
		showFilePickerDialog();
		if (!hasPickedFile()) {
			JOptionPane.showMessageDialog(null, "TXT export cancelled.");
			return;
		}
		ReporterTXT makeTXTFile = new ReporterTXT(salesRepresentative);
		makeTXTFile.createReport(selectedFileAbsolutePath);
		JOptionPane.showMessageDialog(
				null,"TXT export created at:\n" + selectedFileAbsolutePath);
	}

	// Modified to allow the user to select file for saving the report
	private void outputXMLButtonPressed() {
		clearSelectedFileAbsolutePath();
		showFilePickerDialog();
		if (!hasPickedFile()) {
			JOptionPane.showMessageDialog(null, "XML export cancelled.");
			return;
		}
		ReporterXML makeXMLFile = new ReporterXML(salesRepresentative);
		makeXMLFile.createReport(selectedFileAbsolutePath);
		JOptionPane.showMessageDialog(null,
				"XML export created at:\n" + selectedFileAbsolutePath);
	}

	//	Add click listener method for html button
	private void outputHTMLButtonPressed() {
		clearSelectedFileAbsolutePath();
		showFilePickerDialog();
		if (!hasPickedFile()) {
			JOptionPane.showMessageDialog(null, "HTML export cancelled.");
			return;
		}
		ReporterHTML makeHTMLFile = new ReporterHTML(salesRepresentative);
		makeHTMLFile.createReport(selectedFileAbsolutePath);
		JOptionPane.showMessageDialog(
				null,"HTML export created at:\n" + selectedFileAbsolutePath);
	}

	private void clearSelectedFileAbsolutePath() {
		selectedFileAbsolutePath = "";
	}

	private boolean hasPickedFile() {
		return !selectedFileAbsolutePath.isEmpty();
	}
	private void showFilePickerDialog() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			selectedFileAbsolutePath = selectedFile.getAbsolutePath();
		}
	}

	private void okButtonPressed(ActionEvent evt) {
		System.exit(0);		
	}
	
	private void cancelButtonPressed(ActionEvent evt) {
		detailsSelectionWindow.setVisible(true);
		this.dispose();	
	}
	
}
