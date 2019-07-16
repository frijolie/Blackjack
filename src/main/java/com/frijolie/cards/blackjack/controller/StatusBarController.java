package com.frijolie.cards.blackjack.controller;

import com.frijolie.cards.blackjack.model.game.BlackjackGame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class StatusBarController {

  @FXML private Label statusLabel;

  private StackPane stackPane;

  /** No-arg constructor. Assembles an FXML file into a usable JavaFX component. */
  public StatusBarController() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StatusBar.fxml"));
    loader.setController(this);
    try {
      stackPane = loader.load();
    } catch (Exception e) {
      System.err.println("StatusBar.fxml could not be loaded");
    }
  }

  public void injectModel(final BlackjackGame game) {}

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
  public final StackPane getStatusBar() {
    return stackPane;
  }
}
