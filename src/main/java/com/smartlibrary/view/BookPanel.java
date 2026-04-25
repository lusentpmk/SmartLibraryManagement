package com.smartlibrary.view;

import com.smartlibrary.controller.BookController;
import com.smartlibrary.model.Book;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class BookPanel extends TexturedPanel {
  private BookController controller;
  private JTable table;
  private DefaultTableModel model;
  private TableRowSorter<DefaultTableModel> sorter;
  private RoundedTextField titleField;
  private RoundedTextField authorField;
  private RoundedTextField yearField;
  private RoundedTextField searchField;

  public BookPanel() {
    super(new BorderLayout(16, 16));
    setBorder(new EmptyBorder(14, 14, 14, 14));

    controller = new BookController();

    JLabel title = new JLabel("📚 Book Collection");
    title.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(26f));
    title.setForeground(SoftAcademiaTheme.TEXT);
    JLabel subtitle = new JLabel("Shelve titles with quiet confidence. Browse, add, and manage with ease.");
    subtitle.setFont(SoftAcademiaTheme.BODY_FONT.deriveFont(14f));
    subtitle.setForeground(new java.awt.Color(0x6F5E4A));

    JPanel header = new TexturedPanel(new BorderLayout(8, 8));
    header.setBackground(new java.awt.Color(0xF6EEE4));
    header.setBorder(new EmptyBorder(16, 16, 16, 16));
    header.add(title, BorderLayout.NORTH);
    header.add(subtitle, BorderLayout.SOUTH);

    JPanel top = new TexturedPanel(new GridBagLayout());
    top.setBackground(SoftAcademiaTheme.PANEL_BACKGROUND);
    top.setBorder(new EmptyBorder(12, 12, 12, 12));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(8, 8, 8, 8);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel titleLabel = new JLabel("Title");
    SoftAcademiaTheme.styleLabel(titleLabel);
    titleField = new RoundedTextField();
    gbc.gridx = 0;
    gbc.gridy = 0;
    top.add(titleLabel, gbc);
    gbc.gridx = 1;
    top.add(titleField, gbc);

    JLabel authorLabel = new JLabel("Author");
    SoftAcademiaTheme.styleLabel(authorLabel);
    authorField = new RoundedTextField();
    gbc.gridx = 0;
    gbc.gridy = 1;
    top.add(authorLabel, gbc);
    gbc.gridx = 1;
    top.add(authorField, gbc);

    JLabel yearLabel = new JLabel("Year");
    SoftAcademiaTheme.styleLabel(yearLabel);
    yearField = new RoundedTextField();
    gbc.gridx = 0;
    gbc.gridy = 2;
    top.add(yearLabel, gbc);
    gbc.gridx = 1;
    top.add(yearField, gbc);

    RoundedButton save = new RoundedButton("Save Book");
    RoundedButton delete = new RoundedButton("Delete Book");
    gbc.gridx = 0;
    gbc.gridy = 3;
    top.add(save, gbc);
    gbc.gridx = 1;
    top.add(delete, gbc);

    JLabel searchLabel = new JLabel("Search");
    SoftAcademiaTheme.styleLabel(searchLabel);
    searchField = new RoundedTextField();
    gbc.gridx = 0;
    gbc.gridy = 4;
    top.add(searchLabel, gbc);
    gbc.gridx = 1;
    top.add(searchField, gbc);

    model = new DefaultTableModel(new Object[] {"ID", "Title", "Author", "Year", "Available"}, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    table = new HoverTable(model);
    table.setDefaultRenderer(Object.class, new StripedTableCellRenderer());
    sorter = new TableRowSorter<>(model);
    table.setRowSorter(sorter);
    table.setShowGrid(false);
    table.setBackground(SoftAcademiaTheme.PANEL_BACKGROUND);
    table.getTableHeader().setBackground(SoftAcademiaTheme.HEADER_BACKGROUND);
    table.getTableHeader().setForeground(SoftAcademiaTheme.SIDEBAR_TEXT);
    table.getTableHeader().setFont(SoftAcademiaTheme.BODY_FONT.deriveFont(14f));

    JScrollPane formScroll = new JScrollPane(top);
    JScrollPane tableScroll = new JScrollPane(table);
    
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, formScroll, tableScroll);
    splitPane.setDividerLocation(0.45);
    splitPane.setResizeWeight(0.45);
    splitPane.setContinuousLayout(true);

    add(header, BorderLayout.NORTH);
    add(splitPane, BorderLayout.CENTER);

    loadBooks();
    save.addActionListener(e -> saveBook());
    delete.addActionListener(e -> deleteBook());
    searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
      public void insertUpdate(javax.swing.event.DocumentEvent e) {
        applyFilter();
      }
      public void removeUpdate(javax.swing.event.DocumentEvent e) {
        applyFilter();
      }
      public void changedUpdate(javax.swing.event.DocumentEvent e) {
        applyFilter();
      }
    });
  }

  private void loadBooks() {
    model.setRowCount(0);
    List<Book> books = controller.findAll();
    for (Book book : books) {
      model.addRow(new Object[] {book.getId(), book.getTitle(), book.getAuthor(), book.getYear(), book.isAvailable() ? "Yes" : "No"});
    }
  }

  private void saveBook() {
    String title = titleField.getText().trim();
    String author = authorField.getText().trim();
    String year = yearField.getText().trim();
    if (title.isEmpty() || author.isEmpty() || year.isEmpty()) {
      JOptionPane.showMessageDialog(this, "All fields are required");
      return;
    }
    Book book = new Book();
    book.setTitle(title);
    book.setAuthor(author);
    book.setYear(year);
    controller.save(book);
    loadBooks();
    titleField.setText("");
    authorField.setText("");
    yearField.setText("");
  }

  private void deleteBook() {
    int row = table.getSelectedRow();
    if (row < 0) {
      JOptionPane.showMessageDialog(this, "Select a book first");
      return;
    }
    int id = (int) table.getValueAt(table.convertRowIndexToModel(row), 0);
    try {
      controller.delete(id);
      loadBooks();
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "Unable to delete this book. It may still be referenced by borrow history.");
    }
  }

  private void applyFilter() {
    String text = searchField.getText();
    if (text.trim().isEmpty()) {
      sorter.setRowFilter(null);
    } else {
      sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + text));
    }
  }
}
