package com.smartlibrary.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DashboardView extends javax.swing.JFrame {
  private CardLayout cardLayout;
  private JPanel contentPanel;
  private DashboardPanel dashboardPanel;
  private BorrowPanel borrowPanel;

  public DashboardView() {
    setTitle("Aesthetic Library");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(980, 640);
    setExtendedState(MAXIMIZED_BOTH);
    setMinimumSize(new Dimension(980, 640));
    setLocationRelativeTo(null);

    TexturedPanel root = new TexturedPanel(new BorderLayout(16, 16));
    root.setBorder(new EmptyBorder(18, 18, 18, 18));
    root.setBackground(SoftAcademiaTheme.BACKGROUND);
    setContentPane(root);

    JPanel sidebar = new TexturedPanel(new GridBagLayout());
    sidebar.setBackground(SoftAcademiaTheme.SIDEBAR_BACKGROUND);
    sidebar.setPreferredSize(new Dimension(220, 0));
    sidebar.setBorder(new EmptyBorder(12, 12, 12, 12));

    JLabel brand = new JLabel("<html><div style='text-align:center'>Aesthetic<br/>Library</div></html>");
    brand.setFont(SoftAcademiaTheme.HEADER_FONT);
    brand.setForeground(SoftAcademiaTheme.SIDEBAR_TEXT);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(6, 6, 16, 6);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1;
    sidebar.add(brand, gbc);

    RoundedButton dashboardButton = new RoundedButton("🏠 Dashboard");
    RoundedButton bookButton = new RoundedButton("📚 Book Management");
    RoundedButton memberButton = new RoundedButton("👥 Member Management");
    RoundedButton borrowButton = new RoundedButton("🕮 Borrow / Return");
    RoundedButton exitButton = new RoundedButton("🚪 Exit");

    gbc.insets = new Insets(8, 6, 8, 6);
    gbc.gridy++;
    sidebar.add(dashboardButton, gbc);
    gbc.gridy++;
    sidebar.add(bookButton, gbc);
    gbc.gridy++;
    sidebar.add(memberButton, gbc);
    gbc.gridy++;
    sidebar.add(borrowButton, gbc);
    gbc.gridy++;
    sidebar.add(exitButton, gbc);
    gbc.gridy++;
    gbc.weighty = 1;
    sidebar.add(new BookShelfPanel(), gbc);

    cardLayout = new CardLayout();
    contentPanel = new JPanel(cardLayout);
    contentPanel.add(dashboardPanel = new DashboardPanel(), "dashboard");
    contentPanel.add(new BookPanel(), "books");
    contentPanel.add(new MemberPanel(), "members");
    contentPanel.add(borrowPanel = new BorrowPanel(), "borrow");

    JPanel header = new TexturedPanel(new BorderLayout(10, 8));
    header.setBackground(new java.awt.Color(0xF6EEE4));
    header.setBorder(new EmptyBorder(20, 20, 20, 20));
    JLabel title = new JLabel("📖 Aesthetic Library Management");
    title.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(28f));
    title.setForeground(SoftAcademiaTheme.TEXT);
    JLabel subtitle = new JLabel("Navigate collections, members, and loans with calm, layered rhythm.");
    subtitle.setFont(SoftAcademiaTheme.BODY_FONT.deriveFont(15f));
    subtitle.setForeground(new java.awt.Color(0x6F5E4A));
    
    JPanel leftHeader = new javax.swing.JPanel(new BorderLayout(8, 0));
    leftHeader.setOpaque(false);
    leftHeader.add(title, BorderLayout.NORTH);
    leftHeader.add(subtitle, BorderLayout.SOUTH);
    
    JPanel rightDecorator = new TexturedPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 12, 8));
    rightDecorator.setOpaque(false);
    JLabel decoration1 = new JLabel("📚");
    decoration1.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(32f));
    JLabel decoration2 = new JLabel("✨");
    decoration2.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(32f));
    rightDecorator.add(decoration1);
    rightDecorator.add(decoration2);
    
    header.add(leftHeader, BorderLayout.WEST);
    header.add(rightDecorator, BorderLayout.EAST);

    root.add(header, BorderLayout.NORTH);
    root.add(sidebar, BorderLayout.WEST);
    root.add(contentPanel, BorderLayout.CENTER);

    dashboardButton.addActionListener(e -> switchPanel("dashboard"));
    bookButton.addActionListener(e -> switchPanel("books"));
    memberButton.addActionListener(e -> switchPanel("members"));
    borrowButton.addActionListener(e -> switchPanel("borrow"));
    exitButton.addActionListener(e -> System.exit(0));

    switchPanel("dashboard");
  }

  private void switchPanel(String name) {
    cardLayout.show(contentPanel, name);
    if ("borrow".equals(name)) {
      borrowPanel.loadOptions();
    }
    if ("dashboard".equals(name)) {
      dashboardPanel.refreshStats();
    }
  }
}
