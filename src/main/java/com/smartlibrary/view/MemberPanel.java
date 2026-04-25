package com.smartlibrary.view;

import com.smartlibrary.controller.MemberController;
import com.smartlibrary.model.Member;
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
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class MemberPanel extends TexturedPanel {
  private MemberController controller;
  private JTable table;
  private DefaultTableModel model;
  private RoundedTextField nameField;
  private RoundedTextField emailField;
  private RoundedTextField phoneField;

  public MemberPanel() {
    super(new BorderLayout(16, 16));
    setBorder(new EmptyBorder(14, 14, 14, 14));

    controller = new MemberController();

    JLabel title = new JLabel("👥 Member Directory");
    title.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(26f));
    title.setForeground(SoftAcademiaTheme.TEXT);
    JLabel subtitle = new JLabel("Curate your academic community with care. Add and manage members with ease.");
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

    JLabel nameLabel = new JLabel("Name");
    SoftAcademiaTheme.styleLabel(nameLabel);
    nameField = new RoundedTextField();
    gbc.gridx = 0;
    gbc.gridy = 0;
    top.add(nameLabel, gbc);
    gbc.gridx = 1;
    top.add(nameField, gbc);

    JLabel emailLabel = new JLabel("Email");
    SoftAcademiaTheme.styleLabel(emailLabel);
    emailField = new RoundedTextField();
    gbc.gridx = 0;
    gbc.gridy = 1;
    top.add(emailLabel, gbc);
    gbc.gridx = 1;
    top.add(emailField, gbc);

    JLabel phoneLabel = new JLabel("Phone");
    SoftAcademiaTheme.styleLabel(phoneLabel);
    phoneField = new RoundedTextField();
    gbc.gridx = 0;
    gbc.gridy = 2;
    top.add(phoneLabel, gbc);
    gbc.gridx = 1;
    top.add(phoneField, gbc);

    RoundedButton save = new RoundedButton("Save Member");
    RoundedButton delete = new RoundedButton("Delete Member");
    gbc.gridx = 0;
    gbc.gridy = 3;
    top.add(save, gbc);
    gbc.gridx = 1;
    top.add(delete, gbc);

    model = new DefaultTableModel(new Object[] {"ID", "Name", "Email", "Phone"}, 0) {
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

    JScrollPane formScroll = new JScrollPane(top);
    JScrollPane tableScroll = new JScrollPane(table);
    
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, formScroll, tableScroll);
    splitPane.setDividerLocation(0.45);
    splitPane.setResizeWeight(0.45);
    splitPane.setContinuousLayout(true);

    add(header, BorderLayout.NORTH);
    add(splitPane, BorderLayout.CENTER);

    loadMembers();
    save.addActionListener(e -> saveMember());
    delete.addActionListener(e -> deleteMember());
  }

  private void loadMembers() {
    model.setRowCount(0);
    List<Member> members = controller.findAll();
    for (Member member : members) {
      model.addRow(new Object[] {member.getId(), member.getName(), member.getEmail(), member.getPhone()});
    }
  }

  private void saveMember() {
    String name = nameField.getText().trim();
    String email = emailField.getText().trim();
    String phone = phoneField.getText().trim();
    if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
      JOptionPane.showMessageDialog(this, "All fields are required");
      return;
    }
    Member member = new Member();
    member.setName(name);
    member.setEmail(email);
    member.setPhone(phone);
    controller.save(member);
    loadMembers();
    nameField.setText("");
    emailField.setText("");
    phoneField.setText("");
  }

  private void deleteMember() {
    int row = table.getSelectedRow();
    if (row < 0) {
      JOptionPane.showMessageDialog(this, "Select a member first");
      return;
    }
    int id = (int) table.getValueAt(row, 0);
    try {
      controller.delete(id);
      loadMembers();
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "Unable to delete this member. It may still be referenced by borrow history.");
    }
  }
}
