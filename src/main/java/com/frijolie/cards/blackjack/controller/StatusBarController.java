package com.frijolie.cards.blackjack.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class StatusBarController {

  @FXML
  private Label statusLabel;

  private StackPane stackPane;
  private SuperController superController;

  /**
   * No-arg constructor. Assembles an FXML file into a usable JavaFX component.
   */
  StatusBarController(SuperController superController) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StatusBar.fxml"));
    this.superController = superController;
    loader.setController(this);
    try {
      stackPane = loader.load();
    } catch (Exception e) {
      System.err.println("StatusBar.fxml could not be loaded");
      e.printStackTrace();
    }
  }

  /**
   * Sets the text in the statusbar to the provided message.
   *
   * @param message to be displayed in the statusbar
   */
  final void setStatusText(String message) {
    statusLabel.setText(message);
  }

  /**
   * Returns a StackPane to be displayed in the GUI.
   *
   * @return a StackPane component
   */
  final StackPane getStatusBar() {
    return stackPane;
  }
}
