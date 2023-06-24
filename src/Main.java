public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book(1, "Libro 1", 100));
        library.addBook(new Book(2, "Libro 2", 200));
        library.addBook(new Book(3, "Libro 3", 150));
        library.addBook(new Book(4, "Libro 1", 120)); // Intentar agregar un libro con el mismo nombre

        Book bookByName = library.searchBookByName("Libro 2");
        if (bookByName != null) {
            System.out.println("Libro encontrado por nombre: " + bookByName.getNombre());
        } else {
            System.out.println("No se encontró un libro con el nombre especificado.");
        }

        Book bookById = library.searchBookById(2);
        if (bookById != null) {
            System.out.println("Libro encontrado por ID: " + bookById.getNombre());
        } else {
            System.out.println("No se encontró un libro con el ID especificado.");
        }

        library.removeBookById(3);
        library.removeBookByName("Libro 1");

        int totalPages = library.getTotalPagesRecursive();
        System.out.println("Cantidad total de páginas en la biblioteca: " + totalPages);
    }
}
