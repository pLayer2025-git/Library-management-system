import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
public class Library {
    static ArrayList<Library>lib=new ArrayList<>();
    LocalDate it;
    LocalDate rt;
    String bookName;
    String authorName;
    String studentName;
    int uID;
    //int fineDue;
    boolean returnStatus=false;
    Scanner sc1=new Scanner(System.in);
void issueBbook()
    {try{

        System.out.print("Enter Student name");
        String s=sc1.nextLine();
        System.out.println("Enter Student id");
        int n= sc1.nextInt();
        sc1.nextLine();
        //Search for id in library database to check uniqueness of id with name
        System.out.println("Enter name of book");
        String s2=sc1.nextLine();

        // Search book in database i.e in book arraylist all info
        //if book with name not found then go for author
        //if book with author not found go for topic
        //suppose we found the book
        System.out.println("Enter time duration\n1:for one month\n2:for six months");
        int t=0;
        while (t==0)
        {switch(sc1.nextInt())
        {
            case 1:t=1;break;
            case 2:t=6;break;
            default:
                System.out.println("Invalid choice");
        }}
        User ob=new User(n,s,t);
       // LocalDateTime it= ob.issueBookTime();
        Book ob2=new Book(s2);
        if(ob2.numberOfBooks(s2)!=-1)
        {
            bookName=ob2.book_name;
            authorName= ob2.author_name;
            studentName=ob.name;
            uID=n;
            ob2.takeBooks(s2);
            it= ob.issueTime;
            ob.bookIssued(s2);
             updateLibraryDatabase();
        }
        else {
         System.out.println("Book currently not available");
        }


    }
    catch (InputMismatchException e)
    {
        System.out.println("Wrong entry");
    }


    }
    void returnBook()
    {try{
        System.out.println("Enter name of book");
        String s2=sc1.nextLine();
        System.out.println("Enter Student id");
        int n= sc1.nextInt();
        Book ob=new Book(s2);
        User ob2=new User(n);
        ob2.bookReturned(s2);
        ob.returnBooks(s2);
        rt=ob2.returnTime;
        returnStatus=true;}
    catch (InputMismatchException e)
    {
        System.out.println("Wrong entry");
    }

    }
    void updateLibraryDatabase()
    {
        lib.add(this);
    }

}
