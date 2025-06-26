import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.ArrayList;

public class User {
    ArrayList<String> B = new ArrayList<>();
    int id;
    int num_books_issues = 0;
    String name;
    int returnStatus;
    double fine;
    LocalDate issueTime;
    LocalDate returnTime;
    int type;//book issued type

    User(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
User(int id)
{
    this.id=id;
}

//    double getFine() {
//        Duration duration = Duration.between(issueTime, returnTime);
//        long days = duration.toDays();
//        return this.fine;
  //  }

    int numOfBookIssued() {
//Search for id in library database for no.of times it occurs
        return num_books_issues;
    }

    LocalDate issueBookTime() {
        issueTime = LocalDate.now();
        return issueTime;

    }

    LocalDate bookReturnedTime() {
        returnTime = LocalDate.now();
        return returnTime;
    }

    void bookReturned(String name) {
        for (int i = 0; i < B.size(); i++) {
            if (B.get(i).equalsIgnoreCase(name)) {
                B.remove(i);
                break;
            } else {
                System.out.println("No book with name" + name + "  was issued by user");
            }
        }
    }

    void bookIssued(String name) {
        if (this.isOriginal(name)) {
            if (num_books_issues < 5) {
                B.add(name);
                num_books_issues++;
            } else {
                System.out.println("Maximum book issue limit of user is reached\nFirst return the issued books");
            }
        }
    }

    boolean isOriginal(String name) {
        for (int i = 0; i < B.size(); i++) {
            if (B.get(i).equalsIgnoreCase(name)) {
                System.out.println("Same book is already issued by user");
                return false;
            }
        }
        return true;
    }
    void getUserLibraryData()
    {
        for (int i=0;i<B.size();i++)
        {
            System.out.println("book name"+B.get(i));
        }
    }
}



