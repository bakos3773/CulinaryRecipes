package com.bakos.pdf;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bakos.UserDTO.CulinaryRecipes;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFBuilder extends PdfRecipeById{

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CulinaryRecipes recipes = (CulinaryRecipes) model.get("recipes");
        String imagePosition = System.getProperty("catalina.home") +File.separator + "resources" + File.separator + "images"
				+ File.separator + recipes.getId() + ".jpg";
		document.add(new Paragraph("Suitable recipe"));
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100.0f);
//        table.setWidths(new float[] {1.0f, 1.0f, 1.0f, 1.0f, 1.0f});
        table.setSpacingBefore(10);
        
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
//        cell.setPadding(4);
         
        // write table header
        cell.setPhrase(new Phrase("Image", font));
        table.addCell(cell);        
        table.addCell(createImageCell(imagePosition));
        
        cell.setPhrase(new Phrase("Type", font));
        table.addCell(cell);
        table.addCell(recipes.getType());
         
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        table.addCell(recipes.getName());
 
        cell.setPhrase(new Phrase("Components", font));
        table.addCell(cell);
        table.addCell(recipes.getComponents());
         
        cell.setPhrase(new Phrase("How to perform", font));
        table.addCell(cell);
        table.addCell(recipes.getHowToPerform());
        

//        table.addCell(createImageCell(imagePosition));
//        table.addCell(recipes.getType());
//        table.addCell(recipes.getName());
//        table.addCell(recipes.getComponents());
//        table.addCell(recipes.getHowToPerform());
        
        document.add(table);
	}
	
	   public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
	        Image img = Image.getInstance(path);
	        PdfPCell cell = new PdfPCell(img, true);
	        return cell;
	    }

}
