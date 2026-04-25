package com.smartlibrary.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SoftAcademiaTheme {
  public static final Color BACKGROUND = new Color(0xF3E9D2);
  public static final Color PANEL_BACKGROUND = new Color(0xF7EFE0);
  public static final Color SIDEBAR_BACKGROUND = new Color(0x46362A);
  public static final Color SIDEBAR_TEXT = new Color(0xF4E7D4);
  public static final Color HEADER_BACKGROUND = new Color(0x6B5042);
  public static final Color BUTTON_BACKGROUND = new Color(0x6B2F34);
  public static final Color BUTTON_HOVER = new Color(0x8D4C4F);
  public static final Color BUTTON_TEXT = new Color(0xF9EFE3);
  public static final Color ACCENT = new Color(0x7C8D6F);
  public static final Color SUCCESS = new Color(0x7C987B);
  public static final Color ERROR = new Color(0x8C2F3F);
  public static final Color TEXT = new Color(0x3A2F28);
  public static final Color TABLE_STRIPE = new Color(0xF1E7D8);
  public static final Color TABLE_ALT = new Color(0xFBF4E8);
  public static final Font HEADER_FONT = new Font("Serif", Font.BOLD, 22);
  public static final Font TITLE_FONT = new Font("Serif", Font.BOLD, 18);
  public static final Font BODY_FONT = new Font("SansSerif", Font.PLAIN, 14);
  public static final Font LABEL_FONT = new Font("SansSerif", Font.PLAIN, 13);

  private static final Border FIELD_BORDER = BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(new Color(0xD6C4A3), 1),
      BorderFactory.createEmptyBorder(8, 10, 8, 10));

  public static void styleComponent(JComponent component) {
    component.setFont(BODY_FONT);
    component.setForeground(TEXT);
  }

  public static void styleButton(JButton button) {
    button.setFont(BODY_FONT.deriveFont(Font.BOLD));
    button.setForeground(BUTTON_TEXT);
    button.setBackground(BUTTON_BACKGROUND);
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
  }

  public static void styleTextField(JTextField field) {
    field.setFont(BODY_FONT);
    field.setForeground(Color.BLACK);
    field.setBackground(Color.WHITE);
    field.setOpaque(false);
    field.setBorder(FIELD_BORDER);
  }

  public static void styleLabel(JLabel label) {
    label.setFont(LABEL_FONT);
    label.setForeground(TEXT);
  }
}
