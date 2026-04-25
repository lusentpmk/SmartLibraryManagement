package com.smartlibrary.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;

public class RoundedTextField extends JTextField {
  public RoundedTextField() {
    setOpaque(false);
    setBackground(Color.WHITE);
    setForeground(Color.BLACK);
    setPreferredSize(new Dimension(150, 28));
    setMinimumSize(new Dimension(120, 26));
    SoftAcademiaTheme.styleTextField(this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.WHITE);
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
    super.paintComponent(g);
    g2.dispose();
  }
}
