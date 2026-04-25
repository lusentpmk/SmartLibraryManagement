package com.smartlibrary.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class RoundedButton extends JButton {
  private boolean hovered;

  public RoundedButton(String text) {
    super(text);
    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    setContentAreaFilled(false);
    setFocusPainted(false);
    setBorderPainted(false);
    setOpaque(false);
    SoftAcademiaTheme.styleButton(this);
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        hovered = true;
        repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        hovered = false;
        repaint();
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    float scale = hovered ? 1.02f : 1.0f;
    int width = getWidth();
    int height = getHeight();
    int tx = (int) ((width - width * scale) / 2);
    int ty = (int) ((height - height * scale) / 2);
    g2.translate(tx, ty);
    g2.scale(scale, scale);
    g2.setColor(hovered ? SoftAcademiaTheme.BUTTON_HOVER : SoftAcademiaTheme.BUTTON_BACKGROUND);
    g2.fillRoundRect(0, 0, width, height, 18, 18);
    g2.setComposite(AlphaComposite.SrcOver);
    g2.setColor(getForeground());
    FontMetrics fm = g2.getFontMetrics();
    int textWidth = fm.stringWidth(getText());
    int textHeight = fm.getAscent();
    g2.drawString(getText(), (width - textWidth) / 2, (height + textHeight) / 2 - 3);
    g2.dispose();
  }
}
