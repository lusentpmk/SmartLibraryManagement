package com.smartlibrary.controller;
import com.smartlibrary.view.LoginView;
import com.smartlibrary.view.DashboardView;
public class LoginController {
  private LoginView view;
  public LoginController() {
    view = new LoginView();
    view.setLoginAction(e -> authenticate());
  }
  public void showLogin() {
    view.setVisible(true);
  }
  private void authenticate() {
    String user = view.getUsername();
    String pass = view.getPassword();
    if ("admin".equals(user) && "admin".equals(pass)) {
      view.hideWindow();
      try {
        DashboardView dashboard = new DashboardView();
        dashboard.setVisible(true);
      } catch (Exception ex) {
        ex.printStackTrace();
        view.showError("Failed to load dashboard: " + ex.getMessage());
        view.setVisible(true); // show login again
      }
    } else {
      view.showError("Invalid credentials");
    }
  }
}
