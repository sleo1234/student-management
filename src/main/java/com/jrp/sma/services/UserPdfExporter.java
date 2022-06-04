package com.jrp.sma.services;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.jrp.sma.entities.Student;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
 

public class UserPdfExporter {
    private List<Student> listStudents;
     
    public UserPdfExporter(List<Student> listStudents) {
        this.listStudents = listStudents;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
    cell.setPhrase(new Phrase("Student ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Activities", font));
        table.addCell(cell);
        
       cell.setPhrase(new Phrase("Photo", font));
        table.addCell(cell);
         
    }
     
    private void writeTableData(PdfPTable table) throws BadElementException, IOException {
    	
    	
    	
        for (Student student : listStudents) {
        
    
        	//Image img = Image.getInstance("/Users/Leo/eclipse-workspace/student-management/src/main/resources/static/user-photos/IMG_5675.jpg");
        
            table.addCell(String.valueOf(student.getStudentId()));
            table.addCell(student.getFirstName());
            table.addCell(student.getLastName());
            table.addCell((student.getActivities()).toString());
        	//table.addCell(img);
            if (!student.getPhoto().equals("/user-photos/null")) {
                Image img = Image.getInstance("/Users/Leo/eclipse-workspace/student-management/src/main/resources/static"+student.getPhoto());
                 img.scaleAbsolute(10, 10);
                 table.addCell(img);
                 	}
                 else table.addCell("no image");
        
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Students", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 3f, 3.5f, 3.0f, 5.0f,5.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}