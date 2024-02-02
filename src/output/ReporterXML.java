package output;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import data.SalesRepresentative;

public class ReporterXML extends Reporter {

	private Document document;
	private Element agentElem;

	public ReporterXML(SalesRepresentative salesRepresentative) {
		super(salesRepresentative);
	}

	protected void initialize() {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			document = documentBuilder.newDocument();
			// root element
			agentElem = document.createElement("Agent");
			document.appendChild(agentElem);
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(
					null,"Error with creating XML report.");
		}
	}

	protected String getFileExtension() {
		return ".xml";
	}

	protected void createReportContents() {
		Element name = document.createElement("Name");
		name.appendChild(document.createTextNode(salesRepresentative.getName()));
		agentElem.appendChild(name);

		Element afm = document.createElement("AFM");
		afm.appendChild(document.createTextNode(salesRepresentative.getAfm()));
		agentElem.appendChild(afm);

		Element totalSales = document.createElement("TotalSales");
		totalSales.appendChild(document.createTextNode(Double.toString(salesRepresentative.calculateTotalSales())));
		agentElem.appendChild(totalSales);

		Element trouserSales = document.createElement("TrouserSales");
		trouserSales.appendChild(document.createTextNode(Float.toString(salesRepresentative.calculateSalesByKind("Trousers"))));
		agentElem.appendChild(trouserSales);

		Element skirtsSales = document.createElement("SkirtsSales");
		skirtsSales.appendChild(document.createTextNode(Float.toString(salesRepresentative.calculateSalesByKind("Skirts"))));
		agentElem.appendChild(skirtsSales);

		Element shirtsSales = document.createElement("ShirtsSales");
		shirtsSales.appendChild(document.createTextNode(Float.toString(salesRepresentative.calculateSalesByKind("Shirts"))));
		agentElem.appendChild(shirtsSales);

		Element coatsSales = document.createElement("CoatsSales");
		coatsSales.appendChild(document.createTextNode(Float.toString(salesRepresentative.calculateSalesByKind("Coats"))));
		agentElem.appendChild(coatsSales);

		Element commission = document.createElement("Commission");
		commission.appendChild(document.createTextNode(Double.toString(salesRepresentative.calculateCommission())));
		agentElem.appendChild(commission);
	}

	protected void writeReportContentsToFile() {
        try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(absoluteFilePath));
			transformer.transform(domSource, streamResult);
        }
		catch (Exception ex) {
			JOptionPane.showMessageDialog(
					null,"Error with creating XML report.");
        }
	}

}

