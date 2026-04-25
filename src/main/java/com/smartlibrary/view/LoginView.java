package com.smartlibrary.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class LoginView extends javax.swing.JFrame {
  private RoundedTextField usernameField;
  private JPasswordField passwordField;
  private RoundedButton loginButton;

  public LoginView() {
    setTitle("Aesthetic Library Login");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(520, 420);
    setMinimumSize(new java.awt.Dimension(520, 420));
    setLocationRelativeTo(null);

    TexturedPanel container = new TexturedPanel(new BorderLayout(14, 14));
    container.setBorder(new EmptyBorder(24, 24, 24, 24));
    container.setBackground(SoftAcademiaTheme.BACKGROUND);

    JPanel headerPanel = new TexturedPanel(new BorderLayout(10, 8));
    headerPanel.setBackground(new java.awt.Color(0xF6EEE4));
    headerPanel.setBorder(new EmptyBorder(18, 18, 18, 18));
    
    JPanel leftHeader = new javax.swing.JPanel(new BorderLayout(0, 4));
    leftHeader.setOpaque(false);
    JLabel title = new JLabel("📖 Aesthetic Library");
    title.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(26f));
    title.setForeground(SoftAcademiaTheme.TEXT);
    JLabel subtitle = new JLabel("A calm entry to your academic study space");
    subtitle.setFont(SoftAcademiaTheme.BODY_FONT.deriveFont(13f));
    subtitle.setForeground(SoftAcademiaTheme.ACCENT);
    leftHeader.add(title, BorderLayout.NORTH);
    leftHeader.add(subtitle, BorderLayout.SOUTH);
    
    JPanel rightHeader = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 0));
    rightHeader.setOpaque(false);
    JLabel icon1 = new JLabel("📚");
    icon1.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(28f));
    JLabel icon2 = new JLabel("✨");
    icon2.setFont(SoftAcademiaTheme.HEADER_FONT.deriveFont(28f));
    rightHeader.add(icon1);
    rightHeader.add(icon2);
    
    headerPanel.add(leftHeader, BorderLayout.WEST);
    headerPanel.add(rightHeader, BorderLayout.EAST);

    JPanel form = new TexturedPanel(new java.awt.GridBagLayout());
    form.setBackground(SoftAcademiaTheme.PANEL_BACKGROUND);
    form.setBorder(new EmptyBorder(18, 18, 18, 18));
    
    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
    gbc.insets = new java.awt.Insets(10, 10, 10, 10);
    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gbc.gridwidth = 2;

    JLabel userLabel = new JLabel("Username");
    SoftAcademiaTheme.styleLabel(userLabel);
    usernameField = new RoundedTextField();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    form.add(userLabel, gbc);
    gbc.gridx = 1;
    form.add(usernameField, gbc);
    
    JLabel passwordLabel = new JLabel("Password");
    SoftAcademiaTheme.styleLabel(passwordLabel);
    passwordField = new JPasswordField();
    passwordField.setFont(SoftAcademiaTheme.BODY_FONT);
    passwordField.setOpaque(false);
    passwordField.setBorder(javax.swing.BorderFactory.createCompoundBorder(
        javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0xD6C4A3), 1),
        javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10)));
    gbc.gridx = 0;
    gbc.gridy = 1;
    form.add(passwordLabel, gbc);
    gbc.gridx = 1;
    form.add(passwordField, gbc);
    
    loginButton = new RoundedButton("Login");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    form.add(loginButton, gbc);

    container.add(headerPanel, BorderLayout.NORTH);
    container.add(form, BorderLayout.CENTER);
    setContentPane(container);
  }

  public String getUsername() {
    return usernameField.getText().trim();
  }

  public String getPassword() {
    return new String(passwordField.getPassword()).trim();
  }

  public void setLoginAction(java.awt.event.ActionListener action) {
    loginButton.addActionListener(action);
  }

  public void showError(String message) {
    javax.swing.JOptionPane.showMessageDialog(this, message);
  }

  public void hideWindow() {
    setVisible(false);
    dispose();
  }
}
