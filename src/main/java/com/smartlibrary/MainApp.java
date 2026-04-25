package com.smartlibrary;

import com.formdev.flatlaf.FlatLightLaf;
import com.smartlibrary.controller.LoginController;

public class MainApp {
  public static void main(String[] args) {
    try {
      javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (Exception ex) {
      System.err.println("Unable to initialize theme: " + ex.getMessage());
    }
    LoginController controller = new LoginController();
    controller.showLogin();
  }
}
