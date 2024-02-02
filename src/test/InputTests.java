package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import data.Receipt;
import data.SalesRepresentative;
import input.HTMLInput;
import input.TXTInput;
import input.XMLInput;

public class InputTests {

    private final static String txtTestFile = "src/test/resources/test-case-1-TXT.txt";
    private final static String xmlTestFile = "src/test/resources/test-case-2-XML.xml";
    private final static String htmlTestFile = "src/test/resources/test-case-3-HTML.html";


    private TXTInput txtInput;
    private XMLInput xmlInput;
    private HTMLInput htmlInput;

    @Before
    public void init() {
        txtInput = new TXTInput(new File(txtTestFile));
        xmlInput = new XMLInput(new File(xmlTestFile));
        htmlInput = new HTMLInput(new File(htmlTestFile));
    }

    @Test
    public void testTxtInput() {
        txtInput.parseInputFile();
        assertFieldsOfCreatedObjects(txtInput.getSalesRepresentative());
    }

    @Test
    public void testXmlInput() {
        xmlInput.parseInputFile();
        assertFieldsOfCreatedObjects(xmlInput.getSalesRepresentative());
    }

    @Test
    public void testHtmlInput() {
        htmlInput.parseInputFile();
        assertFieldsOfCreatedObjects(htmlInput.getSalesRepresentative());
    }

    private void assertFieldsOfCreatedObjects(SalesRepresentative salesRepresentative) {
        ArrayList<Receipt> receipts = salesRepresentative.getReceipts();

        // Assert equals for sales representative
        assertEquals("Apostolos Zarras", salesRepresentative.getName());
        assertEquals("130456093", salesRepresentative.getAfm());

        // Assert equals for 1st receipt
        assertEquals(1, receipts.get(0).getReceiptID());
        assertEquals("25/2/2014", receipts.get(0).getDate());
        assertEquals("Coats", receipts.get(0).getKind());
        assertEquals(2000.0, receipts.get(0).getSales(), 0);
        assertEquals(10, receipts.get(0).getItems());
        assertEquals("Hand Made Clothes", receipts.get(0).getCompany().getName());
        assertEquals("Greece", receipts.get(0).getCompany().getAddress().getCountry());
        assertEquals("Ioannina", receipts.get(0).getCompany().getAddress().getCity());
        assertEquals("Kaloudi", receipts.get(0).getCompany().getAddress().getStreet());
        assertEquals(10, receipts.get(0).getCompany().getAddress().getNumber());

        // Assert equals for 2nd receipt
        assertEquals(2, receipts.get(1).getReceiptID());
        assertEquals("25/2/2014", receipts.get(1).getDate());
        assertEquals("Coats", receipts.get(1).getKind());
        assertEquals(4000.0, receipts.get(1).getSales(), 0);
        assertEquals(10, receipts.get(1).getItems());
        assertEquals("Hand Made Clothes", receipts.get(1).getCompany().getName());
        assertEquals("Greece", receipts.get(1).getCompany().getAddress().getCountry());
        assertEquals("Ioannina", receipts.get(1).getCompany().getAddress().getCity());
        assertEquals("Kaloudi", receipts.get(1).getCompany().getAddress().getStreet());
        assertEquals(10, receipts.get(1).getCompany().getAddress().getNumber());
    }

}
