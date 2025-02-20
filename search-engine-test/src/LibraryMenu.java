import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LibraryMenu {
    private Library library;
    private UserInteractionLogger logger = new UserInteractionLogger();
    private LibrarySerializer serializer = new LibrarySerializer();  // Added serializer

    public LibraryMenu(Library library) {
        this.library = library;

        // Load the library data when the program starts
        List<Book> books = serializer.loadLibrary("src/resources/data/library.ser");
        if (books != null) {
            library.setBooks(books);
            System.out.println("Library loaded successfully.");
        } else {
            System.out.println("No saved library found. Loading default books.");
            library = new Library();
            library.loadBooks("src/resources/data/books.txt");
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Main Menu ===");
            System.out.println();
            System.out.println("1. View All Books");
            System.out.println("2. Sort Books by Title");
            System.out.println("3. Sort Books by Author");
            System.out.println("4. Sort Books by Year");
            System.out.println("5. Search for a Book by keyword");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");

            switch (scanner.nextInt()) {
                case 1:
                    library.viewAllBooks();
                    logger.log("Viewed all books.");
                    break;
                case 2:
                    SortUtil.bubbleSort(library.getBooks(), Comparator.comparing(Book::getTitle));
                    library.viewAllBooks();
                    logger.log("Sorted books by title.");
                    break;
                case 3:
                    SortUtil.insertionSort(library.getBooks(), Comparator.comparing(Book::getAuthor));
                    library.viewAllBooks();
                    logger.log("Sorted books by author.");
                    break;
                case 4:
                    SortUtil.quickSort(library.getBooks(), Comparator.comparing(Book::getPublicationYear), 0, library.getBooks().size() - 1);
                    library.viewAllBooks();
                    logger.log("Sorted books by publication year.");
                    break;
                case 5:
                    System.out.print("Enter keyword: ");
                    Book book = library.searchBookByKeyword(scanner.next());
                    if (book == null) {
                        System.out.println("Book not found.");
                    } else {
                        System.out.println("Book found: " + book);
                    }
                    logger.log("Searched books by keyword.");
                    break;
                case 6:
                    logger.log("Program exited.");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option (1-6)");
                    logger.log("Invalid choice entered.");
            }
        }
    }

}
