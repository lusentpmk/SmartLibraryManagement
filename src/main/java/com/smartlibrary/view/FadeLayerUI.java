package com.smartlibrary.view;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.LayerUI;

public class FadeLayerUI extends LayerUI<JPanel> {
  private float alpha = 1f;
  private Timer timer;

  public FadeLayerUI() {
    timer = new Timer(15, this::animate);
    timer.setRepeats(true);
  }

  public void fadeIn() {
    alpha = 0f;
    if (timer.isRunning()) {
      timer.stop();
    }
    timer.start();
  }

  private void animate(ActionEvent e) {
    alpha += 0.05f;
    if (alpha >= 1f) {
      alpha = 1f;
      timer.stop();
    }
    firePropertyChange("alpha", 0f, alpha);
  }

  @Override
  public void paint(Graphics g, JComponent c) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    super.paint(g2, c);
    g2.dispose();
  }

  @Override
  public void applyPropertyChange(java.beans.PropertyChangeEvent pce, JLayer<? extends JPanel> l) {
    if ("alpha".equals(pce.getPropertyName())) {
      l.repaint();
    }
  }
}
