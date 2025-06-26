import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExcelManager {
    static String bookFile = "BookDatabase.xlsx";
    static String libFile = "LibraryDatabase.xlsx";

    static void initializeBooksExcel(ArrayList<Book> books) {
        books.clear();
        File file = new File(bookFile);
        if (file.exists()) {
            loadBooksFromExcel(books);
        } else {
            writeBooksToExcel(books);
        }
    }

    static void initializeLibraryExcel(ArrayList<Library> lib) {
        lib.clear();
        File file = new File(libFile);
        if (file.exists()) {
            loadLibraryFromExcel(lib);
        } else {
            writeLibraryToExcel(lib);
        }
    }

    static void writeBooksToExcel(ArrayList<Book> books) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Books");
            int rowCount = 0;
            Row header = sheet.createRow(rowCount++);
            header.createCell(0).setCellValue("Book Name");
            header.createCell(1).setCellValue("Author Name");
            header.createCell(2).setCellValue("Quantity");
            header.createCell(3).setCellValue("Topic");

            for (Book book : books) {
                Row row = sheet.createRow(rowCount++);
                row.createCell(0).setCellValue(book.book_name);
                row.createCell(1).setCellValue(book.author_name);
                row.createCell(2).setCellValue(book.quantity);
                row.createCell(3).setCellValue(book.topic);
            }

            try (FileOutputStream out = new FileOutputStream(bookFile)) {
                workbook.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeLibraryToExcel(ArrayList<Library> lib) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Library");
            int rowCount = 0;
            Row header = sheet.createRow(rowCount++);
            header.createCell(0).setCellValue("Book Name");
            header.createCell(1).setCellValue("Author Name");
            header.createCell(2).setCellValue("Student ID");
            header.createCell(3).setCellValue("Issue Date");
            header.createCell(4).setCellValue("Return Date");
            header.createCell(5).setCellValue("Return Status");

            for (Library l : lib) {
                Row row = sheet.createRow(rowCount++);
                row.createCell(0).setCellValue(l.bookName);
                row.createCell(1).setCellValue(l.authorName);
                row.createCell(2).setCellValue(l.uID);
                row.createCell(3).setCellValue(l.it == null ? "" : l.it.toString());
                row.createCell(4).setCellValue(l.rt == null ? "" : l.rt.toString());
                row.createCell(5).setCellValue(l.returnStatus);
            }

            try (FileOutputStream out = new FileOutputStream(libFile)) {
                workbook.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadBooksFromExcel(ArrayList<Book> books) {
        try (FileInputStream fis = new FileInputStream(bookFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String name = row.getCell(0).getStringCellValue();
                    String author = row.getCell(1).getStringCellValue();
                    int qty = (int) row.getCell(2).getNumericCellValue();
                    int topic = (int) row.getCell(3).getNumericCellValue();
                    books.add(new Book(name, author, qty, topic));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadLibraryFromExcel(ArrayList<Library> lib) {
        try (FileInputStream fis = new FileInputStream(libFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Library l = new Library();
                    l.bookName = row.getCell(0).getStringCellValue();
                    l.authorName = row.getCell(1).getStringCellValue();
                    l.uID = (int) row.getCell(2).getNumericCellValue();
                    l.it = row.getCell(3).getStringCellValue().isEmpty() ? null : LocalDate.parse(row.getCell(3).getStringCellValue());
                    l.rt = row.getCell(4).getStringCellValue().isEmpty() ? null : LocalDate.parse(row.getCell(4).getStringCellValue());
                    l.returnStatus = row.getCell(5).getBooleanCellValue();
                    lib.add(l);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
