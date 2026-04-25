package com.smartlibrary.view;

import com.smartlibrary.controller.BookController;
import com.smartlibrary.controller.BorrowController;
import com.smartlibrary.controller.MemberController;
import com.smartlibrary.model.Book;
import com.smartlibrary.model.BorrowRecord;
import com.smartlibrary.model.Member;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class BorrowPanel extends TexturedPanel {
  private BookController bookController;
  private MemberController memberController;
  private BorrowController borrowController;
  private JComboBox<Book> bookCombo;
  private JComboBox<Member> memberCombo;
  private JTable table;
  private DefaultTableModel model;
  private PulsingLabel overdueLabel;

  public BorrowPanel() {
    super(new BorderLayout(16, 16));
    setBorder(new EmptyBorder(14, 14, 14, 14));

    bookController = new BookController();
    memberController = new MemberController();
    borrowController = new BorrowController();

    JLabel title = new JLabel("🕮 Borrow / Return Records");
    title.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(26f));
    title.setForeground(SoftAcademiaTheme.TEXT);
    JLabel subtitle = new JLabel("A calm checkout desk for your academic collection. Track loans and returns.");
    subtitle.setFont(SoftAcademiaTheme.BODY_FONT.deriveFont(14f));
    subtitle.setForeground(new java.awt.Color(0x6F5E4A));

    JPanel header = new TexturedPanel(new BorderLayout(8, 8));
    header.setBackground(new java.awt.Color(0xF6EEE4));
    header.setBorder(new EmptyBorder(16, 16, 16, 16));
    header.add(title, BorderLayout.NORTH);
    header.add(subtitle, BorderLayout.SOUTH);

    JPanel controlPanel = new TexturedPanel(new GridBagLayout());
    controlPanel.setBackground(SoftAcademiaTheme.PANEL_BACKGROUND);
    controlPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(8, 8, 8, 8);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel bookLabel = new JLabel("Book");
    SoftAcademiaTheme.styleLabel(bookLabel);
    JLabel memberLabel = new JLabel("Member");
    SoftAcademiaTheme.styleLabel(memberLabel);
    bookCombo = new JComboBox<>();
    memberCombo = new JComboBox<>();
    bookCombo.setBackground(Color.WHITE);
    memberCombo.setBackground(Color.WHITE);
    bookCombo.setPreferredSize(new java.awt.Dimension(150, 28));
    memberCombo.setPreferredSize(new java.awt.Dimension(150, 28));
    SoftAcademiaTheme.styleComponent(bookCombo);
    SoftAcademiaTheme.styleComponent(memberCombo);

    gbc.gridx = 0;
    gbc.gridy = 0;
    controlPanel.add(bookLabel, gbc);
    gbc.gridx = 1;
    controlPanel.add(bookCombo, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    controlPanel.add(memberLabel, gbc);
    gbc.gridx = 1;
    controlPanel.add(memberCombo, gbc);

    RoundedButton borrow = new RoundedButton("Borrow Book");
    RoundedButton returnButton = new RoundedButton("Return Book");
    gbc.gridx = 0;
    gbc.gridy = 2;
    controlPanel.add(borrow, gbc);
    gbc.gridx = 1;
    controlPanel.add(returnButton, gbc);

    overdueLabel = new PulsingLabel("• Overdue: 0");
    overdueLabel.setVisible(false);

    JPanel topPanel = new TexturedPanel(new BorderLayout(12, 12));
    topPanel.setBackground(SoftAcademiaTheme.PANEL_BACKGROUND);
    topPanel.add(header, BorderLayout.NORTH);
    JPanel statusRow = new TexturedPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
    statusRow.setBackground(SoftAcademiaTheme.PANEL_BACKGROUND);
    statusRow.add(overdueLabel);
    topPanel.add(statusRow, BorderLayout.CENTER);
    topPanel.add(controlPanel, BorderLayout.SOUTH);

    model = new DefaultTableModel(new Object[] {"ID", "Book", "Member", "Borrow Date", "Returned"}, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    table = new HoverTable(model);
    table.setDefaultRenderer(Object.class, new StripedTableCellRenderer());
    table.setShowGrid(false);
    table.setBackground(SoftAcademiaTheme.PANEL_BACKGROUND);
    table.getTableHeader().setBackground(SoftAcademiaTheme.HEADER_BACKGROUND);
    table.getTableHeader().setForeground(SoftAcademiaTheme.SIDEBAR_TEXT);
    table.getTableHeader().setFont(SoftAcademiaTheme.BODY_FONT.deriveFont(14f));

    add(topPanel, BorderLayout.NORTH);
    add(new JScrollPane(table), BorderLayout.CENTER);

    loadOptions();
    loadRecords();
    borrow.addActionListener(e -> borrowBook());
    returnButton.addActionListener(e -> returnBook());
  }

  public void loadOptions() {
    bookCombo.removeAllItems();
    List<Book> books = bookController.findAvailable();
    for (Book book : books) {
      bookCombo.addItem(book);
    }
    memberCombo.removeAllItems();
    List<Member> members = memberController.findAll();
    for (Member member : members) {
      memberCombo.addItem(member);
    }
  }

  private void loadRecords() {
    model.setRowCount(0);
    List<BorrowRecord> records = borrowController.findAll();
    int overdueCount = 0;
    Date now = new Date();
    Calendar cutoff = Calendar.getInstance();
    cutoff.setTime(now);
    cutoff.add(Calendar.DATE, -30);
    for (BorrowRecord record : records) {
      model.addRow(new Object[] {
          record.getId(),
          record.getBook().getTitle(),
          record.getMember().getName(),
          record.getBorrowDate(),
          record.isReturned() ? "Yes" : "No"});
      if (!record.isReturned() && record.getBorrowDate() != null && record.getBorrowDate().before(cutoff.getTime())) {
        overdueCount++;
      }
    }
    overdueLabel.setText("• Overdue: " + overdueCount);
    overdueLabel.setVisible(overdueCount > 0);
  }

  private void borrowBook() {
    Book book = (Book) bookCombo.getSelectedItem();
    Member member = (Member) memberCombo.getSelectedItem();
    if (book == null || member == null) {
      JOptionPane.showMessageDialog(this, "Select a book and a member");
      return;
    }
    borrowController.borrow(book, member);
    loadOptions();
    loadRecords();
  }

  private void returnBook() {
    int row = table.getSelectedRow();
    if (row < 0) {
      JOptionPane.showMessageDialog(this, "Select a record first");
      return;
    }
    int modelRow = table.convertRowIndexToModel(row);
    int id = (int) model.getValueAt(modelRow, 0);
    List<BorrowRecord> records = borrowController.findAll();
    BorrowRecord selected = null;
    for (BorrowRecord record : records) {
      if (record.getId() == id) {
        selected = record;
        break;
      }
    }
    if (selected == null) {
      JOptionPane.showMessageDialog(this, "Record not found");
      return;
    }
    if (selected.isReturned()) {
      JOptionPane.showMessageDialog(this, "Book already returned");
      return;
    }
    borrowController.returnBook(selected);
    loadOptions();
    loadRecords();
  }
}
