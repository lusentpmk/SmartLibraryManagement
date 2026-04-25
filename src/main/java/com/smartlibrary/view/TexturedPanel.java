package com.smartlibrary.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class TexturedPanel extends JPanel {
  public TexturedPanel() {
    setOpaque(true);
    setBackground(SoftAcademiaTheme.PANEL_BACKGROUND);
  }

  public TexturedPanel(java.awt.LayoutManager layout) {
    super(layout);
    setOpaque(true);
    setBackground(SoftAcademiaTheme.PANEL_BACKGROUND);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    int width = getWidth();
    int height = getHeight();
    g2.setColor(new Color(255, 255, 255, 24));
    for (int y = 0; y < height; y += 16) {
      g2.drawLine(0, y, width, y);
    }
    g2.setColor(new Color(255, 255, 255, 16));
    for (int x = 0; x < width; x += 16) {
      g2.drawLine(x, 0, x, height);
    }
    g2.dispose();
  }
}
