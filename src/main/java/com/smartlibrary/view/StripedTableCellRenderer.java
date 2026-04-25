package com.smartlibrary.view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class StripedTableCellRenderer extends DefaultTableCellRenderer {
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
      int row, int column) {
    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    if (isSelected) {
      c.setBackground(new Color(0xD6BFA8));
    } else {
      int hovered = -1;
      if (table instanceof HoverTable) {
        hovered = ((HoverTable) table).getHoveredRow();
      }
      if (row == hovered) {
        c.setBackground(new Color(0xECE1D2));
      } else {
        c.setBackground(row % 2 == 0 ? SoftAcademiaTheme.TABLE_ALT : SoftAcademiaTheme.PANEL_BACKGROUND);
      }
    }
    c.setForeground(SoftAcademiaTheme.TEXT);
    return c;
  }
}
