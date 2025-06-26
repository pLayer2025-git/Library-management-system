import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ExcelManager.initializeBooksExcel(Book.books);
        ExcelManager.initializeLibraryExcel(Library.lib);

        while (true) {
            try {
                System.out.println("1:Add new Book\n2:Issue book\n3:return book\n4:Exit\nEnter your option number ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:


                        sc.nextLine();//very important
                        System.out.print("Enter book name");
                        String s=sc.nextLine();
                        sc.nextLine();
                        System.out.print("Enter book author");
                        String s1=sc.nextLine();
                        System.out.println("Enter book section number \n1:Science\n2:Language\n3:Maths\n4:Engineering\n5:Law\n6:Commerce)");
                        int s3=sc.nextInt();
                        System.out.print("Enter number of books available-");
                        int s4=sc.nextInt();
                        Book.addBook(s,s1,s4,s3);
                        ExcelManager.writeBooksToExcel(Book.books);
                        break;

                    case 2:
                        Library ob1=new Library();
                        ob1.issueBbook();
                        ExcelManager.writeBooksToExcel(Book.books);
                        ExcelManager.writeLibraryToExcel(Library.lib);
                        break;
                    case 3:
                        Library ob2=new Library();
                        ob2.returnBook();

                    ExcelManager.writeBooksToExcel(Book.books);
                    ExcelManager.writeLibraryToExcel(Library.lib);
                        break;
                    case 4:

                        return;
                    default:
                        System.out.println("Invalid Choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter valid entry");
                sc.nextLine();
            }
        }
    }
}