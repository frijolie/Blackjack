package com.frijolie.cards.blackjack.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/** This controller is used to control the main window of the application. */
public class MainWindowController {

  private BorderPane borderPane;

  /** no-arg constructor. Loads the FXML into a JavaFX container. */
  public MainWindowController() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
    loader.setController(this);
    try {
      borderPane = loader.load();
    } catch (Exception e) {
      System.err.println("MainWindow.fxml could not be loaded");
    }
  }

  /**
   * Returns a BorderPane to be used in the GUI.
   *
   * @return an assembled BorderPane
   */
  public final BorderPane getMainWindow() {
    return borderPane;
  }
}
