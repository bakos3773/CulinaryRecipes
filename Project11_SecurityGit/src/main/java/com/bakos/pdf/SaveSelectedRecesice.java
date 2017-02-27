package com.bakos.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.bakos.UserDTO.CulinaryRecipes;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SaveSelectedRecesice {

	Document document = new Document(PageSize.A4, 50, 50, 50, 50);
	
	public void metoda(CulinaryRecipes recipes) throws DocumentException, IOException{
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Bakos\\Desktop\\"+recipes.getName()+".pdf"));
		document.open();
		
        String imagePosition = System.getProperty("catalina.home") +File.separator + "resources" + File.separator + "images"
				+ File.separator + recipes.getId() + ".jpg";		
//		  Anchor anchorTarget = new Anchor("First page of the document.");
//	      anchorTarget.setName("BackToTop");
//	      
//	      Paragraph paragraph1 = new Paragraph();
//	 
//	      paragraph1.setSpacingBefore(50);
//	 
//	      paragraph1.add(anchorTarget);
//	      document.add(paragraph1);
//	 
//		  document.add(new Paragraph("Some more text on the first page with different color and font type.", 
//			 
//		  FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));	
		
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
        
        document.add(table);		
		  
		  document.close();
	}
	
	   public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
	        Image img = Image.getInstance(path);
	        PdfPCell cell = new PdfPCell(img, true);
	        return cell;
	    }	
}
