package com.smartlibrary.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.Timer;

public class PulsingLabel extends JLabel {
  private float pulse = 0.75f;
  private boolean increasing = true;

  public PulsingLabel(String text) {
    super(text);
    setPreferredSize(new Dimension(120, 28));
    setFont(SoftAcademiaTheme.BODY_FONT.deriveFont(13f));
    setForeground(SoftAcademiaTheme.ERROR);
    setOpaque(false);
    Timer timer = new Timer(120, this::tick);
    timer.start();
  }

  private void tick(ActionEvent e) {
    if (increasing) {
      pulse += 0.04f;
      if (pulse >= 1f) {
        pulse = 1f;
        increasing = false;
      }
    } else {
      pulse -= 0.04f;
      if (pulse <= 0.7f) {
        pulse = 0.7f;
        increasing = true;
      }
    }
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, pulse));
    int width = getWidth();
    int height = getHeight();
    g2.setColor(new Color(140, 47, 63));
    g2.fillRoundRect(0, 0, width, height, 16, 16);
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    g2.setColor(SoftAcademiaTheme.BUTTON_TEXT);
    FontMetrics fm = g2.getFontMetrics();
    int textWidth = fm.stringWidth(getText());
    int textHeight = fm.getAscent();
    g2.drawString(getText(), (width - textWidth) / 2, (height + textHeight) / 2 - 3);
    g2.dispose();
  }
}
