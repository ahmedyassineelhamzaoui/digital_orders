package com.app.utils;

import com.app.models.Demande;
import com.app.models.Devis;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class DevisGenerator {


    public void generate(Devis devis, HttpServletResponse response) throws IOException, IOException {
        double finalPrice = 0;
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        // Opening the created document to change it
        document.open();

        // Load the image
        Image imgg = Image.getInstance("logo.png");
        imgg.setAlignment(Paragraph.ALIGN_CENTER);
        imgg.setSpacingAfter(50f);
        imgg.scaleAbsolute(130, 90); // Set image width and height as needed
        document.add(imgg);






        // Adding a condition title and paragraph
        Font fontTiltler= FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltler.setSize(15);
        Paragraph conditontitle = new Paragraph("Les condition de devis :", fontTiltler);
        conditontitle.setAlignment(Paragraph.ALIGN_LEFT);
        conditontitle.setSpacingBefore(40f);
        document.add(conditontitle);

        Paragraph Conditions = new Paragraph(devis.getTerms());
        Conditions.setSpacingBefore(12f);
        Conditions.setAlignment(Paragraph.ALIGN_LEFT);
        Conditions.setSpacingAfter(30f);
        document.add(Conditions);


        // Creating a table of the 4 columns
        PdfPTable table = new PdfPTable(5);
        table.setSpacingBefore(20f);
        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(109f);
        table.setWidths(new int[] {4,4,4,4,4});
        // Create Table Cells for the table header
        PdfPCell cell = new PdfPCell();
        // Setting the background color and padding of the table cell
        cell.setPadding(5);
        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.BLACK);
        // Adding headings in the created table cell or  header
        // Adding Cell to table
        cell.setPhrase(new Phrase("Type", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Equipment", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Matricule", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("La reservation", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("La date de retoure", font));
        table.addCell(cell);
        // Iterating the list of students
        List<Demande> accepted = devis.getDemandes();

        for (Demande d : accepted){

            table.addCell(d.getEquipment().getCategory().getName());
            table.addCell(d.getEquipment().getName());
            table.addCell(d.getEquipment().getRegistrationNumber());
            table.addCell(String.valueOf(d.getEndDate()));
            table.addCell(String.valueOf(d.getStartDate()));

            finalPrice = finalPrice + d.getDemandeCost();

        }






        // Adding the created table to the document
        document.add(table);


        // Adding text on the right side as a paragraph
        Paragraph leftParagraph = new Paragraph("Prix Total" );
        leftParagraph.setAlignment(Element.ALIGN_LEFT);
        leftParagraph.setSpacingBefore(80f);
        Paragraph priceTottal = new Paragraph(finalPrice + " DH");
        priceTottal.setAlignment(Element.ALIGN_LEFT);

        document.add(leftParagraph );
        document.add(priceTottal );




        // Adding text on the right side as a paragraph
        Paragraph rightParagraph = new Paragraph("Signature");
        rightParagraph.setAlignment(Element.ALIGN_RIGHT);
        rightParagraph.setSpacingBefore(80f); // Set space before the paragraph

        document.add(rightParagraph);

        // Load the image
        Image img = Image.getInstance("image.png");
        img.setAlignment(Element.ALIGN_RIGHT);
        img.scaleAbsolute(100, 50); // Set image width and height as needed
        document.add(img);
        // Close the document
        // Closing the document
        document.close();
    }
}
