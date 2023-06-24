import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryGUI extends JFrame {
    private Library library;
    private JTextField bookNameField;
    private JTextField bookIdField;
    private JButton addButton;
    private JButton removeButton;
    private JButton searchButton;
    private JTextArea resultArea;

    public LibraryGUI(Library library) {
        this.library = library;

        setTitle("Biblioteca");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Biblioteca");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel nameLabel = new JLabel("Nombre del libro:");
        bookNameField = new JTextField(15);

        JLabel idLabel = new JLabel("ID del libro:");
        bookIdField = new JTextField(8);

        addButton = new JButton("Agregar");
        removeButton = new JButton("Eliminar");
        searchButton = new JButton("Buscar");
        addButton.setPreferredSize(new Dimension(80, 25));
        removeButton.setPreferredSize(new Dimension(80, 25));
        searchButton.setPreferredSize(new Dimension(80, 25));

        resultArea = new JTextArea(6, 25);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel();
        topPanel.add(titleLabel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        centerPanel.add(bookNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        centerPanel.add(bookIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(addButton, gbc);
        gbc.gridx = 1;
        centerPanel.add(removeButton, gbc);
        gbc.gridx = 2;
        centerPanel.add(searchButton, gbc);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeBook();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
    }

    private void addBook() {
        String name = bookNameField.getText();
        String idText = bookIdField.getText();

        if (!name.isEmpty() && !idText.isEmpty()) {
            int id = Integer.parseInt(idText);
            Book book = new Book(id, name, 0);
            library.addBook(book);
            resultArea.setText("El libro ha sido agregado.");
        } else {
            resultArea.setText("Por favor, ingrese el nombre y el ID del libro.");
        }
    }

    private void removeBook() {
        String name = bookNameField.getText();
        String idText = bookIdField.getText();

        if (!name.isEmpty() || !idText.isEmpty()) {
            if (!idText.isEmpty()) {
                int id = Integer.parseInt(idText);
                if (library.removeBookById(id)) {
                    resultArea.setText("El libro ha sido eliminado por ID.");
                    return;
                }
            }

            if (!name.isEmpty()) {
                if (library.removeBookByName(name)) {
                    resultArea.setText("El libro ha sido eliminado por nombre.");
                    return;
                }
            }

            resultArea.setText("No se encontró un libro con el nombre o ID especificado.");
        } else {
            resultArea.setText("Por favor, ingrese el nombre o el ID del libro.");
        }
    }

    private void searchBook() {
        String name = bookNameField.getText();
        String idText = bookIdField.getText();

        if (!name.isEmpty() || !idText.isEmpty()) {
            if (!idText.isEmpty()) {
                int id = Integer.parseInt(idText);
                Book book = library.searchBookById(id);
                if (book != null) {
                    resultArea.setText("Libro encontrado (por ID): " + book.getNombre());
                    return;
                }
            }

            if (!name.isEmpty()) {
                Book book = library.searchBookByName(name);
                if (book != null) {
                    resultArea.setText("Libro encontrado (por nombre): " + book.getNombre());
                    return;
                }
            }

            resultArea.setText("No se encontró un libro con el nombre o ID especificado.");
        } else {
            resultArea.setText("Por favor, ingrese el nombre o el ID del libro.");
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book(1, "Libro 1", 100));
        library.addBook(new Book(2, "Libro 2", 200));
        library.addBook(new Book(3, "Libro 3", 150));

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LibraryGUI libraryGUI = new LibraryGUI(library);
                libraryGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                libraryGUI.setVisible(true);
            }
        });
    }
}
