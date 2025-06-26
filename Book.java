import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Book {


    static ArrayList<Book>books=new ArrayList<>();
   String book_name;
   String author_name;
   int topic;
   int quantity;
   //int bookTypeInMonths;
Book(String book_name,String author_name,int quantity,int topic)
{
    this.author_name=author_name;
    this.book_name=book_name;
    this.quantity=quantity;
    this.topic =topic;
//this.bookTypeInMonths=bookTypeInMonths;
}
Book(String name)
{
this.book_name=name;
}
Book(int topic,String author_name)
{
    this.topic=topic;
    this.author_name=author_name;
}
//void addBooks()
//{
//books.add(new Book(book_name,author_name,quantity,topic));
//}
static void addBook(String name, String author, int quantity, int topic) {
    books.add(new Book(name, author, quantity, topic));
}

    void takeBooks(String name)
{
    int i=this.getBookIndex(name);
    if(i!=-1)
    {
     if(books.get(i).quantity>0)
     {
         books.get(i).quantity--;
     }
     else{
         System.out.println("Cannot issue book");
     }
    }
    else {
        System.out.println("Book not available");
    }
}
void returnBooks(String name)
{
    int i=this.getBookIndex(name);
    if(i!=-1)
    {
            books.get(i).quantity++;
        System.out.println("Book returned successfully");
    }
    else {
        System.out.println("Unknown Book, Not found in library database");
    }
}

int numberOfBooks(String name)//tells no.ofbooks available of any author;
{
    for (int i=0;i<books.size();i++)
    {
        if(books.get(i).book_name.equalsIgnoreCase(name.trim()))
        {
          return books.get(i).quantity;
        }

    }
    return -1;
}
//int numberOfBooks(int topic,String author_name)
//{
//    for (int i=0;i<books.size();i++)
//    {
//        if(books.get(i).book_name.equalsIgnoreCase(author_name))
//        {
//            return books.get(i).quantity;
//        }
//
//    }
//
//    return -1;
//}


int getBookIndex(String name)//check database for book availability
{
    int index=-1;
    for (int i=0;i<books.size();i++)
    {
        if(books.get(i).book_name.equalsIgnoreCase(name))
        {
            index=i;
            break;
        }
    }
    return index;
}
void listBookUnderTopic(int topic)
{
switch(topic)
{
    case 1:
        System.out.println("Science");break;
    case 2:
        System.out.println("Language");break;
    case 3:
        System.out.println("Maths");break;
    case 4:
        System.out.println("Engineering");break;
    case 5: System.out.println("Law");break;
    case 6: System.out.println("Commerce");break;
    default: System.out.println("Invalid choice");
}
for(int i=0;i<books.size();i++)
{
    if(books.get(i).topic==topic)
    {
        System.out.println(books.get(i).book_name +"   "+books.get(i).author_name);
    }
}
}

}
