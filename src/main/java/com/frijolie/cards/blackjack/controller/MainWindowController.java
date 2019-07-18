package com.frijolie.cards.blackjack.controller;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * This controller is used to control the main window of the application.
 */
class MainWindowController {

  private BorderPane borderPane;
  private SuperController superController;

  /**
   * no-arg constructor. Loads the FXML into a JavaFX container.
   */
  MainWindowController(SuperController superController) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
    loader.setController(this);
    this.superController = superController;
    try {
      borderPane = loader.load();
    } catch (Exception e) {
      System.err.println("MainWindow.fxml could not be loaded");
      e.printStackTrace();
    }
  }

  /**
   * Returns a BorderPane to be used in the GUI.
   *
   * @return an assembled BorderPane
   */
  final BorderPane getMainWindow() {
    return borderPane;
  }

  final void displayHint() {
    WebView browser = new WebView();
    WebEngine engine = browser.getEngine();
    URL url = this.getClass().getResource("/html/BlackjackStrategyTable.html");
    engine.load(url.toString());
    borderPane.setRight(browser);
  }
}
