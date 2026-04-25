package com.smartlibrary.view;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class HoverTable extends JTable {
  private int hoveredRow = -1;

  public HoverTable(TableModel model) {
    super(model);
    setRowHeight(34);
    setIntercellSpacing(new Dimension(0, 0));
    addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        int row = rowAtPoint(e.getPoint());
        if (row != hoveredRow) {
          hoveredRow = row;
          repaint();
        }
      }
    });
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        hoveredRow = -1;
        repaint();
      }
    });
  }

  public int getHoveredRow() {
    return hoveredRow;
  }
}
