package com.smartlibrary.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class BookShelfPanel extends JPanel {
  public BookShelfPanel() {
    setOpaque(false);
    setPreferredSize(new Dimension(220, 180));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    int width = getWidth();
    int height = getHeight();

    // soft shelf background
    g2.setColor(new Color(0x3F2F24));
    g2.fillRoundRect(0, 10, width, height - 10, 20, 20);

    // shelf planks
    g2.setColor(new Color(0x7B5C45));
    int plankHeight = 18;
    for (int y = height - 52; y < height; y += plankHeight + 4) {
      g2.fillRect(12, y, width - 24, plankHeight);
    }

    // books
    int bookWidth = 22;
    int bookHeight = 60;
    int x = 20;
    Color[] bookColors = { new Color(0xA65F4A), new Color(0x8F7A68), new Color(0xC68E6A), new Color(0x7F5D4C) };
    for (int i = 0; i < 5; i++) {
      g2.setColor(bookColors[i % bookColors.length]);
      g2.fillRoundRect(x, height - 80 - (i % 2) * 8, bookWidth, bookHeight + (i % 2) * 8, 6, 6);
      x += bookWidth + 12;
    }

    // vines
    g2.setColor(new Color(0x8AA67B));
    int vineY = 14;
    for (int i = 0; i < 3; i++) {
      int cx = 26 + i * 56;
      drawVine(g2, cx, vineY, 3 + i);
    }

    g2.dispose();
  }

  private void drawVine(Graphics2D g2, int x, int y, int curls) {
    for (int i = 0; i < curls; i++) {
      int yy = y + i * 18;
      g2.drawArc(x, yy, 16, 24, 180, 180);
      g2.drawArc(x + 8, yy + 12, 16, 24, 180, 180);
    }
  }
}
