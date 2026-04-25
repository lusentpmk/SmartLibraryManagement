package com.smartlibrary.view;

import com.smartlibrary.controller.BookController;
import com.smartlibrary.controller.BorrowController;
import com.smartlibrary.controller.MemberController;
import com.smartlibrary.model.Book;
import com.smartlibrary.model.BorrowRecord;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DashboardPanel extends TexturedPanel {
  private final BookController bookController;
  private final MemberController memberController;
  private final BorrowController borrowController;
  private final JLabel totalBooksLabel;
  private final JLabel borrowedBooksLabel;
  private final JLabel returnedBooksLabel;
  private final JLabel totalUsersLabel;

  public DashboardPanel() {
    super(new BorderLayout(24, 24));
    setBackground(SoftAcademiaTheme.BACKGROUND);
    setBorder(new EmptyBorder(20, 20, 20, 20));

    bookController = new BookController();
    memberController = new MemberController();
    borrowController = new BorrowController();

    JPanel intro = new TexturedPanel(new BorderLayout(8, 8));
    intro.setBackground(new Color(0xF6EEE4));
    intro.setBorder(new EmptyBorder(24, 24, 24, 24));

    JLabel title = new JLabel("Library Dashboard");
    title.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(34f));
    title.setForeground(SoftAcademiaTheme.TEXT);

    JLabel subtitle = new JLabel("A warm overview of your collection, lending activity, and academic community.");
    subtitle.setFont(SoftAcademiaTheme.BODY_FONT.deriveFont(16f));
    subtitle.setForeground(new Color(0x6F5E4A));

    JLabel note = new JLabel("Refresh the dashboard any time by returning here.");
    note.setFont(SoftAcademiaTheme.LABEL_FONT.deriveFont(Font.ITALIC));
    note.setForeground(SoftAcademiaTheme.ACCENT);

    intro.add(title, BorderLayout.NORTH);
    intro.add(subtitle, BorderLayout.CENTER);
    intro.add(note, BorderLayout.SOUTH);

    JPanel statsGrid = new TexturedPanel(new GridBagLayout());
    statsGrid.setOpaque(false);
    statsGrid.setBorder(new EmptyBorder(8, 8, 8, 8));

    totalBooksLabel = createValueLabel("0", SoftAcademiaTheme.TEXT);
    borrowedBooksLabel = createValueLabel("0", new Color(0x8A4B4F));
    returnedBooksLabel = createValueLabel("0", new Color(0x4C6B4D));
    totalUsersLabel = createValueLabel("0", SoftAcademiaTheme.TEXT);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(18, 18, 18, 18);
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1;
    gbc.weighty = 1;

    gbc.gridx = 0;
    gbc.gridy = 0;
    statsGrid.add(createStatPanel(totalBooksLabel, "Total Books", "📚", new Color(0xE7C6A3)), gbc);

    gbc.gridx = 1;
    statsGrid.add(createStatPanel(borrowedBooksLabel, "Books Borrowed", "🕮", new Color(0xD8AEC1)), gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    statsGrid.add(createStatPanel(returnedBooksLabel, "Books Returned", "✅", new Color(0xC9D8C5)), gbc);

    gbc.gridx = 1;
    statsGrid.add(createStatPanel(totalUsersLabel, "Users", "👥", new Color(0xE7D6BF)), gbc);

    add(intro, BorderLayout.NORTH);
    add(statsGrid, BorderLayout.CENTER);

    refreshStats();
  }

  private JLabel createValueLabel(String value, Color color) {
    JLabel label = new JLabel(value, JLabel.CENTER);
    label.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(42f));
    label.setForeground(color);
    return label;
  }

  private JPanel createStatPanel(JLabel valueLabel, String caption, String icon, Color panelColor) {
    JPanel panel = new TexturedPanel(new BorderLayout(12, 12));
    panel.setBackground(panelColor);
    panel.setBorder(new EmptyBorder(20, 20, 20, 20));

    JLabel iconLabel = new JLabel(icon, JLabel.LEFT);
    iconLabel.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(26f));
    iconLabel.setForeground(new Color(0x5A4435));

    JLabel captionLabel = new JLabel(caption);
    captionLabel.setFont(SoftAcademiaTheme.BODY_FONT.deriveFont(16f));
    captionLabel.setForeground(new Color(0x5A4435));

    JPanel header = new TexturedPanel(new BorderLayout());
    header.setOpaque(false);
    header.add(iconLabel, BorderLayout.WEST);
    header.add(captionLabel, BorderLayout.SOUTH);

    panel.add(header, BorderLayout.NORTH);
    panel.add(valueLabel, BorderLayout.CENTER);
    return panel;
  }

  public void refreshStats() {
    List<Book> books = bookController.findAll();
    List<BorrowRecord> records = borrowController.findAll();

    int totalBooks = books.size();
    int totalUsers = memberController.findAll().size();
    long booksBorrowed = records.stream().filter(r -> !r.isReturned()).count();
    long booksReturned = records.stream().filter(BorrowRecord::isReturned).count();

    totalBooksLabel.setText(String.valueOf(totalBooks));
    totalUsersLabel.setText(String.valueOf(totalUsers));
    borrowedBooksLabel.setText(String.valueOf(booksBorrowed));
    returnedBooksLabel.setText(String.valueOf(booksReturned));
  }
}
