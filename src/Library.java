import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (searchBookByName(book.getNombre()) == null) {
            books.add(book);
            System.out.println("El libro ha sido agregado.");
        } else {
            System.out.println("Ya existe un libro con el mismo nombre.");
        }
    }

    public Book searchBookByName(String name) {
        for (Book book : books) {
            if (book.getNombre().equals(name)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public boolean removeBookById(int id) {
        Book bookToRemove = searchBookById(id);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("No se encontró un libro con el ID especificado.");
        }
        return false;
    }

    public boolean removeBookByName(String name) {
        Book bookToRemove = searchBookByName(name);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("No se encontró un libro con el nombre especificado.");
        }
        return false;
    }

    public int getTotalPagesRecursive() {
        return getTotalPagesRecursiveHelper(books);
    }

    private int getTotalPagesRecursiveHelper(ArrayList<Book> bookList) {
        int totalPages = 0;
        for (Book book : bookList) {
            totalPages += book.getPaginas();
        }
        return totalPages;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
