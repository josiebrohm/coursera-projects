import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();

    public void loadBooks(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");

                String title = splitLine[0];
                String author = splitLine[1];
                int publicationYear = Integer.parseInt(splitLine[2]);

                Book book = new Book(title, author, publicationYear);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book searchBookByKeyword(String keyword) {
        keyword = keyword.toLowerCase();

        for (Book book: books) {
            if (book.getTitle().toLowerCase().contains(keyword)
                    || book.getAuthor().toLowerCase().contains(keyword)
                    || String.valueOf(book.getPublicationYear()).equals(keyword)) {
                return book;
            }
        }

        return null;
    }


    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}
