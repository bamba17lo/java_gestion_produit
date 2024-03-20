package com.example.examen_javafx.repository;

import com.example.examen_javafx.model.ProduitModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.scene.text.TextAlignment;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.*;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;




import static com.itextpdf.kernel.pdf.PdfName.Border;


public class PDFGenerate {
    public void generatePDF(List<ProduitModel> produits, String filePath) throws IOException, IOException {

        String path = "font.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        float columnWidth[] = {200f,50f,100f,100f,100f};
        Table table = new Table(columnWidth);
        // Ajouter les en-têtes de colonne
        table.addCell(new Cell().add("ID"));
        table.addCell(new Cell().add("Libellé"));
        table.addCell(new Cell().add("Quantité"));
        table.addCell(new Cell().add("Prix"));
        table.addCell(new Cell().add("Catégorie"));

        for (ProduitModel produit : produits) {
            table.addCell(new Cell().add(String.valueOf(produit.getId()))); // Ajouter l'ID du produit
            table.addCell(new Cell().add(produit.getLibelle())); // Ajouter le libellé du produit
            table.addCell(new Cell().add(produit.getQuantite())); // Ajouter la quantité du produit
            table.addCell(new Cell().add(produit.getPrixU())); // Ajouter le prix du produit
            table.addCell(new Cell().add(produit.getCategorieLibelle())); // Ajouter la catégorie du produit
        }

        document.add(table);

        document.close();
        System.out.println("succes");
    }
}
